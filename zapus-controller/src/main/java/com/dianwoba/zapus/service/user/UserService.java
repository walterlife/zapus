/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package com.dianwoba.zapus.service.user;

import com.dianwoba.zapus.common.constant.ControllerConstants;
import com.dianwoba.zapus.infra.config.Config;
import com.dianwoba.zapus.mapper.ZapusUserMapper;
import com.dianwoba.zapus.model.PerfTest;
import com.dianwoba.zapus.model.Role;
import com.dianwoba.zapus.model.User;
import com.dianwoba.zapus.model.ZapusUser;
import com.dianwoba.zapus.model.ZapusUserExample;
import com.dianwoba.zapus.model.ZapusUserExample.Criteria;
import com.dianwoba.zapus.security.SecuredUser;
import com.dianwoba.zapus.service.AbstractUserService;
import com.dianwoba.zapus.service.perftest.PerfTestService;
import com.dianwoba.zapus.service.script.FileEntryService;
import com.dianwoba.zapus.user.repository.UserSpecification;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class UserService.
 *
 * @author Yubin Mao
 * @author AlexQin
 */
@Service
public class UserService extends AbstractUserService {

	@Autowired
	private ZapusUserMapper userMapper;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private PerfTestService perfTestService;

	@Autowired
	private FileEntryService scriptService;

	@Autowired
	private SaltSource saltSource;

	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@Autowired
	private Config config;

	@Autowired
	private CacheManager cacheManager;

	private Cache userCache;

	@PostConstruct
	public void init() {
		userCache = cacheManager.getCache("users");
	}

	/**
	 * Get user by user id.
	 *
	 * @param userId user id
	 * @return user
	 */
	@Transactional
	@Cacheable("users")
	@Override
	public ZapusUser getOne(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	/**
	 * Encoding given user's password.
	 *
	 * @param user user
	 */
	public void encodePassword(ZapusUser user) {
		if (StringUtils.isNotBlank(user.getPassword())) {
			SecuredUser securedUser = new SecuredUser(user, null);
			String encodePassword = passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(securedUser));
			user.setPassword(encodePassword);
		}
	}


	/**
	 * Save user.
	 *
	 * @param user include id, userID, fullName, role, password.
	 * @return User
	 */
	@Transactional
	@CachePut(value = "users", key = "#user.userId")
	@Override
	public ZapusUser save(ZapusUser user) {
		encodePassword(user);
		return saveWithoutPasswordEncoding(user);
	}

	/**
	 * Save user.
	 *
	 * @param user include id, userID, fullName, role, password.
	 * @return User
	 */
	@Transactional
	@CachePut(value = "users", key = "#user.userId")
	@Override
	public ZapusUser saveWithoutPasswordEncoding(ZapusUser user) {
/*		final List<User> followers = getFollowers(user.getFollowersStr());
		user.setFollowers(followers);*/
		if (user.getPassword() != null && StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
		}
		ZapusUserExample example = new ZapusUserExample();
		ZapusUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(user.getUserId());
		List<ZapusUser> users = userMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(users)) {
			// First expire existing followers.
/*			final List<User> existingFollowers = existing.getFollowers();
			if (existingFollowers != null) {
				for (User eachFollower : existingFollowers) {
					userCache.evict(eachFollower.getUserId());
				}
			}
			user = existing.merge(user);*/
		}
		userMapper.insertSelective(user);
		// Then expires new followers so that new followers info can be loaded.
/*		for (User eachFollower : followers) {
			userCache.evict(eachFollower.getUserId());
		}
		prepareUserEnv(createdUser);*/
		return user;
	}

	private void prepareUserEnv(ZapusUser user) {
		scriptService.prepare(user);
	}


	private List<ZapusUser> getFollowers(String followersStr) {
		List<ZapusUser> newShareUsers = new ArrayList<ZapusUser>();
		List<String> userIds = Lists.newArrayList(StringUtils.split(StringUtils.trimToEmpty(followersStr),
			','));
		ZapusUserExample example = new ZapusUserExample();
		ZapusUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdIn(userIds);
		List<ZapusUser> shareUsers = userMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(shareUsers)) {
			newShareUsers.addAll(shareUsers);
		}
		return newShareUsers;
	}

	@SuppressWarnings("SpringElInspection")
	@Transactional
	@CacheEvict(value = "users", key = "#userId")
	public void delete(Long userId) {
		ZapusUser user = getOne(userId);
		List<PerfTest> deletePerfTests = perfTestService.deleteAll(user);
		userMapper.deleteByPrimaryKey(userId);
		for (PerfTest perfTest : deletePerfTests) {
			FileUtils.deleteQuietly(config.getHome().getPerfTestDirectory(perfTest));
		}
		FileUtils.deleteQuietly(config.getHome().getScriptDirectory(user));
		FileUtils.deleteQuietly(config.getHome().getUserRepoDirectory(user));
	}

	/**
	 * Get the user list by the given role.
	 *
	 * @param role role
	 * @param sort sort
	 * @return found user list
	 */
	public List<User> getAll(Role role, Sort sort) {
		return (role == null) ? userMapper.findAll(sort) : userMapper.findAllByRole(role, sort);
	}

	/**
	 * get the user list by the given role.
	 *
	 * @param role     role
	 * @param pageable sort
	 * @return found user list
	 */
	public Page<User> getPagedAll(Role role, Pageable pageable) {
		return (role == null) ? userMapper.findAll(pageable) : userMapper.findAllByRole(role, pageable);
	}

	/**
	 * Get the users by the given role.
	 *
	 * @param role role
	 * @return found user list
	 */
	public List<User> getAll(Role role) {
		return getAll(role, new Sort(Direction.ASC, "userName"));
	}

	/**
	 * Get the users by nameLike spec.
	 *
	 * @param name name of user
	 * @return found user list
	 */
	public List<User> getAll(String name) {
		return userMapper.findAll(UserSpecification.nameLike(name));
	}

	/**
	 * Get user page by the given keyword.
	 *
	 * @param keyword  keyword to be like search.
	 * @param pageable page
	 * @return user page
	 */
	public Page<User> getPagedAll(String keyword, Pageable pageable) {
		return userMapper.findAll(UserSpecification.nameLike(keyword), pageable);
	}

	/**
	 * Create an user avoiding ModelAspect behavior.
	 *
	 * @param user userID, fullName, role, password.
	 * @return User
	 */
	@Transactional
	@CachePut(value = "users", key = "#user.userId")
	@Override
	public User createUser(User user) {
		encodePassword(user);
		Date createdDate = new Date();
		user.setCreatedDate(createdDate);
		user.setLastModifiedDate(createdDate);
		User createdUser = getOne(ControllerConstants.NGRINDER_INITIAL_ADMIN_USERID);
		user.setCreatedUser(createdUser);
		user.setLastModifiedUser(createdUser);
		return saveWithoutPasswordEncoding(user);
	}


}

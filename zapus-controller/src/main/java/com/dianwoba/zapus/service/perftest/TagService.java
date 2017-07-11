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
package com.dianwoba.zapus.service.perftest;

import static com.dianwoba.zapus.perftest.repository.TagSpecification.hasPerfTest;

import com.dianwoba.zapus.mapper.ZapusPerfTestTagMapperExt;
import com.dianwoba.zapus.mapper.ZapusTagMapperExt;
import com.dianwoba.zapus.model.PerfTest;
import com.dianwoba.zapus.model.Tag;
import com.dianwoba.zapus.model.ZapusTag;
import com.dianwoba.zapus.model.ZapusTagExample;
import com.dianwoba.zapus.model.ZapusUser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.Resource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tag Service. Tag support which is used to categorize {@link PerfTest}
 * 
 * @author JunHo Yoon
 * @since 3.0
 * 
 */
@Service
public class TagService {

	@Resource
    private ZapusTagMapperExt zapusTagMapperExt;
	
	@Resource
    private ZapusPerfTestTagMapperExt zapusPerfTestTagMapperExt;

    /**
     * 增加标签
     * @param user
     * @param tags
     * @return
     */
	@Transactional
	public SortedSet<ZapusTag> addTags(ZapusUser user, String[] tags) {
		if (ArrayUtils.isEmpty(tags)) {
			return new TreeSet<ZapusTag>();
		}

        ZapusTagExample example = new ZapusTagExample();
		example.createCriteria().andLastModifiedUserEqualTo(user.getId());
        example.or(example.createCriteria().andCreatedUserEqualTo(user.getId()));
        List<ZapusTag> foundTags = zapusTagMapperExt.selectByExample(example);
		SortedSet<ZapusTag> allTags = new TreeSet<ZapusTag>(foundTags);
		for (String each : tags) {
			ZapusTag newTag = new ZapusTag();
			newTag.setTagvalue(StringUtils.trimToEmpty(each.trim()));
			if (allTags.contains(newTag)) {
				continue;
			}
			if (!foundTags.contains(newTag) && !allTags.contains(newTag)) {
				allTags.add(saveTag(user, newTag));
			}
		}
		return allTags;
	}

	/**
	 * Get all tags which belongs to given user and start with given string.
	 * 
	 * @param user 		user
	 * @param startWith	string
	 * @return found tags
	 */
	public List<ZapusTag> getAllTags(ZapusUser user, String startWith) {
		Specifications<Tag> spec = Specifications.where(hasPerfTest());
        ZapusTagExample example = new ZapusTagExample();
        example.createCriteria().andLastModifiedUserEqualTo(user.getId());
        example.or().andCreatedUserEqualTo(user.getId());
		if (StringUtils.isNotBlank(startWith)) {
            example.createCriteria().andTagvalueLike(startWith + '%');
		}
		return zapusTagMapperExt.selectByExample(example);
	}

	/**
	 * Get all tags which belongs to given user and start with given string.
	 * 
	 * @param user	user
	 * @param query	query string
	 * @return found tag string lists
	 */
	public List<String> getAllTagStrings(ZapusUser user, String query) {
		List<String> allString = new ArrayList<String>();
		for (ZapusTag each : getAllTags(user, query)) {
			allString.add(each.getTagvalue());
		}
		Collections.sort(allString);
		return allString;
	}

	/**
	 * Save Tag. Because this method can be called in {@link TagService} internally, so created user
	 * / data should be set directly.
	 * 
	 * @param user 	user
	 * @param tag	tag
	 * @return saved {@link Tag} instance
	 */
	public ZapusTag saveTag(ZapusUser user, ZapusTag tag) {
		Date createdDate = new Date();
		if (tag.getCreatedUser() == null) {
			tag.setCreatedUser(user.getId());
			tag.setCreatedDate(createdDate);
		}
		tag.setLastModifiedUser(user.getId());
		tag.setLastModifiedDate(createdDate);
		zapusTagMapperExt.insertSelective(tag);
		return tag;
	}

	/**
	 * Delete a tag.
	 * 
	 * @param user	user
	 * @param tag	tag
	 */
	@Transactional
	public void deleteTag(ZapusUser user, ZapusTag tag) {
/*		for (PerfTest each : tag.getPerfTests()) {
			each.getTags().remove(tag);
		}
		perfTestRepository.save(tag.getPerfTests());*/
/*		tagRepository.delete(tag);*/
	}

	/**
	 * Delete all tags belonging to given user.
	 * 
	 * @param user	user
	 */
	@Transactional
	public void deleteTags(ZapusUser user) {
/*		Specifications<Tag> spec = Specifications.where(lastModifiedOrCreatedBy(user));
		ZapusTagExample example = new ZapusTagExample();
		example.createCriteria().andCreatedUserEqualTo(user.getId());
		List<ZapusTag> userTags = zapusTagMapperExt.selectByExample(example);
		for (ZapusTag each : userTags) {
			Set<PerfTest> perfTests = each.getPerfTests();
			if (perfTests != null) {
				for (PerfTest eachPerfTest : perfTests) {
					eachPerfTest.getTags().remove(each);
				}
				perfTestRepository.save(each.getPerfTests());
			}
		}
		tagRepository.delete(userTags);
		*/
    }
}
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
package com.dianwoba.zapus.service;

import com.dianwoba.zapus.model.ZapusUser;

/**
 * ZapusUser service interface. This interface is visible to plugins
 *
 * @author JunHo Yoon
 * @since 3.0
 */
interface IUserService {

	/**
	 * Encode password of the given user.
	 *
	 * @param user user
	 */
	void encodePassword(ZapusUser user);

	/**
	 * Get user by user id.
	 *
	 * @param userId user id
	 * @return user
	 * @since 3.3
	 */
	ZapusUser getOne(Long userId);


	/**
	 * Save user without password encoding step.
	 *
	 * @param user include id, userID, fullName, role, password.
	 * @return result
	 * @since 3.3
	 */
	ZapusUser saveWithoutPasswordEncoding(ZapusUser user);


	/**
	 * Save user.
	 *
	 * @param user include id, userID, fullName, role, password.
	 * @return result
	 */
	ZapusUser save(ZapusUser user);


	/**
	 * Create user.
	 *
	 * This method exists to avoid ModelAspect injection.
	 *
	 * @param user include id, userID, fullName, role, password.
	 * @return result
	 */
	ZapusUser createZapusUser(ZapusUser user);

}
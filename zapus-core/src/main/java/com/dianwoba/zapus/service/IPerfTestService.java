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

import com.dianwoba.zapus.model.ZapusPerftest;
import com.dianwoba.zapus.model.Status;
import com.dianwoba.zapus.model.User;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * {@link ZapusPerftest} service interface. This is visible from plugin.
 * 
 * @author JunHo Yoon
 * @since 3.0
 */
public interface IPerfTestService {

	/**
	 * get test detail.
	 * 
	 * @param user	current operation user.
	 * @param id	test id
	 * @return perftest	{@link ZapusPerftest}
	 */
	ZapusPerftest getOne(User user, Long id);

	/**
	 * Get {@link ZapusPerftest} list created within the given time frame.
	 * 
	 * @param start	start time.
	 * @param end	end time.
	 * @return found {@link ZapusPerftest} list
	 */
	List<ZapusPerftest> getAll(Date start, Date end);

	/**
	 * Get {@link ZapusPerftest} list created within the given time frame and region name.
	 * 
	 * @param start		start time.
	 * @param end		end time.
	 * @param region	region
	 * @return found {@link ZapusPerftest} list
	 */
	List<ZapusPerftest> getAll(Date start, Date end, String region);

	/**
	 * Get {@link ZapusPerftest} list of some IDs.
	 * 
	 * @param user	current operation user
	 * @param ids	test IDs, which is in format: "1,3,6,11"
	 * @return perftest list test list of those IDs
	 */
	List<ZapusPerftest> getAll(User user, Long[] ids);

	/**
	 * Get ZapusPerftest count which have given status.
	 * 
	 * @param user		user who created test. null to retrieve all
	 * @param statuses	status set
	 * @return the count
	 */
	long count(User user, List<String> statuses);

	/**
	 * Get {@link ZapusPerftest} list which have give state.
	 * 
	 * @param user		user who created {@link ZapusPerftest}. if null, retrieve all test
	 * @param statuses	set of {@link Status}
	 * @return found {@link ZapusPerftest} list.
	 */
	List<ZapusPerftest> getAll(User user, List<String> statuses);

	/**
	 * Save {@link ZapusPerftest}. This function includes logic the updating script revision when it's
	 * READY status.
	 * 
	 * @param user		user
	 * @param perfTest	{@link ZapusPerftest} instance to be saved.
	 * @return Saved {@link ZapusPerftest}
	 */
	ZapusPerftest save(User user, ZapusPerftest perfTest);

	/**
	 * Get ZapusPerftest by testId.
	 * 
	 * @param testId	ZapusPerftest id
	 * @return found {@link ZapusPerftest}, null otherwise
	 */
	ZapusPerftest getOne(Long testId);

	/**
	 * Get ZapusPerftest with tag infos by testId.
	 * 
	 * @param testId	ZapusPerftest id
	 * @return found {@link ZapusPerftest}, null otherwise
	 */
	ZapusPerftest getOneWithTag(Long testId);

	/**
	 * Get currently testing ZapusPerftest.
	 * 
	 * @return found {@link ZapusPerftest} list
	 */
	List<ZapusPerftest> getAllTesting();

	/**
	 * Get ZapusPerftest Directory in which the distributed file is stored.
	 * 
	 * @param perfTest	pefTest from which distribution directory calculated
	 * @return path on in files are saved.
	 */
	File getDistributionPath(ZapusPerftest perfTest);

	/**
	 * Get perf test base directory.
	 * 
	 * @param perfTest	perfTest
	 * @return directory prefTest base path
	 */
	File getPerfTestDirectory(ZapusPerftest perfTest);

	/**
	 * Get all perf test list.
	 * 
	 * Note : This is only for test
	 * 
	 * @return all {@link ZapusPerftest} list
	 * 
	 */
	List<ZapusPerftest> getAllPerfTest();

	/**
	 * Mark Stop on {@link ZapusPerftest}.
	 * 
	 * @param user	user
	 * @param id	perftest id
	 */
	void stop(User user, Long id);

	/**
	 * Return stop requested test.
	 * 
	 * @return stop requested perf test
	 */
	List<ZapusPerftest> getAllStopRequested();

	/**
	 * Add comment on {@link ZapusPerftest}.
	 * 
	 * @param user			current operated user
	 * @param testId		perftest id
	 * @param testComment	comment
	 * @param tagString		tagString
	 */
	void addCommentOn(User user, Long testId, String testComment, String tagString);

	/**
	 * Save performance test with given status.
	 * 
	 * This method is only used for changing {@link Status}
	 * 
	 * @param perfTest	{@link ZapusPerftest} instance which will be saved.
	 * @param status	Status to be assigned
	 * @param message	progress message
	 * @return saved {@link ZapusPerftest}
	 */
	ZapusPerftest markStatusAndProgress(ZapusPerftest perfTest, Status status, String message);

	/**
	 * Get performance test statistic path.
	 * 
	 * @param perfTest	perftest
	 * @return statistic path
	 */
	@SuppressWarnings("UnusedDeclaration")
	File getStatisticPath(ZapusPerftest perfTest);

}
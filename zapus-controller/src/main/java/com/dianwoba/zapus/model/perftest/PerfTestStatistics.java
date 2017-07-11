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
package com.dianwoba.zapus.model.perftest;

import com.dianwoba.zapus.model.ZapusPerftest;
import com.dianwoba.zapus.model.ZapusUser;

/**
 * Current running perf test info of {@link ZapusPerftest} per user.
 * 
 * @author JunHo Yoon
 * @since 3.0
 */
@SuppressWarnings("UnusedDeclaration")
public class PerfTestStatistics {
	private ZapusUser user;
	private int agentCount;
	private int testCount;

	/**
	 * Constructor.
	 * 
	 * @param user
	 *            user
	 */
	public PerfTestStatistics(ZapusUser user) {
		this.user = user;
	}

	/**
	 * Add a {@link ZapusPerftest} instance.
	 * 
	 * @param perfTest
	 *            perftest
	 */
	public void addPerfTest(ZapusPerftest perfTest) {
		testCount++;
		agentCount += perfTest.getAgentCount();
	}

	public ZapusUser getUser() {
		return user;
	}

	public void setUser(ZapusUser user) {
		this.user = user;
	}

	public int getAgentCount() {
		return agentCount;
	}

	public void setAgentCount(int agentCount) {
		this.agentCount = agentCount;
	}

	public int getTestCount() {
		return testCount;
	}

	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}
}

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

import com.dianwoba.zapus.model.User;
import com.dianwoba.zapus.monitor.controller.model.SystemDataModel;
import net.grinder.engine.controller.AgentControllerIdentityImplementation;
import org.apache.commons.lang.mutable.MutableInt;
import com.dianwoba.zapus.model.ZapusAgent;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Agent manager service interface which is used to retrieve the agent info
 * attaching the current controller.
 *
 * @author JunHo Yoon
 * @since 3.1.2
 */
public interface IAgentManagerService {

	/**
	 * Get the available agent count map across all users including the free
	 * agents and user's private agents.
	 *
	 * @param user current user
	 * @return user available agent count map
	 */
	Map<String, MutableInt> getAvailableAgentCountMap(User user);

	/**
	 * Get all local agents. The agent list is obtained from db and cached.
	 *
	 * @return agent list
	 */
	List<ZapusAgent> getAllLocal();

	/**
	 * Get all local agent agents. The agent list is obtained by combining the data from
	 *
	 * @return agent list
	 */
	@SuppressWarnings("UnusedDeclaration")
	List<ZapusAgent> getAllLocalWithFullInfo();

	/**
	 * Create the agent key from the given agent info.
	 *
	 * @param ZapusAgent agent information
	 * @return agent key
	 */
	String createKey(ZapusAgent ZapusAgent);

	/**
	 * Create the agent key from the given agent identity.
	 *
	 * @param agentIdentity agent identity
	 * @return agent key
	 */
	String createKey(AgentControllerIdentityImplementation agentIdentity);

	/**
	 * Get the agent identity by IP and host name.
	 *
	 * @param ip   ip
	 * @param name host name
	 * @return {@link AgentControllerIdentityImplementation} instance.
	 */
	AgentControllerIdentityImplementation getAgentIdentityByIpAndName(String ip, String name);

	/**
	 * Get all active agents from DB.
	 *
	 * @return agent list
	 */
	List<ZapusAgent> getAllActive();


	/**
	 * Get local agents. This is only for backward compatibility.
	 *
	 * @return local agents
	 * @deprecated Use IAgentManagerService#getAllLocal
	 */
	@SuppressWarnings("UnusedDeclaration")
	List<ZapusAgent> getLocalAgents();

	/**
	 * Get all visible agents from DB.
	 *
	 * @return agent list
	 */
	List<ZapusAgent> getAllVisible();

	/**
	 * Get the agent for the given id without agent identity info. If it's called from the other controller, only
	 * limited info available in db will be return.
	 *
	 * @param id agent id
	 * @return agent
	 */
	ZapusAgent getOne(Long id);

	/**
	 * Get the agent for the given id. If it's called from the other controller, only
	 * limited info available in db will be return.
	 *
	 * @param id                   agent id
	 * @param includeAgentIdentity include agent identity field.
	 * @return agent
	 */
	ZapusAgent getOne(Long id, boolean includeAgentIdentity);

	/**
	 * Get the agent system data model for the given ip. This method is cluster
	 * aware.
	 *
	 * @param ip   agent ip.
	 * @param name agent name
	 * @return {@link SystemDataModel} instance.
	 */
	SystemDataModel getSystemDataModel(String ip, String name);

	/**
	 * Update agent
	 *
	 * @param id ids.
	 */
	void update(Long id) throws IOException;
	
	/**
	 * Get Ready agent state count return
	 *
	 * @param user current user
	 * @return int readyAgentCnt
	 */
	int getReadyAgentCount(User user, String targetRegion);

}
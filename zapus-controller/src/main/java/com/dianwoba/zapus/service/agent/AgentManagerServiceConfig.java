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
package com.dianwoba.zapus.service.agent;

import com.dianwoba.zapus.infra.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Dynamically create a subclass of {@link AgentManagerService} depending on the cluster activation
 * status.
 *
 * @author JunHo Yoon
 * @since 3.1
 */
@Configuration
@Profile("production")
@EnableScheduling
@EnableTransactionManagement
public class AgentManagerServiceConfig implements ApplicationContextAware {

	@Autowired
	Config config;

	private ApplicationContext applicationContext;

	/**
	 * Create a subclass of {@link AgentManagerService} depending on the cluster activation status.
	 *
	 * @return {@link AgentManagerService}
	 */
	@Bean(name = "agentManagerService")
	public AgentManagerService agentManagerService() {
		return (AgentManagerService) applicationContext.getAutowireCapableBeanFactory()
				.autowire(config.isClustered() ? ClusteredAgentManagerService.class : AgentManagerService.class,
						AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}

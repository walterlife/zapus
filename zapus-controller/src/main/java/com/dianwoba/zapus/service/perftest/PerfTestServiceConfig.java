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

import com.dianwoba.zapus.infra.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("production")
@EnableScheduling
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class PerfTestServiceConfig implements ApplicationContextAware {

	@Autowired
	private Config config;

	private ApplicationContext applicationContext;

	@Bean(name = "perfTestService")
	public PerfTestService perfTestService() {
		return (PerfTestService) applicationContext.getAutowireCapableBeanFactory().createBean(
				config.isClustered() ? ClusteredPerfTestService.class: PerfTestService.class,
			AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
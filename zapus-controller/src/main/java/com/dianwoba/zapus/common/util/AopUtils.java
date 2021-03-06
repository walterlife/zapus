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
package com.dianwoba.zapus.common.util;


import org.springframework.aop.framework.AopContext;

import static com.dianwoba.zapus.common.util.TypeConvertUtils.cast;

/**
 * Aop Related utilities.
 *
 * @since 3.3
 */
public class AopUtils {
	/**
	 * Get current proxy.
	 *
	 * @param current current proxy wrapped component
	 * @param <T>     the type of proxy
	 * @return currently used proxy. current if no proxy is used.
	 */
	public static <T> T proxy(T current) {
		try {
			return cast(AopContext.currentProxy());
		} catch (IllegalStateException e) {
			return current;
		}
	}
}

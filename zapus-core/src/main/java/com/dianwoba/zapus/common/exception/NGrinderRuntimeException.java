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
package com.dianwoba.zapus.common.exception;

/**
 * NGrinderRuntimeException. This is for translating a general exception to
 * {@link RuntimeException} .
 *
 * @author JunHo Yoon
 * @since 3.0
 */
public class NGrinderRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 8662535812004958944L;
	private boolean sanitized = false;

	/**
	 * Constructor.
	 *
	 * @param message message
	 */
	public NGrinderRuntimeException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message message
	 * @param t       root cause
	 */
	public NGrinderRuntimeException(String message, Throwable t) {
		this(message, t, false);
	}

	/**
	 * Constructor.
	 *
	 * @param message   message
	 * @param t         root cause
	 * @param sanitized sanitized
	 */
	public NGrinderRuntimeException(String message, Throwable t, boolean sanitized) {
		super(message, t);
		this.sanitized = sanitized;
	}

	/**
	 * Constructor.
	 *
	 * @param t root cause
	 */
	public NGrinderRuntimeException(Throwable t) {
		super(t.getMessage(), t);
	}

	/**
	 * Constructor.
	 *
	 * @param t         root cause
	 * @param sanitized sanitized
	 */
	public NGrinderRuntimeException(Throwable t, boolean sanitized) {
		super(t.getMessage(), t);
		this.sanitized = sanitized;
	}

	public boolean isSanitized() {
		return sanitized;
	}

	public void setSanitized(boolean sanitized) {
		this.sanitized = sanitized;
	}
}

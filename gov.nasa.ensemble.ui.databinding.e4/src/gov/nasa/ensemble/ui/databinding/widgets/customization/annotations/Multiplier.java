/*******************************************************************************
 * Copyright (c) 2013 United States Government as represented by the 
 * Administrator of the National Aeronautics and Space Administration. 
 * All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package gov.nasa.ensemble.ui.databinding.widgets.customization.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The multiplier for the display value. 
 * For example, if you are displaying cm but storing m, the multiplier would be 100.
 * WARNING: You can use this or the Angle annotation but not both.
 * @author tecohen
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Multiplier {
	/**
	 * The value to multiply the stored value in order to display it
	 * @return
	 */
	double value();
	
	/**
	 * The type of the stored value (ie Double)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Class type();
}


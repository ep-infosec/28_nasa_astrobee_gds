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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Order-specific list of names of child groups / child fields.
 * Implementer must exactly spell fields correctly.
 * Names of fields as follows:
 * getTemperature()  method accessed with name temperature
 * @author tecohen
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Group {
	String name();
	String description() default "";
	boolean twistie() default false;
	boolean expanded() default true;
	boolean advanced() default false;
	String flagName() default ""; // this is if there is a flag checkbox that controls this whole group.
	boolean skipLabel() default false; // to skip having labels for children.
	boolean titleBar() default true; // title bar treatment or not.
	String[] children();
}

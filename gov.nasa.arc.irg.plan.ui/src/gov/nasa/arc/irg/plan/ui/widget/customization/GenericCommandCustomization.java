/******************************************************************************
 * Copyright © 2019, United States Government, as represented by the 
 * Administrator of the National Aeronautics and Space Administration. All 
 * rights reserved.
 * 
 * The Astrobee Control Station platform is licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License. You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0. 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 *****************************************************************************/
package gov.nasa.arc.irg.plan.ui.widget.customization;

import java.util.ArrayList;
import java.util.List;

import gov.nasa.ensemble.ui.databinding.widgets.customization.Customization;
import gov.nasa.ensemble.ui.databinding.widgets.customization.FieldCustomization;
import gov.nasa.ensemble.ui.databinding.widgets.customization.ICustomization;

public class GenericCommandCustomization extends Customization {
public static GenericCommandCustomization s_customization= new GenericCommandCustomization();
	
	@Override
	protected void initialize() {
		
		FieldCustomization panCustom = new FieldCustomization("commandName", "Command Name", null);
		m_fields.add(panCustom);

		FieldCustomization tiltCustom = new FieldCustomization("param", "Param", null);
		m_fields.add(tiltCustom);
		
		List<String> children = new ArrayList<String>();
		children.add("commandName");
		children.add("name");
		children.add("param");
		setOrderedWidgets(children);
	}
	
	public static ICustomization getCustomization() {
		return s_customization;
	}
}

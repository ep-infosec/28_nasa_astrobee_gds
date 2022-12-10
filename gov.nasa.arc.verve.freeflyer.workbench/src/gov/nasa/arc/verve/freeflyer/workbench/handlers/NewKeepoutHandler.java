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
package gov.nasa.arc.verve.freeflyer.workbench.handlers;

import gov.nasa.arc.verve.freeflyer.workbench.parts.keepouteditor.KeepoutFileManager;
import gov.nasa.arc.verve.robot.freeflyer.utils.ContextNames;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MCompositePart;

public class NewKeepoutHandler {
	@Inject
	private IEclipseContext context;
	
	@Execute
	public void execute(KeepoutFileManager kfm) {		
		if(kfm != null) {
			kfm.onNewKeepoutCommand();
		}
	}

	@CanExecute
	public boolean canExecute(MCompositePart part) {
		boolean valid = (boolean) context.get(ContextNames.NEW_KEEPOUT_ENABLED);
		boolean active = part.getLabel().equals("Modeling");
		return valid && active;
	}
}

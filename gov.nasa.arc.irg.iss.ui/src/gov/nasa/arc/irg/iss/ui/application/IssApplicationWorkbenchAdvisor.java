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
package gov.nasa.arc.irg.iss.ui.application;

import gov.nasa.arc.irg.util.ui.PlatformParameterUtil;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class IssApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	protected static final String SAMPLE_PERSPECTIVE_ID = "gov.nasa.arc.irg.iss.ui.perspective.SamplePerspective"; //$NON-NLS-1$

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new IssApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return SAMPLE_PERSPECTIVE_ID;
	}
	
	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		//TODO this makes the application save state, which we don't really want.
		configurer.setSaveAndRestore(PlatformParameterUtil.isDebug());
	}
}

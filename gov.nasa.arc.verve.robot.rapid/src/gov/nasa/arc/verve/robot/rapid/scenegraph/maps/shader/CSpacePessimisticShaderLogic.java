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
package gov.nasa.arc.verve.robot.rapid.scenegraph.maps.shader;

import gov.nasa.arc.verve.robot.rapid.scenegraph.maps.SharedNavMapTextures;

public class CSpacePessimisticShaderLogic extends NavMapSingleLayerShaderLogic {

    public CSpacePessimisticShaderLogic() {
        //super("pessimistic", SharedNavMapTextures.Gradient.PurpleToCyan);
        super("pessimistic", SharedNavMapTextures.Gradient.Purple0BlueToCyan);
        m_layerAlpha = 0.8f;
    }

}

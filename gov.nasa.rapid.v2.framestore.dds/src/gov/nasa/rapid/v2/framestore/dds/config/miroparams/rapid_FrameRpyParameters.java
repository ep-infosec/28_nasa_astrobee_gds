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
package gov.nasa.rapid.v2.framestore.dds.config.miroparams;

/**
 * miro params struct
 * maps to rapid::FrameRpyParameters 
 * angles are in degrees
 */
public class rapid_FrameRpyParameters {
    public String parent = "/";
    public double x = 0;
    public double y = 0;
    public double z = 0;
    public double roll  = 0; 
    public double pitch = 0;
    public double yaw   = 0; 
    
    @Override
    public String toString() {
        return String.format("xyz[%f,%f,%f] rpy[%f,%f,%f] parent=%s", x,y,z, roll, pitch, yaw, parent);
    }
}

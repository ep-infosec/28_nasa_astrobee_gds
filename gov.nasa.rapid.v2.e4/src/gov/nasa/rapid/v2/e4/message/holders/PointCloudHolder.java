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
package gov.nasa.rapid.v2.e4.message.holders;

import rapid.PointCloudConfig;
import rapid.PointCloudSample;

public class PointCloudHolder {
    public final PointCloudConfig config;
    public final PointCloudSample sample;
    
    public PointCloudHolder(PointCloudSample sample, PointCloudConfig config) {
        this.sample = sample;
        this.config = config;
    }
    
    /**
     * sketchy ctor
     */
    public PointCloudHolder(Object sample, Object config) {
        this.sample = (PointCloudSample)sample;
        this.config = (PointCloudConfig)config;
    }
    
    
}

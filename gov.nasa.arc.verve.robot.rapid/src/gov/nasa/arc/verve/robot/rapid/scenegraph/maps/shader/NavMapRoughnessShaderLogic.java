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

import gov.nasa.arc.verve.common.DataBundleHelper;
import gov.nasa.arc.verve.robot.rapid.scenegraph.maps.NavMapDataTextures;
import gov.nasa.arc.verve.robot.rapid.scenegraph.maps.SharedNavMapTextures;
import gov.nasa.arc.verve.robot.rapid.scenegraph.maps.SharedNavMapTextures.Gradient;

import java.net.URL;

import org.apache.log4j.Logger;

import rapid.ext.NAVMAP_CERTAINTY;
import rapid.ext.NAVMAP_ROUGHNESS;
import rapid.ext.NAVMAP_NORMALS;

import com.ardor3d.image.Texture2D;
import com.ardor3d.math.ColorRGBA;
import com.ardor3d.renderer.state.GLSLShaderObjectsState;
import com.ardor3d.renderer.state.RenderState.StateType;
import com.ardor3d.renderer.state.TextureState;
import com.ardor3d.scenegraph.Mesh;

/**
 * 
 * @author mallan
 *
 */
public class NavMapRoughnessShaderLogic implements INavMapShaderLogic  {
    private static final Logger logger = Logger.getLogger(NavMapRoughnessShaderLogic.class);
    protected GLSLShaderObjectsState[] m_shaderStates = new GLSLShaderObjectsState[] { new GLSLShaderObjectsState(),
                                                                                       new GLSLShaderObjectsState() };

    final String ROUGHNESS = NAVMAP_ROUGHNESS.VALUE;
    final String CERTAINTY = NAVMAP_CERTAINTY.VALUE;
    final String NORMALS   = NAVMAP_NORMALS.VALUE;

    final String[] layerNames = new String[] { ROUGHNESS, CERTAINTY, NORMALS };

    protected float m_roughnessMin = 0f;
    protected float m_roughnessMax = 100f;
    protected float m_certaintyThresh = 0.75f;
    protected Gradient m_gradient = SharedNavMapTextures.Gradient.GreenToRedHSVc;

    protected ColorRGBA m_grayClr = new ColorRGBA(0.5f, 0.5f, 0.6f, 1f);
    protected final float ALIVE_MIX = 0.0f;
    protected final float DEAD_MIX  = 0.5f;

    protected float     m_zSign = -1;

    /**
     * 
     */
    public NavMapRoughnessShaderLogic(float zSign) {
        final String name = "NavMapRoughness";
        final String category = "robot.rapid";
        try {
            URL vertUrl = DataBundleHelper.getURL(category, "shaders/"+name+".vert");
            URL fragUrl = DataBundleHelper.getURL(category, "shaders/"+name+".frag");            
            for(GLSLShaderObjectsState shaderState : m_shaderStates) {
                shaderState.setVertexShader(vertUrl.openStream());
                shaderState.setFragmentShader(fragUrl.openStream());
                shaderState._needSendShader = true;
            }
        }
        catch(Throwable t) {
            logger.warn("Error setting up shader: "+name, t);
        }
        m_zSign = zSign;
    }

    public String[] getLayerNames() {
        return layerNames;
    }

    public void setup(Mesh mesh, NavMapDataTextures textures) {
        updateTextureState(mesh, textures);
        for(GLSLShaderObjectsState shaderState : m_shaderStates) {
            shaderState.setUniform("normScale",      127f/100f);
            shaderState.setUniform("zSign",          m_zSign);
        }
        update(mesh, textures);
    }

    public void updateTextureState(Mesh mesh, NavMapDataTextures textures) {
        TextureState ts = (TextureState)mesh.getLocalRenderState(StateType.Texture);
        if(ts == null) {
            ts = new TextureState();
            mesh.setRenderState(ts);
        }
        Texture2D cellTex      = SharedNavMapTextures.getCellTexture();
        Texture2D gradientTex  = SharedNavMapTextures.getGradient(m_gradient);
        Texture2D roughnessTex = textures.getLayerData(ROUGHNESS).texture;
        Texture2D certaintyTex = textures.getLayerData(CERTAINTY).texture;
        Texture2D normalsTex   = textures.getLayerData(NORMALS).texture;
        ts.setTexture(cellTex,      0);
        ts.setTexture(gradientTex,  1);
        ts.setTexture(roughnessTex,  2);
        ts.setTexture(certaintyTex, 3);
        ts.setTexture(normalsTex,   4);
        for(GLSLShaderObjectsState shaderState : m_shaderStates) {
            shaderState.setUniform("cellTex",      0);
            shaderState.setUniform("gradientTex",  1);
            shaderState.setUniform("roughnessTex",  2);
            shaderState.setUniform("certaintyTex", 3);
            shaderState.setUniform("normalsTex",   4);
            shaderState.setUniform("grayClr",      m_grayClr);
        }
        m_shaderStates[0].setUniform("grayMix", ALIVE_MIX);
        m_shaderStates[1].setUniform("grayMix",  DEAD_MIX);
    }

    public void update(Mesh mesh, NavMapDataTextures textures) {
        update();
    }

    public void update() {
        float gMin = m_roughnessMin/127f;
        float gMax = m_roughnessMax/127f;
        float cMin = 0;
        float cMax = 100f/127f;
        for(GLSLShaderObjectsState shaderState : m_shaderStates) {
            shaderState.setUniform("roughnessMin",    gMin);
            shaderState.setUniform("roughnessRange",  gMax-gMin);
            shaderState.setUniform("certaintyMin",   cMin);
            shaderState.setUniform("certaintyRange", cMax-cMin);
            shaderState.setUniform("certaintyThresh", m_certaintyThresh);
        }
    }

    @Override
    public GLSLShaderObjectsState aliveShaderState() {
        return m_shaderStates[0];
    }

    @Override
    public GLSLShaderObjectsState deadShaderState() {
        return m_shaderStates[1];
    }

    public float getCertaintyThreshold() {
        return m_certaintyThresh;
    }

    public void setCertaintyThreshold(float threshold) {
        m_certaintyThresh = threshold;
    }

    public float getGoodnessMin() {
        return m_roughnessMin;
    }

    public void setGoodnessMin(float min) {
        m_roughnessMin = min;
    }

    public float getGoodnessMax() {
        return m_roughnessMax;
    }

    public void setGoodnessMax(float max) {
        m_roughnessMax = max;
    }

    public Gradient getGradient() {
        return m_gradient;
    }

    public void setGradient(Gradient gradient) {
        m_gradient = gradient;
        m_texDirty = true;
    }

    boolean m_texDirty = false;
    @Override
    public boolean needsTextureStateUpdate() {
        boolean retVal = m_texDirty;
        m_texDirty = false;
        return retVal;
    }
}

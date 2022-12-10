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
package gov.nasa.arc.verve.freeflyer.workbench.parts;


import gov.nasa.arc.verve.ardor3d.e4.framework.IVerveCanvasView;

import java.util.Set;

import com.ardor3d.framework.Canvas;
import com.ardor3d.input.ButtonState;
import com.ardor3d.input.Key;
import com.ardor3d.input.KeyboardState;
import com.ardor3d.input.MouseButton;
import com.ardor3d.input.MouseState;
import com.ardor3d.input.logical.InputTrigger;
import com.ardor3d.input.logical.MouseWheelMovedCondition;
import com.ardor3d.input.logical.TriggerAction;
import com.ardor3d.input.logical.TriggerConditions;
import com.ardor3d.input.logical.TwoInputStates;
import com.ardor3d.math.type.ReadOnlyVector3;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class SideCamControl extends FreeFlyerNadirCamControl {

	public SideCamControl(IVerveCanvasView canvasView, ReadOnlyVector3 upAxis) {
		super(canvasView, upAxis);
	}

	@Override
	public void setUpAxis(ReadOnlyVector3 upAxis) {
		m_upAxis.set ( 1, 0, 0);
		m_camLook.set( -1, 0, 0);
		m_camLeft.set(0, -1, 0);
		m_camUp.set  ( 0, 0, 1);
	}
    // Trigger actions
    //===============================================================
    @Override
	protected void keyInput(KeyboardState keyState) {
      // ignore
    }

    @Override
	protected void leftMouseClick(MouseState mouse) {
    	 // ignore
    }

    @Override
	protected void rightMouseClick(MouseState mouse) {
    	 // ignore
    }

    @Override
	protected void middleMouseClick(MouseState mouse) {
    	 // ignore
    }
    

//    // Create Triggers
//    //====================================================================
    @Override
    InputTrigger createDraggedInputTrigger() {
        final FreeFlyerNadirCamControl control = this;
        final Predicate<TwoInputStates> someMouseDown 
        = Predicates.or(TriggerConditions.leftButtonDown(), Predicates.or(TriggerConditions.rightButtonDown(), TriggerConditions.middleButtonDown()));

        final Predicate<TwoInputStates> dragged = Predicates.and(TriggerConditions.mouseMoved(), someMouseDown);

        final TriggerAction dragAction = new TriggerAction() {

            @Override
            public void perform(final Canvas source, final TwoInputStates inputStates, final double tpf) {
                if( source.equals(control.getCanvas()) ) {
                    final MouseState mouse = inputStates.getCurrent().getMouseState();
                    final KeyboardState keybd = inputStates.getCurrent().getKeyboardState();
                    if (mouse.getDx() != 0 || mouse.getDy() != 0) {
                        Set<Key> keySet = keybd.getKeysDown();
                        if(keySet.size() == 0) {
                            if(mouse.getButtonState(MouseButton.RIGHT).equals(ButtonState.DOWN)) {
                              //  control.spin(mouse.getDx(), mouse.getDy());
                            }
                            else {
                               // control.crab(mouse.getDx(), mouse.getDy());
                            }
                        }
                        else if(keybd.isDown(Key.LCONTROL) || keybd.isDown(Key.RCONTROL)) {
                            //control.dolly((int)(mouse.getDy()*0.6));
                        }
                    }
                }
            }
        };
        return new InputTrigger(dragged, dragAction);
    }
    
    @Override
	InputTrigger createWheelInputTrigger() {
        final FreeFlyerNadirCamControl control = this;

        final TriggerAction wheelAction = new TriggerAction() {
            @Override
            public void perform(Canvas source, TwoInputStates inputStates, double tpf) {
                if( source.equals(control.getCanvas()) ) {
                    final MouseState mouse = inputStates.getCurrent().getMouseState();
                    if(mouse.getDwheel() != 0) {
                        //control.dolly(mouse.getDwheel());
                    }
                }
            }
        };
        return new InputTrigger(new MouseWheelMovedCondition(), wheelAction);
    }
}

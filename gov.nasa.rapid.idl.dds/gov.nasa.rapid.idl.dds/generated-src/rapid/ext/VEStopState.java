

/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from .idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

package rapid.ext;

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;
import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;

public class VEStopState  extends rapid.Message implements Copyable, Serializable{

    public boolean active= false;
    public boolean advisoryOnly= false;

    public VEStopState() {

        super();

    }
    public VEStopState (VEStopState other) {

        this();
        copy_from(other);
    }

    public static Object create() {

        VEStopState self;
        self = new  VEStopState();
        self.clear();
        return self;

    }

    public void clear() {

        super.clear();
        active= false;
        advisoryOnly= false;
    }

    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }        

        if (!super.equals(o)) {
            return false;
        }

        if(getClass() != o.getClass()) {
            return false;
        }

        VEStopState otherObj = (VEStopState)o;

        if(active != otherObj.active) {
            return false;
        }
        if(advisoryOnly != otherObj.advisoryOnly) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result = super.hashCode();
        __result += (active == true)?1:0;
        __result += (advisoryOnly == true)?1:0;
        return __result;
    }

    /**
    * This is the implementation of the <code>Copyable</code> interface.
    * This method will perform a deep copy of <code>src</code>
    * This method could be placed into <code>VEStopStateTypeSupport</code>
    * rather than here by using the <code>-noCopyable</code> option
    * to rtiddsgen.
    * 
    * @param src The Object which contains the data to be copied.
    * @return Returns <code>this</code>.
    * @exception NullPointerException If <code>src</code> is null.
    * @exception ClassCastException If <code>src</code> is not the 
    * same type as <code>this</code>.
    * @see com.rti.dds.infrastructure.Copyable#copy_from(java.lang.Object)
    */
    public Object copy_from(Object src) {

        VEStopState typedSrc = (VEStopState) src;
        VEStopState typedDst = this;
        super.copy_from(typedSrc);
        typedDst.active = typedSrc.active;
        typedDst.advisoryOnly = typedSrc.advisoryOnly;

        return this;
    }

    public String toString(){
        return toString("", 0);
    }

    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();        

        if (desc != null) {
            CdrHelper.printIndent(strBuffer, indent);
            strBuffer.append(desc).append(":\n");
        }

        strBuffer.append(super.toString("",indent));

        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("active: ").append(active).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("advisoryOnly: ").append(advisoryOnly).append("\n");  

        return strBuffer.toString();
    }

}

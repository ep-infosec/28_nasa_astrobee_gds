
/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from .idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

package rapid;

import com.rti.dds.typecode.*;

public class  FileAnnounceTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int __i=0;
        ValueMember sm[]=new ValueMember[4];

        sm[__i]=new  ValueMember("fileUuid", false, (short)-1, true,PUBLIC_MEMBER.VALUE,(TypeCode) rapid.String64TypeCode.VALUE,1 , false);__i++;
        sm[__i]=new  ValueMember("fileLocator", false, (short)-1,  false,PUBLIC_MEMBER.VALUE,(TypeCode) rapid.String256TypeCode.VALUE,2 , false);__i++;
        sm[__i]=new  ValueMember("fileSize", false, (short)-1,  false,PUBLIC_MEMBER.VALUE,(TypeCode) TypeCode.TC_LONGLONG,3 , false);__i++;
        sm[__i]=new  ValueMember("metaData", false, (short)-1,  false,PUBLIC_MEMBER.VALUE,(TypeCode) rapid.KeyTypeValueSequence16TypeCode.VALUE,4 , false);__i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_value_tc("rapid::FileAnnounce",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY, VM_NONE.VALUE,rapid.MessageTypeCode.VALUE, sm);        
        return tc;
    }
}


package com.nur1popcorn.basm.classfile.attributes.annotation.element;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ElementValueConstant extends ElementValue {
    private final int constValueIndex;

    public ElementValueConstant(int tag, DataInputStream in) throws IOException {
        super(tag);
        constValueIndex = in.readUnsignedShort();
    }

    @Override
    public void write(DataOutputStream os) throws IOException {
        super.write(os);
        os.writeShort(constValueIndex);
    }
}

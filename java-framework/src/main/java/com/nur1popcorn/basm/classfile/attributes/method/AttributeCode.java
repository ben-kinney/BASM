/*
 * Copyright (C) Keanu Poeschko - All Rights Reserved
 * Unauthorized copying of this file is strictly prohibited
 *
 * Created by Keanu Poeschko <nur1popcorn@gmail.com>, August 2017
 * This file is part of {BASM}.
 *
 * Do not copy or distribute files of {BASM} without permission of {Keanu Poeschko}
 *
 * Permission to use, copy, modify, and distribute my software for
 * educational, and research purposes, without a signed licensing agreement
 * and for free, is hereby granted, provided that the above copyright notice
 * and this paragraph appear in all copies, modifications, and distributions.
 *
 * {BASM} is based on this document: https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html
 *
 */

package com.nur1popcorn.basm.classfile.attributes.method;

import com.nur1popcorn.basm.classfile.ConstantPool;
import com.nur1popcorn.basm.classfile.attributes.AttributeInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AttributeCode extends AttributeInfo {
    private int maxStack /* u2 */,
                maxLocals /* u2 */;

    private byte code[] /* length: u4
                           entries: u1 */;

    private ExceptionTableEntry exceptionTable[] /* length: u2 */;
    private AttributeInfo attributes[] /* length: u2 */;

    public AttributeCode(int nameIndex, DataInputStream in, ConstantPool constantPool) throws IOException {
        super(nameIndex, in);
        maxStack = in.readUnsignedShort();
        maxLocals = in.readUnsignedShort();

        code = new byte[in.readInt()];
        in.readFully(code);

        exceptionTable = new ExceptionTableEntry[in.readUnsignedShort()];
        for(int i = 0; i < exceptionTable.length; i++)
            exceptionTable[i] = new ExceptionTableEntry(in);

        attributes = AttributeInfo.read(in, constantPool);
    }


    public AttributeCode(int nameIndex, int maxStack, int maxLocals, byte code[], ExceptionTableEntry exceptionTable[], AttributeInfo attributes[]) {
        super(nameIndex, 0); // TODO: new constructor ?
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
        computeLength();
    }

    public void computeLength() {
        // TODO: make part of AttributeInfo.
        int attributeLength = 12 + code.length;
        for(ExceptionTableEntry entry : exceptionTable)
            attributeLength += 8;
        for(AttributeInfo attributeInfo : attributes)
            attributeLength += attributeInfo.getAttributeLength();
        this.attributeLength = attributeLength;
    }

    @Override
    public void write(DataOutputStream os, ConstantPool constantPool) throws IOException {
        super.write(os, constantPool);
        os.writeShort(maxStack);
        os.writeShort(maxLocals);

        os.writeInt(code.length);
        for(byte b : code)
            os.writeByte(b);

        os.writeShort(exceptionTable.length);
        for(ExceptionTableEntry entry : exceptionTable)
            entry.write(os);

        os.writeShort(attributes.length);
        for(AttributeInfo info : attributes)
            info.write(os, constantPool);
    }

    @Override
    public String toString() {
        return "Code" /* TODO: add stuff */;
    }

    public byte[] getByteCode() {
        return code;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public static class ExceptionTableEntry {
        private int startPc /* u2 */,
                    endPc /* u2 */,
                    handlerPc /* u2 */,
                    catchType /* u2 */;

        public ExceptionTableEntry(DataInputStream in) throws IOException {
            startPc = in.readUnsignedShort();
            endPc = in.readUnsignedShort();
            handlerPc = in.readUnsignedShort();
            catchType = in.readUnsignedShort();
        }

        public void write(DataOutputStream os) throws IOException {
            os.writeShort(startPc);
            os.writeShort(endPc);
            os.writeShort(handlerPc);
            os.writeShort(catchType);
        }
    }
}

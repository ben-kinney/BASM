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

package com.nur1popcorn.basm.classfile.attributes.annotation;

import com.nur1popcorn.basm.classfile.ConstantPool;
import com.nur1popcorn.basm.classfile.attributes.AttributeInfo;
import com.nur1popcorn.basm.classfile.attributes.annotation.type.TypeAnnotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AttributeRuntimeInvisibleTypeAnnotations extends AttributeInfo {
    private final TypeAnnotation[] annotations;

    public AttributeRuntimeInvisibleTypeAnnotations(int nameIndex, DataInputStream in) throws IOException {
        super(nameIndex, in);
        annotations = new TypeAnnotation[in.readUnsignedShort()];
        for(int i = 0; i < annotations.length; i++)
            annotations[i] = new TypeAnnotation(in);
    }

    @Override
    public void write(DataOutputStream os, ConstantPool constantPool) throws IOException {
        super.write(os, constantPool);
        os.writeShort(annotations.length);
        for(TypeAnnotation typeAnnotation : annotations)
            typeAnnotation.write(os);
    }
}

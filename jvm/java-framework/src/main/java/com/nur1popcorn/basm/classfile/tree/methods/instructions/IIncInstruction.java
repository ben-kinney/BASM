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

package com.nur1popcorn.basm.classfile.tree.methods.instructions;

import com.nur1popcorn.basm.classfile.tree.methods.Instruction;
import com.nur1popcorn.basm.classfile.tree.methods.InstructionType;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.nur1popcorn.basm.classfile.Opcode.IINC;
import static com.nur1popcorn.basm.classfile.tree.methods.InstructionType.IINC_INS;

public final class IIncInstruction extends Instruction {
    /*
     *
     */
    private byte index, constant;

    /**
     * @param index
     * @param constant
     */
    public IIncInstruction(byte index, byte constant) {
        super(IINC);
        this.index = index;
        this.constant = constant;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(IInstructionVisitor visitor) {
        visitor.visitIIncInstruction(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        super.write(os);
        os.writeByte(index);
        os.writeByte(constant);
    }

    public byte getIndex() {
        return index;
    }

    public void setIndex(byte index) {
        this.index = index;
    }

    public byte getConstant() {
        return constant;
    }

    public void setConstant(byte constant) {
        this.constant = constant;
    }

    @Override
    public InstructionType getType() {
        return IINC_INS;
    }
}

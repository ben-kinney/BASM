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

#include "cutest.h"

#include "cli.h"

CU_TEST(bob) {
    cli_options_delete(cli_options_parse("-h,--help,-b=16464,--bind=123"));
    cli_options_delete(cli_options_parse(""));
}

static struct CUTestInfo example;

CU_TEST_SUITE_START(awesome)

CU_TEST_SUITE_ADD(
    &example
)

CU_TEST_SUITE_END





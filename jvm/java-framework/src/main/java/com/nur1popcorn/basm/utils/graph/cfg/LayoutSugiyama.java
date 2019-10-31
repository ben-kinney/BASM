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

package com.nur1popcorn.basm.utils.graph.cfg;

import com.nur1popcorn.basm.utils.graph.DirectedGraph;
import com.nur1popcorn.basm.utils.graph.iterator.BreadthFirstSearch;
import com.nur1popcorn.basm.utils.graph.iterator.GraphIterator;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

public final class LayoutSugiyama<V, E> implements LayoutStrategy<V, E> {
    private static final Rectangle2D DUMMY_NODE = new Rectangle(0, 0, 0, 0);

    private final LayerAssignment<V, E> layering;
    private final List<CrossingReduction<V, E>> reduction = new LinkedList<>();
    //private final CoordinateAssignment assignment;

    public LayoutSugiyama(int hg, int vg, GraphIterator<Rectangle2D> layerIterator) {
        this.layering = new LayerSearchAssignment<>(BreadthFirstSearch::new);
        //reduction.add(new )
    }

    @Override
    public void reposition(DirectedGraph<V, E> graph, V start) {
        final List<List<V>> layered = layering.assign(graph, start);
    }
}

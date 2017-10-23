package com.github.hycos.cnetwork.api.labelmgr;


import com.github.hycos.cnetwork.api.NodeInterface;
import com.github.hycos.cnetwork.api.NodeKindInterface;
import com.github.hycos.cnetwork.api.labelmgr.exception.InconsistencyException;

import java.util.Collection;
import java.util.List;

/**
 * Interface for extension to query the constraint network
 */
public interface ConstraintNetworkInterface<T extends NodeInterface> {
    boolean containsVertex(T n);
    List<T> getParametersFor(T n);
    Collection<T> vertexSet();

    T addOperand(NodeKindInterface kind, String lbl) throws InconsistencyException;

    void addConnection(T frst, T snd, int prio) throws InconsistencyException;

}

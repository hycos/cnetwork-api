package com.github.hycos.cnetwork.api.cchecktinf;

import com.github.hycos.cnetwork.api.NodeInterface;
import com.github.hycos.cnetwork.api.labelmgr.ConstraintNetworkInterface;

public interface ConsistencyCheckerInterface<T extends NodeInterface> {
    boolean check(ConstraintNetworkInterface<T> cb, T n);
    ConsistencyCheckerInterface<T> clone();
}

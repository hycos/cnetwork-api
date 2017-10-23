package com.github.hycos.cnetwork.api.labelmgr;

import com.github.hycos.cnetwork.api.NodeInterface;
import com.github.hycos.cnetwork.api.labelmgr.exception.InconsistencyException;

/**
 * A label manager takes care of managing node labels and infer equivalent
 * nodes based on them
 */
public interface LabelManagerInterface<T extends NodeInterface> extends
        ConstraintNetworkListenerInterface<T> {

    String computeLabel(T n);

    T getNodeByLabel(String lbl);
    boolean hasNodeForLabel(String lbl);
    String getLabelForNode(T n);
    void setLabelForNode(T n, String lbl);
    NodeInterface infer(T n) throws InconsistencyException;

    LabelManagerInterface<T> clone();
}

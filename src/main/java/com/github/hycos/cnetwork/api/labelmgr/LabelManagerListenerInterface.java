package com.github.hycos.cnetwork.api.labelmgr;

import com.github.hycos.cnetwork.api.NodeInterface;
import com.github.hycos.cnetwork.api.labelmgr.exception.InconsistencyException;


/**
 * An observer interface to check for events that might
 */
public interface LabelManagerListenerInterface {


    void onEquivalentNodeLabels(NodeInterface toReplace, NodeInterface Replacement) throws
            InconsistencyException;
}

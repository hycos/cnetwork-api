/*
 * polyglot - translate constraints in between different formats
 * Copyright (C) 2017 Julian Thome <julian.thome.de@gmail.com>
 *
 * polyglot is licensed under the EUPL, Version 1.1 or – as soon
 * they will be approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence"); You may not use this work except in compliance with the
 * Licence. You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/sites/default/files/eupl1.1.-licence-en_0.pdf
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.  See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */

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

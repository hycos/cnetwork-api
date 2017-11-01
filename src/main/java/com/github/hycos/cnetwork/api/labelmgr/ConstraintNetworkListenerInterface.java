/*
 * cnetwork - a constraint network implementation for Java
 * Copyright (C) 2017 Julian Thome <julian.thome.de@gmail.com>
 *
 * cnetwork is licensed under the EUPL, Version 1.1 or – as soon
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
 * Interface to react to certain constraint network events
 * @param <T> node interface
 */
public interface ConstraintNetworkListenerInterface<T extends NodeInterface> {

    void onNodeCollapse(T toReplace, T replacement) throws InconsistencyException;
    void onNodeDelete(T n);
    void onNodeAdd(T n, boolean isConstraint) throws InconsistencyException;
    void onConstraintAdd(T n) throws InconsistencyException;
    void onConnectionAdd(T frst, T snd);
    void update(T n) throws InconsistencyException;
    void attach(T n);

    void register(ConstraintNetworkInterface<T> c);

}

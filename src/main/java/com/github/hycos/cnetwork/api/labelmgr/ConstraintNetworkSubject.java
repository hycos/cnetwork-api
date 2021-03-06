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

import com.github.hycos.cnetwork.api.EdgeInterface;
import com.github.hycos.cnetwork.api.NodeInterface;
import com.github.hycos.cnetwork.api.labelmgr.exception.InconsistencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;


public class ConstraintNetworkSubject<T extends NodeInterface> {

    final static Logger LOGGER = LoggerFactory.getLogger(ConstraintNetworkSubject.class);


    private Set<ConstraintNetworkListenerInterface<T, EdgeInterface>> observers = new
            HashSet<>();

    public void attach(ConstraintNetworkListenerInterface observer){
        observers.add(observer);
    }

    public void onDomainChange(T item) throws InconsistencyException {
        LOGGER.debug("notify all observers {}", observers.size());
        for (ConstraintNetworkListenerInterface observer : observers) {
            observer.update(item);
        }
    }

}

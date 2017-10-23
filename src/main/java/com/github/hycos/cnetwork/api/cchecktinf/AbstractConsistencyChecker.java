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

package com.github.hycos.cnetwork.api.cchecktinf;

import com.github.hycos.cnetwork.api.NodeInterface;
import com.github.hycos.cnetwork.api.labelmgr.ConstraintNetworkInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

/**
 * An interface for checking consistency of operations and
 * for type inference
 */
public abstract class AbstractConsistencyChecker {

    final static Logger LOGGER = LoggerFactory.getLogger(AbstractConsistencyChecker.class);


    public abstract boolean check(ConstraintNetworkInterface cb, NodeInterface n);


    public boolean checkNary(ConstraintNetworkInterface cb,
                                NodeInterface op,
                                int arity) {

        List<NodeInterface> params = cb.getParametersFor(op);

        LOGGER.debug("params size for {}:{} == {}", op.getKind().toString(),
                arity, params.size());

        if(arity > 0) {
            if(params.size() != arity)
                return false;
        }

        return params.size() == arity;
    }

    public boolean checkNary(ConstraintNetworkInterface cb,
                                NodeInterface op,
                                int arity,
                                Predicate<NodeInterface> paramPredicate,
                                Predicate<NodeInterface> opPredicate) {

        List<NodeInterface> params = cb.getParametersFor(op);


        if(arity > 0) {
            if(params.size() != arity)
                return false;
        }

        return params.stream().filter(paramPredicate).count() == params.size
                () && opPredicate.test(op);

    }

    public boolean checkNaryPlus2(ConstraintNetworkInterface cb,
                                     NodeInterface op,
                                Predicate<NodeInterface> paramPredicate,
                                Predicate<NodeInterface> opPredicate) {

        List<NodeInterface> params = cb.getParametersFor(op);

        if(params.size() < 2) {
            return false;
        }


        return params.stream().filter(paramPredicate).count() == params.size
                () && opPredicate.test(op);

    }

}

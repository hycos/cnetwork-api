/*
 * cnetwork-api - a constraint network api
 * Copyright (C) 2017 Julian Thome <julian.thome.de@gmail.com>
 *
 * cnetwork-api is licensed under the EUPL, Version 1.1 or – as soon
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

package com.github.hycos.cnetwork.api;

import com.github.hycos.cnetwork.api.domctrl.Domain;
import com.github.hycos.cnetwork.api.domctrl.DomainControllerInterface;
import com.github.hycos.cnetwork.api.domctrl.Term;
import com.github.hycos.cnetwork.api.labelmgr.exception.InconsistencyException;
import com.github.hycos.cnetwork.sig.JavaMethodSignature;

public interface NodeInterface extends ConstraintNetworkItemInterface {

    int getId();

    @Override
    int hashCode();

    String getLabel();
    String getShortLabel();

    String getDotLabel();

    Domain getDomain();
    void setDomain(Domain d) throws InconsistencyException;

    Term getTerm();
    void setTerm(Term t) throws InconsistencyException;

    // for convenience
    boolean isOperation();
    boolean isOperand();
    boolean isLiteral();
    boolean isRegex();
    boolean isString();
    boolean isNumeric();
    boolean isBoolean();
    boolean isVariable();
    boolean isConstraint();
    boolean isEquality();
    boolean isInequality();

    void setDomainController(DomainControllerInterface<NodeInterface> dctrl);

    JavaMethodSignature getSig();

    NodeKindInterface getKind();
    void setKind(NodeKindInterface n);

}

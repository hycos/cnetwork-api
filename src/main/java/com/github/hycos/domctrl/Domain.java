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

package com.github.hycos.domctrl;


import com.github.hycos.cnetwork.api.labelmgr.exception.InconsistencyException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public interface Domain extends Serializable {

    boolean isAlwaysTrue();
    boolean isAlwaysFalse();

    void taint(Collection<Integer> id);
    Set<Integer> getTaints();

    void setTrue() throws InconsistencyException;
    void setFalse() throws InconsistencyException;

    Domain intersect(Domain other);
    boolean isEmpty();

    String toString();

    SubDomainInterface getSubDomain(String subdomain);
    void setSubDomain(SubDomainInterface s);

    boolean isLiteral();
    boolean isRegex();
    boolean isString();
    boolean isNumeric();
    boolean isBoolean();
    boolean isVariable();
    boolean isConstraint();

    boolean isNegative();

    String getLabel();

    Domain clone();

    DomainKind getKind();
}

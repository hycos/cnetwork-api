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

import com.github.hycos.domctrl.DomainKind;

import java.io.Serializable;

public interface NodeKindInterface extends Serializable {
    boolean isSanitizer();
    boolean isThreatModel();
    boolean isComparative();
    boolean isEquality();
    boolean isInequality();
    boolean isOperation();
    boolean isBranch();
    boolean isLiteral();
    boolean isNumeric();
    boolean isBoolean();
    boolean isString();
    boolean isRegex();
    boolean isVariable();
    boolean isOperand();
    DomainKind getDomainKind();
    String getValue();
    String getDesc();
}

package com.github.hycos.cnetwork.api;

import com.github.hycos.cnetwork.api.domctrl.DomainKind;

public interface NodeKindInterface {
    String getDesc();
    boolean isSanitizer();
    boolean isComparative();
    boolean isEquality();
    boolean isInequality();
    boolean isOperation();
    boolean isBranch();
    boolean isLiteral();
    boolean isNumeric();
    boolean isBoolean();
    boolean isString();
    boolean isThreatModel();
    boolean isRegex();
    boolean isVariable();
    boolean isOperand();
    DomainKind getDomainKind();
    String getValue();

}

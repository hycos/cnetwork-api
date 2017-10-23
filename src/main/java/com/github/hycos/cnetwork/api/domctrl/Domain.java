package com.github.hycos.cnetwork.api.domctrl;

import com.github.hycos.cnetwork.api.labelmgr.exception.InconsistencyException;

public interface Domain {


    boolean isAlwaysTrue();
    boolean isAlwaysFalse();
    void setTrue() throws InconsistencyException;
    void setFalse();

    Domain intersect(Domain other);
    boolean isEmpty();

    String toString();

    SubDomainInterface getSubDomain(String subdomain);

    boolean isLiteral();
    boolean isRegex();
    boolean isString();
    boolean isNumeric();
    boolean isBoolean();
    boolean isVariable();
    boolean isConstraint();

    String getDotLabel();

    Domain clone();

    DomainKind getKind();
}

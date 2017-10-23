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

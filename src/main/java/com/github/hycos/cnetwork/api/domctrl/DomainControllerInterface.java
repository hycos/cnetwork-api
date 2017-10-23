package com.github.hycos.cnetwork.api.domctrl;


import com.github.hycos.cnetwork.api.NodeInterface;
import com.github.hycos.cnetwork.api.domctrl.exception.DomainControllerException;
import com.github.hycos.cnetwork.api.labelmgr.ConstraintNetworkListenerInterface;

/**
 * control the interaction between the cnetwork and
 * the domain Interface
 */
public interface DomainControllerInterface<T extends NodeInterface>  extends
        ConstraintNetworkListenerInterface<T> {
    Domain getDomain(T n) throws
            DomainControllerException;
    void setDomain(T n, Domain dom) throws
            DomainControllerException;
    boolean hasDomain(T n);
    Domain createDomainFor(T n);
    Domain getDomainFor(T n);
    DomainControllerInterface<T> clone();
}

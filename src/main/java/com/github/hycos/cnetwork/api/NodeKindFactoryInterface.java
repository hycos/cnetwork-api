package com.github.hycos.cnetwork.api;

import java.io.Serializable;

public interface NodeKindFactoryInterface extends Serializable {
    NodeKindInterface getNodeKindFromString(String s);
}

package de.bht.fpa.mail.s797307.util;

import java.util.List;

public interface MTargetNode {

public abstract List<?> getChildren();
public abstract List<?> getMessages();
public abstract boolean hasElement();
public abstract boolean hasChildren();
public abstract boolean hasMessages();
public abstract Object getElement();

}

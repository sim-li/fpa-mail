package de.bht.fpa.mail.s797307.util;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;

public interface MTargetNode {

public abstract List<?> getChildren();
public abstract List<?> getMessages();
public abstract List<?> getElements();
public abstract boolean hasElement();
public abstract boolean hasChildren();
public abstract boolean hasMessages();
public abstract boolean isFolder();
public abstract Object getElement();
public String getLabel();

}

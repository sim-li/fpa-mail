package de.bht.fpa.mail.s797307.util;

import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.BaseEntity;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public interface MTargetNode {

public abstract List<Object> getChildren();
public abstract List<Message> getMessages();
public abstract boolean hasFolder();
public abstract Folder getFolder();

}

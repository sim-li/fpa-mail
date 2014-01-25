package de.bht.fpa.mail.s797307.util;

import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class MFolder implements MTargetNode {
	private final Folder folder;
	
	public MFolder (Folder folder) {
		this.folder = folder;
	}
	
	@Override
	public List<Folder> getChildren() {
		return folder.getFolders();
	}

	@Override
	public List<Message> getMessages() {
		return folder.getMessages();
	}

	@Override
	public boolean hasElement() {
		return folder != null;
	}

	@Override
	public Folder getElement() {
		return folder;
	}

	@Override
	public boolean hasChildren() {
		return folder.getFolders().size() > 0;
	}

	@Override
	public boolean hasMessages() {
		 return folder.getMessages().size() > 0;
	}

}

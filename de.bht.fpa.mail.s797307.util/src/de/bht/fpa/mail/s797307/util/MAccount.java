package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;
import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class MAccount implements MTargetNode {
	private final Account account;
	
	public MAccount (Account account) {
		this.account = account;
	}
	
	@Override
	public List<Folder> getChildren() {
		return account.getFolders();
	}

	@Override
	public List<Message> getMessages() {
		return new LinkedList<Message>();
	}

	@Override
	public boolean hasElement() {
		return account != null;
	}

	@Override
	public Object getElement() {
		return account;
	}

	@Override
	public boolean hasChildren() {
		return account.getFolders().size() > 0;
	}

	@Override
	public boolean hasMessages() {
		return false;
	}

}

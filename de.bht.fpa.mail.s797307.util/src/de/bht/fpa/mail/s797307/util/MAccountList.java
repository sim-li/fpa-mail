package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;
import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class MAccountList implements MTargetNode {
	private List <Account> accounts;
	
	public MAccountList() {
		accounts = new LinkedList <Account>();
	}
	
	@Override
	public List<?> getChildren() {
		return accounts;
	}

	@Override
	public List<?> getMessages() {
		return new LinkedList <Message>();
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void removeAccount(Account account) {
		accounts.remove(account);
	}
	
	@Override
	public boolean hasElement() {
		return accounts.size() > 0;
	}

	@Override
	public boolean hasChildren() {
		return hasElement();
	}

	@Override
	public boolean hasMessages() {
		return false;
	}

	@Override
	public boolean isFolder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getElement() {
		return false;
	}
	
	@Override
	public String getLabel() {
		return "Accounts";
	}

}

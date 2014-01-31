package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;
import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class MAccountList implements MTargetNode {
	private List <MAccount> elements;

	public MAccountList() {
		elements = new LinkedList <MAccount>();
	}
	
	public void swipeList() {
		elements = new LinkedList <MAccount>();
	}
	
	@Override
	public List<?> getChildren() {
		return elements;
	}

	@Override
	public List<?> getMessages() {
		return new LinkedList <Message>();
	}

	public int size() {
		return elements.size();
	}
	
	public void add(MAccount element) {
		elements.add(element);
	}
	
	public void add(Account element) {
		elements.add(new MAccount(element));
	}
	
	public void remove(MTargetNode element) {
		elements.remove(element);
	}
	
	@Override
	public boolean hasElement() {
		return elements.size() > 0;
	}

	@Override
	public boolean hasChildren() {
		return elements.size() > 0;
	}

	@Override
	public boolean hasMessages() {
		return false;
	}

	@Override
	public boolean isFolder() {
		return false;
	}

	@Override
	public Object getElement() {
		return null;
	}
	
	@Override
	public String getLabel() {
		return "Accounts";
	}

	
	public List<?> getElements() {
		return elements;
	}

}

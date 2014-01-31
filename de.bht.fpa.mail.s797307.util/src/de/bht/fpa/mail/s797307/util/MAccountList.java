package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "accountsWu")
public class MAccountList implements MTargetNode {
	private static final long serialVersionUID = -2160650637811169762L;
	
	private List <MAccount> elements;
	
    @XmlElement(name = "account", type = Account.class)
	private List <Account> baseElements;
	

	public MAccountList() {
		elements = new LinkedList <MAccount>();
		baseElements = new LinkedList <Account>();
	}
	
	public void swipeList() {
		elements = new LinkedList <MAccount>();
		for (Account account : baseElements) {
			elements.add(new MAccount(account));
		}
	}
	
	@Override
	public List<?> getChildren() {
		System.out.println("Got children: " + elements.size());
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
		baseElements.add(Tools.clearOutAccount((Account) element.getElement()));
	}
	
	public void add(Account element) {
		elements.add(new MAccount(element));
		baseElements.add(element);
	}
	
	public void remove(MTargetNode element) {
		elements.remove(element);
		baseElements.remove((Account) element.getElement());
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

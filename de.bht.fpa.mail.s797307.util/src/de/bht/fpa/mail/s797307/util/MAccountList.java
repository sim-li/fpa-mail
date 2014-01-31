package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
public class MAccountList implements MTargetNode {
	private static final long serialVersionUID = -2160650637811169762L;
	
	private List <MAccount> elements;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List <Account> baseElements;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public MAccountList() {
		elements = new LinkedList <MAccount>();
		baseElements = new LinkedList <Account>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		baseElements.add((Account) element.getElement());
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

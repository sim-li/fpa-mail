package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;
import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class MAccountList implements MTargetNode {
	private List <MTargetNode> elements;
	
	public MAccountList() {
		elements = new LinkedList <MTargetNode>();
	}
	
	@Override
	public List<?> getChildren() {
		System.out.println("Got children: " + elements.size());
		return elements;
	}

	@Override
	public List<?> getMessages() {
		System.out.println("Got Karl. ");
		return new LinkedList <Message>();
	}

	public void add(MTargetNode element) {
		elements.add(element);
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
		// TODO Auto-generated method stub
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

	@Override
	public List<?> getElements() {
		return elements;
	}

}

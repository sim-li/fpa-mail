package de.bht.fpa.mail.s797307.imapnavigation;

import java.util.Collection;
import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;

public class FolderNode {
	Collection <Folder> children = new LinkedList<Folder>();
	Object child;
	
	public FolderNode(Account account) {
		this.child = account;
		children.addAll(account.getFolders());
	}
	public FolderNode(Folder folder) {
		this.child = folder;
		children.addAll(folder.getFolders());
	}
	
	public Folder[] getChildren() {
		Folder [] folders = new Folder [children.size()];
		children.toArray(folders);
		return folders;
	}
	
	public Account getAccount() {
		if (child instanceof Account) {
			return (Account) child;
		}
		// See if sinnvoll.
		return new Account();
	}
	
	public Folder getFolder() {
		if (child instanceof Folder) {
			return (Folder) child;
		}
		return new Folder();
	}
	
	public Object getChild() {
		return child;
	}
}

package de.bht.fpa.mail.s797307.imapnavigation;

import java.awt.List;
import java.util.Collection;
import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;

public class FolderNode {
	Collection <FolderNode> children = new LinkedList<FolderNode>();
	Object element;
	
	public FolderNode() {
		
	}
	public FolderNode(Account account) {
		this.element = account;
		children.addAll(wrapFolders(account.getFolders()));
	}
	public FolderNode(Folder folder) {
		this.element = folder;
		children.addAll(wrapFolders(folder.getFolders()));
	}
	
	public Collection<FolderNode> wrapFolders(Collection<Folder> input) {
		Collection <FolderNode> foldersWrapped = new LinkedList<FolderNode>();
		for (Folder folder : input) {
			foldersWrapped.add(new FolderNode(folder));
		}
		return foldersWrapped;
	}
	
	public void addAccount(Account account) {
		children.add(new FolderNode(account));
	}
	
	public FolderNode[] getChildren() {
		FolderNode [] folders = new FolderNode [children.size()];
		children.toArray(folders);
		return folders;
	}
	
	public Account getAccount() {
		if (element instanceof Account) {
			return (Account) element;
		}
		// See if sinnvoll.
		return new Account();
	}
	
	public boolean hasAccount() {
		return element instanceof Account;
	}
	
	public boolean hasFolder() {
		return element instanceof Folder;
	}
	
	public Folder getFolder() {
		if (element instanceof Folder) {
			return (Folder) element;
		}
		return new Folder();
	}
	
	public Object getChild() {
		return element;
	}
}

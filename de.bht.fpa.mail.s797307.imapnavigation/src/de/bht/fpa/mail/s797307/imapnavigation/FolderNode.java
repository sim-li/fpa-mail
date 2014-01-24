package de.bht.fpa.mail.s797307.imapnavigation;

import java.awt.List;
import java.util.Collection;
import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;

public class FolderNode {
	Collection <FolderNode> children = new LinkedList<FolderNode>();
	Object child;
	
	public FolderNode(Account account) {
		this.child = account;
		children.addAll(wrapFolders(account.getFolders()));
	}
	public FolderNode(Folder folder) {
		this.child = folder;
		children.addAll(wrapFolders(folder.getFolders()));
	}
	
	public Collection<FolderNode> wrapFolders(Collection<Folder> input) {
		Collection <FolderNode> foldersWrapped = new LinkedList<FolderNode>();
		for (Folder folder : input) {
			foldersWrapped.add(new FolderNode(folder));
		}
		return foldersWrapped;
	}
	public FolderNode[] getChildren() {
		FolderNode [] folders = new FolderNode [children.size()];
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
	
	public boolean hasAccount() {
		return child instanceof Account;
	}
	
	public boolean hasFolder() {
		return child instanceof Folder;
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

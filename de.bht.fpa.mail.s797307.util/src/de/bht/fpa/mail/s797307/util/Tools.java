package de.bht.fpa.mail.s797307.util;

import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public final class Tools {
	private Tools() {};
	
	public static Object [] listToArray(List list) {
		Object [] array = new Object[list.size()];
		list.toArray(array);
		return array;
	}
	
	public static boolean friendlyFilter(String s1, String s2) {
		if (s1 == null || s2 == null) { 
			return false;
		}
		return s1.contains(s2);
	}
	
	public static Account clearOutAccount(Account account) {
		Account a = new Account();
		a.setHost(account.getHost());
		a.setId(account.getId());
		a.setName(account.getName());
		a.setPassword(account.getPassword());
		a.setUsername(a.getUsername());
		return a;
	}
	
	public static MTargetNode makeMTargetNode(Object element) {
		if (element instanceof Folder) {
			return new MFolder((Folder) element);
		}
		if (element instanceof Account) {
			return new MAccount((Account) element);
		}
		if (element instanceof MAccount) {
			return (MAccount) element;
		}
		
		if (element instanceof MAccountList) {
			return (MAccountList) element;
		}
		System.out.println(element.getClass());
		return new MFolder(new Folder());
	}
}

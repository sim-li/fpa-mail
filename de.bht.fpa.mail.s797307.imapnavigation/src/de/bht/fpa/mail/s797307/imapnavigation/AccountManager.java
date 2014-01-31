package de.bht.fpa.mail.s797307.imapnavigation;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s797307.util.MAccount;
import de.bht.fpa.mail.s797307.util.MAccountList;


public final class AccountManager {
	private static final AccountManager INSTANCE = new AccountManager();
	private static MAccountList accounts;
	
	private AccountManager() {
		accounts = new MAccountList();
	}
	
	public static AccountManager getInstance() {
		return INSTANCE;
	}
	
	public static MAccountList getInput () {
		return accounts;
	}
	
	public static void addAccount(MAccount account) {
		accounts.add(account);
	}
	
	public static void addAccount(Account account) {
		accounts.add(new MAccount(account));
	}
	
	public static void removeAccount(MAccount account) {
		accounts.remove(account);
	}
	
	public static void removeAccount(Account account) {
		accounts.remove(new MAccount(account));
	}
	
	
}

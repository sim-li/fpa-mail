package de.bht.fpa.mail.s797307.imapnavigation;


public final class AccountManager {
	private static final AccountManager INSTANCE = new AccountManager();
	
	private AccountManager() {
		;
	}
	
	public static AccountManager getInstance() {
		return INSTANCE;
	}
}

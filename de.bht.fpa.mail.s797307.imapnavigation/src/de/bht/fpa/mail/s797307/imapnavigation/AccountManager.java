package de.bht.fpa.mail.s797307.imapnavigation;

import java.io.File;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.imapsync.SynchronizationException;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797307.util.MAccount;
import de.bht.fpa.mail.s797307.util.MAccountList;


public final class AccountManager {
	private static final AccountManager INSTANCE = new AccountManager();
	private static MAccountList accounts;
	private static final File SETTINGS_FILE = new File("/Users/funkjaymatada/test.xml");
	
	private AccountManager() {
		accounts = new MAccountList();
	}
	
	public static AccountManager getInstance() {
		return INSTANCE;
	}
	
	public static MAccountList getInput () {
		return accounts;
	}
	
	public static void saveAccount(MAccount mAccount) {
		Account account = (Account) mAccount.getElement();
		if (ImapHelper.getAccount(account.getName()) == null) {
			ImapHelper.saveAccount(account); 
			accounts.add(mAccount);
		} else {
			accounts.add(new MAccount(ImapHelper.getAccount(account.getName())));
		}
	}
	
	public static void saveAccount(Account account) {
		saveAccount(new MAccount(account));
	}

	/*
	 * Just removes from local collection, so it won't be
	 * displayed, account remains in database.
	 */
	public static void removeAccount(MAccount account) {
		accounts.remove(account);
	}
	
	public static void removeAccount(Account account) {
		accounts.remove(new MAccount(account));
	}
	
	public static void saveSettings() {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(MAccountList.class);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(getInput(), SETTINGS_FILE);
		} catch (JAXBException e) {
			System.err.println("Got a problem saving that configuration file, you know.");
			e.printStackTrace();
		}
	}
	
	public static void loadSettings() {
		accounts = JAXB.unmarshal(SETTINGS_FILE, MAccountList.class);
		System.out.println("Hello dear");
		System.out.println(accounts.size());
		accounts.swipeList();
	}
	
	public static IStatus syncAll(IProgressMonitor monitor) {
		ImapHelper.setDebug(false);
		try {
			for (Object accountNode : accounts.getElements()) {
				MAccount account = (MAccount) accountNode;
				ImapHelper.syncAllFoldersToAccount((Account) account.getElement(), 
						new SubProgressMonitor(monitor, accounts.size()));
			}
		}
		catch (SynchronizationException e) {
			System.err.println("Legend of the phoenix.");
			return Status.CANCEL_STATUS;
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}
}

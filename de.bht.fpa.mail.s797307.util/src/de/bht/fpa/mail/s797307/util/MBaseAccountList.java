package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.bht.fpa.mail.s000000.common.mail.model.Account;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "simonsaccountshop")
public class MBaseAccountList {
	private static final long serialVersionUID = -2160650637811169762L;
	
    @XmlElement(name = "account", type = Account.class)
	private List <Account> baseElements;
	

    public MBaseAccountList() {
    	
    }
	public MBaseAccountList(MAccountList accounts) {
		baseElements = new LinkedList <Account>();
		for (Object account : accounts.getElements()) {
			MAccount element = (MAccount) account;
			baseElements.add((Account) element.getElement()); 
		}
	}
	
	public MAccountList getMAccountList() {
	    MAccountList retrievedAccounts = new MAccountList();
		for (Account account : baseElements) {
			retrievedAccounts.add(new MAccount(account));
		}
		return retrievedAccounts;
	}
	

}

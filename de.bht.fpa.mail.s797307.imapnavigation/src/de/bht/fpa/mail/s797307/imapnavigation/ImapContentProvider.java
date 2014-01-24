package de.bht.fpa.mail.s797307.imapnavigation;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;

public class ImapContentProvider implements ITreeContentProvider {
  protected ImapNavigationView view;

  public ImapContentProvider(ImapNavigationView view) {
    this.view = view;
  }

  @Override
  public Object[] getChildren(Object parentElement) {
	  Collection <Object> folders = new LinkedList <Object>();
	  if (parentElement instanceof Account) {
		  Account account = (Account) parentElement;
		  folders.addAll(account.getFolders());
	  } else {
		  Folder parent = (Folder) parentElement;
		  folders.addAll(parent.getFolders());
	  }
	  Folder [] output = new Folder [folders.size()];
	  folders.toArray(output);
	  return output;
  }

  @Override
  public boolean hasChildren(Object element) {
//	  Account parent = (Account) element;
//	  if (parent.getFolders().size() > 0) {
//		  return true;
//	  }
	  return true;
  }

  @Override
  public Object[] getElements(Object parent) {
    return getChildren(parent);
  }

  @Override
  public void inputChanged(Viewer v, Object oldInput, Object newInput) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public Object getParent(Object element) {
    return null;
  }
}
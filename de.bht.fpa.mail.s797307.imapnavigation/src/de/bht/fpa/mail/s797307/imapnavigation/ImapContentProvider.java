package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.bht.fpa.mail.s797307.util.MTargetNode;
import de.bht.fpa.mail.s797307.util.Tools;

public class ImapContentProvider implements ITreeContentProvider {
  protected ImapNavigationView view;

  public ImapContentProvider(ImapNavigationView view) {
    this.view = view;
  }

  @Override
  public Object[] getChildren(Object element) {
	 MTargetNode node = Tools.makeMTargetNode(element);
	 return Tools.listToArray(node.getChildren());
  }

  @Override
  public boolean hasChildren(Object element) {
	  MTargetNode node = Tools.makeMTargetNode(element);
	  return node.hasChildren();
  }

  @Override
  public Object[] getElements(Object element) {
	  MTargetNode node = Tools.makeMTargetNode(element);
	  return new Object[] {
			  node.getElement()
	  };
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
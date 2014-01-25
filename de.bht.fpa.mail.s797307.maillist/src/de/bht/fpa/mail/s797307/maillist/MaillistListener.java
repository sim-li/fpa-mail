package de.bht.fpa.mail.s797307.maillist;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797307.util.MTargetNode;
import de.bht.fpa.mail.s797307.util.TFile;

public class MaillistListener implements ISelectionListener {
  private final MailListView mailListView;

  public MaillistListener(MailListView mailListView) {
    this.mailListView = mailListView;
  }

  @Override
  public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof TreeSelection) {
      Object element = ((TreeSelection) selection).getFirstElement();
      if (element == null) {
    	  return;
      }
      if (element instanceof TFile) {
    	  addXmlDirectory((TFile) element);
      }
      
      if (element instanceof MTargetNode) {
    	  addMessages((MTargetNode) element);
      }
    }
  }

  public void addMessages(MTargetNode node) {
	  	mailListView.clear();
	  	for (Object m : node.getMessages()) {
	  		mailListView.addMessage((Message) m);
		}
	  	mailListView.updateMessages();
	  	mailListView.refresh();
  }
  
  public void addXmlDirectory(TFile directory) {
    mailListView.clear();
    if (!directory.hasChildren(FilterFactory.xmlFilter())) {
      return;
    }
    boolean gotMessages = false;
    for (TFile f : directory.getChildren(FilterFactory.xmlFilter())) {
      try {
        Message message = JAXB.unmarshal(f.getFile(), Message.class);
        if (message.getText() != null) {
          gotMessages = true;
          mailListView.addMessage(message);
        }
      } catch (DataBindingException e) {
        System.err.println("Error parsing XML File: " + f.getText());
      }
    }
    if (gotMessages) {
      mailListView.updateMessages();
      gotMessages = false;
    }
    mailListView.refresh();
  }
}

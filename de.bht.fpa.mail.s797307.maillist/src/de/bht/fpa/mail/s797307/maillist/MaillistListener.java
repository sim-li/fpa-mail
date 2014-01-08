package de.bht.fpa.mail.s797307.maillist;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797307.common.util.TFile;

public class MaillistListener implements ISelectionListener {
    private final MailListView mailListView;

    public MaillistListener(MailListView mailListView) {
        this.mailListView = mailListView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (selection instanceof TreeSelection) {
            Object firstElement = ((TreeSelection) selection).getFirstElement();
            if (firstElement instanceof TFile) {
                parseDirectory((TFile) firstElement);
            }
        }
    }

    public void parseDirectory(TFile directory) {
        if (!directory.hasChildren(FilterFactory.xmlFilter())) {
            return;
        }
        boolean gotMessages = false;
        mailListView.clear();
        for (TFile f : directory.getChildren(FilterFactory.xmlFilter())) {
            try {
                Message message = JAXB.unmarshal(f.getFile(), Message.class);
                if (message != null) {
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

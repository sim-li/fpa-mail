package de.bht.fpa.mail.s797307.maillist;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

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
            // if (firstElement instanceof TFile) {
            // parseDirectory((TFile) firstElement);
        }
    }
}

// public void parseDirectory(TFile directory) {
// System.out.println(directory.getText());
// Collection<Message> messages = new LinkedList<Message>();
// for (TFile f : directory.getChildren(FilterFactory.xmlFilter())) {
// try {
// Message message = JAXB.unmarshal(f.getFile(), Message.class);
// if (message != null) {
// messages.add(message);
// }
// } catch (DataBindingException e) {
// System.err.println("Error parsing XML File: " + f.getText());
// }
// if (messages.size() > 0) {
// System.out.println("Hello Kitty");
// // printMessages(messages, directory);
// }
// }
// }


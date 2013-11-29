package de.bht.fpa.mail.s797307.fsnavigation.handlers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.bht.fpa.mail.s797307.fsnavigation.HistoryManager;

public class HistoryContentProvider implements IStructuredContentProvider {

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (HistoryManager.getInstance().isEmpty()) {
            return new String[] { "No base directories in history" };
        }
        return HistoryManager.getInstance().getHistory();
    }
}

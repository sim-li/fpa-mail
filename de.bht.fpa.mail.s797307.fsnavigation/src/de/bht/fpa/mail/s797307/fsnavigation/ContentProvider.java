package de.bht.fpa.mail.s797307.fsnavigation;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ContentProvider implements ITreeContentProvider {
    protected NavigationView view;

    public ContentProvider(NavigationView view) {
        this.view = view;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        TFile d = new TFile(parentElement);
        return d.getChildren();
    }

    @Override
    public boolean hasChildren(Object element) {
        TFile d = new TFile(element);
        return d.isDirectory();
    }

    // ==========================================================================

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
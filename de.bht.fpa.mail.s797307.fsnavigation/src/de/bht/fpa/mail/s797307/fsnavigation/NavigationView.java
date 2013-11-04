package de.bht.fpa.mail.s797307.fsnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public final class NavigationView extends ViewPart {
    private TFileComposite root;
    private TreeViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer(parent);
        LabelProvider l = new LabelProvider();
        ContentProvider cp = new ContentProvider(this);
        viewer.setLabelProvider(l);
        viewer.setContentProvider(cp);
        createModel();
        viewer.setInput(root.getPath());
    }

    private TFileComposite createModel() {
        root = new TFileComposite(System.getenv("HOME"));
        return root;
    }

    public TFileComposite getModel() {
        return root;
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }
}

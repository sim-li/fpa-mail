package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public final class NavigationView extends ViewPart {
    private TreeViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer(parent);
        ViewLabelProvider l = new ViewLabelProvider();
        ContentProvider cp = new ContentProvider(this);
        viewer.setLabelProvider(l);
        viewer.setContentProvider(cp);
        viewer.setInput(createModel());
    }

    private TFile createModel() {
        return new TFile(new File(System.getenv("HOME")));
    }

    public void setInput(String s) {
        System.out.println(s);
        // viewer.setInput(new TFile(new File(s)));
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }
}

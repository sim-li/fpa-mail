package de.bht.fpa.mail.s797307.fsnavigation;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s797307.common.util.TFile;


public final class NavigationView extends ViewPart {
    private TreeViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
        ViewLabelProvider l = new ViewLabelProvider();
        ContentProvider cp = new ContentProvider(this);
        viewer = new TreeViewer(parent);
        viewer.setLabelProvider(l);
        viewer.setContentProvider(cp);
        viewer.setInput(createModel());
        // getViewSite().getPage().addSelectionListener(new
        // NavigationListener(this));
        getSite().setSelectionProvider(viewer);
        initalizeExecutionListener();
    }

    private TFile createModel() {
        HistoryManager.getInstance().loadHistory();
        return new TFile(HistoryManager.getInstance().getBaseDirectory());
    }

    public void initalizeExecutionListener() {
        ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getService(ICommandService.class);
        commandService.addExecutionListener(new ExecutionController(viewer));
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub

    }
}

package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
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
        initalizeExecutionListener();
    }

    private TFile createModel() {
        return new TFile(new File(System.getenv("HOME")));
    }

    public void initalizeExecutionListener() {
        ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getService(ICommandService.class);
        commandService.addExecutionListener(new IExecutionListener() {

            @Override
            public void notHandled(String commandId, NotHandledException exception) {
                // TODO Auto-generated method stub

            }

            @Override
            public void postExecuteFailure(String commandId, ExecutionException exception) {
                // TODO Auto-generated method stub

            }

            @Override
            public void postExecuteSuccess(String commandId, Object returnValue) {
                viewer.setInput(new TFile(new File((String) returnValue)));

            }

            @Override
            public void preExecute(String commandId, ExecutionEvent event) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }
}

package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797307.fsnavigation.filters.XMLFilter;
import de.bht.fpa.mail.s797307.fsnavigation.listeners.ExecutionListener;

public final class NavigationView extends ViewPart implements ISelectionChangedListener {
    private TreeViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer(parent);
        ViewLabelProvider l = new ViewLabelProvider();
        ContentProvider cp = new ContentProvider(this);
        viewer.setLabelProvider(l);
        viewer.setContentProvider(cp);
        viewer.setInput(createModel());
        viewer.addSelectionChangedListener(this);
        initalizeExecutionListener();
    }

    private TFile createModel() {
        // return new TFile(new File(System.getenv("HOME")));
        return new TFile(new File("/Users/ccrider/Beuth/FPA/bht.haschemi/mailer-common/de.bht.fpa.mail.common"));
    }

    public void initalizeExecutionListener() {
        ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getService(ICommandService.class);
        commandService.addExecutionListener(new ExecutionListener(viewer));
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        TFile directory = (TFile) viewer.getInput();
        Collection<Message> messages = new LinkedList<Message>();

        for (TFile f : directory.getChildren(new XMLFilter())) {
            try {
                System.out.print(f.getText() + " ");
                Message message = JAXB.unmarshal(f.getFile(), Message.class);
                if (message != null) {
                    messages.add(message);
                }
            } catch (DataBindingException e) {
                System.err.println("Error parsing XML File.");
            }

        }
        System.out.println("COUNT: " + messages.size());
        System.out.println(messages.toString());
    }
}

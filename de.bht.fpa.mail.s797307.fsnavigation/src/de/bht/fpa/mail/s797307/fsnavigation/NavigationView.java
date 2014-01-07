package de.bht.fpa.mail.s797307.fsnavigation;

import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public final class NavigationView extends ViewPart implements ISelectionChangedListener {
    private TreeViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
        ViewLabelProvider l = new ViewLabelProvider();
        ContentProvider cp = new ContentProvider(this);
        viewer = new TreeViewer(parent);
        viewer.setLabelProvider(l);
        viewer.setContentProvider(cp);
        viewer.setInput(createModel());
        viewer.addSelectionChangedListener(this);
        getSite().setSelectionProvider(viewer);
        getViewSite().getPage().addSelectionListener(new NavigationListener(this));
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
        viewer.getControl().setFocus();
    }

    public void printMessages(Collection<Message> messages, TFile directory) {
        System.out.println("Selected base directory: " + directory.getFile().getAbsolutePath());
        System.out.println("Number of messages: " + messages.size());
        for (Message message : messages) {
            System.out.println(message.toString());
        }
        System.out.println("*");
    }

    public void parseDirectory(TFile directory) {
        Collection<Message> messages = new LinkedList<Message>();
        for (TFile f : directory.getChildren(FilterFactory.xmlFilter())) {
            try {
                Message message = JAXB.unmarshal(f.getFile(), Message.class);
                if (message != null) {
                    messages.add(message);
                }
            } catch (DataBindingException e) {
                System.err.println("Error parsing XML File: " + f.getText());
            }
            if (messages.size() > 0) {
                printMessages(messages, directory);
            }
        }
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        ITreeSelection selection = (ITreeSelection) viewer.getSelection();
        if (!selection.isEmpty()) {
            parseDirectory((TFile) selection.getFirstElement());
        }
    }

}

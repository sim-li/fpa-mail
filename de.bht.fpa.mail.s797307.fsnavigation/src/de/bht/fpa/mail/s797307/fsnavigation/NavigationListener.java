package de.bht.fpa.mail.s797307.fsnavigation;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class responds to selections of {@link Message} objects in the workspace
 * and updates the UI of the {@link MessageView}.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class NavigationListener implements ISelectionListener {

    private final NavigationView navigationView;

    public NavigationListener(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
    }
}

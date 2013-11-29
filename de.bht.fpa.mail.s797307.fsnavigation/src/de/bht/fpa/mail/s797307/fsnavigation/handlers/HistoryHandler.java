package de.bht.fpa.mail.s797307.fsnavigation.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s797307.fsnavigation.HistoryManager;
import de.bht.fpa.mail.s797307.fsnavigation.TFile;

public class HistoryHandler extends AbstractHandler {

    public HistoryHandler() {
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        ListDialog dialog = new ListDialog(window.getShell());
        dialog.setContentProvider(new HistoryContentProvider());
        dialog.setLabelProvider(new HistoryLabelProvider());
        dialog.setInput(new TFile[] {});
        Object returnCode = dialog.open();
        if ((int) returnCode != Window.OK || HistoryManager.getInstance().isEmpty()) {
            return null;
        }
        TFile result = new TFile((File) dialog.getResult()[0]);
        return result;
    }
}

package de.bht.fpa.mail.s797307.fsnavigation.handlers;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s797307.fsnavigation.HistoryManager;

public class HistoryLabelProvider extends LabelProvider {

    @Override
    public Image getImage(Object element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getText(Object element) {
        if (HistoryManager.getInstance().isEmpty()) {
            return "No base directories in history";
        }
        return ((File) element).getAbsolutePath();
    }

}

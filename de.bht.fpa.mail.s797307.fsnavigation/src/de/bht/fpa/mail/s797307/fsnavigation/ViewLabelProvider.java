package de.bht.fpa.mail.s797307.fsnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ViewLabelProvider extends LabelProvider {
    @Override
    public String getText(Object element) {
        return ((TFile) element).getText();
    }

    @Override
    public Image getImage(Object element) {
        return ((TFile) element).getImage();
    }
}

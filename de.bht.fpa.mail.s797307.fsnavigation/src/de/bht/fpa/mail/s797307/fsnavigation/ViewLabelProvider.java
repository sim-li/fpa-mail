package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s797307.util.TFile;

public class ViewLabelProvider extends LabelProvider {
  private final Image folderIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder.ico")
      .createImage();
  private final Image fileIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/file.ico")
      .createImage();

  @Override
  public String getText(Object element) {
    return ((TFile) element).getText();
  }

  @Override
  public Image getImage(Object element) {
    TFile file = (TFile) element;
    if (file.isDirectory()) {
      return folderIcon;
    }
    return fileIcon;
  }

  public Image getImage() {
    // if (file.isDirectory()) {
    // return folderIcon;
    // }
    // return fileIcon;
    return null;
  }
}

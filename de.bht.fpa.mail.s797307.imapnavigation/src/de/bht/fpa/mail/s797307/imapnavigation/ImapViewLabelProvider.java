package de.bht.fpa.mail.s797307.imapnavigation;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s797307.util.TFile;

public class ImapViewLabelProvider extends LabelProvider {
//  private final ImagefolderIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder.ico")
//      .createImage();
//  private final Image fileIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/file.ico")
//      .createImage();

  @Override
  public String getText(Object element) {
//    return ((TFile) element).getText();
	  return "Hello Java";
  }

  @Override
  public Image getImage(Object element) {
	  return null;
//    TFile file = (TFile) element;
//    if (file.isDirectory()) {
//      return folderIcon;
//    }
//    return fileIcon;
  }

  public Image getImage() {
    // if (file.isDirectory()) {
    // return folderIcon;
    // }
    // return fileIcon;
    return null;
  }
}

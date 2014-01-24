package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.Folder;

public class ImapViewLabelProvider extends LabelProvider {
  private final Image folderIcon;
  private final Image accountIcon;

  public ImapViewLabelProvider() {
	  folderIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder.ico")
		      .createImage();
	  accountIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/account.ico")
		      .createImage();
  }
  @Override
  public String getText(Object element) {
	  FolderNode folder = (FolderNode) element;
	  if (folder.hasAccount()) {
		  return folder.getAccount().getName();
	  }
	  return folder.getFolder().getFullName();
  }

  @Override
  public Image getImage(Object element) {
	  FolderNode node = (FolderNode) element;
	  return node.hasAccount() ? accountIcon : folderIcon;
  }

  public Image getImage() {
    return folderIcon;
  }
}

package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s797307.util.MTargetNode;
import de.bht.fpa.mail.s797307.util.Tools;

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
	  MTargetNode node = Tools.makeMTargetNode(element);
	  if (node.hasElement()) {
		  return node.getLabel();
	  }
	  return "Empty";
  }

  @Override
  public Image getImage(Object element) {
	  MTargetNode node = Tools.makeMTargetNode(element);
	  return node.isFolder() ? folderIcon : accountIcon;
  }

  public Image getImage() {
    return folderIcon;
  }
}

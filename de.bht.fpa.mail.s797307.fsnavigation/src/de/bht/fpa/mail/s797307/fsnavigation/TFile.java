package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.swt.graphics.Image;

public class TFile {
    private final Image folderIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder.ico")
            .createImage();
    private final Image fileIcon = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/file.ico")
            .createImage();

    private final File file;

    public TFile(File file) {
        this.file = file;
    }

    public TFile[] getChildren(FileFilter filter) {
        File[] listFiles = file.listFiles(filter);
        Collection<TFile> c = new LinkedList<TFile>();
        if (listFiles == null) {
            return new TFile[0];
        }
        for (File f : listFiles) {
            c.add(new TFile(f));
        }
        return c.toArray(new TFile[c.size()]);
    }

    public boolean hasChildren(FileFilter filter) {
        return getChildren(filter).length > 0;
    }

    public File getFile() {
        return file;
    }

    public String getText() {
        return file.getName();
    }

    public Image getImage() {
        if (file.isDirectory()) {
            return folderIcon;
        }
        return fileIcon;
    }

}

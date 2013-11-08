package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.swt.graphics.Image;

public class TFile {
    Image img = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder.png").createImage();

    private final File file;

    public TFile(File file) {
        this.file = file;
    }

    public TFile[] getChildren() {
        Collection<TFile> c = new ArrayList<TFile>();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return new TFile[0];
        }

        for (File f : listFiles) {
            c.add(new TFile(f));
        }
        return c.toArray(new TFile[c.size()]);
    }

    public boolean hasChildren() {
        return getChildren().length > 0;
    }

    public String getText() {
        return file.getName();
    }

    public Image getImage() {
        if (file.isDirectory()) {
            return img;
        }
        return null;
    }

}

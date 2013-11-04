package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class TFile extends File {

    public TFile(TFile parent) {
        super(parent.getPath());
    }

    public TFile(File parent) {
        super(parent.getPath());
    }

    public TFile(String parent) {
        super(parent);
    }

    public TFile(Object parent) {
        super((String) parent);
    }

    public String[] getChildren() {
        File[] files = listFiles();
        Collection<String> c = new ArrayList<String>();
        for (File f : files) {
            c.add(f.getPath());
        }
        String[] sFiles = new String[c.size()];
        c.toArray(sFiles);
        return sFiles;
    }
}

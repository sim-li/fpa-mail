package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;
import java.util.Collection;

public class TFileComposite extends TFileComponent {
    Collection<String> filesystem;
    File f;

    public TFileComposite(String currentPath) {
        super(currentPath);
        f = new File(currentPath);

    }

    @Override
    public boolean addFile(String path) {
        filesystem.add(path);
        return true;
    }

    @Override
    public boolean removeFile(String path) {
        filesystem.remove(path);
        return true;
    }

    @Override
    public boolean isDirectory() {
        if (currentPath.isDirectory()) {
            return true;
        }
        return false;
    }

    @Override
    public String getDirectory() {

        return null;
    }

    @Override
    public String[] listFiles() {
        // TODO Auto-generated method stub
        return null;
    }
}

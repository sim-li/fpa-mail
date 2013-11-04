package de.bht.fpa.mail.s797307.fsnavigation;

public abstract class TFileComponent {
    protected String currentPath;

    public TFileComponent(String currentPath) {
        this.currentPath = currentPath;
    }

    public abstract boolean addFile(String path);

    public abstract boolean removeFile(String path);

    public abstract boolean isDirectory();

    public abstract String getDirectory();

    public abstract String[] listFiles();

}

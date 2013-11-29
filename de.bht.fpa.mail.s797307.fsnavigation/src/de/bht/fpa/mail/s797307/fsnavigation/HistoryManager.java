package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public final class HistoryManager {
    private static final HistoryManager INSTANCE = new HistoryManager();
    private LinkedList<File> history = new LinkedList<File>();
    private final File f = new File(getDefaultRoot() + "/history.txt");

    private HistoryManager() {
    }

    public void loadHistory() {
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                history = (LinkedList<File>) ois.readObject();
            } catch (ClassNotFoundException e) {
                System.err.println("Illegal file format for history file");
            }
            fis.close();
        } catch (IOException e) {
        }
    }

    public void saveHistory() {
        FileOutputStream fos = null;
        if (!history.isEmpty()) {
            try {
                fos = new FileOutputStream(f.getAbsolutePath());
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(history);
            } catch (IOException e) {
                System.err.println("Error writing history");
            }
        }
        try {
            fos.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }
    }

    public static HistoryManager getInstance() {
        return INSTANCE;
    }

    public File[] getHistory() {
        File[] a = new File[history.size()];
        return history.toArray(a);
    }

    public void add(File file) {
        history.add(file);
    }

    public boolean isEmpty() {
        return history.size() == 0;
    }

    public String getDefaultRoot() {
        return System.getenv("HOME");
    }

    public File getBaseDirectory() {
        if (!isEmpty()) {
            return history.getLast();
        } else {
            return new File(getDefaultRoot());
        }
    }

}
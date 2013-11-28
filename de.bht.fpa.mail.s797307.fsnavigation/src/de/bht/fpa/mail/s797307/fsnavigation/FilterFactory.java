package de.bht.fpa.mail.s797307.fsnavigation;

import java.io.File;

import java.io.FileFilter;

public class FilterFactory {

    public static FileFilter directoryFilter() {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                }
                return false;
            }
        };
    }

    public static FileFilter openFilter() {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
    }

    public static FileFilter xmlFilter() {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().toLowerCase().endsWith(".xml")) {
                    return true;
                }
                return false;
            }
        };
    }
}

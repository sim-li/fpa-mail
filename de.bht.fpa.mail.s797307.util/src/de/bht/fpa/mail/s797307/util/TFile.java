package de.bht.fpa.mail.s797307.util;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class TFile implements Serializable {
  /**
   * TFile Object
   */
  private static final long serialVersionUID = 1234234324234L;

  private final File file;

  public TFile(File file) {
    this.file = file;
  }

  public boolean isDirectory() {
    return file.isDirectory();
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
}

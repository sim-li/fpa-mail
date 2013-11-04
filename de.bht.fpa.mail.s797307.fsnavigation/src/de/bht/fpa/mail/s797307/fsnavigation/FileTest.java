package de.bht.fpa.mail.s797307.fsnavigation;

public class FileTest {
    public static void main(String[] args) {
        TFile test = new TFile("/");
        String[] children = test.getChildren();
        for (String s : children) {
            System.out.println(s);
        }
    }
}

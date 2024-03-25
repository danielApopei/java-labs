package org.example;

public class Main {
    public static void main(String[] args) {
        Repository r = new Repository("./sample");
        r.displayRepositoryContent();
//
//        ViewCommand vc = new ViewCommand();
//        vc.setFilepath(".\\README.md");
////        vc.execute();
//
//        ReportCommand rc = new ReportCommand();
//        rc.setDocumentList(r.getDocumentList());
//        rc.execute();
//
//
//        ExportCommand ec = new ExportCommand(r.getDocumentList());
//        ec.execute();
        RepositoryShell rs = new RepositoryShell();
        try {
            rs.start();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
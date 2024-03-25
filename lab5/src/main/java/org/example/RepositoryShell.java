package org.example;

import org.example.exceptions.InvalidParamsException;
import org.example.exceptions.NullRepoException;

import java.util.Scanner;

/**
 * class giving user access to basic repo management
 */
public class RepositoryShell {
    Repository repository = null;
    public void start() throws NullRepoException, InvalidParamsException {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        label:
        while(true) {
            System.out.print("RS> ");
            line = scanner.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
                case "setdir":
                    if(words.length < 2) throw new InvalidParamsException("InvalidParamsException: missing repo path");
                    String dir = words[1];
                    repository = new Repository(dir);
                    System.out.println("Directory set to " + dir);
                    break;
                case "getdir":
                    if (repository == null) {
                        System.out.println("No repository set!");
                    } else {
                        System.out.println("Repository set to " + repository.getDirectory());
                    }
                    break;
                case "deldir":
                    repository = null;
                    System.out.println("Cleared repo!");
                    break;
                case "view":
                    if(words.length < 2) throw new InvalidParamsException("InvalidParamsException: missing file");
                    String filepath = words[1];
                    ViewCommand vc = new ViewCommand();
                    vc.setFilepath(filepath);
                    vc.execute();
                    break;
                case "report":
                    if(repository == null) throw new NullRepoException("NullRepoException: Repository not set!");
                    ReportCommand rc = new ReportCommand();
                    rc.setDocumentList(repository.getDocumentList());
                    rc.execute();
                    break;
                case "export":
                    if(repository == null) throw new NullRepoException("NullRepoException: Repository not set!");
                    ExportCommand ec = new ExportCommand(repository.getDocumentList());
                    ec.execute();
                    break;
                case "exit":
                    break label;
                default:
                    System.out.println("Unknown command!");
                    break;
            }
        }
    }
}

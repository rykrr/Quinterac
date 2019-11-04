package ca.queensu.cisc327.afk;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountListReader {

    private static NullAccount NULL = new NullAccount();

    public static List<Account> read(String path)
            throws FileNotFoundException, AccountListReaderException {

        List<Account> accounts = new ArrayList<>();
        Account account = null;

        try(Scanner scanner = new Scanner(new File(path))) {
            while(scanner.hasNextLine()) {
                try {
                    account = new Account(scanner.nextLine());
                }
                catch(NumberFormatException e) {
                    throw new AccountListReaderException(
                            "Bad account on line " + accounts.size(), e);
                }

                if(NULL.equals(account))
                    break;

                accounts.add(account);
            }
        }
        catch(FileNotFoundException e) {
            throw e;
        }
        catch(Exception e) {
            throw new AccountListReaderException("Malformed account list file", e);
        }

        return accounts;
    }

    public static class AccountListReaderException extends RuntimeException {
        public AccountListReaderException(String msg, Exception src) {
            super(msg, src);
        }
    }

}

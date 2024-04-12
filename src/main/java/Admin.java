import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Account{
    Subreddit subreddit;
    Admin(Account account , Subreddit subreddit) {
        super(account.name, account.username, account.password, account.emailAddress , account.accountID);
        this.subreddit = subreddit;
    }


    public void adminAction() {
        boolean page = true;
        boolean option;
        Scanner scanner = new Scanner(System.in);


        while (page) {
            System.out.println("Hey , you entered Add / Remove section .\nwant to continue ? (y/n)");
            page = scanner.nextLine() == "y";

            if (!page) {
                continue;
            }

            System.out.print("Please enter the username of this user : u/");
            String username = scanner.nextLine();

            ArrayList<Account> similarAccounts = Reddit.findSimilarUsernames(username);

            if (similarAccounts.isEmpty()) {
                System.out.println("Sorry we couldn't find any account with that username </3");
                continue;
            }

            System.out.println("Please choose one of the above similar usernames : ");
            int optionNumber = 1;
            int input;

            for (Account account : similarAccounts) {
                System.out.println(optionNumber + ") u/" + account.username);
                optionNumber++;
            }

            System.out.print(": ");

            do {
                input = scanner.nextInt();

                if (input > 0 & input <= similarAccounts.size())  {
                    break;
                } else {
                    System.out.print("Please choose one of the above : ");
                }

            } while (true);

            Account account = similarAccounts.get(input - 1);

            if (subreddit.users.contains(account)) {
                if (subreddit.admins.contains(account)) {
                    System.out.println("This user is already admin , would you want to remove it ? : 1) Yes 2) No");
                    option = scanner.nextInt() == 1;

                    if (option) {
                        subreddit.admins.remove(account);
                        System.out.println("Successfully removed <3");

                    } else {
                        System.out.println("Ok <3");
                    }

                } else {
                    System.out.println("This user isn't in admins list , would you want to add it ? : 1) Yes 2) No");
                    option = scanner.nextInt() == 1;

                    if (option) {
                        Admin admin = new Admin(account , subreddit);
                        subreddit.admins.add(admin);
                        System.out.println("User u/" + account.username + " has successfully added to admins <3");

                    } else {
                        System.out.println("Ok <3");
                    }
                }


            } else {
                System.out.println("Sorry but this user isn't joined to this subreddit already </3 .");
            }

        }


    }

}

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Reddit {
    static ArrayList<Subreddit> Subreddits = new ArrayList<>();
    static ArrayList<Account> accounts = new ArrayList<>();

    public static boolean emailValidator(String email) {
        String regEx = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regEx);
    }


    public static ArrayList<String> getAllUsernames() {
        ArrayList<String> usernames = new ArrayList<>();

        for (Account account : accounts) {
            usernames.add(account.username);
        }

        return usernames;
    }


    public static ArrayList<String> getAllEmails() {
        ArrayList<String> emailAddress = new ArrayList<>();

        for (Account account : accounts) {
            emailAddress.add(account.emailAddress);
        }

        return emailAddress;
    }

    public void signUp() {
        String name;
        String username;
        String password;
        String emailAddress;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hey , welcome to JReddit sign up page .");
        System.out.print("\tTo continue please enter your email address : ");

        do {
            emailAddress = scanner.nextLine();

            if (emailValidator(emailAddress)) {
                break;

            } else if (getAllEmails().contains(emailAddress)) {
                System.out.println("Sorry but the email address you entered is already in use </3 . \n\tTry something else : ");

            } else {
                System.out.print("Sorry but the email address you enter is not valid </3 . \n\tTry something else: ");
            }

        } while (true);

        System.out.print("Good , now please enter your username : ");

        do {
           username = scanner.nextLine();

           if (getAllUsernames().contains(username)) {
               System.out.print("Sorry but the username you enter is already in use </3 . \n\tTry something else : ");
           } else {
               break;
           }

        } while(true);

        System.out.print("Oh it's going well , please enter your password : ");
        password = scanner.nextLine();

        System.out.print("Just one more step .\n\tEnter your name : ");
        name = scanner.nextLine();

        Account account = new Account(name , username , password , emailAddress);
        accounts.add(account);
    }


    public static boolean authenticate() {
        String password;
        Scanner scanner = new Scanner(System.in);

        System.out.print("You want to log in with username or email address ? \n\t1) Username \t2) Email\n: ");

        do {
            int loginMethod = scanner.nextInt();

            if (loginMethod == 1 | loginMethod == 2) {
                switch (loginMethod) {
                    case 1 :
                        System.out.print("So please enter your username : ");
                        String username = scanner.nextLine();

                        System.out.print("Now enter your password : ");
                        password = scanner.nextLine();

                        for (Account account : accounts) {
                            if (Objects.equals(account.username , username)) {
                                if (Objects.equals(account.password , DigestUtils.sha256Hex(password))) {
                                    return true;
                                }
                            }
                        }

                        return false;

                    case 2 :
                        System.out.print("So please enter your email address : ");
                        String emailAddress;

                        do {
                            emailAddress = scanner.nextLine();

                            if (emailValidator(emailAddress)) {
                                break;
                            } else {
                                System.out.print("Sorry but the entered email address is not valid . \nTry again : ");
                            }

                        } while (true);

                        System.out.print("Now enter your password : ");
                        password = scanner.nextLine();

                        for (Account account : accounts) {
                            if (Objects.equals(account.emailAddress , emailAddress)) {
                                if (Objects.equals(account.password , DigestUtils.sha256Hex(password))) {
                                    return true;
                                }
                            }
                        }

                        return false;
                }

            } else {
                System.out.print("Please choose between 1 or 2 : ");
            }

        } while (true);
    }
}

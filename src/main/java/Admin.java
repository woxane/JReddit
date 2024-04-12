public class Admin extends Account{
    Admin(Account account) {
        super(account.name, account.username, account.password, account.emailAddress , account.accountID);
    }
}

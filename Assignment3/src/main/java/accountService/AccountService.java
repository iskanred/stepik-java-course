package accountService;

import dbService.DBService;

public class AccountService {

    private final DBService dbService;

    public AccountService() {
        this.dbService = new DBService();
    }

    /**
     * Adds user to the database
     * @return false if user was not added because they are already exist
     */
    public boolean addNewUser(String login, String password) {
        if (getUserAccountByLogin(login) != null) // the user with such a login is already exists
            return false;

        dbService.insertUserAccount(login, password);

        return true;
    }

    public UserAccount getUserAccountByLogin(String login) {
        return dbService.getUserAccountByLogin(login);
    }
}

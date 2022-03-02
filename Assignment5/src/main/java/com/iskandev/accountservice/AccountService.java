package com.iskandev.accountservice;

public class AccountService implements IAccountService {
    private int usersLimit;

    public AccountService(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
       this.usersLimit = usersLimit;
    }
}

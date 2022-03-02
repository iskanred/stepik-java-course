package com.iskandev.accountservice;

public class AccountServiceController implements AccountServiceControllerMBean {
    private final IAccountService accountService;

    public AccountServiceController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public int getUsersLimit() {
        return accountService.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        accountService.setUsersLimit(usersLimit);
    }
}

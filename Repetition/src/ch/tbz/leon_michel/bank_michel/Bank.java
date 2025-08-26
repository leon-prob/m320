package ch.tbz.leon_michel.bank_michel;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Account> accounts;

    public Bank (){
        accounts = new ArrayList<>();
    }

    public void createTransaction(String username, String recipientUsername, double amount){
        Account userAccount = getAccountByUsername(username);
        Account recipientAccount = getAccountByUsername(recipientUsername);
        if(userAccount == null || recipientAccount == null){
            return;
        }
        if(userAccount.getCredit() > amount){
            userAccount.setCredit(userAccount.getCredit() - amount);
            recipientAccount.setCredit(recipientAccount.getCredit() + amount);
        } else {
            System.out.println("Not enough money in account");
        }
    }

    public void createAccount(String username, double startCredit) {
        accounts.add(new Account(username, startCredit));
        System.out.println("Created Account with username: " + username + " and start credit of: " + startCredit + " CHF");
    }

    private Account getAccountByUsername(String username){
        Account userAccount = null;
        for(Account account : accounts) {
            if (account.getUsername().equals(username)) {
                userAccount = account;
            }
        }
        return userAccount;
    }

    public void withdrawMoney(String username, double amount) {
        Account userAccount = getAccountByUsername(username);
        if(userAccount == null){
            return;
        }
        if(userAccount.getCredit() > amount){
            userAccount.setCredit(userAccount.getCredit() - amount);
            System.out.println("Withdraw " + amount + " from account: " + username);
        } else {
            System.out.println("Not enough money in account");
        }
    }

    public void depositMoney(String username, double amount) {
        Account userAccount = getAccountByUsername(username);
        if(userAccount == null){
            return;
        }
        userAccount.setCredit(userAccount.getCredit() + amount);
        System.out.println("Deposit " + amount + " to account: " + username);
    }
}

package bank;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Account {

    public float getFunds() {
        return funds;
    }

    public void setFunds(float funds) {
        this.funds = funds;
    }
    public void deposit(float addFunds) {
        this.funds += addFunds;
    }

    private float funds = 0f;

    public final float accountNumber;

    public float getAccountNumber() {
        return accountNumber;
    }

    public Map<String, Boolean> getAccountFlags() {
        return accountFlags;
    }

    private Map<String, Boolean> accountFlags = new HashMap<>();

    public void setAccountFlags(Map<String, Boolean> accountFlags) {
        this.accountFlags = accountFlags;
    }

    public Map<User, Boolean> getOwnerMap() {
        return ownerMap;
    }

    public void setOwnerMap(Map<User, Boolean> ownerMap) {
        this.ownerMap = ownerMap;
    }

    private Map<User, Boolean> ownerMap;


    public void setAccountFlags(Map<String, Boolean> accountFlags, User user, Bank bank) throws NoSuchAlgorithmException {
        if (user.checkAdminCredentials(bank.getBankAdminList())) {
            this.accountFlags = accountFlags;
        }
    }


    public void setDefaultAccountFlags() {
        this.accountFlags.put("CanAutoWithdraw", false);
        this.accountFlags.put("CanChangePassword", true);
        this.accountFlags.put("IsDisabled", false);
        this.accountFlags.put("HasDependents", false);
        this.accountFlags.put("HasBeneficiaries", false);
        this.accountFlags.put("PlatinumMember", false);
        this.accountFlags.put("DiamondMember", false);
        this.accountFlags.put("OnHold", false);
        this.accountFlags.put("ShowSuspiciousActivityWarning", false);
        this.accountFlags.put("IsChecking", false);
    }

    public Account(float accountNumber) {
        this.accountNumber = accountNumber;

    }
}

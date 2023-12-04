package bank;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class User {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;
    private Customer appliedCustomer;
    private List<Account> accountList = new ArrayList<Account>();

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addToAccountList(Account account) {
        this.accountList.add(account);
    }

    protected SecurityMD5Hash hashInput = new SecurityMD5Hash();

    public Map<String, Boolean> getAccountFlags() {
        return accountFlags;
    }

    public void setAccountFlags(String flag, Boolean trueFalse) {
        if (this.accountFlags.containsKey(flag)) {
            this.accountFlags.put(flag, trueFalse);
        } else {
            System.out.println("No such flag exists>");
        }
    }

    private Map<String, Boolean> accountFlags = new HashMap<>();

    public void setDefaultAccountFlags() {
        this.accountFlags.put("CanChangePassword", true);
        this.accountFlags.put("IsDisabled", false);
        this.accountFlags.put("PlatinumMember", false);
        this.accountFlags.put("DiamondMember", false);
        this.accountFlags.put("ShowSuspiciousActivityWarning", false);
        this.accountFlags.put("IsAdmin", false);
    }

    public void addBankAdmin(Map<String, String> accountList, Scanner scanner, Bank bank) throws NoSuchAlgorithmException {
        if (this.checkAdminCredentials(bank.getBankAdminList())) {
            Map<String, String> adminList = bank.getBankAdminList();
            String username = promptAdminUser(scanner);
            String password = accountList.get(username);
            adminList.put(username, password);
            bank.setBankAdminList(adminList);
        }
    }

    public String promptAdminUser(Scanner scanner) throws NoSuchAlgorithmException {
        System.out.println("Performing this function requires administrative rights. Please enter an admin username:");
        String input = scanner.next();
        return hashInput.createMD5Hash(input);
    }

    public String promptAdminPassword(Scanner scanner) throws NoSuchAlgorithmException {
        System.out.println("Performing this function requires administrative rights. Please enter an admin password:");
        String input = scanner.next();
        return hashInput.createMD5Hash(input);
    }

    public boolean checkAdminCredentials(Map<String, String> adminList) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        String username = promptAdminUser(scanner);
        String password = promptAdminPassword(scanner);
        if (adminList.containsKey(username)) {
            if (adminList.get(username).equals(password)) {
                System.out.println(adminList.get(username));
                return true;

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

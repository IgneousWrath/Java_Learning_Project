package bank;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Bank {
    private SecurityMD5Hash hashInput = new SecurityMD5Hash();
    public final String bankName;
    public final String bankAddress;
    public final float bankRoutingNumber;
    private List<Customer> customerList = new ArrayList<Customer>();

    public List<User> getBankUserList() {
        return bankUserList;
    }

    private List<User> bankUserList = new ArrayList<User>();
    private List<Account> bankAccountList = new ArrayList<Account>();
    private float accountNumbers = 1000000;

    private Map<Customer, User> customerUserMap = new HashMap<>();

    public Map<Customer, User> getCustomerUserMap() {
        return customerUserMap;
    }

    private Map<String, String> bankAdminList = new HashMap<>();


    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Map<String, String> getBankAdminList() {
        return bankAdminList;
    }

    public void setBankAdminList(Map<String, String> bankAdminList) {
        this.bankAdminList = bankAdminList;
    }

    public void createCustomer(Scanner scanner) {
        String name = null;
        String address = null;
        String phone = null;
        int age = 0;
        boolean validEntry = false;
        while (!validEntry) {
            try {
                System.out.println("Enter your first and last name here:");
                String input = scanner.nextLine();
                name = input;
                System.out.println("Enter your address:");
                input = scanner.nextLine();
                address = input;
                System.out.println("Enter your phone number:");
                input = scanner.nextLine();
                phone = input;
                System.out.println("Enter your age:");
                input = scanner.nextLine();
                age = parseInt(input);
                validEntry = true;
            } catch (Exception e) {
                System.out.println("One of your entries was invalid. Try again.");
                validEntry = false;
            }

        }
        int x;
        int y;
        User placeholderUser = null;
        Customer placeholderCustomer = null;

        customerList.add(new Customer(name, address, phone, age));
        String username = createUser(scanner);
        for (User user : bankUserList) {
            if (user.getUsername().equals(username)) {
                placeholderUser = user;
            }

        }
        for (Customer customer : customerList) {
            if (customer.getCustomerName().equals(name)) {
                placeholderCustomer = customer;
            }

        }
        if (placeholderCustomer != null && placeholderUser != null) {
            customerUserMap.put(placeholderCustomer, placeholderUser);
            for (var entry : customerUserMap.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                System.out.println(entry.getKey().customerName);
                System.out.println(entry.getValue().getUsername());
                /* All code below is meant to see if the map users and customers are the same as the list ones
                if (entry.getKey().customerName.equals(name)) {
                    entry.getKey().setCustomerName("Fail");
                    System.out.println("Attempting to match with costumer name.");
                } */
            }
           /* for (Customer customer : customerList) {
                if (customer.getCustomerName().equals(name)) {
                    System.out.println("The customer in customer list was not changed.");
                } else if (customer.getCustomerName().equals("Fail")) {
                    System.out.println("Yet somehow the new name is here too.");
                }
            } */
        }
    }

    public String createUser(Scanner scanner) {
        String username = null;
        String password = null;
        String password2 = null;
        boolean validEntry = false;
        while (!validEntry) {
            try {
                System.out.println("Enter your username:");
                String input = scanner.nextLine();
                username = hashInput.createMD5Hash(input);
                System.out.println("Enter your password:");
                input = scanner.nextLine();
                password = hashInput.createMD5Hash(input);
                System.out.println("Re-enter your password:");
                input = scanner.nextLine();
                password2 = hashInput.createMD5Hash(input);
                String finalUsername = username;
                if (bankUserList.stream().anyMatch(o -> finalUsername.equals(o.getUsername()))) {
                    System.out.println("Username is not available. Try again.");
                    validEntry = false;
                } else if (!password.equals(password2)) {
                    System.out.println("Passwords did not match. Try Again.");
                    validEntry = false;
                } else {
                    validEntry = true;
                }
            } catch (Exception e) {
                System.out.println("One of your entries was invalid. Try again.");
                validEntry = false;
            }

        }

        bankUserList.add(new User(username, password));
        for (User user : bankUserList) {
            System.out.println(user.getUsername());
        }
        for (User user : bankUserList) {
            if (username.equals(user.getUsername())) {
                user.setDefaultAccountFlags();
                System.out.println("Default account flags set for " + user.getUsername());
                System.out.println(username);
            }
        }
        return username;

    }

    public float createAccount(Scanner scanner, User user, int initialDeposit) {
        String username = "";
        String password = "";
        boolean validEntry = false;
        if (user == null) {
            while (!validEntry) {
                try {
                    System.out.println("Enter your username:");
                    String input = scanner.nextLine();
                    username = hashInput.createMD5Hash(input);
                    for (User user2 : bankUserList) {
                        if (username.equals(user2.getUsername())) {
                            System.out.println("Enter your password:");
                            input = scanner.nextLine();
                            password = hashInput.createMD5Hash(input);
                            if (password.equals(user2.getPassword())) {
                                validEntry = true;
                                user = user2;
                            }
                        } else {
                            System.out.println("No user exists under that name.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("One of your entries was invalid. Try again.");
                    validEntry = false;
                }
            }

        } else {
            username = user.getUsername();
            password = user.getPassword();
        }
        float currentAccountNumber = accountNumbers;
        float accountNumber = currentAccountNumber + 1;
        if (initialDeposit < 150 && Objects.requireNonNull(user).getAccountList().isEmpty()) {
            System.out.println("Insufficient funds to open a new account");
        } else {

            bankAccountList.add(new Account(accountNumber));
            accountNumbers++;
            for (Account account : bankAccountList) {
                if (account.getAccountNumber() == accountNumber) {
                    System.out.println("Successfully created account number " + account.getAccountNumber());
                    account.setDefaultAccountFlags();
                    System.out.println("Set default account flags." + account.getAccountFlags());
                    for (User user2 : bankUserList) {
                        if (username.equals(user2.getUsername())) {
                            user2.addToAccountList(account);
                            Map<User, Boolean> firstOwner = new HashMap<>();
                            firstOwner.put(user2, true);
                            account.setOwnerMap(firstOwner);
                            System.out.println("User " + user2.getUsername() + "set as account owner.");
                        }
                    }
                }

            }
        }
        return accountNumber;

    }

    public void addBankAdmin(Scanner scanner) throws NoSuchAlgorithmException {
        if (this.checkAdminCredentials(this.getBankAdminList())) {
            System.out.println("Credentials accepted. ");
            System.out.println("Enter the username of the user you wish to grant administrative rights:");
            String input = scanner.nextLine();
            String username = hashInput.createMD5Hash(input);
            boolean containsName = false;
            for (User user : bankUserList) {
                if (username.equals(user.getUsername())) {
                    System.out.println("User " + input + " added to admin list.");
                    String password = user.getPassword();
                    this.bankAdminList.put(username, password);
                    user.setAccountFlags("IsAdmin", true);
                    containsName = true;
                    break;
                }
            }
            if (!containsName) {
                System.out.println("No user exists in this bank with that username.");
            }
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


    public Bank(String bankName, String bankAddress, float bankRoutingNumber) {
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.bankRoutingNumber = bankRoutingNumber;
    }

}

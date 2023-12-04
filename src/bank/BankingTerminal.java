package bank;

import java.security.NoSuchAlgorithmException;
import java.util.*;


import static java.lang.Integer.parseInt;

/* 1. Read account
2. Add money to account
3. Change a flag (checking account)
4. Add multiple owners to account
5. Disable and clear account with admin
6. Delete default admin account
*/
public class BankingTerminal {
    //Use this class to perform all banking functions.
    static List<Bank> bankList = new ArrayList<>();
    private static final SecurityMD5Hash hashInput = new SecurityMD5Hash();

    private static Bank selectedBank = null;
    private static int selectedBankNumber = 9999;
    private static User selectedUser = null;
    private static int selectedUserNumber = 9999;
    private static Customer selectedCustomer = null;
    private static int selectedCustomerNumber = 99999;

    public static void useBankingTerminal() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean promptLoop = true;
        createSampleBanks(bankList);
        int getBankCount = bankList.size();
        System.out.println("Welcome to the banking terminal.");
        if (getBankCount < 1) {
            System.out.println("You have no banks yet. Creating new bank now.");
            createBank(scanner, bankList);
        }
        getBankCount = bankList.size();
        System.out.println("There are " + getBankCount + " banks present.");
        while (promptLoop) {

            System.out.println("------------------------------");
            if (selectedBank != null) {
                System.out.println("Currently selected bank: " + selectedBank.bankName);
            }
            if (selectedCustomer != null) {
                System.out.println("Currently logged in customer: " + selectedCustomer.getCustomerName());
            }
            if (selectedBank != null && selectedCustomer == null) {
                System.out.println("------------------------------");
            }
            System.out.println("What would you like to do?");
            if (selectedBank == null) {
                System.out.println("A. Select your bank.");
            } else {
                System.out.println("A. Clear selected bank.");
            }
            if (selectedBank != null && selectedCustomer == null) {
                System.out.println("B. Log in.");
            } else if (selectedBank != null && selectedCustomer != null) {
                System.out.println("B. Log out.");
            }
            System.out.println("------------------------------");
            System.out.println("1. Add another bank.");
            System.out.println("2. Sign up.");
            System.out.println("3. Open account.");
            System.out.println("4. List banks.");
            System.out.println("5. Admin panel.");
            System.out.println("Type anything else to exit.");
            input = scanner.nextLine();
            switch (input) {
                case "A", "A.", "a", "a.":
                    if (selectedBank == null) {
                        selectBank(scanner);
                    } else {
                        clearBank();
                    }
                    break;
                case "B", "B.", "b", "b.":
                    if (selectedCustomer == null) {
                        login(scanner);
                    } else {
                        clearBank();
                    }
                    break;
                case "1", "1.":
                    System.out.println("Adding another bank.");
                    createBank(scanner, bankList);
                    break;
                case "2", "2.":
                    System.out.println("Signing up. You must be 16 or older to open an account.");
                    if (selectedBank == null) {
                        System.out.println("Enter your bank number:");
                        input = scanner.nextLine();
                    } else {
                        input = String.valueOf(selectedBankNumber);
                    }
                    try {
                        int bankNum = parseInt(input) - 1;
                        bankList.get(bankNum).createCustomer(scanner);
                    } catch (Exception e) {
                        System.out.println("Invalid bank number. Aborting.");
                        break;
                    }
                    break;
                case "3", "3.":
                    System.out.println("Opening new account. A minimum deposit of $150 is required.");
                    if (selectedBank == null) {
                        System.out.println("Enter your bank number:");
                        input = scanner.nextLine();
                    } else {
                        input = String.valueOf(selectedBankNumber);
                    }
                    try {
                        System.out.println("Enter your initial deposit amount:");
                        String input2 = scanner.nextLine();
                        int initialDeposit = parseInt(input2);
                        int bankNum = parseInt(input) - 1;
                        bankList.get(bankNum).createAccount(scanner, selectedUser, initialDeposit);
                    } catch (Exception e) {
                        System.out.println("Invalid bank number. Aborting.");
                        break;
                    }
                    break;
                case "4", "4.":
                    System.out.println("Listing all banks.");
                    getBankCount = bankList.size();
                    System.out.println("------------------------------");
                    System.out.println("Number of banks: " + getBankCount);
                    for (int i = 0; i < bankList.size(); i++) {
                        System.out.println("------------------------------");
                        System.out.println("Bank number: " + (i + 1));
                        System.out.println("Name: " + bankList.get(i).bankName);
                        System.out.println("Address: " + bankList.get(i).bankAddress);
                        System.out.println("Routing number: " + bankList.get(i).bankRoutingNumber);
                    }

                    break;
                case "5", "5.":
                    System.out.println("Attempting to enter admin mode.");
                    if (selectedBank != null) {
                        System.out.println("Currently selected bank: " + selectedBank.bankName);
                        if (selectedBank.checkAdminCredentials(selectedBank.getBankAdminList())) {
                            BankingAdminPage.useBankingTerminal2(bankList, selectedBank, selectedUser, selectedBankNumber);

                        } else {
                            System.out.println("Admin credentials not accepted for currently selected bank: " + selectedBank.bankName);
                            break;
                        }
                    } else {
                        System.out.println("You must select a bank with option A first.");
                        break;
                    }
                    break;
                default:
                    System.out.println("Closing terminal.");
                    promptLoop = false;
                    break;
            }
        }


    }

    public static void createBank(Scanner scanner, List<Bank> bankList) {
        String name = null;
        String address = null;
        float routingNumber = 0;
        boolean validEntry = false;
        while (!validEntry) {
            try {
                System.out.println("Enter your new bank Name:");
                String input = scanner.nextLine();
                name = input;
                System.out.println("Enter your new bank Address:");
                input = scanner.nextLine();
                address = input;
                System.out.println("Enter your new bank routing number:");
                input = scanner.nextLine();
                routingNumber = Float.parseFloat(input);
                validEntry = true;
            } catch (Exception e) {
                System.out.println("One of your entries was invalid. Try again.");
                validEntry = false;
            }

        }

        bankList.add(new Bank(name, address, routingNumber));
        for (Bank bank : bankList) {
            if (bank.bankRoutingNumber == routingNumber) {
                System.out.println("Create a default admin account. Do not forget to remove after assigning admins.");
                setBankFirstAdmin(bank, scanner);
            }

        }
    }

    public static void createSampleBanks(List<Bank> bankList) throws NoSuchAlgorithmException {
        String name = "Freedom Bank";
        String address = "1867 Murica St., Saint Mary IJ 78906";
        float routingNumber = 145674268f;
        bankList.add(new Bank(name, address, routingNumber));
        for (Bank bank : bankList) {
            if (bank.bankRoutingNumber == routingNumber) {
                Map<String, String> defaultAdminCreds = new HashMap<>();
                String username = hashInput.createMD5Hash("admin");
                String password = hashInput.createMD5Hash("admin");
                defaultAdminCreds.put(username, password);
                bank.setBankAdminList(defaultAdminCreds);
            }

        }
        name = "Lewis Bank";
        address = "1433 Sun Terrace, Titusville FL 32780";
        routingNumber = 900067543f;
        bankList.add(new Bank(name, address, routingNumber));
        for (Bank bank : bankList) {
            if (bank.bankRoutingNumber == routingNumber) {
                Map<String, String> defaultAdminCreds = new HashMap<>();
                String username = hashInput.createMD5Hash("admin");
                String password = hashInput.createMD5Hash("admin");
                defaultAdminCreds.put(username, password);
                bank.setBankAdminList(defaultAdminCreds);
            }

        }
        name = "Jamestown Credit Union";
        address = "1401 A St., Jamestown OP 30907";
        routingNumber = 900056742f;
        bankList.add(new Bank(name, address, routingNumber));
        for (Bank bank : bankList) {
            if (bank.bankRoutingNumber == routingNumber) {
                Map<String, String> defaultAdminCreds = new HashMap<>();
                String username = hashInput.createMD5Hash("admin");
                String password = hashInput.createMD5Hash("admin");
                defaultAdminCreds.put(username, password);
                bank.setBankAdminList(defaultAdminCreds);
            }

        }
    }

    public static void setBankFirstAdmin(Bank bank, Scanner scanner) {
        System.out.println("Seting up default admin account for " + bank.bankName);
        String username = "admin";
        String password = "admin";
        String password2 = "admin2";
        Map<String, String> defaultAdminCreds = new HashMap<>();
        boolean validEntry = false;
        while (!validEntry) {
            try {
                System.out.println("Enter default admin username:");
                String input = scanner.nextLine();
                username = hashInput.createMD5Hash(input);
                System.out.println("Enter default admin password:");
                input = scanner.nextLine();
                password = hashInput.createMD5Hash(input);
                System.out.println("Enter default admin password again:");
                input = scanner.nextLine();
                password2 = hashInput.createMD5Hash(input);
                if (password.equals(password2)) {
                    defaultAdminCreds.put(username, password);
                    validEntry = true;
                } else {
                    System.out.println("Passwords did not match. Try again.");
                }
            } catch (Exception e) {
                System.out.println("One of your entries was invalid. Try again.");
                validEntry = false;
            }

        }

        bank.setBankAdminList(defaultAdminCreds);
        System.out.println(bank.getBankAdminList());


    }

    public static void selectBank(Scanner scanner) {
        System.out.println("Listing all banks.");
        int getBankCount = bankList.size();
        System.out.println("------------------------------");
        System.out.println("Number of banks: " + getBankCount);
        for (int i = 0; i < bankList.size(); i++) {
            System.out.println("------------------------------");
            System.out.println("Bank number: " + (i + 1));
            System.out.println("Name: " + bankList.get(i).bankName);
            System.out.println("Address: " + bankList.get(i).bankAddress);
            System.out.println("Routing number: " + bankList.get(i).bankRoutingNumber);
        }
        System.out.println("Select a bank by number or by name.");
        String input = scanner.nextLine();
        for (int i = 0; i < bankList.size(); i++) {
            try {
                if (bankList.get(i).bankName.equals(input)) {
                    selectedBank = bankList.get(i);
                    selectedBankNumber = i + 1;
                    break;
                } else if (parseInt(input) == i + 1) {
                    selectedBank = bankList.get(i);
                    selectedBankNumber = i + 1;
                    break;
                }
            } catch (Exception e) {
            }

        }
    }

    public static void clearBank() {
        selectedBank = null;
        selectedCustomer = null;
        selectedUser = null;
        selectedBankNumber = 999999;
        selectedCustomerNumber = 999999;
        selectedUserNumber = 999999;
    }

    public static void login(Scanner scanner) throws NoSuchAlgorithmException {
        String username = null;
        String password = null;
        System.out.println("Listing all customers.");
        int getCustomerCount = bankList.get(selectedBankNumber - 1).getCustomerList().size();
        System.out.println("------------------------------");
        System.out.println("Number of customers: " + getCustomerCount);
        for (int i = 0; i < bankList.size(); i++) {
            System.out.println("------------------------------");
            System.out.println("Bank number: " + (i + 1));
            System.out.println("Name: " + bankList.get(i).bankName);
            System.out.println("Address: " + bankList.get(i).bankAddress);
            System.out.println("Routing number: " + bankList.get(i).bankRoutingNumber);
        }
        System.out.println("Enter your username:");
        String input = scanner.nextLine();
        username = hashInput.createMD5Hash(input);
        System.out.println("Enter your password:");
        input = scanner.nextLine();
        password = hashInput.createMD5Hash(input);

        for (var entry : bankList.get(selectedBankNumber - 1).getCustomerUserMap().entrySet()) {
            if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password)) {
                selectedCustomer = entry.getKey();
                selectedUser = entry.getValue();

            }
        }
        for (int i = 0; i < bankList.get(selectedBankNumber - 1).getCustomerList().size(); i++) {
            if (bankList.get(selectedBankNumber - 1).getCustomerList().get(i) == selectedCustomer) {
                selectedCustomerNumber = i + 1;
            }

        }
    }

}



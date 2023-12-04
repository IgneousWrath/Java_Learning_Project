package bank;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class BankingAdminPage {
    public static void useBankingTerminal2(List<Bank> bankList, Bank selectedBank, User selectedUser, int selectedBankNumber) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean promptLoop = true;
        int getBankCount = bankList.size();
        System.out.println("Welcome to the banking terminal.");
        if (getBankCount < 1) {
            System.out.println("You have no banks yet. Creating new bank now.");
            BankingTerminal.createBank(scanner, bankList);
        }
        getBankCount = bankList.size();
        System.out.println("There are " + getBankCount + " banks present.");
        while (promptLoop) {

            System.out.println("------------------------------");
            System.out.println("Admin Functions");
            System.out.println("------------------------------");
            System.out.println("1. Add another bank.");
            System.out.println("2. Add customer to bank.");
            System.out.println("3. Add an account to customer.");
            System.out.println("4. List all banks.");
            System.out.println("5. List all customers.");
            System.out.println("6. Change account or user flags.");
            System.out.println("7. Add user to bank admin list.");
            System.out.println("Type anything else to exit admin mode.");
            input = scanner.nextLine();
            switch (input) {
                case "1", "1.":
                    System.out.println("Adding another bank.");
                    BankingTerminal.createBank(scanner, bankList);
                    break;
                case "2", "2.":
                    System.out.println("Adding customer.");
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
                    System.out.println("Adding bank account to customer.");
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
                    System.out.println("Listing all customers.");
                    getBankCount = bankList.size();
                    System.out.println("------------------------------");
                    System.out.println("Number of banks: " + getBankCount);
                    for (int i = 0; i < bankList.size(); i++) {
                        System.out.println("------------------------------");
                        System.out.println("Bank number: " + (i + 1));
                        System.out.println("Name: " + bankList.get(i).bankName);
                        for (int x = 0; x < bankList.get(i).getCustomerList().size(); x++) {
                            System.out.println("------------------------------");
                            System.out.println("Customer number " + (x + 1));
                            System.out.println("Name: " + bankList.get(i).getCustomerList().get(x).customerName);
                            System.out.println("Address: " + bankList.get(i).getCustomerList().get(x).customerAddress);
                            System.out.println("Phone: " + bankList.get(i).getCustomerList().get(x).customerPhone);
                            System.out.println("Age: " + bankList.get(i).getCustomerList().get(x).customerAge);
                        }
                    }
                    break;
                case "6", "6.":
                    System.out.println("Adding user account.");
                    System.out.println("Enter your bank number:");
                    input = scanner.nextLine();
                    try {
                        int bankNum = parseInt(input) - 1;
                        bankList.get(bankNum).createUser(scanner);
                    } catch (Exception e) {
                        System.out.println("Invalid bank number. Aborting.");
                        break;
                    }
                    break;
                case "7", "7.":
                    System.out.println("Adding user to admin list.");
                    System.out.println("Enter your bank number:");
                    input = scanner.nextLine();
                    try {
                        int bankNum = parseInt(input) - 1;
                        bankList.get(bankNum).addBankAdmin(scanner);
                    } catch (Exception e) {
                        System.out.println("Invalid bank number. Aborting.");
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
}

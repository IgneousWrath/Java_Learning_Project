package bank;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Customer{
    protected String customerName;
    protected final String customerAddress;
    protected final String customerPhone;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    protected final int customerAge;


    public Customer(String customerName, String customerAddress, String customerPhone, int customerAge) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerAge = customerAge;
    }


}

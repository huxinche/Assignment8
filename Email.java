package emailapp;

import java.util.Random;
import java.util.Scanner;

public class Email {

    private int mailboxCapacity = 500;
    private int defaultPasswordLength = 10;
    private String companySuffix = "aeycompany.com";
    private String firstName;
    private String lastName;
    private Scanner scanner;
    private String department;
    private String email;
    private String password;
    private String alternateEmail;
    private final String TEMP_PASS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
    private Random random;

    // Constructor to receive the first name and last name
    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        random = new Random();
        scanner = new Scanner(System.in);
        this.SetDepartment();
        this.password = this.randomPassword(defaultPasswordLength);
        this.email = String.format("%s.%s@%s.%s", this.firstName, this.lastName, this.department, this.companySuffix);

    }

    public void setMailboxCapacity(int mailboxCapacity) {
        this.mailboxCapacity = mailboxCapacity;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    // Ask for the department
    private String SetDepartment() {
        System.out.println(String.format("New Worker: %s %s. Department Codes:\n" +
                "1 for Sales\n" +
                "2 for Development\n" +
                "3 for Accounting\n" +
                "0 for none\n" +
                "Enter department code: \n", this.firstName, this.lastName));
        String code = scanner.nextLine();
        while (true) {
            switch (code) {
                case "1":
                    this.department = "Sales";
                    return "Sales";
                case "2":
                    this.department = "Development";
                    return "Development";
                case "3":
                    this.department = "Accounting";
                    return "Accounting";
                case "0":
                    this.department = "none";
                    return "none";
                default:
                    System.out.println("Please input from 0, 1, 2, 3! ");
                    code = scanner.nextLine();
            }

        }

    }

    // Generate a random password
    private String randomPassword(int length) {

        String password = "";
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(41);
            password += TEMP_PASS.charAt(idx);

        }
        return password;
    }

    public String showInfo() {
        return String.format("DISPLAY NAME: %s %s\n" +
                "DEPARTMENT: %s\n" +
                "COMPANY EMAIL: %s\n" +
                "PASSWORD: %s\n" +
                "MAILBOX CAPACITY: %smb", this.firstName, this.lastName, this.department, this.email, this.password, mailboxCapacity);
    }


}

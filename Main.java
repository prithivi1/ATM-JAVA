package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Account> account_details = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("MARI_AATHA INDIAN BANK");

            System.out.println("1.LOGIN");
            System.out.println("2.REGISTER ACCOUNT");
            int choice = sc.nextInt();
            if(choice==1)
            {
                login();
            }else{
                register();
            }
        }
    }

    public static void login()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER YOUR ACCOUNT NUMBER : ");
        String accountNumber = sc.nextLine();
        System.out.println("ENTER YOUR PIN NUMBER : ");
        int pin = sc.nextInt();

        Account obj = findAccount(accountNumber,pin);

        openAccount(obj);
    }

    public static void openAccount(Account obj)
    {
        Scanner sc = new Scanner(System.in);

        if(obj!=null)
        {
            System.out.println("**** LOGIN SUCCESS ****");

            while (true)
            {
                System.out.println("HELLO "+obj.getName());

                System.out.println("CHOOSE YOUR OPTION");
                System.out.println("1.WITHDRAW");
                System.out.println("2.DEPOSIT");
                System.out.println("3.CHECK BALANCE");
                System.out.println("4.EXIT");

                System.out.println("ENTER YOUR CHOICE");
                int n = sc.nextInt();
                switch (n)
                {
                    case 1 :
                        withDraw(obj);
                        break;
                    case 2:
                        deposit(obj);
                        break;
                    case 3:
                        checkBalance(obj);
                        break;
                    case 4:
                        System.out.println("THANK YOU");
                        return;
                    default:
                        System.out.println("ENTER PROPER NUMBER");
                }
            }
        }
        else {
            System.out.println("**** INVALID CRENDTIALS ****");
        }
    }

    public static void register()
    {
            Scanner sc = new Scanner(System.in);
            System.out.println("ENTER NAME");
            String name = sc.nextLine();
            System.out.println("ENTER ACCOUNT NUMBER : ");
            String accountNumber = sc.nextLine();
            int pin = 0,confirmPin=0;
            while (true) {
                System.out.println("ENTER PIN");
                pin = sc.nextInt();
                System.out.println("CONFIRM PIN");
                confirmPin = sc.nextInt();
                if(pin==confirmPin)
                {
                    break;
                }else {
                    System.out.println("PIN MISMATCH");
                }
            }
            System.out.println("ENTER MINIMUM BALANCE TO BE ADDED : ");
            double amount = sc.nextDouble();

            Account ob = new Account(name,accountNumber,pin,amount);
            account_details.add(ob);

        System.out.println("ACCOUNT REGISTER SUCCESSFUL");
        openAccount(ob);
    }

    public static Account findAccount(String accountNumber,int pin)
    {
        for(int i=0;i<account_details.size();i++)
        {
            if(account_details.get(i).getAccountNumber().equals(accountNumber) && account_details.get(i).getPin()==pin)
            {
                return account_details.get(i);
            }
        }
        return null;
    }

    public static void withDraw(Account obj)
    {
        Scanner sc = new Scanner(System.in);
       while (true) {
           System.out.println("ENTER AMOUNT TO WITHDRAW");
           int amount = sc.nextInt();

           if (amount % 100 == 0) {

               if(obj.getBalance()>=amount)
               {
                   obj.setBalance(obj.getBalance()-amount);
                   System.out.println("PLEASE, COLECT YOUR CASH!");
                   System.out.println("Rs."+amount+" HAS BEEN DEBITED SUCCESSFULLY");
                   System.out.println("BALANCE AMOUNT : "+obj.getBalance());
                   return;
               }else {
                   System.out.println("INSUFFICIENT FUND");
               }
           } else {
               System.out.println("WITHDRAW AMOUNT SHOULD BE MULTIPLES OF 100,500,2000");
           }
       }
    }

    public static void deposit(Account ob)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER AMOUNT TO DEPOSIT");
        double amount = sc.nextDouble();

        ob.setBalance(ob.getBalance()+amount);

        System.out.println(amount+" has been deposited");
        System.out.println("BALANCE : "+ob.getBalance());
    }

    public static void checkBalance(Account ob)
    {
        System.out.println("AVAILABLE BALANCE : "+ob.getBalance());
    }
}

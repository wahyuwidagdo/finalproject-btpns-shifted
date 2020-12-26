package com.edowidagdo.finalproject.bank;

import com.edowidagdo.finalproject.bank.rabbitmq.DatabaseReceive;

public class BankMain {
    public static DatabaseReceive receive = new DatabaseReceive();

    public static void main (String[] args) {
        try {
            System.out.println("[x] Waiting for message . . . .");
            receive.addNasabah();
            receive.loginNasabah();
            receive.logoutNasabah();
            receive.checkSaldo();
        } catch (Exception e) {
            System.out.println("Error BankMain = " + e);
        }
    }
}

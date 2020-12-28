package com.edowidagdo.finalproject.bank.rabbitmq;

import com.edowidagdo.finalproject.bank.model.Nasabah;
import com.edowidagdo.finalproject.bank.service.Services;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class DatabaseReceive {
    DatabaseSend send = new DatabaseSend();
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private Services services = new Services();

    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void addNasabah() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("addNasabah", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + mhsString + "'");
                services.register(mhsString);
            };
            channel.basicConsume("addNasabah", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Add Nasabah = " + e);
        }
    }

    public void loginNasabah() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("loginNasabah", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + mhsString + "'");
                services.loginNasabah(mhsString);
//                if (res!=0){
//                    Nasabah nasabah  = new Nasabah();
//                    nasabah.setId(res);
//                    System.out.println("Cek Update Status");
//                    services.updateStatusLogin(nasabah,"true");
//                }
                try {
                    send.sendToRestApi("Login sukses");
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("loginNasabah", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Login Nasabah = " + e);
        }
    }

    public void logoutNasabah() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("logoutNasabah", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + mhsString + "'");
                int res =services.logoutNasabah(mhsString);
                if (res!=0){
                    Nasabah nasabah  = new Nasabah();
                    nasabah.setId(res);
                    System.out.println("Cek Update Status");
                    services.updateStatusLogin(nasabah,"false");
                }
                try {
                    send.sendToRestApi(String.valueOf(res));
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("logoutNasabah", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Login Nasabah = " + e);
        }
    }

    public void checkSaldo() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("checkSaldo", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mhsString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("[x] Receive '" + mhsString + "'");
                int res = services.checkNasabah(mhsString);
                String nilaiSaldo = "";
                if (res != 0) {
                    Nasabah nasabah = new Nasabah();
                    nasabah.setId(res);
                    System.out.println("Cek Saldo Status : " + res);
                    int saldoTotal = services.checkSaldo(nasabah);
                    System.out.println("Isi saldo total : " + saldoTotal);
                    nilaiSaldo = String.valueOf(saldoTotal);
                } else {
                    System.out.println("Test123");
                    nilaiSaldo = "0";
                }
                try {
                    send.sendToRestApi(nilaiSaldo);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("checkSaldo", true, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            System.out.println("Error Check Saldo = " + e);
        }
    }

    public void getTotalTagihan() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("getTotalTagihan", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String nbString =new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("[x] Receive from nasabah '" + nbString + "'");
                try {
                    send.sendToPlnServer(nbString);
                } catch (Exception e) {
                    System.out.println("Error send transfer : " + e);
                }
            };
            channel.basicConsume("getTotalTagihan", true, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            System.out.println("Error Registrasi Nasabah : " + e);
        }
    }

    public void getTagihanPlnServer() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("sendTagihanToPln", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String nbString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("[x] Receive from Pln '" + nbString + "'");
                try {
                    send.sendToApiReceive(nbString);
                } catch (Exception e) {
                    System.out.println("Error send transfer : " + e);
                }
            };
            channel.basicConsume("sendTagihanToPln", true, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            System.out.println("Error Registrasi Nasabah : " + e);
        }
    }

}

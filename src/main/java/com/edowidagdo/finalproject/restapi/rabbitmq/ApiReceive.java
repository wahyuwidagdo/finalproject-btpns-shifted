package com.edowidagdo.finalproject.restapi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ApiReceive {
    protected String message;
    private String saldoResponse;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSaldoResponse() {
        return saldoResponse;
    }

    public void setSaldoResponse(String saldoResponse) {
        this.saldoResponse = saldoResponse;
    }

    public String receiveFromDatabase() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("messageFromDatabase 1", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            this.message = message;
        };
        channel.basicConsume("messageFromDatabase 1", true, deliverCallback, consumerTag -> {
        });

        return this.message;
    }

    public String loginApiRes() throws IOException, TimeoutException {
        String loginResponse = "";
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

        channel.queueDeclare("messageFromDatabase", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                this.message = message;
            };
            channel.basicConsume("messageFromDatabase", true, deliverCallback, consumerTag -> {
            });
            TimeUnit.SECONDS.sleep(5);
            if (!this.message.equals("0")) {
                JSONObject object = new JSONObject();
                object.put("response", 200);
                object.put("status", "Success");
                object.put("message", "Success Login");
                loginResponse = object.toJSONString();
            } else {
                JSONObject object = new JSONObject();
                object.put("response", 400);
                object.put("status", "Error");
                object.put("message", "Error Login");
                loginResponse = object.toJSONString();
            }
        } catch (Exception e) {
            System.out.println("Exception login Res: " + e);
        }

        return loginResponse;
    }

    public String logoutApiRes() throws IOException, TimeoutException {
        String loginResponse = "";
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();


            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                this.message = message;
            };
            channel.basicConsume("messageFromDatabase", true, deliverCallback, consumerTag -> {
            });
            TimeUnit.SECONDS.sleep(5);
            if (!this.message.equals("0")) {
                JSONObject object = new JSONObject();
                object.put("response", 200);
                object.put("status", "Success");
                object.put("message", "Success Logout");
                loginResponse = object.toJSONString();
            } else {
                JSONObject object = new JSONObject();
                object.put("response", 400);
                object.put("status", "Error");
                object.put("message", "Error Login");
                loginResponse = object.toJSONString();
            }
        } catch (Exception e) {
            System.out.println("Exception login Res: " + e);
            e.printStackTrace();
        }

        return loginResponse;
    }

    public String checkSaldo() throws IOException, TimeoutException {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("[x] Received '" + message + "'");
                this.message = message;
            };
            channel.basicConsume("messageFromDatabase", true, deliverCallback, consumerTag -> {});
            TimeUnit.SECONDS.sleep(5);
            if (!this.message.equals("0")) {
                JSONObject object = new JSONObject();
                object.put("response", 200);
                object.put("status", "Success");
                object.put("message", "Success Check Saldo");
                object.put("Saldo", this.message);
                saldoResponse = object.toJSONString();
            } else {
                JSONObject object = new JSONObject();
                object.put("response", 400);
                object.put("status", "Error");
                object.put("message", "Anda Tidak Memiliki Akses untuk cek saldo, Mohon Login Terlebih Dahulu");
                saldoResponse = object.toJSONString();
            }
        } catch (Exception e) {
            System.out.println("Exception Login Res : " + e);
        }
        System.out.println("Isi Saldo Response : " + this.getSaldoResponse());
        return this.getSaldoResponse();
    }

}

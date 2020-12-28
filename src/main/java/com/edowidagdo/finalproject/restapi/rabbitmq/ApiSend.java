package com.edowidagdo.finalproject.restapi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ApiSend {

    public static void addNasabah(String nasabah) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("addNasabah", false, false, false, null);
            channel.basicPublish("", "addNasabah", null, nasabah.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + nasabah + "'");
        }
    }

    public static void login(String nasabah) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("loginNasabah", false, false, false, null);
            channel.basicPublish("", "loginNasabah", null, nasabah.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + nasabah + "'");
        }
    }

    public static void logout(String nasabah) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("logoutNasabah", false, false, false, null);
            channel.basicPublish("", "logoutNasabah", null, nasabah.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + nasabah + "'");
        }
    }

    public static void checkSaldo(String nasabah) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            channel.queueDeclare("checkSaldo", false, false, false, null);
            channel.basicPublish("", "checkSaldo", null, nasabah.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent '" + nasabah + "'");
        }
    }

    public static void getTotalTagihan(String tagihan) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            channel.queueDeclare("getTotalTagihan", false, false, false, null);
            channel.basicPublish("", "getTotalTagihan", null, tagihan.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Send Id Pelanggan : '" + tagihan + "'");
        }
    }

}

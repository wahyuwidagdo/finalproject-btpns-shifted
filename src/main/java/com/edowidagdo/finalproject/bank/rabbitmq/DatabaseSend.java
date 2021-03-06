package com.edowidagdo.finalproject.bank.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class DatabaseSend {
    public void sendToRestApi(String  message) throws IOException, TimeoutException {
        System.out.println(message);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("messageFromDatabase", false, false, false, null);
            channel.basicPublish("", "messageFromDatabase", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println("Gagal mengirim pesan ke API. . . " + e);
        }
    }

    public void sendToRestApiLogin(String message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            channel.queueDeclare("messageFromDatabase", false, false, false, null);
            channel.basicPublish("", "messageFromDatabase", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println("Gagal mengirim pesan ke API. . ." + e);
        }
    }

    public void sendToPlnServer(String message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            channel.queueDeclare("sendToPln", false, false, false, null);
            channel.basicPublish("", "sendToPln", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println("Gagal mengirim data transfer ke Dummy Server : (" + e);
        }
    }

    public void sendToApiReceive(String message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            channel.queueDeclare("sendToApiReceive", false, false, false, null);
            channel.basicPublish("", "sendToApiReceive", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println("Gagal mengirim data transfer ke Dummy Server : (" + e);
        }
    }

}

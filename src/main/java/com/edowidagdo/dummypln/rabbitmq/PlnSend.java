package com.edowidagdo.dummypln.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PlnSend {
    public void sendTagihanPlnServer(String tagihan) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("sendTagihanToPln", false, false, false, null);
            channel.basicPublish("", "sendTagihanToPln", null, tagihan.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Send Tagihan ke Pln : '" + tagihan + "'");
        }
    }
}

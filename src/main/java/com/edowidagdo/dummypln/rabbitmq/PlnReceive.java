package com.edowidagdo.dummypln.rabbitmq;

import com.edowidagdo.dummypln.model.Pln;
import com.edowidagdo.dummypln.service.PlnDAO;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PlnReceive {
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private EntityManager entityManager;
    private PlnDAO plnDAO;

    @Autowired
    private PlnSend plnSend = new PlnSend();
    private Object tagihan;

    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void connectJPAPln() {
        this.entityManager = Persistence
                .createEntityManagerFactory("nasabah-unit")
                .createEntityManager();
        plnDAO = new PlnDAO(entityManager);
        try {
            entityManager.getTransaction().begin();
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void commitJPA() {
        try {
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void getTagihanPln() {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("sendToPln", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String rekening = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received Cek Saldo Dummy: '" + rekening + "'");
                connectJPAPln();
//                int i = Integer.parseInt(rekening);
                try {
                    Pln dumdum = plnDAO.getTagihan(rekening);
                    String tagihan =new Gson().toJson(dumdum);
                    System.out.println(tagihan);
                    plnSend.sendTagihanPlnServer(tagihan);
                } catch (Exception e) {
                    System.out.println("Error send Check Saldo Dummy = " + e);
                }
                commitJPA();
            };
            channel.basicConsume("sendToPln", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error checkSaldoDummy= " + e);
        }
    }

}

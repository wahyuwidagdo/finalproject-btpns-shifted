package com.edowidagdo.finalproject.bank.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tagihan")
public class Tagihan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pelanggan")
    private int id_pelanggan;
    @Column(name = "nama_pelanggan")
    private String nama_pelanggan;

    @CreationTimestamp
    @Column(name = "transaction_date", insertable = false, updatable = false, nullable = false)
    private Date transaction_date;

    public Tagihan() {
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }
}

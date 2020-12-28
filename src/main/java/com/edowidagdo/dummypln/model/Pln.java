package com.edowidagdo.dummypln.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pln")
public class Pln {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "id_pelanggan")
    private int id_pelanggan;

    @Column(name = "nama_pelanggan")
    private String nama_pelanggan;

    @Column(name = "total_tagihan")
    private int total_tagihan;

    @Column(name = "status_pembayaran")
    private String status_pembayaran;

    @CreationTimestamp
    @Column(name = "transaction_date", insertable = false, updatable = false, nullable = false)
    private Date transaction_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getTotal_tagihan() {
        return total_tagihan;
    }

    public void setTotal_tagihan(int total_tagihan) {
        this.total_tagihan = total_tagihan;
    }

    public String getStatus_pembayaran() {
        return status_pembayaran;
    }

    public void setStatus_pembayaran(String status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }
}

package com.edowidagdo.dummypln.model;

import javax.persistence.*;

@Entity
@Table(name = "pln")
public class Pln {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "idPelanggan")
    private int idPelanggan;

    @Column(name = "namaPelanggan")
    private String namaPelanggan;

    @Column(name = "totalTagihan")
    private int totalTagihan;

    @Column(name = "statusPembayaran")
    private String statusPembayaran;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public int getTotalTagihan() {
        return totalTagihan;
    }

    public void setTotalTagihan(int totalTagihan) {
        this.totalTagihan = totalTagihan;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }
}

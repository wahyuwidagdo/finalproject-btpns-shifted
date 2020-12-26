package com.edowidagdo.finalproject.bank.service;

import com.edowidagdo.finalproject.bank.model.Login;
import com.edowidagdo.finalproject.bank.model.Nasabah;
import com.edowidagdo.finalproject.bank.model.Register;
import com.edowidagdo.finalproject.bank.repositories.NasabahDao;
import com.google.gson.Gson;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Services {
    private NasabahDao nasabahDao;
    private EntityManager entityManagerNasabah;

    public void connectJPANasabah(){
        this.entityManagerNasabah = Persistence
                .createEntityManagerFactory("ibanking-unit")
                .createEntityManager();
        nasabahDao = new NasabahDao(entityManagerNasabah);
        try {
            entityManagerNasabah.getTransaction().begin();
        } catch (IllegalStateException e) {
            entityManagerNasabah.getTransaction().rollback();
        }
    }

    public void commitJPA(EntityManager entity){
        try {
            entity.getTransaction().commit();
            entity.close();
        } catch (IllegalStateException e) {
            entity.getTransaction().rollback();
        }
    }

    public void register(String register) {
        Register regis = new Gson().fromJson(register, Register.class);

        Nasabah nasabah = new Nasabah();
        nasabah.setUsername(regis.getUsername());
        nasabah.setPassword(regis.getPassword());
        nasabah.setNamaLengkap(regis.getNamaLengkap());
        nasabah.setEmail(regis.getEmail());
        nasabah.setNohp(regis.getNohp());
        nasabah.setSaldo(5000000);
        nasabah.setIsLogin("false");
        connectJPANasabah();
        int nasabahId = nasabahDao.addNasabah(nasabah);
        commitJPA(entityManagerNasabah);
    }

    public void loginNasabah(String dataNasabah){
        Login login = new Gson().fromJson(dataNasabah, Login.class);

//        Nasabah nasabah = new Gson().fromJson(dataNasabah, Nasabah.class);
//        Nasabah nasabah = new Nasabah();
//        nasabah.setIsLogin("false");
        connectJPANasabah();
        nasabahDao.login(login);
        commitJPA(entityManagerNasabah);
        System.out.println("Cek: ");
//        return cek;
    }

    public int logoutNasabah(String dataNasabah){
        connectJPANasabah();
        Nasabah nasabah = new Gson().fromJson(dataNasabah, Nasabah.class);
        int cek =nasabahDao.checkNasabah(nasabah);
        commitJPA(entityManagerNasabah);
        System.out.println("Cek: "+cek);
        return cek;
    }

    public void updateStatusLogin(Nasabah nasabah,String status){
        connectJPANasabah();
        nasabahDao.updateStatusLogin(nasabah,status);
        commitJPA(entityManagerNasabah);
    }

    public int checkNasabah(String dataNasabah) {
        connectJPANasabah();
        int check = nasabahDao.checkSaldoNasabah(dataNasabah);
        commitJPA(entityManagerNasabah);
        System.out.println("Cek : " + check);
        return check;
    }

    public int checkSaldo(Nasabah nasabah) {
        connectJPANasabah();
        int saldo = nasabahDao.checkSaldoId(nasabah);
        System.out.println("Method Check Saldo : " + saldo);
        commitJPA(entityManagerNasabah);
        return saldo;
    }

}

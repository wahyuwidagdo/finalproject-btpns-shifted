package com.edowidagdo.finalproject.bank.repositories;

import com.edowidagdo.finalproject.bank.model.Login;
import com.edowidagdo.finalproject.bank.model.Nasabah;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class NasabahDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private Nasabah nsb;

    public NasabahDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public int addNasabah(Nasabah nasabah) {
        entityManager.persist(nasabah);
        return nasabah.getId();
    }

    public void login(Login nasabah) {
        String select = "SELECT a FROM Nasabah a WHERE username='"+nasabah.getUsername() +"' and password='"+ nasabah.getPassword()+"'";
        nsb = entityManager.createQuery(select, Nasabah.class).getSingleResult();
        if (nsb.getUsername().equals(nasabah.getUsername()) && nsb.getPassword().equals(nasabah.getPassword())){
            Nasabah nasabah1 = entityManager.find(Nasabah.class, nsb.getId());
            nasabah1.setIsLogin("true");
            entityManager.merge(nasabah1);
        } else {
            System.out.println("Gagal login..!!!");
        }
    }

    public int updateStatusLogin(Nasabah nasabah,String status){
        Nasabah nasabah1 = entityManager.find(Nasabah.class, nasabah.getId());
        nasabah1.setIsLogin(status);
        entityManager.merge(nasabah1);
//        System.out.println("Status Login: ");
        return 1;
    }

    public int checkNasabah(Nasabah nasabah){
        String select = "SELECT id FROM Nasabah WHERE username=:username";
        Query query = entityManager.createQuery(select);
        query.setParameter("username", nasabah.getUsername());
        if (query.getResultList().size()!=0){
            System.out.println("masuk 12");
            return (int)query.getResultList().get(0);
        } else {
            System.out.println("masuk 22");
            return query.getResultList().size();
        }
    }

    public int checkSaldoNasabah(String username) {
        String check = "SELECT id FROM Nasabah WHERE username=:username AND isLogin=:isLogin";
        Query query = entityManager.createQuery(check);
        query.setParameter("username", username);
        query.setParameter("isLogin", "true");
        if (query.getResultList().size() != 0) {
            System.out.println("Masuk 12");
            return (int)query.getResultList().get(0);
        } else {
            System.out.println("Masuk 22");
            return query.getResultList().size();
        }
    }

    public int checkSaldoId(Nasabah nasabah) {
        String check = "SELECT saldo FROM Nasabah WHERE id=:id";
        Query query = entityManager.createQuery(check);
        query.setParameter("id", nasabah.getId());
        if (query.getResultList().size() != 0 ) {
            System.out.println("Masuk Cek Saldo 12");
            return (int)query.getResultList().get(0);
        } else {
            System.out.println("Masuk Cek Saldo 22");
            return query.getResultList().size();
        }
    }
}

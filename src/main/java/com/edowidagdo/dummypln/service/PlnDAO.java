package com.edowidagdo.dummypln.service;

import com.edowidagdo.dummypln.model.Pln;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PlnDAO {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public PlnDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public void setIdPelanggan(String nbStr) {

    }

//    public int getIdPelanggan(String mhsString){
//        String select = "SELECT p.totalTagihan FROM Pln p WHERE p.idPelanggan=:id_pelanggan";
//        Query query = entityManager.createQuery(select);
//        query.setParameter("id_pelanggan", mhsString);
//        if (query.getResultList().size()!=0){
//            System.out.println("masuk 12");
//            return (int)query.getResultList().get(0);
//        } else {
//            System.out.println("masuk 22");
//            return query.getResultList().size();
//        }
//    }

    public Pln getTagihan(String userString) {
        Pln mhs;
        try {
            mhs = entityManager.createQuery("SELECT a FROM Pln a where a.id_pelanggan ='"+userString+"'", Pln.class).getSingleResult();
        } catch (NoResultException e){
            System.out.println("no Result");
            mhs = null;

        }
        return mhs;
    }

}

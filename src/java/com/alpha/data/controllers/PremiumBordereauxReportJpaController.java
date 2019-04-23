/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.controllers.exceptions.PreexistingEntityException;
import com.alpha.data.entities.PremiumBordereauxReport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Tsotsoh
 */
public class PremiumBordereauxReportJpaController implements Serializable {

    public PremiumBordereauxReportJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PremiumBordereauxReport premiumBordereauxReport) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(premiumBordereauxReport);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPremiumBordereauxReport(premiumBordereauxReport.getPolicyNumber()) != null) {
                throw new PreexistingEntityException("PremiumBordereauxReport " + premiumBordereauxReport + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PremiumBordereauxReport premiumBordereauxReport) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            premiumBordereauxReport = em.merge(premiumBordereauxReport);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = premiumBordereauxReport.getPolicyNumber();
                if (findPremiumBordereauxReport(id) == null) {
                    throw new NonexistentEntityException("The premiumBordereauxReport with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PremiumBordereauxReport premiumBordereauxReport;
            try {
                premiumBordereauxReport = em.getReference(PremiumBordereauxReport.class, id);
                premiumBordereauxReport.getPolicyNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The premiumBordereauxReport with id " + id + " no longer exists.", enfe);
            }
            em.remove(premiumBordereauxReport);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PremiumBordereauxReport> findPremiumBordereauxReportEntities() {
        return findPremiumBordereauxReportEntities(true, -1, -1);
    }

    public List<PremiumBordereauxReport> findPremiumBordereauxReportEntities(int maxResults, int firstResult) {
        return findPremiumBordereauxReportEntities(false, maxResults, firstResult);
    }

    private List<PremiumBordereauxReport> findPremiumBordereauxReportEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PremiumBordereauxReport.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PremiumBordereauxReport findPremiumBordereauxReport(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PremiumBordereauxReport.class, id);
        } finally {
            em.close();
        }
    }

    public int getPremiumBordereauxReportCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PremiumBordereauxReport> rt = cq.from(PremiumBordereauxReport.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void batchCreate(List<PremiumBordereauxReport> list) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        int batchSize = 25;
        try {
            em = getEntityManager();
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            for (int i = 0; i < list.size(); i++) {
                if (i > 0 && i % batchSize == 0) {
                    entityTransaction.commit();
                    entityTransaction.begin();
                    em.clear();
                }
                PremiumBordereauxReport object = list.get(i);
                em.persist(object);
                System.out.println("Done./...." + i);
            }
            entityTransaction.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public int getCountOfMotorNonMotorBasedOnGroupCode(String groupCode){
        EntityManager em = getEntityManager();
        try{
            return em.createNamedQuery("PremiumBordereauxReport.countWithTransactionReportByGroupCode")
                    .setParameter("groupCode", groupCode)
                    .getResultList()
                    .size();
        } finally{
            em.close();
        }
    }
    
    public List<Object[]> testMethod(String groupCode){
        EntityManager em = getEntityManager();
        try{
            return em.createNamedQuery("PremiumBordereauxReport.countWithTransactionReportByGroupCode")
                    .setParameter("groupCode", groupCode)
                    .getResultList();
        } finally{
            em.close();
        }
    }
}

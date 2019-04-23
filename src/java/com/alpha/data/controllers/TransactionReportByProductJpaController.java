/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.controllers.exceptions.PreexistingEntityException;
import com.alpha.data.entities.TransactionReportByProduct;
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
public class TransactionReportByProductJpaController implements Serializable {

    public TransactionReportByProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransactionReportByProduct transactionReportByProduct) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(transactionReportByProduct);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransactionReportByProduct(transactionReportByProduct.getPolicyNumber()) != null) {
                throw new PreexistingEntityException("TransactionReportByProduct " + transactionReportByProduct + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransactionReportByProduct transactionReportByProduct) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            transactionReportByProduct = em.merge(transactionReportByProduct);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = transactionReportByProduct.getPolicyNumber();
                if (findTransactionReportByProduct(id) == null) {
                    throw new NonexistentEntityException("The transactionReportByProduct with id " + id + " no longer exists.");
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
            TransactionReportByProduct transactionReportByProduct;
            try {
                transactionReportByProduct = em.getReference(TransactionReportByProduct.class, id);
                transactionReportByProduct.getPolicyNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactionReportByProduct with id " + id + " no longer exists.", enfe);
            }
            em.remove(transactionReportByProduct);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TransactionReportByProduct> findTransactionReportByProductEntities() {
        return findTransactionReportByProductEntities(true, -1, -1);
    }

    public List<TransactionReportByProduct> findTransactionReportByProductEntities(int maxResults, int firstResult) {
        return findTransactionReportByProductEntities(false, maxResults, firstResult);
    }

    private List<TransactionReportByProduct> findTransactionReportByProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionReportByProduct.class));
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

    public TransactionReportByProduct findTransactionReportByProduct(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransactionReportByProduct.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionReportByProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransactionReportByProduct> rt = cq.from(TransactionReportByProduct.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void batchCreate(List<TransactionReportByProduct> list) throws PreexistingEntityException, Exception {
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
                TransactionReportByProduct object = list.get(i);
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

    public List<TransactionReportByProduct> getBySubAgentName(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("TransactionReportByProduct.findByAgencySubAgentName")
                    .setParameter("agencySubAgentName", name)
                    .getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Object[]> getBySubAgentNameForMotorNonMotorRatio(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("TransactionReportByProduct.getMotorNonMotorRatio")
                    .setParameter("agent", name)
                    .getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public int getCountBySubAgentName(String name) {
        EntityManager em = getEntityManager();
        try {
            return ((Long)em.createNamedQuery("TransactionReportByProduct.countByAgencySubAgentName")
                    .setParameter("agencySubAgentName", name)
                    .getSingleResult()).intValue();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

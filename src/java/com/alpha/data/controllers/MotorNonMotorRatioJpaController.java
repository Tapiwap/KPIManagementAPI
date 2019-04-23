/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.controllers.exceptions.PreexistingEntityException;
import com.alpha.data.entities.MotorNonMotorRatio;
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
public class MotorNonMotorRatioJpaController implements Serializable {

    public MotorNonMotorRatioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MotorNonMotorRatio motorNonMotorRatio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(motorNonMotorRatio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMotorNonMotorRatio(motorNonMotorRatio.getPolicyNumber()) != null) {
                throw new PreexistingEntityException("MotorNonMotorRatio " + motorNonMotorRatio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void batchCreate(List<MotorNonMotorRatio> list) throws PreexistingEntityException, Exception {
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
                MotorNonMotorRatio object = list.get(i);
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

    public void edit(MotorNonMotorRatio motorNonMotorRatio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            motorNonMotorRatio = em.merge(motorNonMotorRatio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = motorNonMotorRatio.getPolicyNumber();
                if (findMotorNonMotorRatio(id) == null) {
                    throw new NonexistentEntityException("The motorNonMotorRatio with id " + id + " no longer exists.");
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
            MotorNonMotorRatio motorNonMotorRatio;
            try {
                motorNonMotorRatio = em.getReference(MotorNonMotorRatio.class, id);
                motorNonMotorRatio.getPolicyNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The motorNonMotorRatio with id " + id + " no longer exists.", enfe);
            }
            em.remove(motorNonMotorRatio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MotorNonMotorRatio> findMotorNonMotorRatioEntities() {
        return findMotorNonMotorRatioEntities(true, -1, -1);
    }

    public List<MotorNonMotorRatio> findMotorNonMotorRatioEntities(int maxResults, int firstResult) {
        return findMotorNonMotorRatioEntities(false, maxResults, firstResult);
    }

    private List<MotorNonMotorRatio> findMotorNonMotorRatioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MotorNonMotorRatio.class));
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

    public MotorNonMotorRatio findMotorNonMotorRatio(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MotorNonMotorRatio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMotorNonMotorRatioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MotorNonMotorRatio> rt = cq.from(MotorNonMotorRatio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.RdPartyRecoveries;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Tsotsoh
 */
public class RdPartyRecoveriesJpaController implements Serializable {

    public RdPartyRecoveriesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RdPartyRecoveries rdPartyRecoveries) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rdPartyRecoveries);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RdPartyRecoveries rdPartyRecoveries) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rdPartyRecoveries = em.merge(rdPartyRecoveries);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rdPartyRecoveries.getId();
                if (findRdPartyRecoveries(id) == null) {
                    throw new NonexistentEntityException("The rdPartyRecoveries with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RdPartyRecoveries rdPartyRecoveries;
            try {
                rdPartyRecoveries = em.getReference(RdPartyRecoveries.class, id);
                rdPartyRecoveries.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rdPartyRecoveries with id " + id + " no longer exists.", enfe);
            }
            em.remove(rdPartyRecoveries);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RdPartyRecoveries> findRdPartyRecoveriesEntities() {
        return findRdPartyRecoveriesEntities(true, -1, -1);
    }

    public List<RdPartyRecoveries> findRdPartyRecoveriesEntities(int maxResults, int firstResult) {
        return findRdPartyRecoveriesEntities(false, maxResults, firstResult);
    }

    private List<RdPartyRecoveries> findRdPartyRecoveriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RdPartyRecoveries.class));
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

    public RdPartyRecoveries findRdPartyRecoveries(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RdPartyRecoveries.class, id);
        } finally {
            em.close();
        }
    }

    public int getRdPartyRecoveriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RdPartyRecoveries> rt = cq.from(RdPartyRecoveries.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

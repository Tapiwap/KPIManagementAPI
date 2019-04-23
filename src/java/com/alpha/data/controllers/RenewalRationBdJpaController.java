/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.RenewalRationBd;
import java.io.Serializable;
import java.util.Date;
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
public class RenewalRationBdJpaController implements Serializable {

    public RenewalRationBdJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RenewalRationBd renewalRationBd) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(renewalRationBd);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RenewalRationBd renewalRationBd) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            renewalRationBd = em.merge(renewalRationBd);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = renewalRationBd.getId();
                if (findRenewalRationBd(id) == null) {
                    throw new NonexistentEntityException("The renewalRationBd with id " + id + " no longer exists.");
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
            RenewalRationBd renewalRationBd;
            try {
                renewalRationBd = em.getReference(RenewalRationBd.class, id);
                renewalRationBd.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The renewalRationBd with id " + id + " no longer exists.", enfe);
            }
            em.remove(renewalRationBd);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RenewalRationBd> findRenewalRationBdEntities() {
        return findRenewalRationBdEntities(true, -1, -1);
    }

    public List<RenewalRationBd> findRenewalRationBdEntities(int maxResults, int firstResult) {
        return findRenewalRationBdEntities(false, maxResults, firstResult);
    }

    private List<RenewalRationBd> findRenewalRationBdEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RenewalRationBd.class));
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

    public RenewalRationBd findRenewalRationBd(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RenewalRationBd.class, id);
        } finally {
            em.close();
        }
    }

    public int getRenewalRationBdCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RenewalRationBd> rt = cq.from(RenewalRationBd.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<RenewalRationBd> getSingularByTypeBetweenDates(String agent, String type, Date startDate, Date endDate) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("RenewalRationBd.findSingularByTypeAndDate")
                    .setParameter("agent", agent)
                    .setParameter("type", type)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public double getAggregatedByTypeBetweenDates(String agent, String type, Date startDate, Date endDate){
        EntityManager em = getEntityManager();
        try{
            return (Double)em.createNamedQuery("RenewalRationBd.findAggregatedByTypeAndDate")
                    .setParameter("agent", agent)
                    .setParameter("type", type)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
    
}

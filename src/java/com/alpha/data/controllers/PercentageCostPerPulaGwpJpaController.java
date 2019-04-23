/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PercentageCostPerPulaGwp;
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
public class PercentageCostPerPulaGwpJpaController implements Serializable {

    public PercentageCostPerPulaGwpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PercentageCostPerPulaGwp percentageCostPerPulaGwp) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(percentageCostPerPulaGwp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PercentageCostPerPulaGwp percentageCostPerPulaGwp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            percentageCostPerPulaGwp = em.merge(percentageCostPerPulaGwp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = percentageCostPerPulaGwp.getId();
                if (findPercentageCostPerPulaGwp(id) == null) {
                    throw new NonexistentEntityException("The percentageCostPerPulaGwp with id " + id + " no longer exists.");
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
            PercentageCostPerPulaGwp percentageCostPerPulaGwp;
            try {
                percentageCostPerPulaGwp = em.getReference(PercentageCostPerPulaGwp.class, id);
                percentageCostPerPulaGwp.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The percentageCostPerPulaGwp with id " + id + " no longer exists.", enfe);
            }
            em.remove(percentageCostPerPulaGwp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PercentageCostPerPulaGwp> findPercentageCostPerPulaGwpEntities() {
        return findPercentageCostPerPulaGwpEntities(true, -1, -1);
    }

    public List<PercentageCostPerPulaGwp> findPercentageCostPerPulaGwpEntities(int maxResults, int firstResult) {
        return findPercentageCostPerPulaGwpEntities(false, maxResults, firstResult);
    }

    private List<PercentageCostPerPulaGwp> findPercentageCostPerPulaGwpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PercentageCostPerPulaGwp.class));
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

    public PercentageCostPerPulaGwp findPercentageCostPerPulaGwp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PercentageCostPerPulaGwp.class, id);
        } finally {
            em.close();
        }
    }

    public int getPercentageCostPerPulaGwpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PercentageCostPerPulaGwp> rt = cq.from(PercentageCostPerPulaGwp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<PercentageCostPerPulaGwp> getPercentageBetweenRange(String agent, Date startDate, Date endDate){
        EntityManager em = getEntityManager();        
        try{
            return em.createNamedQuery("PercentageCostPerPulaGwp.findSingularBetweenRange")
                    .setParameter("agent", agent)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public double getAggregatedBetweenRanges(String agent, Date startDate, Date endDate){
        EntityManager em = getEntityManager();        
        try{
            return ((Double)em.createNamedQuery("PercentageCostPerPulaGwp.findAggregated")
                    .setParameter("agent", agent)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult()).doubleValue();
        } finally {
            em.close();
        }
    }
    
}

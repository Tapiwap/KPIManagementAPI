/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PercentageComplianceClaims;
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
public class PercentageComplianceClaimsJpaController implements Serializable {

    public PercentageComplianceClaimsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PercentageComplianceClaims percentageComplianceClaims) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(percentageComplianceClaims);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PercentageComplianceClaims percentageComplianceClaims) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            percentageComplianceClaims = em.merge(percentageComplianceClaims);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = percentageComplianceClaims.getId();
                if (findPercentageComplianceClaims(id) == null) {
                    throw new NonexistentEntityException("The percentageComplianceClaims with id " + id + " no longer exists.");
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
            PercentageComplianceClaims percentageComplianceClaims;
            try {
                percentageComplianceClaims = em.getReference(PercentageComplianceClaims.class, id);
                percentageComplianceClaims.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The percentageComplianceClaims with id " + id + " no longer exists.", enfe);
            }
            em.remove(percentageComplianceClaims);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PercentageComplianceClaims> findPercentageComplianceClaimsEntities() {
        return findPercentageComplianceClaimsEntities(true, -1, -1);
    }

    public List<PercentageComplianceClaims> findPercentageComplianceClaimsEntities(int maxResults, int firstResult) {
        return findPercentageComplianceClaimsEntities(false, maxResults, firstResult);
    }

    private List<PercentageComplianceClaims> findPercentageComplianceClaimsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PercentageComplianceClaims.class));
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

    public PercentageComplianceClaims findPercentageComplianceClaims(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PercentageComplianceClaims.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<PercentageComplianceClaims> getPercentageBetweenRange(String agent, Date startDate, Date endDate){
        EntityManager em = getEntityManager();        
        try{
            return em.createNamedQuery("PercentageComplianceClaims.singular")
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
            return ((Double)em.createNamedQuery("PercentageComplianceClaims.aggregate")
                    .setParameter("agent", agent)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult()).doubleValue();
        } finally {
            em.close();
        }
    }

    public int getPercentageComplianceClaimsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PercentageComplianceClaims> rt = cq.from(PercentageComplianceClaims.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

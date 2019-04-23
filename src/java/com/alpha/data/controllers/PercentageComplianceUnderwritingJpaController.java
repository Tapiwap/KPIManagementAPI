/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PercentageComplianceUnderwriting;
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
public class PercentageComplianceUnderwritingJpaController implements Serializable {

    public PercentageComplianceUnderwritingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PercentageComplianceUnderwriting percentageComplianceUnderwriting) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(percentageComplianceUnderwriting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PercentageComplianceUnderwriting percentageComplianceUnderwriting) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            percentageComplianceUnderwriting = em.merge(percentageComplianceUnderwriting);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = percentageComplianceUnderwriting.getPercentageComplianceId();
                if (findPercentageComplianceUnderwriting(id) == null) {
                    throw new NonexistentEntityException("The percentageComplianceUnderwriting with id " + id + " no longer exists.");
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
            PercentageComplianceUnderwriting percentageComplianceUnderwriting;
            try {
                percentageComplianceUnderwriting = em.getReference(PercentageComplianceUnderwriting.class, id);
                percentageComplianceUnderwriting.getPercentageComplianceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The percentageComplianceUnderwriting with id " + id + " no longer exists.", enfe);
            }
            em.remove(percentageComplianceUnderwriting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PercentageComplianceUnderwriting> findPercentageComplianceUnderwritingEntities() {
        return findPercentageComplianceUnderwritingEntities(true, -1, -1);
    }

    public List<PercentageComplianceUnderwriting> findPercentageComplianceUnderwritingEntities(int maxResults, int firstResult) {
        return findPercentageComplianceUnderwritingEntities(false, maxResults, firstResult);
    }

    private List<PercentageComplianceUnderwriting> findPercentageComplianceUnderwritingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PercentageComplianceUnderwriting.class));
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

    public PercentageComplianceUnderwriting findPercentageComplianceUnderwriting(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PercentageComplianceUnderwriting.class, id);
        } finally {
            em.close();
        }
    }

    public int getPercentageComplianceUnderwritingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PercentageComplianceUnderwriting> rt = cq.from(PercentageComplianceUnderwriting.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<PercentageComplianceUnderwriting> getPercentageBetweenRange(String agent, Date startDate, Date endDate){
        EntityManager em = getEntityManager();        
        try{
            return em.createNamedQuery("PercentageComplianceUnderwriting.singular")
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
            return ((Double)em.createNamedQuery("PercentageComplianceUnderwriting.aggregate")
                    .setParameter("agent", agent)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult()).doubleValue();
        } finally {
            em.close();
        }
    }
    
}

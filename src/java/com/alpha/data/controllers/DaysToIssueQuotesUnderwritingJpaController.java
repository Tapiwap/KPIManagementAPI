/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.DaysToIssueQuotesUnderwriting;
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
public class DaysToIssueQuotesUnderwritingJpaController implements Serializable {

    public DaysToIssueQuotesUnderwritingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DaysToIssueQuotesUnderwriting daysToIssueQuotesUnderwriting) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(daysToIssueQuotesUnderwriting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DaysToIssueQuotesUnderwriting daysToIssueQuotesUnderwriting) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            daysToIssueQuotesUnderwriting = em.merge(daysToIssueQuotesUnderwriting);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = daysToIssueQuotesUnderwriting.getDaysToIssueQuotesId();
                if (findDaysToIssueQuotesUnderwriting(id) == null) {
                    throw new NonexistentEntityException("The daysToIssueQuotesUnderwriting with id " + id + " no longer exists.");
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
            DaysToIssueQuotesUnderwriting daysToIssueQuotesUnderwriting;
            try {
                daysToIssueQuotesUnderwriting = em.getReference(DaysToIssueQuotesUnderwriting.class, id);
                daysToIssueQuotesUnderwriting.getDaysToIssueQuotesId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The daysToIssueQuotesUnderwriting with id " + id + " no longer exists.", enfe);
            }
            em.remove(daysToIssueQuotesUnderwriting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DaysToIssueQuotesUnderwriting> findDaysToIssueQuotesUnderwritingEntities() {
        return findDaysToIssueQuotesUnderwritingEntities(true, -1, -1);
    }

    public List<DaysToIssueQuotesUnderwriting> findDaysToIssueQuotesUnderwritingEntities(int maxResults, int firstResult) {
        return findDaysToIssueQuotesUnderwritingEntities(false, maxResults, firstResult);
    }

    private List<DaysToIssueQuotesUnderwriting> findDaysToIssueQuotesUnderwritingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DaysToIssueQuotesUnderwriting.class));
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

    public DaysToIssueQuotesUnderwriting findDaysToIssueQuotesUnderwriting(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DaysToIssueQuotesUnderwriting.class, id);
        } finally {
            em.close();
        }
    }

    public int getDaysToIssueQuotesUnderwritingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DaysToIssueQuotesUnderwriting> rt = cq.from(DaysToIssueQuotesUnderwriting.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Integer> getDaysToIssueQuotesAggregate(String agent, Date startDate, Date endDate){
        EntityManager em = getEntityManager();
        try{
            return em.createNamedQuery("DaysToIssueQuotesUnderwriting.avgDaysToIssueQuoteAggregate")
                    .setParameter("agent", agent)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<DaysToIssueQuotesUnderwriting> getAvgDaysToIssueQuoteSingular(String agent, Date startDate, Date endDate){
        EntityManager em = getEntityManager();
        try{
            return em.createNamedQuery("DaysToIssueQuotesUnderwriting.avgDaysToIssueQuoteSingular")
                    .setParameter("agent", agent)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PercentageClosedPolicyUnderwitting;
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
public class PercentageClosedPolicyUnderwittingJpaController implements Serializable {

    public PercentageClosedPolicyUnderwittingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PercentageClosedPolicyUnderwitting percentageClosedPolicyUnderwitting) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(percentageClosedPolicyUnderwitting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PercentageClosedPolicyUnderwitting percentageClosedPolicyUnderwitting) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            percentageClosedPolicyUnderwitting = em.merge(percentageClosedPolicyUnderwitting);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = percentageClosedPolicyUnderwitting.getPercentageClosedPolicyId();
                if (findPercentageClosedPolicyUnderwitting(id) == null) {
                    throw new NonexistentEntityException("The percentageClosedPolicyUnderwitting with id " + id + " no longer exists.");
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
            PercentageClosedPolicyUnderwitting percentageClosedPolicyUnderwitting;
            try {
                percentageClosedPolicyUnderwitting = em.getReference(PercentageClosedPolicyUnderwitting.class, id);
                percentageClosedPolicyUnderwitting.getPercentageClosedPolicyId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The percentageClosedPolicyUnderwitting with id " + id + " no longer exists.", enfe);
            }
            em.remove(percentageClosedPolicyUnderwitting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PercentageClosedPolicyUnderwitting> findPercentageClosedPolicyUnderwittingEntities() {
        return findPercentageClosedPolicyUnderwittingEntities(true, -1, -1);
    }

    public List<PercentageClosedPolicyUnderwitting> findPercentageClosedPolicyUnderwittingEntities(int maxResults, int firstResult) {
        return findPercentageClosedPolicyUnderwittingEntities(false, maxResults, firstResult);
    }

    private List<PercentageClosedPolicyUnderwitting> findPercentageClosedPolicyUnderwittingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PercentageClosedPolicyUnderwitting.class));
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

    public PercentageClosedPolicyUnderwitting findPercentageClosedPolicyUnderwitting(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PercentageClosedPolicyUnderwitting.class, id);
        } finally {
            em.close();
        }
    }

    public int getPercentageClosedPolicyUnderwittingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PercentageClosedPolicyUnderwitting> rt = cq.from(PercentageClosedPolicyUnderwitting.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public double getAggregateBetweenDates(String agent, Date startDate, Date endDate) {
        EntityManager em = getEntityManager();
        try {
            return ((Double) em.createNamedQuery("PercentageClosedPolicyUnderwitting.AggregateBetweenDates")
                    .setParameter("agent", agent)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult())
                    .doubleValue();
        } finally {
            em.close();
        }
    }

    public List<PercentageClosedPolicyUnderwitting> getSingularPercentageClosedPolicyBetweenDates(String agent, Date startDate, Date endDate) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("PercentageClosedPolicyUnderwitting.SingularPercentageClosedPolicyBetweenDates")
                    .setParameter("agent", agent)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}

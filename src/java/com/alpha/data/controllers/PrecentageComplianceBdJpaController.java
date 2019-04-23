/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PrecentageComplianceBd;
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
public class PrecentageComplianceBdJpaController implements Serializable {

    public PrecentageComplianceBdJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrecentageComplianceBd precentageComplianceBd) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(precentageComplianceBd);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrecentageComplianceBd precentageComplianceBd) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            precentageComplianceBd = em.merge(precentageComplianceBd);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = precentageComplianceBd.getId();
                if (findPrecentageComplianceBd(id) == null) {
                    throw new NonexistentEntityException("The precentageComplianceBd with id " + id + " no longer exists.");
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
            PrecentageComplianceBd precentageComplianceBd;
            try {
                precentageComplianceBd = em.getReference(PrecentageComplianceBd.class, id);
                precentageComplianceBd.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precentageComplianceBd with id " + id + " no longer exists.", enfe);
            }
            em.remove(precentageComplianceBd);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrecentageComplianceBd> findPrecentageComplianceBdEntities() {
        return findPrecentageComplianceBdEntities(true, -1, -1);
    }

    public List<PrecentageComplianceBd> findPrecentageComplianceBdEntities(int maxResults, int firstResult) {
        return findPrecentageComplianceBdEntities(false, maxResults, firstResult);
    }

    private List<PrecentageComplianceBd> findPrecentageComplianceBdEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrecentageComplianceBd.class));
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

    public PrecentageComplianceBd findPrecentageComplianceBd(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrecentageComplianceBd.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrecentageComplianceBdCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecentageComplianceBd> rt = cq.from(PrecentageComplianceBd.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<PrecentageComplianceBd> getSingularByTypeBetweenDates(String agent, String type, Date startDate, Date endDate) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("PrecentageComplianceBd.findSingularByTypeAndDate")
                    .setParameter("agent", agent)
                    .setParameter("type", type)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public double getAggregatedByTypeBetweenDates(String agent, String type, Date startDate, Date endDate) {
        EntityManager em = getEntityManager();
        try {
            return (Double) em.createNamedQuery("PrecentageComplianceBd.findAggregatedByTypeAndDate")
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

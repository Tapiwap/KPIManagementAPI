/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.ComplianceCashOutBd;
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
public class ComplianceCashOutBdJpaController implements Serializable {

    public ComplianceCashOutBdJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComplianceCashOutBd complianceCashOutBd) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(complianceCashOutBd);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComplianceCashOutBd complianceCashOutBd) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            complianceCashOutBd = em.merge(complianceCashOutBd);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = complianceCashOutBd.getId();
                if (findComplianceCashOutBd(id) == null) {
                    throw new NonexistentEntityException("The complianceCashOutBd with id " + id + " no longer exists.");
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
            ComplianceCashOutBd complianceCashOutBd;
            try {
                complianceCashOutBd = em.getReference(ComplianceCashOutBd.class, id);
                complianceCashOutBd.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The complianceCashOutBd with id " + id + " no longer exists.", enfe);
            }
            em.remove(complianceCashOutBd);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComplianceCashOutBd> findComplianceCashOutBdEntities() {
        return findComplianceCashOutBdEntities(true, -1, -1);
    }

    public List<ComplianceCashOutBd> findComplianceCashOutBdEntities(int maxResults, int firstResult) {
        return findComplianceCashOutBdEntities(false, maxResults, firstResult);
    }

    private List<ComplianceCashOutBd> findComplianceCashOutBdEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComplianceCashOutBd.class));
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

    public ComplianceCashOutBd findComplianceCashOutBd(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComplianceCashOutBd.class, id);
        } finally {
            em.close();
        }
    }

    public int getComplianceCashOutBdCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComplianceCashOutBd> rt = cq.from(ComplianceCashOutBd.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<ComplianceCashOutBd> getSingularByTypeBetweenDates(String agent, String type, Date startDate, Date endDate) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("ComplianceCashOutBd.findSingularByTypeAndDateRange")
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
            return (Double) em.createNamedQuery("ComplianceCashOutBd.findAggreatedByTypeAndDateRange")
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

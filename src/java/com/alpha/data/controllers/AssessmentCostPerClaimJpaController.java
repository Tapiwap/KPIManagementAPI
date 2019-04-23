/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.controllers.exceptions.PreexistingEntityException;
import com.alpha.data.entities.AssessmentCostPerClaim;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
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
public class AssessmentCostPerClaimJpaController implements Serializable {

    public AssessmentCostPerClaimJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AssessmentCostPerClaim assessmentCostPerClaim) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(assessmentCostPerClaim);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void batchCreate(List<AssessmentCostPerClaim> list) throws PreexistingEntityException, Exception {
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
                AssessmentCostPerClaim object = list.get(i);
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

    public void edit(AssessmentCostPerClaim assessmentCostPerClaim) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            assessmentCostPerClaim = em.merge(assessmentCostPerClaim);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = assessmentCostPerClaim.getId();
                if (findAssessmentCostPerClaim(id) == null) {
                    throw new NonexistentEntityException("The assessmentCostPerClaim with id " + id + " no longer exists.");
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
            AssessmentCostPerClaim assessmentCostPerClaim;
            try {
                assessmentCostPerClaim = em.getReference(AssessmentCostPerClaim.class, id);
                assessmentCostPerClaim.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The assessmentCostPerClaim with id " + id + " no longer exists.", enfe);
            }
            em.remove(assessmentCostPerClaim);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AssessmentCostPerClaim> findAssessmentCostPerClaimEntities() {
        return findAssessmentCostPerClaimEntities(true, -1, -1);
    }

    public List<AssessmentCostPerClaim> findAssessmentCostPerClaimEntities(int maxResults, int firstResult) {
        return findAssessmentCostPerClaimEntities(false, maxResults, firstResult);
    }

    private List<AssessmentCostPerClaim> findAssessmentCostPerClaimEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AssessmentCostPerClaim.class));
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

    public AssessmentCostPerClaim findAssessmentCostPerClaim(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AssessmentCostPerClaim.class, id);
        } finally {
            em.close();
        }
    }

    public int getAssessmentCostPerClaimCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AssessmentCostPerClaim> rt = cq.from(AssessmentCostPerClaim.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public int getCountBetweenDates(Date startDate, Date endDate){
        EntityManager em = getEntityManager();
        try{
            return ((Long)em.createNamedQuery("AssessmentCostPerClaim.findCountBetweenDates")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<AssessmentCostPerClaim> getBetweenDates(Date startDate, Date endDate){
        EntityManager em = getEntityManager();
        try{
            return em.createNamedQuery("AssessmentCostPerClaim.findBetweenDates")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
}

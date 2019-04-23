/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.controllers;

import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.controllers.exceptions.PreexistingEntityException;
import com.alpha.data.entities.ClaimAsOnDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ClaimAsOnDateJpaController implements Serializable {

    public ClaimAsOnDateJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClaimAsOnDate claimAsOnDate) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(claimAsOnDate);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClaimAsOnDate(claimAsOnDate.getClaimNumber()) != null) {
                throw new PreexistingEntityException("ClaimAsOnDate " + claimAsOnDate + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void batchCreate(List<ClaimAsOnDate> claimsList) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        int batchSize = 25;
        try {
            em = getEntityManager();
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            for (int i = 0; i < claimsList.size(); i++) {
                if (i > 0 && i % batchSize == 0) {
                    entityTransaction.commit();
                    entityTransaction.begin();
                    em.clear();
                }
                ClaimAsOnDate claim = claimsList.get(i);
                em.persist(claim);
                System.out.println("Done./...." + i);
            }
            entityTransaction.commit();
        } catch (Exception ex) {
//            if (findClaimAsOnDate(claimAsOnDate.getClaimNumber()) != null) {
//                throw new PreexistingEntityException("ClaimAsOnDate " + claimAsOnDate + " already exists.", ex);
//            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClaimAsOnDate claimAsOnDate) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            claimAsOnDate = em.merge(claimAsOnDate);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = claimAsOnDate.getClaimNumber();
                if (findClaimAsOnDate(id) == null) {
                    throw new NonexistentEntityException("The claimAsOnDate with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClaimAsOnDate claimAsOnDate;
            try {
                claimAsOnDate = em.getReference(ClaimAsOnDate.class, id);
                claimAsOnDate.getClaimNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The claimAsOnDate with id " + id + " no longer exists.", enfe);
            }
            em.remove(claimAsOnDate);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClaimAsOnDate> findClaimAsOnDateEntities() {
        return findClaimAsOnDateEntities(true, -1, -1);
    }

    public List<ClaimAsOnDate> findClaimAsOnDateEntities(int maxResults, int firstResult) {
        return findClaimAsOnDateEntities(false, maxResults, firstResult);
    }

    private List<ClaimAsOnDate> findClaimAsOnDateEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClaimAsOnDate.class));
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

    public ClaimAsOnDate findClaimAsOnDate(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClaimAsOnDate.class, id);
        } finally {
            em.close();
        }
    }

    public int getClaimAsOnDateCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClaimAsOnDate> rt = cq.from(ClaimAsOnDate.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getNumberOfClaimsHandledByAgent(String claimsAllocatedTo) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("ClaimAsOnDate.claimsCountByAgent")
                    .setParameter("claimsAllocatedTo", claimsAllocatedTo)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getNumberOfClaimsByAgentAndDescription(String claimsAllocatedTo, String shortDesc) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("ClaimAsOnDate.ClaimsCountByAgentAndDesc")
                    .setParameter("claimsAllocatedTo", claimsAllocatedTo)
                    .setParameter("shortDesc", shortDesc)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getNumberOfClaimsByShortDesc(String shortDesc) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("ClaimAsOnDate.ClaimsCountByDesc")
                    .setParameter("shortDesc", shortDesc)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getNumberOfClaimsByReportedDate(Date date) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("ClaimAsOnDate.claimsCountByReportedDate")
                    .setParameter("date", date)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getNumberOfClaimsByStatusDate(Date date) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("ClaimAsOnDate.claimsCountByStatusDate")
                    .setParameter("date", date)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getNumberOfClaimsByReportedDateAndAgent(Date date, String allocatedTo) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("ClaimAsOnDate.claimsCountByReportedDateAndAgent")
                    .setParameter("date", date)
                    .setParameter("claimsAllocatedTo", allocatedTo)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getNumberOfClaimsByAgentBetweenStatusDates(Date startDate, Date endDate, String allocatedTo) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("ClaimAsOnDate.claimsCountByAgentBetweenStatusDates")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setParameter("claimsAllocatedTo", allocatedTo)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getNumberOfClaimsByAgentAndDescBetweenStatusDates(Date startDate, Date endDate, String allocatedTo, String desc) {
        EntityManager em = getEntityManager();

        try {
            if (desc.equals("Non Motor")) {
                return getIfDescIsNonMotor(em, startDate, endDate, allocatedTo);
            } else {
                return getIfDescIsNotNonMotor(em, startDate, endDate, allocatedTo, desc);
            }
        } finally {
            em.close();
        }
    }

    private int getIfDescIsNotNonMotor(EntityManager em, Date startDate, Date endDate, String allocatedTo, String desc) {
        return ((Long) em.createNamedQuery("ClaimAsOnDate.claimsCountByAgentAndDescBetweenStatusDates")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("claimsAllocatedTo", allocatedTo)
                .setParameter("desc", desc)
                .getSingleResult()).intValue();
    }

    private int getIfDescIsNonMotor(EntityManager em, Date startDate, Date endDate, String allocatedTo) {
        return ((Long) em.createNamedQuery("ClaimAsOnDate.NonMotorClaimsCountByAgentBetweenStatusDates")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("claimsAllocatedTo", allocatedTo)
                .getSingleResult()).intValue();
    }

    public int getNumberOfClaimsByAgentBetweenReportedDates(Date startDate, Date endDate, String allocatedTo) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("ClaimAsOnDate.claimsCountByAgentBetweenReportedDates")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setParameter("claimsAllocatedTo", allocatedTo)
                    .getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Object> getDaysToResolveClaimsByAgent(String allocatedTo) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("ClaimAsOnDate.claimsDifferenceByAgent")
                    .setParameter("claimsAllocatedTo", allocatedTo)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> getDaysToResolveClaimsByAgentAndDesc(String allocatedTo, String desc) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("ClaimAsOnDate.claimsDifferenceByAgentAndDesc")
                    .setParameter("claimsAllocatedTo", allocatedTo)
                    .setParameter("desc", desc)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Object> getDaysToResolveClaimsByAgentAndDescBetweenStatusDates(String allocatedTo, String desc,
            Date startDate, Date endDate) {
        EntityManager em = getEntityManager();
        try {
            
            if (desc.equals("Non Motor")){
                return getDaysToResolveIfNonMotor(em, allocatedTo, startDate, endDate);
            } else {
                return getDaysToResolveIfNotNonMotor(em, allocatedTo, desc, startDate, endDate);
            }
            
        } finally {
            em.close();
        }
    }

    private List<Object> getDaysToResolveIfNotNonMotor(EntityManager em, String allocatedTo, String desc, Date startDate, Date endDate) {
        return em.createNamedQuery("ClaimAsOnDate.daysDifferenceByAgentAndDescAndDates")
                .setParameter("claimsAllocatedTo", allocatedTo)
                .setParameter("desc", desc)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    private List<Object> getDaysToResolveIfNonMotor(EntityManager em, String allocatedTo, Date startDate, Date endDate) {
        return em.createNamedQuery("ClaimAsOnDate.nonMotorDaysDifferenceByAgentAndDates")
                .setParameter("claimsAllocatedTo", allocatedTo)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
    
    public List<Object[]> getClaimsGroupedByMonth(String allocatedTo, String desc, Date startDate, Date endDate){
         EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("ClaimAsOnDate.findGroupedByMonth")
                    .setParameter("claimsAllocatedTo", allocatedTo)
                    .setParameter("desc", desc)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}

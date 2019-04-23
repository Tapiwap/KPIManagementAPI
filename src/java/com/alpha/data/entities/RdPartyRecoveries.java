/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tsotsoh
 */
@Entity
@Table(name = "3rd_party_recoveries")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RdPartyRecoveries.findAll", query = "SELECT r FROM RdPartyRecoveries r")
    , @NamedQuery(name = "RdPartyRecoveries.findById", query = "SELECT r FROM RdPartyRecoveries r WHERE r.id = :id")
    , @NamedQuery(name = "RdPartyRecoveries.findByTransType", query = "SELECT r FROM RdPartyRecoveries r WHERE r.transType = :transType")
    , @NamedQuery(name = "RdPartyRecoveries.findByTransSubType", query = "SELECT r FROM RdPartyRecoveries r WHERE r.transSubType = :transSubType")
    , @NamedQuery(name = "RdPartyRecoveries.findByRservePaymentAmt", query = "SELECT r FROM RdPartyRecoveries r WHERE r.rservePaymentAmt = :rservePaymentAmt")})
public class RdPartyRecoveries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "trans_type")
    private String transType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "trans_sub_type")
    private String transSubType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rserve_payment_amt")
    private BigDecimal rservePaymentAmt;

    public RdPartyRecoveries() {
    }

    public RdPartyRecoveries(Integer id) {
        this.id = id;
    }

    public RdPartyRecoveries(Integer id, String transType, String transSubType) {
        this.id = id;
        this.transType = transType;
        this.transSubType = transSubType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransSubType() {
        return transSubType;
    }

    public void setTransSubType(String transSubType) {
        this.transSubType = transSubType;
    }

    public BigDecimal getRservePaymentAmt() {
        return rservePaymentAmt;
    }

    public void setRservePaymentAmt(BigDecimal rservePaymentAmt) {
        this.rservePaymentAmt = rservePaymentAmt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RdPartyRecoveries)) {
            return false;
        }
        RdPartyRecoveries other = (RdPartyRecoveries) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.RdPartyRecoveries[ id=" + id + " ]";
    }
    
}

package com.alpha.data.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-24T12:50:34")
@StaticMetamodel(AssessmentCostPerClaim.class)
public class AssessmentCostPerClaim_ { 

    public static volatile SingularAttribute<AssessmentCostPerClaim, String> transSubType;
    public static volatile SingularAttribute<AssessmentCostPerClaim, BigDecimal> rservePaymentAmt;
    public static volatile SingularAttribute<AssessmentCostPerClaim, Date> reportedDate;
    public static volatile SingularAttribute<AssessmentCostPerClaim, Integer> id;

}
package com.alpha.data.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-24T12:50:34")
@StaticMetamodel(RenewalRationBd.class)
public class RenewalRationBd_ { 

    public static volatile SingularAttribute<RenewalRationBd, String> agent;
    public static volatile SingularAttribute<RenewalRationBd, Double> expectedRatio;
    public static volatile SingularAttribute<RenewalRationBd, Integer> id;
    public static volatile SingularAttribute<RenewalRationBd, String> type;
    public static volatile SingularAttribute<RenewalRationBd, Double> actualRatio;
    public static volatile SingularAttribute<RenewalRationBd, Date> timestamp;

}
package com.alpha.data.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-24T12:50:35")
@StaticMetamodel(PercentageComplianceUnderwriting.class)
public class PercentageComplianceUnderwriting_ { 

    public static volatile SingularAttribute<PercentageComplianceUnderwriting, Integer> percentageComplianceId;
    public static volatile SingularAttribute<PercentageComplianceUnderwriting, Double> expectedPercentage;
    public static volatile SingularAttribute<PercentageComplianceUnderwriting, String> agent;
    public static volatile SingularAttribute<PercentageComplianceUnderwriting, Double> actualPercentage;
    public static volatile SingularAttribute<PercentageComplianceUnderwriting, Date> timestamp;

}
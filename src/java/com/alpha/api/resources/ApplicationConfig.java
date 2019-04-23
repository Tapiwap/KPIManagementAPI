/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.api.resources;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Tsotsoh
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.alpha.api.filters.AuthenticationFilter.class);
        resources.add(com.alpha.api.filters.CorsFilter.class);
        resources.add(com.alpha.api.filters.ResponseBodyLogFilter.class);
        resources.add(com.alpha.api.resources.BusinessDevelopmentResource.class);
        resources.add(com.alpha.api.resources.ClaimsResource.class);
        resources.add(com.alpha.api.resources.UnderwritingResource.class);
    }
    
}

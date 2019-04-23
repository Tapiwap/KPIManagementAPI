/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.api.filters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Tsotsoh
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Context
    private ResourceInfo resourceInfo;
    private static final String AUTH_HEADER = "authorization";

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        System.out.println("From AuthFilter " + context.getHeaders());
        Method method = resourceInfo.getResourceMethod();
        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (isMethodAnnotationDenyAll(method, context))return;

            String clientSpecifiedRole = context.getHeaderString(AUTH_HEADER);
            System.out.println("THis is the client specified role : " + clientSpecifiedRole);
            if (isClientSpecifiedRolePresent(clientSpecifiedRole, context))return;

            if (method.isAnnotationPresent(RolesAllowed.class)) {
                Set<String> rolesSet = getAllowedResourceRoles(method);

                isClientSpecifiedRoleValid(rolesSet, clientSpecifiedRole, context);
            }
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println("Response status " + responseContext.getStatus() + " And headers " + responseContext.getHeaders());
        if (requestContext.getProperty("auth-failed") != null) {
            Boolean failed = (Boolean) requestContext.getProperty("auth-failed");
            if (failed) {
                Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, "Authentication Failed");
                return;
            }
        }
    }

    private Set<String> getAllowedResourceRoles(Method method) {
        RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
        Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
        return rolesSet;
    }

    private void isClientSpecifiedRoleValid(Set<String> rolesSet, String clientSpecifiedRole, ContainerRequestContext context) {
        if (!rolesSet.contains(clientSpecifiedRole)) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, "Role is not set.....");
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isClientSpecifiedRolePresent(String clientSpecifiedRole, ContainerRequestContext context) {
        if (clientSpecifiedRole == null || clientSpecifiedRole.isEmpty()) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, "Client specified role is either null or empty......");
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return true;
        }
        return false;
    }

    private boolean isMethodAnnotationDenyAll(Method method, ContainerRequestContext context) {
        if (method.isAnnotationPresent(DenyAll.class)) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, "Deny All set on this endpoint");
            context.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            return true;
        }
        return false;
    }

}

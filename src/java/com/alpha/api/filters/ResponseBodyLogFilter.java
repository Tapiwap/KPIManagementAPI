/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.api.filters;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Tsotsoh
 */
@Provider
public class ResponseBodyLogFilter implements ContainerResponseFilter {

    @Context
    private ResourceInfo resourceInfo;
    private static final String AUTH_HEADER = "user_type";
    private static final Logger log = Logger.getLogger(ResponseBodyLogFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext context, ContainerResponseContext response) throws IOException {
//        FileHandler fh = new FileHandler("log.log", true);
//        SimpleFormatter sf = new SimpleFormatter();
//        fh.setFormatter(sf);
//        fh.setLevel(Level.INFO);
//        log.addHandler(fh);
//
//        Method method = resourceInfo.getResourceMethod();
//        log.log(Level.INFO, "Current DateTime: {0}", LocalDate.now());
//        log.log(Level.INFO, "Request Headers: {0}", context.getHeaders());
//        String clientSpecifiedRole = context.getHeaderString(AUTH_HEADER);
//        log.log(Level.INFO, "Client Specified role: {0}", clientSpecifiedRole);
//        System.out.println("ResponseBodyLogFilter : " + response.getEntity().toString());
//        log.log(Level.INFO, "ResponseBodyLogFilter : {0}", response.getEntity().toString());
//        System.out.println("MediaType : " + response.getMediaType());
//        log.log(Level.INFO, "MediaType : {0}", response.getMediaType());
//        System.out.println("Method: " + method.getName());
//        log.log(Level.INFO, "Method: {0}", method.getName());
//        System.out.println("Method details : ");
//        log.log(Level.INFO, "Method details : ");
//
//        Arrays.asList(method.getAnnotations()).stream().forEach(annotation -> {
//            log.log(Level.INFO, "Method details : {0}", annotation);
//        });
    }

}

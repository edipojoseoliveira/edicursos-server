package com.edicursos.edicursos.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;

/**
 *
 * @author edipo
 */
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest containerRequest, ContainerResponse containerResponse) {
        Response.ResponseBuilder responseBuilder = Response.fromResponse(containerResponse.getResponse());
        
        responseBuilder.header("Access-Control-Allow-Origin", "*");
        responseBuilder.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        responseBuilder.header("Access-Control-Allow-Headers", "Content-Type, Foo-Header");
        responseBuilder.header("Access-Control-Max-Age", "86400");
        
        containerResponse.setResponse(responseBuilder.build());
        
        return containerResponse;
    }
    
}

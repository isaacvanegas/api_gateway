package org.api.service;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.api.Request.ProductoRequest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:9091")
public interface ProductoService {

    @POST
    @Path("/api/productoOperation")
    @Consumes(MediaType.APPLICATION_JSON)
    void operarProducto(@HeaderParam("Authorization") String bearerToken, ProductoRequest request);

}

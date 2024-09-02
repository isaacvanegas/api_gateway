package org.api.service;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.api.Request.UserCredentials;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(baseUri = "http://localhost:9090")
public interface JwtService {
    @POST
    @Path("/jwt/generate-jwt")
    @Consumes(MediaType.APPLICATION_JSON)
    String generateToken(UserCredentials credentials);

}

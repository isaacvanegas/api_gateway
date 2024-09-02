package org.api.RS;

import io.smallrye.faulttolerance.api.RateLimit;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.api.Request.ProductoRequest;
import org.api.Request.UserCredentials;
import org.api.service.JwtService;
import org.api.service.ProductoService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api_gateway")
public class ApiGatewy {

    @Inject
    @RestClient
    JwtService jwtService;

    @Inject
    @RestClient
    ProductoService productoService;

    @POST
    @Path("/operation_product")
    @RateLimit(value = 5, window = 10000)
    public Uni<Response> operarProducto(ProductoRequest request) {
        // Generar JWT
        UserCredentials credentials = new UserCredentials();
        credentials.userName = "jvanegas";
        credentials.password = "e10adc3949ba59abbe56e057f20f883e";

        return Uni.createFrom().item(jwtService.generateToken(credentials))
                .onItem().transformToUni(token -> {
                    String bearerToken = "Bearer " + token;
                    // Enviar petición al API de Producto
                    productoService.operarProducto(bearerToken, request);
                    return Uni.createFrom().item(Response.ok().build());
                });
    }
}

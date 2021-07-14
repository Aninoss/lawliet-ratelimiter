package core;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
@Singleton
public class RestService {

    private final Ratelimiter ratelimiter = new Ratelimiter(Integer.parseInt(System.getenv("REQUESTS_PER_SECOND")));

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Pong!";
    }

    @GET
    @Path("/relative")
    @Produces(MediaType.TEXT_PLAIN)
    public int relative() {
        return ratelimiter.nextRequestRelative();
    }

}

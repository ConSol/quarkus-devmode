package de.consol.dus;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/greetings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    private final GreetingRepository repository;

    public GreetingResource(GreetingRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<Greeting> getAllGreetings() {
        return repository.findAll();
    }

    @POST
    public Greeting createNewGreeting(Greeting greeting) {
        return repository.save(greeting);
    }

    @GET
    @Path("{name}")
    public Greeting getByName(@PathParam("name") String name) {
        return repository.findByName(name)
            .orElseGet(() -> new Greeting().setSalutation("Hello").setName(name));
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreetingStringByName(@PathParam("name") String name) {
        final Greeting greeting = getByName(name);
        return String.format("%s, %s!", greeting.getSalutation(), greeting.getName());
    }

    @DELETE
    @Path("{name}")
    public void deleteGreeting(@PathParam("name") String name) {
        repository.deleteByName(name);
    }

}
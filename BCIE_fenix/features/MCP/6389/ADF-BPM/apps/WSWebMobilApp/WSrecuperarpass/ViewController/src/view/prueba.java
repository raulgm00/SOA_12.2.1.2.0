package view;

import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("Test")
@Consumes("application/x-www-form-urlencoded")
@Produces("application/json")
public class prueba {
    public prueba() {
        super();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    @Path("/prueba")
    public String wstest (@Encoded @FormParam("param1") String param1, @Encoded @FormParam("param2") String param2){
        String response = "param1: "+param1+"->"+"param2: "+param2+"/n"+"test OK";
        return response;
    }
}

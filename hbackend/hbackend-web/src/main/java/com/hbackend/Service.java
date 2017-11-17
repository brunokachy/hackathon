package com.hbackend;

import com.hbackend.email.services.EmailService;
import com.hbackend.persistence.entity.portal.PortalUser;
import com.hbackend.persistence.services.PortalPersistence;
import com.hbackend.persistence.services.Util;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("")
public class Service {

    @EJB
    PortalPersistence portalPersistence;

    @EJB
    Util util;

    @EJB
    EmailService emailService;

    @GET
    @Path("validateEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateEmail(@QueryParam("email") String email) {
        boolean isValid = true;
        try {
            PortalUser pu = portalPersistence.getUserByEmailAddress(email);
            if (pu != null) {
                isValid = false;
            }
        } catch (Exception e) {
        }
        return Response.ok(isValid).build();
    }

    @POST
    @Path("createUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(PortalUser ru) {
        try {
            ru.setPassword(Util.hashPassword(ru.getPassword()));
            portalPersistence.create(ru);
            return Response.status(200).entity(ru).build();
        } catch (Exception e) {
            return Response.status(400).entity(new StringBuilder("Error Creating User")).build();
        }
    }

//    @POST
//    @Path("login")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response login(@QueryParam("email") String email, @QueryParam("password") String password) {
//        try {
//            ru.setPassword(Util.hashPassword(ru.getPassword()));
//            portalPersistence.create(ru);
//            return Response.status(200).entity(ru).build();
//        } catch (Exception e) {
//            return Response.status(400).entity(new StringBuilder("Error Creating User")).build();
//        }
//    }
}

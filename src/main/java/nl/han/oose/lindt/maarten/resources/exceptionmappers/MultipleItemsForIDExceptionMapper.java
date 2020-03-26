package nl.han.oose.lindt.maarten.resources.exceptionmappers;

import nl.han.oose.lindt.maarten.services.exceptions.MultipleItemsForIDException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MultipleItemsForIDExceptionMapper implements ExceptionMapper<MultipleItemsForIDException> {
    @Override
    public Response toResponse(MultipleItemsForIDException e) {
        return Response.status(500).entity("je hebt meerdere items met dit id").build();
    }
}



package nl.han.oose.lindt.maarten.resources.exceptionmappers;

import nl.han.oose.lindt.maarten.datasource.dao.FailedQueryException;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class FailedQueryExceptionMapper implements ExceptionMapper<FailedQueryException> {
    @Override
    public Response toResponse(FailedQueryException e) {
        return Response.status(500).entity("de sql query ging fout").build();
    }
}
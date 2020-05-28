package nl.han.oose.lindt.maarten.resources.exceptionmappers;

import nl.han.oose.lindt.maarten.datasource.databaseExceptions.FailedConnectionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class FailedConnectionExceptionMapper implements ExceptionMapper<FailedConnectionException> {

@Override
public Response toResponse(FailedConnectionException e) {
        return Response.status(500).entity("de sql verbinding ging fout").build();
        }
}

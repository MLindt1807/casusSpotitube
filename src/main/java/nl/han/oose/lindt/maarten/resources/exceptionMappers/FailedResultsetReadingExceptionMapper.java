package nl.han.oose.lindt.maarten.resources.exceptionMappers;

import nl.han.oose.lindt.maarten.datasource.DAO.FailedQueryException;
import nl.han.oose.lindt.maarten.datasource.DAO.FailedResultsetReadingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class FailedResultsetReadingExceptionMapper implements ExceptionMapper<FailedResultsetReadingException> {
    @Override
    public Response toResponse(FailedResultsetReadingException e) {
        return Response.status(500).entity("er ging iets fout bij het uitlezen van de resultset").build();
    }

}

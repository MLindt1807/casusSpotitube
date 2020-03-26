package nl.han.oose.lindt.maarten.resources.exceptionmappers;

import nl.han.oose.lindt.maarten.services.exceptions.NotConsistantDataException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotPersistantDataExceptionMapper implements ExceptionMapper<NotConsistantDataException>

    {
        @Override
        public Response toResponse(NotConsistantDataException e) {
        return Response.status(500).entity("geen consistente data").build();
    }

    }
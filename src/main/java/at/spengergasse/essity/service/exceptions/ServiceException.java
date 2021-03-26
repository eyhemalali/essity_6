package at.spengergasse.essity.service.exceptions;

import javax.persistence.PersistenceException;

public class ServiceException extends AbstractBusinessException{

    private ServiceException(String message, Throwable rootCause)
    {
        super(message, rootCause);
    }

    public static final  ServiceException wrap(String message, PersistenceException pEx)
    {
        return new ServiceException(message, pEx);
    }


}

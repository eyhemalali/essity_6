package at.spengergasse.essity.service.exceptions;

public class DataQualityException extends AbstractBusinessException{

    private DataQualityException(String message)
    {
        super(message);
    }

    public static final DataQualityException forMissingEntityForId(Long id, Class<?> entity)
    {
        String message = String.format("Unknown: id %d for entity'%s'", id, entity.getSimpleName());
        return new DataQualityException(message);
    }
}

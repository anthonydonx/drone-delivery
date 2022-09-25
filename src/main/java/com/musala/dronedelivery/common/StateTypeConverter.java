package com.musala.dronedelivery.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author anthonydonx
 * Covert {@link StateType} to String value while saving
 * JPA will automatically apply the conversion logic to all mapped attributes
 */
@Converter(autoApply = true)
public class StateTypeConverter implements AttributeConverter<StateType, String> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database
     * column
     */
    @Override
    public String convertToDatabaseColumn(StateType attribute) {
        return attribute == null ? null : attribute.getStateCode();
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct <code>dbData</code> type for the corresponding
     * column for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be
     *               converted
     * @return the converted value to be stored in the entity
     * attribute
     */
    @Override
    public StateType convertToEntityAttribute(String dbData) {
        return dbData == null ? null :
                Stream.of(StateType.values()).filter(state -> state.getStateCode().equals(dbData)).findFirst().orElseThrow(IllegalAccessError::new);
    }
}

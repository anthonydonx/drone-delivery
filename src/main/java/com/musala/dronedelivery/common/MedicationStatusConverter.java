/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author anthonydonx
 * Covert {@link MedicationStatus} to String value while saving
 * JPA will automatically apply the conversion logic to all mapped attributes
 */
@Converter(autoApply = true)
public class MedicationStatusConverter implements AttributeConverter<MedicationStatus, String> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database
     * column
     */
    @Override
    public String convertToDatabaseColumn(MedicationStatus attribute) {
        return attribute == null ? null : attribute.getStatusCode();
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
    public MedicationStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null :
                Stream.of(MedicationStatus.values()).filter(model -> model.getStatusCode().equals(dbData)).findFirst().orElseThrow(IllegalAccessError::new);
    }
}

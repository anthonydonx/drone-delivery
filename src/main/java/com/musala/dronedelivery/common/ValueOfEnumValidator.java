/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator  implements ConstraintValidator<ValueOfEnum, CharSequence> {


        private List<String> acceptedValues;

        @Override
        public void initialize(ValueOfEnum annotation)
        {
            acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                    .map(Enum::name)
                    .collect(Collectors.toList());
        }

        @Override
        public boolean isValid(CharSequence value, ConstraintValidatorContext context)
        {
            if (value == null) {
                return true;
            }
            return acceptedValues.contains(value.toString());
        }
    }



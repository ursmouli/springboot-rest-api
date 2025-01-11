package com.app.restapi.util;

import com.app.restapi.exceptions.FieldNotFoundException;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EntityUtil {

    public static boolean isFieldValid(Class<?> entityClass, String fieldName) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toSet())
                .contains(fieldName);
    }

    public static void isFieldExist(Class<?> entityClass, String fieldName) {
        if (!isFieldValid(entityClass, fieldName)) {
            throw new FieldNotFoundException(
                    MessageFormat.format("Field {0} not found in entity {1}", fieldName, entityClass.getName()));
        }
    }

}

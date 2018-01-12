package com.xzh.basepopup.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class ReflectUtils {

    public static void setFieldValue(Object object, String fieldName, Object newFieldValue) throws Exception {
        Field field = getDeclaredField(object, fieldName);
        Field modifiersField = Field.class.getDeclaredField("accessFlags");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        field.set(object, newFieldValue);
    }

    public static Object getFieldValue(Object obj, final String fieldName) throws Exception {
        Field field = getDeclaredField(obj, fieldName);
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field.get(obj);
        }
        return null;
    }

    public static Field getDeclaredField(final Object obj, final String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                return field;
            } catch (NoSuchFieldException e) {
                continue;
            }
        }
        return null;
    }
}

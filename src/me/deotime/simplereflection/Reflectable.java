package me.deotime.simplereflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflectable<T> {

    private final Object object;
    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    Reflectable(T object) {
        this.object = object;
        this.clazz = (Class<T>) object.getClass();
    }

    public void setValue(String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Sneaky.fling(ex);
        }
    }

    @SuppressWarnings({"unchecked", "unused"})
    public <A> A getValue(String fieldName, Class<A> type) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (A) field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Sneaky.fling(ex);
        }
        return null;
    }

    public Object getValue(String fieldName) {
        return getValue(fieldName, Object.class);
    }

    @SuppressWarnings({"unchecked", "unused"})
    public <A> A invoke(String methodName, Class<A> returnType, Object... params) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, SimpleReflect.mapParams(params));
            method.setAccessible(true);
            return (A) method.invoke(object, params);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            Sneaky.fling(ex);
        }
        return null;
    }

    public Object invoke(String methodName, Object... params) {
        return invoke(methodName, Object.class, params);
    }

}

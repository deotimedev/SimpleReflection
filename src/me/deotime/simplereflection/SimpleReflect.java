package me.deotime.simplereflection;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class SimpleReflect {

    public static <T> Reflectable<T> upon(T obj) {
        return new Reflectable<>(obj);
    }
    
    public static <T> T construct(Class<T> clazz, Object... params) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(mapParams(params));
            constructor.setAccessible(true);
            return constructor.newInstance(params);
        } catch (ReflectiveOperationException ex) {
            Sneaky.fling(ex);
        }
        return null;
    }

    static Class<?>[] mapParams(Object[] params) {
        return Arrays.stream(params).map(Object::getClass).toArray(Class[]::new);
    }

}

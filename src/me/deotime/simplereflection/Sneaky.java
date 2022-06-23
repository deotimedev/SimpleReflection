package me.deotime.simplereflection;

class Sneaky {
    public static <T extends Throwable> void fling(Throwable t) throws T {
        throw (T) t;
    }
}

package me.katas.marsrover.utils;

public class Checks {
    
    private Checks() {}
    
    public static <T> T notNull(T target) {
        if (target == null)
            throw new IllegalArgumentException();
        
        return target;
    }
}

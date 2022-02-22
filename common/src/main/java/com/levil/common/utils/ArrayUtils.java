package com.levil.common.utils;

import java.util.Arrays;


public class ArrayUtils {

    public static boolean isEmpty(final Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean contains(final Object[] array, final Object objectToFind) {
        if (array == null) {
            return false;
        }
        
        return Arrays.asList(array).contains(objectToFind);
    }
    
}

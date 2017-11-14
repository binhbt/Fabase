package com.vn.fa.base.util;

/**
 * Created by binhbt on 10/1/2016.
 */
public class EventUtil {
    public static <T> T getEventData(Object event, Class<T> clazz){
        if (event.getClass() == clazz){
            return (T)event;
        }
        return null;
    }
}

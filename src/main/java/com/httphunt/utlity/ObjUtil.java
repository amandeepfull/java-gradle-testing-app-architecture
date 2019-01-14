package com.httphunt.utlity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.*;

public interface ObjUtil {

     static boolean isBlank(String value) {
        return (value == null || value.trim().length() <= 0);
    }

     static String getJson(Object object) {

        try {
            return new JacksonObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }

    }


     static Map<String, Object> getMapFromJson(String json) {

        try {
            return new JacksonObjectMapper().readValue(json,
                    new TypeReference<HashMap<String, Object>>() {
                    });
        } catch (IOException e) {
            return null;
        }
    }


     static <T> T safeConvertJson(String json, Class<T> clazz) {

        if (isBlank(json))
            return null;

        try {
            return new JacksonObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

}


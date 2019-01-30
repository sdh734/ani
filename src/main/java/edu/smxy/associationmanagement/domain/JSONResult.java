package edu.smxy.associationmanagement.domain;

import java.util.*;
import com.fasterxml.jackson.databind.*;

public class JSONResult
{
    private static final ObjectMapper MAPPER;
    private Integer status;
    private String msg;
    private Object data;
    
    public static JSONResult build(final Integer status, final String msg, final Object data) {
        return new JSONResult(status, msg, data);
    }
    
    public static JSONResult ok(final Object data) {
        return new JSONResult(data);
    }
    
    public static JSONResult ok() {
        return new JSONResult(null);
    }
    
    public static JSONResult errorMsg(final String msg) {
        return new JSONResult(500, msg, null);
    }
    
    public static JSONResult errorMap(final Object data) {
        return new JSONResult(501, "error", data);
    }
    
    public static JSONResult errorTokenMsg(final String msg) {
        return new JSONResult(502, msg, null);
    }
    
    public static JSONResult errorException(final String msg) {
        return new JSONResult(555, msg, null);
    }
    
    public JSONResult() {
    }
    
    public JSONResult(final Integer status, final String msg, final Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public JSONResult(final Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    
    public Boolean isOK() {
        return this.status == 200;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(final String msg) {
        this.msg = msg;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
    
    public static JSONResult formatToPojo(final String jsonData, final Class<?> clazz) {
        try {
            if (clazz == null) {
                return (JSONResult)JSONResult.MAPPER.readValue(jsonData, (Class)JSONResult.class);
            }
            final JsonNode jsonNode = JSONResult.MAPPER.readTree(jsonData);
            final JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = JSONResult.MAPPER.readValue(data.traverse(), (Class)clazz);
                }
                else if (data.isTextual()) {
                    obj = JSONResult.MAPPER.readValue(data.asText(), (Class)clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static JSONResult format(final String json) {
        try {
            return (JSONResult)JSONResult.MAPPER.readValue(json, (Class)JSONResult.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static JSONResult formatToList(final String jsonData, final Class<?> clazz) {
        try {
            final JsonNode jsonNode = JSONResult.MAPPER.readTree(jsonData);
            final JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = JSONResult.MAPPER.readValue(data.traverse(), (JavaType)JSONResult.MAPPER.getTypeFactory().constructCollectionType((Class)List.class, (Class)clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    static {
        MAPPER = new ObjectMapper();
    }
}

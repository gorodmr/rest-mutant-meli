package co.com.mercado.libre.quasar.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {

  private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
  public static final ObjectMapper mapper = new ObjectMapper();

  public JsonUtil() {
  }

  public static String toJsonString(Object object) {
    String parseObject = "";
    try {
      parseObject = mapper.writeValueAsString(object);
    } catch (JsonProcessingException var3) {
      log.error("parse::JsonProcessingException={}", var3.getMessage());
    }

    return parseObject;
  }

  public static <T> T stringToObject(String data, Class<T> type) {
    Object object = null;
    try {
      object = mapper.readValue(data, type);
    } catch (IOException var4) {
      log.error("JsonUtil:stringToObject::exceptionMessage={}", var4.getMessage());
    }
    return (T) object;
  }

  public static <T> T stringToObject(String data, TypeReference<T> type) {
    Object object = null;
    try {
      object = mapper.readValue(data, type);
    } catch (IOException var4) {
      log.error("JsonUtil:stringToObject::exceptionMessage={}", var4.getMessage());
    }
    return (T) object;
  }

}

package com.meli.mutant.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.meli.mutant.exception.MapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Expose static Jackson Object Mapper
 */
public class JsonUtil {

  /**
   * The constant MAPPER.
   */
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

  static {
    MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  private JsonUtil() {

  }

  protected static ObjectMapper getMapper() {
    return MAPPER;
  }

  protected static String writeValueAsString(final Object object) throws JsonProcessingException {
    return getMapper().writeValueAsString(object);
  }

  protected static <T> T readValue(String content, Class<T> valueType) throws IOException {
    return getMapper().readValue(content, valueType);
  }

  protected static <T> T treeToValue(TreeNode nodes, Class<T> classType) throws IOException {
    return getMapper().treeToValue(nodes, classType);
  }

  /**
   * Converts Object to json string .
   *
   * @param object the object
   *
   * @return the string
   */
  public static String toJsonString(final Object object) {
    try {
      return writeValueAsString(object);
    } catch (JsonProcessingException e) {
      LOGGER.error("toJsonString::JsonProcessingException={}", e.getMessage(), e);
      throw new MapperException(e.getMessage(), e);
    }

  }

  public static <T> T toObject(final String string, final Class<T> classType) {
    try {
      return readValue(string, classType);
    } catch (IOException e) {
      LOGGER.error("toObject::JsonProcessingException={}", e.getMessage(), e);
      throw new MapperException(e.getMessage(), e);
    }
  }

  public static <T> T toObject(final JsonNode nodes, final Class<T> classType) {
    try {
      return treeToValue(nodes, classType);
    } catch (IOException e) {
      LOGGER.error("parse::JsonProcessingException={}", e.getMessage(), e);
      throw new MapperException(e.getMessage(), e);
    }
  }

  public static ObjectMapper writeValue(
          final OutputStream object,
          final Map<String, Object> map
  ) throws IOException {
    getMapper().writeValue(object, map);
    return getMapper();
  }
}

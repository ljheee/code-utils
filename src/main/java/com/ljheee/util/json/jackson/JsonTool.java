package com.ljheee.util.json.jackson;

import com.alibaba.excel.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;


public class JsonTool {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        //序列化时，跳过null属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //序列化时，遇到空bean（无属性）时不会失败
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化时，遇到未知属性（在bean上找不到对应属性）时不会失败
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //反序列化时，将空数组([])当做null来处理（以便把空数组反序列化到对象属性上——对php生成的json的map属性很有用）
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        //不通过fields来探测（仅通过标准getter探测）
        //mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, false);


        //configure(MapperFeature.AUTO_DETECT_FIELDS, true),同时配合以下设置，可以实现按字段检测
        mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
    }

    /* ====================== 反序列化工具 ==================== */

    /**
     * Json串转为对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, TypeReference<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Json串转为对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, Class<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 输入流转为对象
     *
     * @param stream
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parse(InputStream stream, TypeReference<T> type) {
        try {
            return mapper.readValue(stream, type);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 输入流转为对象
     *
     * @param <T>
     * @param stream
     * @param type
     * @return
     */
    public static <T> T parse(InputStream stream, Class<T> type) {
        try {
            return mapper.readValue(stream, type);
        } catch (IOException e) {
            return null;
        }
    }

    /* ====================== 序列化工具 ==================== */

    /**
     * 序列化对象转为json-string
     *
     * @param target
     * @return
     */
    public static String writeToString(Object target) {
        try {
            return mapper.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 序列化对象并写入Writer
     *
     * @param writer
     * @param target
     * @throws IOException
     */
    public static void write(Writer writer, Object target) throws IOException {
        mapper.writeValue(writer, target);
    }

    /**
     * 序列化对象并写入Stream
     *
     * @param stream
     * @param target
     * @throws IOException
     */
    public static void write(OutputStream stream, Object target) throws IOException {
        mapper.writeValue(stream, target);
    }


}

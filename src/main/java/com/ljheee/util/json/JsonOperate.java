package com.ljheee.util.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author lijianhua.
 */
public class JsonOperate {


    public static void main(String[] args) throws Exception {


        jsonObject();
    }


    public static void jsonObject() throws IOException {
        /**
         * {
         "  data": {
         "      birth_day": 7,
         "      birth_month": 6
         },
         "  errcode": 0,
         "  msg": "ok",
         "  ret": 0
         }
         */
        String str = "{\"data\":{\"birth_day\":7,\"birth_month\":6},\"errcode\":0,\"msg\":\"ok\",\"ret\":0}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(str);

        JsonNode data = root.path("data");

        JsonNode birth_day = data.path("birth_day");
        System.out.println(birth_day.asInt());

        JsonNode birth_month = data.path("birth_month");
        System.out.println(birth_month.asInt());

        JsonNode msg = root.path("msg");
        System.out.println(msg.textValue());
    }


    public static void jsonList() throws IOException {
        /**
         * {
         "data": {
         "info": [{
         "id": "288206077664983",
         "timestamp": 1371052476
         }, {
         "id": "186983078111768",
         "timestamp": 1370944068
         }, {
         "id": "297031120529307",
         "timestamp": 1370751789
         }, {
         "id": "273831022294863",
         "timestamp": 1369994812
         }],
         "total": 422
         },
         "errcode": 0,
         "msg": "ok",
         "ret": 0,
         "seqid": 5903702688915195270
         }
         */
        String str = "{\"data\":{\"info\":[{\"id\":\"288206077664983\",\"timestamp\":1371052476},{\"id\":\"186983078111768\",\"timestamp\":1370944068},{\"id\":\"297031120529307\",\"timestamp\":1370751789},{\"id\":\"273831022294863\",\"timestamp\":1369994812}],\"total\":422},\"errcode\":0,\"msg\":\"ok\",\"ret\":0,\"seqid\":5903702688915195270}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(str);

        // 提取 data
        JsonNode data = root.path("data");
        // 提取 info
        JsonNode info = data.path("info");

        System.out.println(info.size());

        // 得到 info 的第 0 个
        JsonNode item = info.get(0);
        System.out.println(item.get("id"));
        System.out.println(item.get("timestamp"));


        // 遍历 info 内的 array
        if (info.isArray()) {
            for (JsonNode objNode : info) {

                // 收集需要的字段
                System.out.println(objNode.get("id"));
            }
        }
    }








}

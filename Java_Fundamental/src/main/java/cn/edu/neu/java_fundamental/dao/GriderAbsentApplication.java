package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GriderAbsentApplication {
    private static final String filename = "GriderAbsenceRecord.json";
    private List<Grider> absentGriders = new ArrayList<>();

    public List<Grider> getAllAbsentGriders() throws IOException {
        String json = FileTools.readStringFromFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        if (rootNode.isArray()) {
            for (JsonNode griderNode : rootNode) {
                if (!griderNode.get("absentReason").asText().isEmpty()) {
                    Grider absentGrider = mapper.treeToValue(griderNode, Grider.class);
                    absentGriders.add(absentGrider);
                }
            }
        }
        return absentGriders;
    }

    public void addAbsentGrider(Grider grider) throws IOException {
        String json = FileTools.readStringFromFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        if (rootNode.isArray()) {
            ArrayNode gridersArray = (ArrayNode) rootNode;
            for (JsonNode griderNode : gridersArray) {
                if (griderNode.get("id").asText().equals(grider.getId())) {
                    ((ObjectNode) griderNode).put("absentReason", grider.getAbsentReason());
                }
            }
        }
    }

    public void readAbsentGrider() {
        try {
            String json = FileTools.readStringFromFile(filename);
            ObjectMapper mapper = new ObjectMapper();
            absentGriders = mapper.readValue(json, new com.fasterxml.jackson.core.type.TypeReference<List<Grider>>() {
            });

        } catch (IOException e) {
            System.out.println("readAbsentGrider failed: " + e.getMessage());
        }
    }

    //    public void deleteAbsentGrider(Grider grider) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String json = FileTools.readStringFromFile(filename);
//        JsonNode rootNode = mapper.readTree(json);
//
//        if (rootNode.isArray()) {
//            for (JsonNode griderNode : rootNode) {
//                if (griderNode.isObject() && griderNode.has("id") &&
//                        griderNode.get("id").asText().equals(grider.getId())) {
//                    // 删除字段
//                    ((ObjectNode) griderNode).remove("absentReason");
//                    ((ObjectNode) griderNode).remove("id");
//                    ((ObjectNode) griderNode).remove("password");
//                    ((ObjectNode) griderNode).remove("name");
//                    ((ObjectNode) griderNode).remove("sex");
//                    ((ObjectNode) griderNode).remove("score");
//                    ((ObjectNode) griderNode).remove("area");
//                    ((ObjectNode) griderNode).remove("isOnline");
//                    ((ObjectNode) griderNode).remove("gridInfo");
//                }
//            }
//        }
//
//        // 写回文件
//            FileTools.writeStringToFile(filename, mapper.writeValueAsString(rootNode));
//    }
    public int deleteAbsentGrider(Grider grider) throws IOException {
        if (absentGriders == null) {
            readAbsentGrider();
            if (absentGriders == null) {
                absentGriders = new ArrayList<>();
                return -1;
            }
        }
        if (!absentGriders.contains(grider)) {
            System.out.println("不存在这个请假的网格员");
            return -1;
        }
        absentGriders.remove(grider);
        return FileTools.writeStringToFile(filename, new ObjectMapper().writeValueAsString(absentGriders));
    }


}

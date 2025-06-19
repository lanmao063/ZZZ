package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GriderAbsentApplication {
    private static final String filename = "GriderAbsenceRecord.json";
    private List<String> absentGridersId = new ArrayList<>();
    public List<String> getAllAbsentGriders() throws IOException {
        String json = FileTools.readStringFromFile(filename);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        if (rootNode.isArray()) {
            for (JsonNode griderNode : rootNode) {
                if (griderNode.has("absentReason")) {
                    String griderId = griderNode.get("id").asText(); // 提取 Grider ID
                    absentGridersId.add(griderId); // 添加到列表中
                }
            }
        }

        return absentGridersId;
    }
}

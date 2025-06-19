package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.Grider;
import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
}

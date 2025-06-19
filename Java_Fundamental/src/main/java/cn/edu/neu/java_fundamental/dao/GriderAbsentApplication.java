package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.util.FileTools;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GriderAbsentApplication {
    private static final String filename = "GriderAbsenceRecord.json";
    public String readGriderAbsenceRecord()
    {
        String reason = null;
        try {
            String json = FileTools.readStringFromFile(filename);
            ObjectMapper mapper = new ObjectMapper();


            // 使用 JsonNode 解析 JSON
            JsonNode rootNode = mapper.readTree(json);

            // 读取 absentReason 字段
            if (rootNode.has("absentReason")) {
                String absentReason = rootNode.get("absentReason").asText();
                reason = absentReason;

                // 可以在这里将 absentReason 设置到某个 Grider 对象上
                // 例如：currentGrider.setAbsentReason(absentReason);
            } else {
                reason = "JSON 中没有 absentReason 字段";
            }
        } catch (IOException e) {
            System.err.println("读取失败:" + e.getMessage());
        }
        return reason;
    }
}

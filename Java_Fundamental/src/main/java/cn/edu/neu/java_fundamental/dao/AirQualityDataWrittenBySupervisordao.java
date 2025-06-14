package cn.edu.neu.java_fundamental.dao;

import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AirQualityDataWrittenBySupervisordao {
    private static final String FILE_PATH = "data/AirQualityDataWrittenBySupervisor.json";
    private List<AirQualityDataWrittenBySupervisor> dataList=null; // 持久化数据列表
    private final ObjectMapper mapper = new ObjectMapper();

    // 构造方法中初始化时直接加载文件数据
    public AirQualityDataWrittenBySupervisordao() throws IOException {
        loadDataFromFile();
    }

    // 加载文件数据到内存
    private void loadDataFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() > 0) {
            String json = new String(Files.readAllBytes(file.toPath()));
            dataList = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, AirQualityDataWrittenBySupervisor.class));
        } else {
            dataList = new ArrayList<>();
        }
    }

    // 保存数据到文件
    private void saveDataToFile() throws IOException {
        String json = mapper.writeValueAsString(dataList);
        Files.write(Paths.get(FILE_PATH), json.getBytes());
    }

    // 添加新数据（自动保存）
    public int addAirQualityDataWrittenBySupervisor(AirQualityDataWrittenBySupervisor data) throws IOException {
        dataList.add(data);
        saveDataToFile();
        return dataList.size(); // 返回当前数据总量
    }

    // 获取所有数据
    public List<AirQualityDataWrittenBySupervisor> getAllData() {
        return new ArrayList<>(dataList); // 返回副本避免外部修改
    }
}
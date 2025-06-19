package cn.edu.neu.java_fundamental.entity;

import cn.edu.neu.java_fundamental.dao.SupervisorSubmit;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grid {

    public List<GridInfo> readGridInfo() throws IOException {
        SupervisorSubmit submitDao = new SupervisorSubmit();
        Map<String, List<AirQualityDataWrittenBySupervisor>> allDataMap = submitDao.getAllData();
        List<GridInfo> gridInfos;
        // 使用 Stream 提取所有数据
        List<AirQualityDataWrittenBySupervisor> allData = allDataMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        gridInfos = allData.stream()
                .map(data -> new GridInfo(
                        data.getProvince(),
                        data.getCity(),
                        data.getDistrict(),
                        data.getDate(),
                        data.getAQL().toString(),
                        data.getText()
                ))
                .collect(Collectors.toList());
        return gridInfos;
    }

}

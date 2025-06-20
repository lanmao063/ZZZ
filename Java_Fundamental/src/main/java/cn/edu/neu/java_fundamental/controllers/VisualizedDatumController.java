package cn.edu.neu.java_fundamental.controllers;

import cn.edu.neu.java_fundamental.dao.GriderSubmit;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenByGrider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VisualizedDatumController {
    @FXML
    private LineChart<String, Number> LC_co;

    @FXML
    private LineChart<String,Number> LC_so2;

    @FXML
    private LineChart<String, Number> LC_spm;

    @FXML
    private PieChart PC_pollution;

    @FXML
    private void initialize(){
        List<AirQualityDataWrittenByGrider> data = new ArrayList<>();
        Map<String, List<AirQualityDataWrittenByGrider>> allData = new GriderSubmit().getAllData();
        Set<String> ID = allData.keySet();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenDaysAgo = now.minusDays(10);


        Map<String, Integer> pollutionCount = new HashMap<>();


        for (String id : ID) {
            for (AirQualityDataWrittenByGrider d : allData.get(id)) {
                try {
                    LocalDateTime date = LocalDateTime.parse(d.getDate(), formatter);
                    if (!date.isBefore(tenDaysAgo)) {
                        data.add(d);
                        String level = d.getAQL().getChinese_explain();
                        pollutionCount.put(level, pollutionCount.getOrDefault(level, 0) + 1);
                    }
                } catch (Exception e) {
                    System.err.println("Invalid date format: " + d.getDate());
                }
            }
        }

        // 排序数据（按日期）
        data.sort(Comparator.comparing(AirQualityDataWrittenByGrider::getDate));

        // 设置图表
        setupPieChart(PC_pollution, pollutionCount);


        setupLineChart(LC_so2,0,data);
        setupLineChart(LC_co,1,data);
        setupLineChart(LC_spm,2,data);
    }


    public void setupLineChart(LineChart<String, Number> LC, int type, List<AirQualityDataWrittenByGrider> data) {
        CategoryAxis xAxis = (CategoryAxis) LC.getXAxis();
        NumberAxis yAxis = (NumberAxis) LC.getYAxis();

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        if (type == 0) {
            for (AirQualityDataWrittenByGrider d : data) {
                series.getData().add(new XYChart.Data<>(d.getDate(), d.getSo2_concentration()));
            }
        } else if (type == 1) {
            for (AirQualityDataWrittenByGrider d : data) {
                series.getData().add(new XYChart.Data<>(d.getDate(), d.getCo_concentration()));
            }
        } else {
            for (AirQualityDataWrittenByGrider d : data) {
                series.getData().add(new XYChart.Data<>(d.getDate(), d.getSpm_concentration()));
            }
        }

        LC.getData().add(series);
    }

    public void setupPieChart(PieChart pieChart, Map<String, Integer> pollutionCount) {
        pieChart.getData().clear();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int total = pollutionCount.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String, Integer> entry : pollutionCount.entrySet()) {
            double percentage = ((double) entry.getValue() / total) * 100;
            String label = String.format("%s %.1f%%", entry.getKey(), percentage);
            pieChartData.add(new PieChart.Data(label, entry.getValue()));
        }

        pieChart.setData(pieChartData);
        pieChart.setLegendVisible(true);
    }

}

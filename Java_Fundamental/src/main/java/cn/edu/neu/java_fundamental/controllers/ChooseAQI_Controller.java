package cn.edu.neu.java_fundamental.controllers;
import cn.edu.neu.java_fundamental.dao.SupervisorSubmit;
import cn.edu.neu.java_fundamental.entity.AirQualityDataWrittenBySupervisor;
import cn.edu.neu.java_fundamental.entity.Supervisor;
import cn.edu.neu.java_fundamental.util.AQI_Calculator;
import cn.edu.neu.java_fundamental.util.AirQualityLevel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ChooseAQI_Controller {
    private List<Button> buttons;
    private Button ClickedButton;
    private final Map<String, List<String>> provinceCityMap = new HashMap<>();

    @FXML
    private Button bt1;

    @FXML
    private Button bt2;

    @FXML
    private Button bt3;

    @FXML
    private Button bt4;

    @FXML
    private Button bt5;

    @FXML
    private Button bt6;

    @FXML
    private Button submit;

    @FXML
    private TextField sp_district;

    @FXML
    private ComboBox<String> sp_city;

    @FXML
    private ComboBox<String> sp_province;


    @FXML
    private TextField text1;

    @FXML
    public void initialize() {
        // 初始化按钮列表
        buttons = Arrays.asList(bt1, bt2, bt3, bt4, bt5, bt6);
        provinceCityMap.put("北京", List.of("北京市"));
        provinceCityMap.put("上海", List.of("上海市"));
        provinceCityMap.put("天津", List.of("天津市"));
        provinceCityMap.put("重庆", List.of("重庆市"));

        provinceCityMap.put("黑龙江", List.of("哈尔滨", "齐齐哈尔", "牡丹江", "佳木斯"));
        provinceCityMap.put("吉林", List.of("长春", "吉林市", "延吉", "四平"));
        provinceCityMap.put("辽宁", List.of("沈阳", "大连", "鞍山", "本溪"));

        provinceCityMap.put("河北", List.of("石家庄", "唐山", "秦皇岛", "保定"));
        provinceCityMap.put("山西", List.of("太原", "大同", "临汾"));
        provinceCityMap.put("内蒙古", List.of("呼和浩特", "包头", "赤峰"));

        provinceCityMap.put("江苏", List.of("南京", "苏州", "无锡", "常州"));
        provinceCityMap.put("浙江", List.of("杭州", "宁波", "温州"));
        provinceCityMap.put("安徽", List.of("合肥", "芜湖", "黄山"));
        provinceCityMap.put("福建", List.of("福州", "厦门", "泉州"));
        provinceCityMap.put("江西", List.of("南昌", "九江", "赣州"));
        provinceCityMap.put("山东", List.of("济南", "青岛", "烟台", "临沂"));

        provinceCityMap.put("河南", List.of("郑州", "洛阳", "开封"));
        provinceCityMap.put("湖北", List.of("武汉", "宜昌", "襄阳"));
        provinceCityMap.put("湖南", List.of("长沙", "衡阳", "株洲"));

        provinceCityMap.put("广东", List.of("广州", "深圳", "佛山", "东莞"));
        provinceCityMap.put("广西", List.of("南宁", "桂林", "柳州"));
        provinceCityMap.put("海南", List.of("海口", "三亚", "儋州"));

        provinceCityMap.put("四川", List.of("成都", "绵阳", "德阳"));
        provinceCityMap.put("贵州", List.of("贵阳", "遵义", "安顺"));
        provinceCityMap.put("云南", List.of("昆明", "大理", "丽江"));
        provinceCityMap.put("西藏", List.of("拉萨", "日喀则", "林芝"));

        provinceCityMap.put("陕西", List.of("西安", "咸阳", "宝鸡"));
        provinceCityMap.put("甘肃", List.of("兰州", "天水", "酒泉"));
        provinceCityMap.put("青海", List.of("西宁", "海东"));
        provinceCityMap.put("宁夏", List.of("银川", "吴忠", "石嘴山"));
        provinceCityMap.put("新疆", List.of("乌鲁木齐", "喀什", "伊宁"));

        provinceCityMap.put("香港", List.of("香港特别行政区"));
        provinceCityMap.put("澳门", List.of("澳门特别行政区"));
        provinceCityMap.put("台湾", List.of("台北", "高雄", "台中"));

        // 添加省份到下拉框
        sp_province.getItems().addAll(provinceCityMap.keySet());
    }
    @FXML
    void onProvinceSelected(ActionEvent event) {
        String selectedProvince = sp_province.getValue();
        List<String> cities = provinceCityMap.getOrDefault(selectedProvince, List.of());

        sp_city.getItems().clear(); // 清除旧城市
        sp_city.getItems().addAll(cities);
        sp_city.setValue(null); // 重置选中项
    }

    @FXML
    void HandleButtonClick(ActionEvent event) {
        ClickedButton = (Button) event.getSource();
        for(Button bt : buttons){
            bt.setStyle("-fx-min-width:15px;-fx-min-height:15px;-fx-pref-width:15px;-fx-pref-height:15px;-fx-max-width:15px;-fx-max-height:15px;-fx-background-radius:50%");
        }
        ClickedButton.setStyle("-fx-background-color: #28c8f5;-fx-min-width:15px;-fx-min-height:15px;-fx-pref-width:15px;-fx-pref-height:15px;-fx-max-width:15px;-fx-max-height:15px;-fx-background-radius:50%");
    }

    @FXML
    void Submit(ActionEvent event) throws IOException {
        String province = sp_province.getValue();
        String city = sp_city.getValue();
        String district = sp_district.getText();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        String text = text1.getText();
        AirQualityLevel AQL = switch (text){
            case "1" -> AirQualityLevel.EXCELLENT;
            case "2" -> AirQualityLevel.GOOD;
            case "3" -> AirQualityLevel.LIGHT_POLLUTED;
            case "4" -> AirQualityLevel.MODERATE_POLLUTED;
            case "5" -> AirQualityLevel.HEAVY_POLLUTED;
            case "6" -> AirQualityLevel.SEVERE_POLLUTED;
            default -> null;
        };
        AirQualityDataWrittenBySupervisor airQualityDataWrittenBySupervisor = new AirQualityDataWrittenBySupervisor();
        airQualityDataWrittenBySupervisor.setCity(city);
        airQualityDataWrittenBySupervisor.setDistrict(district);
        airQualityDataWrittenBySupervisor.setDate(formattedDate);
        airQualityDataWrittenBySupervisor.setAQL(AQL);
        airQualityDataWrittenBySupervisor.setText(text);
        SupervisorSubmit AQdao = new SupervisorSubmit();
        try {
            int i = AQdao.addAirQualityData(new Supervisor(), airQualityDataWrittenBySupervisor);
            if(i > 0){
                System.out.println("提交成功");
            }else {
                System.out.println("提交失败");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

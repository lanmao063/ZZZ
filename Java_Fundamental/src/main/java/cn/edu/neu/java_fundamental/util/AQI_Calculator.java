package cn.edu.neu.java_fundamental.util;

public class AQI_Calculator {
    /**
     * 污染物浓度阈值
     */
    private static final double[][] CONCENTRATION_THRESHOLDS={
            //  SO2阈值
            {0,50,150,475,800,1600,2100,2620},
            //  CO阈值
            {0,2,4,14,24,36,48,60},
            //  spm阈值
            {0,50,150,250,350,420,500,600}

    };

    /**
     * AQI对应阈值
     */
    private static final double[] AQI_THRESHOLDS={0,50,100,150,200,300,400,500};


    /**
     * 计算AQI
     * @param type 污染物种类 0表示二氧化硫 1表示一氧化碳 2表示spm
     * @param concentration 污染物浓度(全为24小时平均浓度)
     * @return AQI
     */
    public static int calculateAQI_forSinglePollutant(int type,double concentration){
        if(type<0||type>2)
            throw new IllegalArgumentException("输入的种类不在当前范围之内");


        double[] singlePollutant_THRESHOLDS=CONCENTRATION_THRESHOLDS[type];
        double AQI=0;

        if(concentration<=singlePollutant_THRESHOLDS[0])
            return 0;
        if(concentration>=singlePollutant_THRESHOLDS[singlePollutant_THRESHOLDS.length-1])
            return (int)AQI_THRESHOLDS[AQI_THRESHOLDS.length-1];
        for(int i=0;i<singlePollutant_THRESHOLDS.length-1;i++){
            if(concentration>=singlePollutant_THRESHOLDS[i]&&concentration<singlePollutant_THRESHOLDS[i+1]){
                AQI=(AQI_THRESHOLDS[i+1]-AQI_THRESHOLDS[i])/(singlePollutant_THRESHOLDS[i+1]-singlePollutant_THRESHOLDS[i])
                        *(concentration-singlePollutant_THRESHOLDS[i])+AQI_THRESHOLDS[i];
                break;
            }

        }
        return (int)Math.ceil(AQI);
    }

    public static int calculateAQI_forAllPollutants(double so2_concentration,double co_concentration,double spm_concentration){
        int aqi0,aqi1,aqi2;
        aqi0= AQI_Calculator.calculateAQI_forSinglePollutant(0,so2_concentration);
        aqi1= AQI_Calculator.calculateAQI_forSinglePollutant(1,co_concentration);
        aqi2= AQI_Calculator.calculateAQI_forSinglePollutant(2,spm_concentration);
        return Math.max(aqi0,Math.max(aqi1,aqi2));
    }


}

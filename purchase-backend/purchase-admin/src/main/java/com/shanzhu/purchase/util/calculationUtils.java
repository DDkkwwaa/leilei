package com.shanzhu.purchase.util;

import com.shanzhu.purchase.constant.Constant;

import java.math.BigDecimal;


/**
 * (kg)公斤 = (volume)容积（长cmx宽cmx高cm）/6000     1立方米=10的六次方厘米
 * <p>
 * Volume  容积立方米
 */
public class calculationUtils {

    public static BigDecimal INTCalculatingVolumeToWeight(Long Volume) {
        Volume = Volume * Constant.SQUARECEMETERTONTIMETER;     //转为立方厘米
        BigDecimal a = new BigDecimal(String.valueOf(Volume));
        BigDecimal weight = a.divide(BigDecimal.valueOf(Constant.DENOMINATOR), 0);  //除法，
        return weight;
    }

}

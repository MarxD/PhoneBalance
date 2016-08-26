package com.myasapp.djn.phonebalance.Model.Utils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/8/9.
 */
public class RoundUpUtils {
    public static double getDecimals(Double value,int scale)
    {
        return new BigDecimal(value).setScale(scale,BigDecimal.ROUND_UP).doubleValue();
    }
}

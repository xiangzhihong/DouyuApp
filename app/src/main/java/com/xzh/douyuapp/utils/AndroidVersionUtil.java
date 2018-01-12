package com.xzh.douyuapp.utils;

import android.os.Build;


public class AndroidVersionUtil {
    /**
     * Android历史版本及代号
     *          纸杯蛋糕（Cupcake）                       1.5
     *          甜甜圈（Donut）                           1.6
     *          闪电泡芙（Éclair）                        2.0/2.1
     *          冻酸奶（Froyo）                           2.2
     *          姜饼（Gingerbread）                       2.3
     *          蜂巢（Honeycomb）                         3.0
     *          冰淇淋三明治（Ice Cream Sandwich）         4.0
     *          果冻豆（Jelly Bean）                      4.1
     *          奇巧（KitKat）                            4.4.2
     *          棒棒糖（Lollipop）                        5.0.1
     *          棉花糖（Marshmallow）                     6.0
     *          牛轧糖（Nougat）                          7.0
     */

    public static boolean isFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }


    public static boolean isGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }


    public static boolean isGingerbreadMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1;
    }


    public static boolean isHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }


    public static boolean isHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }


    public static boolean isIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }


    public static boolean isIceCreamSandwichMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    }


    public static boolean isJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }


    public static boolean isKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }


    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


    public static boolean isLollipopMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }


    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    public static boolean isNougat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }


}

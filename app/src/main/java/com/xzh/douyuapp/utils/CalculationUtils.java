package com.xzh.douyuapp.utils;

import java.text.DecimalFormat;


public class CalculationUtils {

      public static String  getOnLine(int number)
      {
          DecimalFormat df=new DecimalFormat(".#");
               if(number<10000)
               {
                   return number+"";
               }
              else{
                     double  nums=(double)number/10000;
                   String result=df.format(nums);
                   return  result+"万";
               }
      }

}

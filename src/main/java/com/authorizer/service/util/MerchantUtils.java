package com.authorizer.service.util;

public class MerchantUtils {

    private static final String FOOD = "MERCADO PADARIA ACOGUE VERDUREIRA FRUTA";
    private static final String MEAL = "UBER IFOOD RESTAURANTE";
    public static String findMccByMerchantName(String merchant){
        String[] strings = merchant.split(" ");

        for(String word: strings){
            if(FOOD.contains(word)){
                return "FOOD";
            }
            if(MEAL.contains(word)){
                return "MEAL";
            }
        }

        return "";
    }

}
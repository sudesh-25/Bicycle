package bicycle.components;

import bicycle.pricing.PriceCalculator;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Frame implements IComponentPrice{
    private static Double FIXED_PRICE = 2500d;
    private static Map<String, String> subComponentVsCategory = new HashMap<>();

    private void fillDefaultCategory(){
        subComponentVsCategory.put("carbonFibreFrame", "frameMaterial");
        subComponentVsCategory.put("aluminiumFrame", "frameMaterial");
        subComponentVsCategory.put("steelFrame", "frameMaterial");
        subComponentVsCategory.put("titaniumFrame", "frameMaterial");
    }

    @Override
    public Double getPrice(JSONObject componentArchitecture) {
        fillDefaultCategory();
        return calculatePrice(componentArchitecture);
    }

    @Override
    public Double calculatePrice(JSONObject subComponentName) {
        Double calculatedPrice = FIXED_PRICE;
        PriceCalculator priceCalculator = new PriceCalculator();
        Set keySet = subComponentName.keySet();
        for(Object keyStr: keySet){
            Object keyValue = subComponentName.get(keyStr);
            System.out.println("key: "+ keyStr + " value: " + keyValue);
            if(subComponentVsCategory.containsKey((String)keyValue)){
                if(!subComponentVsCategory.get((String)keyValue).equals((String)keyStr)){
                    System.out.println("Invalid pair - key: " + (String)keyStr + " | value: " + (String)keyValue);
                    continue;
                }
            } else if(PriceCalculator.isNumeric((String)keyValue)){
                if(!subComponentVsCategory.get("Integer").equals((String)keyStr)){
                    System.out.println("Invalid pair - key: " + (String)keyStr + " | value: " + (String)keyValue);
                    continue;
                }
            }

            calculatedPrice += priceCalculator.calculatePrice(keyStr, keyValue);
        };
        return calculatedPrice;
    }
}

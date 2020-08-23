package bicycle.components;

import bicycle.pricing.PriceCalculator;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Components implements IComponentPrice {
    @Override
    public Double calculatePrice(JSONObject subComponentName, HashMap subComponentVsCategory, Double fixedPrice) {
        Double calculatedPrice = fixedPrice;
        PriceCalculator priceCalculator = new PriceCalculator();
        Set keySet = subComponentName.keySet();
        for(Object keyStr: keySet){
            Object keyValue = subComponentName.get(keyStr);

            if(subComponentVsCategory.containsKey(keyValue)){
                if(!subComponentVsCategory.get(keyValue).equals(keyStr)){
                    System.out.println("Invalid pair - key: " + keyStr + " | value: " + keyValue);
                    continue;
                }
            } else if(PriceCalculator.isNumeric((String)keyValue)){
                if(!subComponentVsCategory.get("Integer").equals(keyStr)){
                    System.out.println("Invalid pair - key: " + keyStr + " | value: " + keyValue);
                    continue;
                }
            } else if(!subComponentVsCategory.containsKey(keyValue)){
                System.out.println("Invalid pair - key: " + keyStr + " | value: " + keyValue);
                continue;
            }

            calculatedPrice += priceCalculator.calculatePrice(keyStr, keyValue);
        }
        return calculatedPrice;
    }
}

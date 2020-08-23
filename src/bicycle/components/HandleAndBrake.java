package bicycle.components;

import bicycle.pricing.PriceCalculator;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HandleAndBrake extends Components {
    private static Double FIXED_PRICE = 2000d;
    private static HashMap<String, String> subComponentVsCategory = new HashMap<>();

    private void fillDefaultCategory(){
        subComponentVsCategory.put("softGrip", "grip");
        subComponentVsCategory.put("hardGrip", "grip");
        subComponentVsCategory.put("flatHandle", "handleBar");
        subComponentVsCategory.put("butterflyHandle", "handleBar");
        subComponentVsCategory.put("normalBrake", "brake");
        subComponentVsCategory.put("discBrake", "brake");
        subComponentVsCategory.put("hydraulic", "brakeType");
        subComponentVsCategory.put("caliper", "brakeType");
    }

    public Double getPrice(JSONObject componentArchitecture) {
        fillDefaultCategory();
        return calculatePrice(componentArchitecture, subComponentVsCategory, FIXED_PRICE);
    }
}

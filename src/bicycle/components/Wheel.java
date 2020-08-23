package bicycle.components;

import bicycle.pricing.PriceCalculator;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Wheel extends Components {
    private static Double FIXED_PRICE = 2000d;
    private static HashMap<String, String> subComponentVsCategory = new HashMap<>();

    private void fillDefaultCategory(){
        subComponentVsCategory.put("wideTyre", "tyre");
        subComponentVsCategory.put("normalTyre", "tyre");
        subComponentVsCategory.put("Integer", "rim");
        subComponentVsCategory.put("steelSpoke", "spoke");
        subComponentVsCategory.put("aluminiumSpoke", "spoke");
        subComponentVsCategory.put("tubeless", "tube");
        subComponentVsCategory.put("withTube", "tube");
    }

    public Double getPrice(JSONObject componentArchitecture) {
        fillDefaultCategory();
        return calculatePrice(componentArchitecture, subComponentVsCategory, FIXED_PRICE);
    }


}

package bicycle.components;

import bicycle.pricing.PriceCalculator;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Frame extends Components{
    private static Double FIXED_PRICE = 2500d;
    private static HashMap<String, String> subComponentVsCategory = new HashMap<>();

    private void fillDefaultCategory(){
        subComponentVsCategory.put("carbonFibreFrame", "frameMaterial");
        subComponentVsCategory.put("aluminiumFrame", "frameMaterial");
        subComponentVsCategory.put("steelFrame", "frameMaterial");
        subComponentVsCategory.put("titaniumFrame", "frameMaterial");
    }

    public Double getPrice(JSONObject componentArchitecture) {
        fillDefaultCategory();
        return calculatePrice(componentArchitecture, subComponentVsCategory, FIXED_PRICE);
    }
}

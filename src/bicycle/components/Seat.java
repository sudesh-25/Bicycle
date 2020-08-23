package bicycle.components;

import bicycle.pricing.PriceCalculator;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Seat extends Components {
    private static Double FIXED_PRICE = 1500d;
    private static HashMap<String, String> subComponentVsCategory = new HashMap<>();

    private void fillDefaultCategory(){
        subComponentVsCategory.put("fibreSeat", "seatMaterial");
        subComponentVsCategory.put("nylonSeat", "seatMaterial");
        subComponentVsCategory.put("leatherSeat", "seatMaterial");
        subComponentVsCategory.put("performanceSaddle", "seatType");
        subComponentVsCategory.put("cushionedSaddle", "seatType");
    }

    public Double getPrice(JSONObject componentArchitecture) {
        fillDefaultCategory();
        return calculatePrice(componentArchitecture, subComponentVsCategory, FIXED_PRICE);
    }
}

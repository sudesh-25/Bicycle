package bicycle.components;

import org.json.simple.JSONObject;

import java.util.HashMap;

public interface IComponentPrice {
    Double calculatePrice(JSONObject subComponentName, HashMap subComponentVsCategory, Double fixedPrice);
}

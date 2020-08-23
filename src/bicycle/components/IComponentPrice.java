package bicycle.components;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IComponentPrice {
    public Double getPrice(JSONObject componentArchitecture);

    public Double calculatePrice(JSONObject subComponentName);
}

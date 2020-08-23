package bicycle.pricing;

public class PriceCalculator {

    public Double calculatePrice(Object key, Object value){
        Double compPrice = 0d;
        String keyStr = key.toString();
        String valStr = value.toString();
        if(isNumeric(valStr)){
            if(ComponentPriceConstants.valueOf(keyStr) != null) {
                compPrice = ComponentPriceConstants.valueOf(keyStr).getValue() * Integer.parseInt(valStr);
            } else{
                System.out.println("Invalid pair - key: " + keyStr + " / value: " + valStr);
                compPrice = 0d;
            }
        } else{
            if(ComponentPriceConstants.valueOf(valStr) != null) {
                compPrice = ComponentPriceConstants.valueOf(valStr).getValue();
            } else{
                compPrice = 0d;
            }
        }
        return compPrice;
    }

    public static boolean isNumeric(String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.chars().allMatch(Character::isDigit);

    }
}

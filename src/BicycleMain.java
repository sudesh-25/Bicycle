import bicycle.components.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BicycleMain {

    private static final String START_DATE = "2020-01-01";
    private static final Double INTEREST_RATE = 10d;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final String BICYCLE = "bicycle";
    private static final String DATE = "dateForPricing";

    BicycleMain(){
    }

    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser();
        Integer numQuarters = 0;
        Double finalWheelPrice = 0d;
        Double finalHandleAndBrakePrice = 0d;
        Double finalFramePrice = 0d;
        Double finalSeatPrice = 0d;
        Double finalChainAssemblyPrice = 0d;
        Double finalBicyclePrice = 0d;

        Scanner scan = new Scanner(System.in);
        System.out.println("Provide File path: (-1 for exit)");
        String fileName = scan.nextLine();

        while(!fileName.equals("-1")) {
            try {
                Object obj;
                JSONObject jsonObject = new JSONObject();
                try {
                    obj = parser.parse(new FileReader(fileName));
                    jsonObject = (JSONObject) obj;
                    numQuarters = calculateNumberOfQuarters(jsonObject);
                } catch (ParseException e) {
                    System.out.println("Date: " + jsonObject.get(DATE) + " not provided in right " +
                            "format: 'yyyy-MM-dd', cannot calculate pricing");
                    continue;
                } catch(FileNotFoundException e1){
                    System.out.println("File not exists at given location: " + fileName);
                    continue;
                }

                if (jsonObject.get(BICYCLE) instanceof String) {
                    System.out.println("No components provided for bicycle printing default price for all components");
                    finalWheelPrice = getDefaultFinalComponentPrice(ComponentNameConstants.wheel.getValue(), numQuarters);
                    finalHandleAndBrakePrice = getDefaultFinalComponentPrice(
                            ComponentNameConstants.handleAndBrake.getValue(), numQuarters);
                    finalFramePrice = getDefaultFinalComponentPrice(ComponentNameConstants.frame.getValue(), numQuarters);
                    finalSeatPrice = getDefaultFinalComponentPrice(ComponentNameConstants.seat.getValue(), numQuarters);
                    finalChainAssemblyPrice = getDefaultFinalComponentPrice(ComponentNameConstants.chainassembly.getValue(),
                            numQuarters);
                } else {
                    JSONObject bicycleObject = (JSONObject) jsonObject.get(BICYCLE);
                    if (bicycleObject == null) {
                        System.out.println("No bicycle key exists in provided Json, cannot provide a pricing");
                        continue;
                    } else {

                        if (bicycleObject.get(ComponentNameConstants.wheel.getValue()) != null) {
                            Wheel wheelObject = new Wheel();
                            finalWheelPrice = calculateCompoundInterest(
                                    wheelObject.getPrice((JSONObject) bicycleObject.get(
                                            ComponentNameConstants.wheel.getValue())), numQuarters);
                        } else {
                            finalWheelPrice = getDefaultFinalComponentPrice(ComponentNameConstants.wheel.getValue(), numQuarters);
                        }

                        if (bicycleObject.get(ComponentNameConstants.handleAndBrake.getValue()) != null) {
                            HandleAndBrake handleAndBrakeObject = new HandleAndBrake();
                            finalHandleAndBrakePrice = calculateCompoundInterest(
                                    handleAndBrakeObject.getPrice((JSONObject) bicycleObject.get(
                                            ComponentNameConstants.handleAndBrake.getValue())), numQuarters);
                        } else {
                            finalHandleAndBrakePrice = getDefaultFinalComponentPrice(
                                    ComponentNameConstants.handleAndBrake.getValue(), numQuarters);
                        }

                        if (bicycleObject.get(ComponentNameConstants.frame.getValue()) != null) {
                            Frame frameObject = new Frame();
                            finalFramePrice = calculateCompoundInterest(
                                    frameObject.getPrice((JSONObject) bicycleObject.get(
                                            ComponentNameConstants.frame.getValue())), numQuarters);
                        } else {
                            finalFramePrice = getDefaultFinalComponentPrice(ComponentNameConstants.frame.getValue(), numQuarters);
                        }

                        if (bicycleObject.get(ComponentNameConstants.seat.getValue()) != null) {
                            Seat seatObject = new Seat();
                            finalSeatPrice = calculateCompoundInterest(
                                    seatObject.getPrice((JSONObject) bicycleObject.get(
                                            ComponentNameConstants.seat.getValue())), numQuarters);
                        } else {
                            finalSeatPrice = getDefaultFinalComponentPrice(ComponentNameConstants.seat.getValue(), numQuarters);
                        }

                        if (bicycleObject.get(ComponentNameConstants.chainassembly.getValue()) != null) {
                            ChainAssembly chainAssemblyObject = new ChainAssembly();
                            finalChainAssemblyPrice = calculateCompoundInterest(
                                    chainAssemblyObject.getPrice((JSONObject) bicycleObject.get(
                                            ComponentNameConstants.chainassembly.getValue())), numQuarters);
                        } else {
                            finalChainAssemblyPrice = getDefaultFinalComponentPrice(ComponentNameConstants.chainassembly.getValue(),
                                    numQuarters);
                        }
                    }
                }

                finalBicyclePrice = finalWheelPrice + finalHandleAndBrakePrice + finalFramePrice + finalSeatPrice
                        + finalChainAssemblyPrice;

                printFinalPrices(finalBicyclePrice, finalWheelPrice, finalHandleAndBrakePrice, finalFramePrice,
                        finalSeatPrice, finalChainAssemblyPrice);

            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Provide File path: (-1 for exit)");
                fileName = scan.nextLine();
            }
        }


    }

    private static Integer calculateNumberOfQuarters(JSONObject jsonObject) throws ParseException {
        Calendar defaultTime = new GregorianCalendar();
        Calendar billDate = new GregorianCalendar();
        String date;
        try {
            date = (String) jsonObject.get(DATE);

            if (date == null || date.isEmpty()) {
                System.out.println("Date parameter is missing, taking current date as default.");
                date = formatter.format(new Date());
            }

            defaultTime.setTime(formatter.parse(START_DATE));
            billDate.setTime(formatter.parse(date));
        } catch(ParseException e){
            throw new ParseException(e.getMessage(), 0);
        }

        Integer yearsDifference = billDate.get(Calendar.YEAR) - defaultTime.get(Calendar.YEAR);

        if (yearsDifference < 0) {
            System.out.println("Provided date: " + date + " is lesser than start date: " + START_DATE +
                    ", Cannot provide a price for that");
            System.exit(0);
        }

        Integer numQuarters = (yearsDifference * 12 + (billDate.get(Calendar.MONTH)
                - defaultTime.get(Calendar.MONTH))) / 3;
        return numQuarters;

    }

    private static Double calculateCompoundInterest(Double principle, Integer numQuarters) {
        Double amount = principle * Math.pow(1 + INTEREST_RATE / 100, numQuarters);
        return amount;
    }

    private static Double getDefaultFinalComponentPrice(String compName, Integer numQuarters){
        if(ComponentNameConstants.wheel.getValue().equals(compName)){
            Wheel wheelObject = new Wheel();
            return calculateCompoundInterest(wheelObject.getPrice(new JSONObject()), numQuarters);
        } else if(ComponentNameConstants.handleAndBrake.getValue().equals(compName)){
            HandleAndBrake handleAndBrakeObject = new HandleAndBrake();
            return calculateCompoundInterest(handleAndBrakeObject.getPrice(new JSONObject()), numQuarters);
        } else if(ComponentNameConstants.frame.getValue().equals(compName)){
            Frame frameObject = new Frame();
            return calculateCompoundInterest(frameObject.getPrice(new JSONObject()), numQuarters);
        } else if(ComponentNameConstants.seat.getValue().equals(compName)){
            Seat seatObject = new Seat();
            return calculateCompoundInterest(seatObject.getPrice(new JSONObject()), numQuarters);
        } else if(ComponentNameConstants.chainassembly.getValue().equals(compName)){
            ChainAssembly chainAssemblyObject = new ChainAssembly();
            return calculateCompoundInterest(chainAssemblyObject.getPrice(new JSONObject()), numQuarters);
        }
        return 0d;
    }

    private static void printFinalPrices(Double finalBicyclePrice, Double finalWheelPrice,
                                         Double finalHandleAndBrakePrice, Double finalFramePrice, Double finalSeatPrice,
                                         Double finalChainAssemblyPrice){
        System.out.println("Bicycle Price: " + String.format("%.2f", finalBicyclePrice));
        System.out.println("Wheel Price: " + String.format("%.2f", finalWheelPrice));
        System.out.println("Handle And Brake Price: " + String.format("%.2f", finalHandleAndBrakePrice));
        System.out.println("Frame Price: " + String.format("%.2f", finalFramePrice));
        System.out.println("Seat Price: " + String.format("%.2f", finalSeatPrice));
        System.out.println("Chain Assembly Price: " + String.format("%.2f", finalChainAssemblyPrice));
    }
}

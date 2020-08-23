package bicycle.pricing;

public enum ComponentPriceConstants {

    alloy(200d), tubeless(200d), rim(10d), wideTyre(200d), aluminiumSpoke(300d), softGrip(200d), butterflyHandle(500d),
    discBrake(400d), hydraulic(700d), carbonFibreFrame(800d), aluminiumFrame(400d), titaniumFrame(1000d),
    fibreSeat(400d), nylonSeat(200d), performanceSaddle(500d), gears(200d);

    private Double price;
    ComponentPriceConstants(Double price) {
        this.price = price;
    }

    public Double getValue(){
        return this.price;
    }
}

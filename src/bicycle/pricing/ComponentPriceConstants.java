package bicycle.pricing;

public enum ComponentPriceConstants {

    alloy(200d), tubeless(200d), rim(10d), wideTyre(200d), aluminiumSpoke(300d), softGrip(200d), butterflyHandle(500d),
    discBrake(400d), hydraulic(700d), carbonFibreFrame(800d), aluminiumFrame(400d), titaniumFrame(1000d),
    fibreSeat(400d), nylonSeat(200d), performanceSaddle(500d), gear(200d), steelSpoke(0d), normalTyre(0d), withTube(0d),
    hardGrip(0d), flatHandle(0d), normalBrake(0d), caliper(0d), steelFrame(0d), leatherSeat(0d), cushionedSaddle(0d);

    private Double price;
    ComponentPriceConstants(Double price) {
        this.price = price;
    }

    public Double getValue(){
        return this.price;
    }
}

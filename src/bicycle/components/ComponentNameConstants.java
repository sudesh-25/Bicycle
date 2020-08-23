package bicycle.components;

public enum ComponentNameConstants {
    wheel("wheel"), chainAssembly("chainAssembly"), frame("frame"),
        handleAndBrake("handleAndBrake"), seat("seat");

    private String compName;
    ComponentNameConstants(String compName) {
        this.compName = compName;
    }

    public String getValue(){
        return this.compName;
    }
}

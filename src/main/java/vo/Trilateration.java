package vo;

public class Trilateration {

    private final Beacon beaconA;
    private final Beacon beaconB;
    private final Beacon beaconC;

    private final Double distanceFromA;
    private final Double distanceFromB;
    private final Double distanceFromC;

    public Trilateration(Beacon beaconA, Beacon beaconB, Beacon beaconC, Double distanceFromA, Double distanceFromB, Double distanceFromC){
        this.beaconA = beaconA;
        this.beaconB = beaconB;
        this.beaconC = beaconC;
        this.distanceFromA = distanceFromA;
        this.distanceFromB = distanceFromB;
        this.distanceFromC = distanceFromC;
    }

    public Beacon getBeaconA() {
        return beaconA;
    }

    public Beacon getBeaconB() {
        return beaconB;
    }

    public Beacon getBeaconC() {
        return beaconC;
    }

    public Double getDistanceFromA() {
        return distanceFromA;
    }

    public Double getDistanceFromB() {
        return distanceFromB;
    }

    public Double getDistanceFromC() {
        return distanceFromC;
    }
}

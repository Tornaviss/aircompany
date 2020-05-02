package test;

import main.Airport;
import main.types.ClassificationLevel;
import main.planes.ExperimentalPlane;
import main.types.ExperimentalType;
import main.types.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.planes.MilitaryPlane;
import main.planes.PassengerPlane;
import main.planes.Plane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AirportTest {
    private static List<Plane> planes;
    private static MilitaryPlane transportMilitaryPlane;
    private static PassengerPlane passengerPlaneWithMaxPassengerCapacity;

    @BeforeClass
    public static void init() {
        passengerPlaneWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
        transportMilitaryPlane = new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT);

        planes = Arrays.asList(
                new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
                new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
                passengerPlaneWithMaxPassengerCapacity,
                new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
                new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
                new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
                new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
                new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
                new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
                new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
                new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
                transportMilitaryPlane,
                new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
                new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalType.VTOL, ClassificationLevel.TOP_SECRET)
        );

    }

    @Test
    public void getTransportMilitaryPlanes() {
        List<MilitaryPlane> actual = new Airport(planes).getTransportMilitaryPlanes();
        Assert.assertTrue(actual.contains(transportMilitaryPlane));
    }

    @Test
    public void getPassengerPlaneWithMaxCapacity() {
        PassengerPlane actual = new Airport(planes).getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(actual, passengerPlaneWithMaxPassengerCapacity);
    }

    @Test
    public void sortPlanesByMaxLoadCapacity() {
        List<Plane> expected = new ArrayList<>(planes);
        expected.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));

        Airport airport = new Airport(planes);
        airport.sortPlanesByMaxLoadCapacity();
        List<? extends Plane> actual = airport.getPlanes();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void hasAtLeastOneBomberInMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        Assert.assertTrue(bomberMilitaryPlanes.size() >= 1);
    }

    @Test
    public void getExperimentalPlanesHasNoClassificationLevelUnclassified(){
        List<ExperimentalPlane> experimentalPlanes = new Airport(planes).getExperimentalPlanes();

        boolean hasUnclassifiedPlanes = experimentalPlanes.stream()
                .anyMatch(plane -> plane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED);

        Assert.assertFalse(hasUnclassifiedPlanes);
    }
}

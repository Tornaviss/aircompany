package main;

import main.planes.ExperimentalPlane;
import main.planes.Plane;
import main.types.MilitaryType;
import main.planes.MilitaryPlane;
import main.planes.PassengerPlane;

import java.util.*;
import java.util.stream.Collectors;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {

    private final List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return (List<PassengerPlane>)planes.stream()
                .filter(x -> x instanceof PassengerPlane)
                .collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlanes().stream()
                .max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity))
                .orElseThrow(NoSuchElementException::new);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return (List<MilitaryPlane>)planes.stream()
                .filter(x -> x instanceof MilitaryPlane)
                .collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return (List<ExperimentalPlane>)planes.stream()
                .filter(x -> x instanceof ExperimentalPlane)
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
    return getMilitaryPlanes().stream()
            .filter(x -> x.getType() == MilitaryType.TRANSPORT)
            .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getMilitaryPlanes().stream()
                .filter(x -> x.getType() == MilitaryType.BOMBER)
                .collect(Collectors.toList());
    }

    public void sortPlanesByMaxDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
    }

    public void sortPlanesByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
    }

    public void sortPlanesByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

}

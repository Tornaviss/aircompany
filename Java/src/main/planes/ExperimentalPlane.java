package main.planes;

import main.types.ClassificationLevel;
import main.types.ExperimentalType;

public class ExperimentalPlane extends Plane {

    private final ExperimentalType type;
    private final ClassificationLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity,
                             ExperimentalType type, ClassificationLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.type = type;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevel getClassificationLevel(){
        return classificationLevel;
    }

    public ExperimentalType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ExperimentalPlane{" +
                "type=" + type +
                ", classificationLevel=" + classificationLevel +
                '}';
    }
}

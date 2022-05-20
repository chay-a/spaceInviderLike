package campus.valence.movement;

public interface InMovement {
    void move();

    boolean intersect(InMovement other);
}

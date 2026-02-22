
import processing.core.PImage;
import java.util.List;

public abstract class Entity {

    private final String id;
    private Point position;
    private final List<PImage> images;

    public Entity(String id,
                  Point position,
                  List<PImage> images) {
        this.id = id;
        this.position = position;
        this.images = images;
    }

    public PImage getCurrentImage() { return this.images.get(0); }

    protected List<PImage> getImages() { return images; }

    protected String getId() { return id; }

    public Point getPosition() { return position; }

    void setPosition(Point newPoint) { position = newPoint; }
}


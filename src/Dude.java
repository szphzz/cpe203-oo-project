
import processing.core.PImage;
import java.util.List;

public abstract class Dude extends Moving {

    private int resourceLimit;

    public Dude(String id,
                Point position,
                List<PImage> images,
                int animationPeriod,
                int actionPeriod,
                int resourceLimit) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
    }

    protected int getResourceLimit() { return resourceLimit; }
}

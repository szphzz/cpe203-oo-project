
import processing.core.PImage;
import java.util.List;

public abstract class Plant extends ExecuteActivity {

    private int health;

    public Plant(String id,
                 Point position,
                 List<PImage> images,
                 int animationPeriod,
                 int actionPeriod,
                 int health) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
    }

    protected abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);

    protected abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    protected int getHealth() { return health; }

    public void increaseHealth() { health++; }

    public void decreaseHealth() { health--; }
}

import processing.core.PImage;
import java.util.List;

public class Sapling extends Plant
{
    private final int healthLimit;

    public Sapling(String id,
                   Point position,
                   List<PImage> images,
                   int animationPeriod,
                   int actionPeriod,
                   int health,
                   int healthLimit) {
        super(id, position, images, animationPeriod, actionPeriod, health);
        this.healthLimit = healthLimit;
    }

    protected boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        if (super.getHealth() <= 0) {
            Entity stump = Factory.createStump(super.getId(),
                    super.getPosition(),
                    imageStore.getImageList(Load.STUMP_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(stump);

            return true;
        }
        else if (super.getHealth() >= healthLimit)
        {
            Animated tree = Factory.createTree("tree_" + super.getId(),
                    super.getPosition(),
                    imageStore.getImageList(Load.TREE_KEY),
                    Load.getNumFromRange(Load.TREE_ANIMATION_MAX, Load.TREE_ANIMATION_MIN),
                    Load.getNumFromRange(Load.TREE_ACTION_MAX, Load.TREE_ACTION_MIN),
                    Load.getNumFromRange(Load.TREE_HEALTH_MAX, Load.TREE_HEALTH_MIN));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        super.increaseHealth();
        if (!this.transform(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    super.getActionPeriod());
        }
    }
}

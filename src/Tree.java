
import processing.core.PImage;
import java.util.List;

public class Tree extends Plant
{
    public Tree(String id,
                 Point position,
                 List<PImage> images,
                 int animationPeriod,
                 int actionPeriod,
                 int health) {
        super(id, position, images, animationPeriod, actionPeriod, health);
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

        return false;
    }

    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        if (!this.transform(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    super.getActionPeriod());
        }
    }
}

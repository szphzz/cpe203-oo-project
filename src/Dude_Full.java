
import processing.core.PImage;
import java.util.*;

public class Dude_Full extends Dude
{
    public Dude_Full(String id,
                Point position,
                List<PImage> images,
                int animationPeriod,
                int actionPeriod,
                int resourceLimit) {
        super(id, position, images, animationPeriod, actionPeriod, resourceLimit);
    }

    private void transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        Animated miner = Factory.createDudeNotFull(super.getId(), super.getPosition(),
                super.getImages(), super.getAnimationPeriod(), super.getActionPeriod(),
                super.getResourceLimit());

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        miner.scheduleActions(scheduler, world, imageStore);
    }

    protected boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        return true;
    }

    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> fullTarget =
                world.findNearest(super.getPosition(), new ArrayList<>(Arrays.asList(House.class)));

        if (fullTarget.isPresent() && this.moveTo(world,
                fullTarget.get(), scheduler))
        {
            this.transform(world, scheduler, imageStore);
        }
        else {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    super.getActionPeriod());
        }
    }
}

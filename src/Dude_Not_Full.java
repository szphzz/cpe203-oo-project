
import processing.core.PImage;
import java.util.*;

public class Dude_Not_Full extends Dude {

    private int resourceCount;

    public Dude_Not_Full(String id,
                         Point position,
                         List<PImage> images,
                         int animationPeriod,
                         int actionPeriod,
                         int resourceLimit) {
        super(id, position, images, animationPeriod, actionPeriod, resourceLimit);
        this.resourceCount = 0;
    }

    private boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (resourceCount >= super.getResourceLimit()) {
            Animated miner = Factory.createDudeFull(super.getId(),
                    super.getPosition(), super.getImages(), super.getAnimationPeriod(),
                    super.getActionPeriod(), super.getResourceLimit());

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(miner);
            miner.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    protected boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        resourceCount += 1;
        if (target instanceof Plant) {
            ((Plant) target).decreaseHealth();
        }
        return true;
    }

    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> target =
                world.findNearest(super.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (!target.isPresent() || !this.moveTo(world,
                target.get(),
                scheduler)
                || !this.transform(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    super.getActionPeriod());
        }
    }
}


import processing.core.PImage;
import java.util.*;

public class Fairy extends Moving
{
    public Fairy(String id,
                Point position,
                List<PImage> images,
                int animationPeriod,
                int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    protected boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);
        return true;
    }

    protected void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> fairyTarget =
                world.findNearest(super.getPosition(), new ArrayList<>(Arrays.asList(Stump.class)));

        if (fairyTarget.isPresent()) {
            Entity fairy = fairyTarget.get();
            Point tgtPos = fairy.getPosition();

            if (this.moveTo(world, fairy, scheduler)) {
                Animated sapling = Factory.createSapling("sapling_" + super.getId(), tgtPos,
                        imageStore.getImageList(Load.SAPLING_KEY));

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                super.getActionPeriod());
    }
}

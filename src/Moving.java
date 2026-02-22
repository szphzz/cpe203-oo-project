
import processing.core.PImage;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class Moving extends ExecuteActivity {

    public Moving(String id,
                  Point position,
                  List<PImage> images,
                  int animationPeriod,
                  int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    protected Point nextPosition(WorldModel world, Point destPos)
    {
        PathingStrategy strategy = new AStarPathingStrategy();

        List<Point> path = strategy.computePath(getPosition(),
                destPos,
                p -> (world.withinBounds(p) && !world.isOccupied(p)),
                Point::adjacent,
                PathingStrategy.CARDINAL_NEIGHBORS);

        if (path.size() > 0) { return path.get(0); }
        return super.getPosition();
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (Point.adjacent(super.getPosition(), target.getPosition())) {
            return _moveTo(world, target, scheduler);
        }
        else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!super.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    protected abstract boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler);
}
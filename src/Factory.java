import processing.core.PImage;
import java.util.List;

public class Factory {

    public static Action createAnimationAction(Animated entity,
                                               int repeatCount)
    {
        return new Animation(entity, repeatCount);
    }

    public static Action createActivityAction(ExecuteActivity entity,
                                              WorldModel world,
                                              ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore);
    }

    public static Entity createHouse(String id,
                                     Point position,
                                     List<PImage> images)
    {
        return new House(id, position, images);
    }

    public static Animated createObstacle(String id,
                                          Point position,
                                          List<PImage> images,
                                          int animationPeriod)
    {
        return new Obstacle(id, position, images, animationPeriod);
    }

    public static Plant createTree(String id,
                                   Point position,
                                   List<PImage> images,
                                   int animationPeriod,
                                   int actionPeriod,
                                   int health)
    {
        return new Tree(id, position, images, actionPeriod, animationPeriod, health);
    }

    public static Entity createStump(String id, Point position, List<PImage> images)
    {
        return new Stump(id, position, images);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static Plant createSapling(String id,
                                      Point position,
                                      List<PImage> images)
    {
        return new Sapling(id, position, images, Load.SAPLING_ACTION_ANIMATION_PERIOD,
                Load.SAPLING_ACTION_ANIMATION_PERIOD, 0, Load.SAPLING_HEALTH_LIMIT);
    }

    public static Moving createFairy(String id,
                                     Point position,
                                     List<PImage> images,
                                     int animationPeriod,
                                     int actionPeriod)
    {
        return new Fairy(id, position, images, animationPeriod, actionPeriod);
    }

    // need resource count, though it always starts at 0
    public static Moving createDudeNotFull(String id,
                                           Point position,
                                           List<PImage> images,
                                           int animationPeriod,
                                           int actionPeriod,
                                           int resourceLimit)
    {
        return new Dude_Not_Full(id, position, images, animationPeriod, actionPeriod, resourceLimit);
    }

    // don't technically need resource count ... full
    public static Moving createDudeFull(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod,
            int actionPeriod,
            int resourceLimit)
    {
        return new Dude_Full(id, position, images, animationPeriod, actionPeriod, resourceLimit);
    }
}

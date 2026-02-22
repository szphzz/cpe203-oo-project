
public class Parse {

    public static boolean parseBackground(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Load.BGND_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Load.BGND_COL]),
                    Integer.parseInt(properties[Load.BGND_ROW]));
            String id = properties[Load.BGND_ID];
            world.setBackground(pt,
                    new Background(id, imageStore.getImageList(id)));
        }

        return properties.length == Load.BGND_NUM_PROPERTIES;
    }

    public static boolean parseSapling(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Load.SAPLING_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Load.SAPLING_COL]),
                    Integer.parseInt(properties[Load.SAPLING_ROW]));
            Entity entity = Factory.createStump(properties[Load.SAPLING_ID], pt,
                    imageStore.getImageList(Load.STUMP_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == Load.SAPLING_NUM_PROPERTIES;
    }

    public static boolean parseDude(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Load.DUDE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Load.DUDE_COL]),
                    Integer.parseInt(properties[Load.DUDE_ROW]));
            Entity entity = Factory.createDudeNotFull(properties[Load.DUDE_ID], pt,
                    imageStore.getImageList(Load.DUDE_KEY),
                    Integer.parseInt(properties[Load.DUDE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[Load.DUDE_ACTION_PERIOD]),
                    Integer.parseInt(properties[Load.DUDE_LIMIT]));
            world.tryAddEntity(entity);
        }

        return properties.length == Load.DUDE_NUM_PROPERTIES;
    }

    public static boolean parseFairy(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Load.FAIRY_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Load.FAIRY_COL]),
                    Integer.parseInt(properties[Load.FAIRY_ROW]));
            Entity entity = Factory.createFairy(properties[Load.FAIRY_ID], pt,
                    imageStore.getImageList(Load.FAIRY_KEY),
                    Integer.parseInt(properties[Load.FAIRY_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[Load.FAIRY_ACTION_PERIOD]));
            world.tryAddEntity(entity);
        }

        return properties.length == Load.FAIRY_NUM_PROPERTIES;
    }

    public static boolean parseTree(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Load.TREE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Load.TREE_COL]),
                    Integer.parseInt(properties[Load.TREE_ROW]));
            Entity entity = Factory.createTree(properties[Load.TREE_ID], pt,
                    imageStore.getImageList(Load.TREE_KEY),
                    Integer.parseInt(properties[Load.TREE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[Load.TREE_ACTION_PERIOD]),
                    Integer.parseInt(properties[Load.TREE_HEALTH]));
            world.tryAddEntity(entity);
        }

        return properties.length == Load.TREE_NUM_PROPERTIES;
    }

    public static boolean parseObstacle(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Load.OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Load.OBSTACLE_COL]),
                    Integer.parseInt(properties[Load.OBSTACLE_ROW]));
            Entity entity = Factory.createObstacle(properties[Load.OBSTACLE_ID], pt,
                    imageStore.getImageList(Load.OBSTACLE_KEY),
                    Integer.parseInt(properties[Load.OBSTACLE_ANIMATION_PERIOD]));
            world.tryAddEntity(entity);
        }

        return properties.length == Load.OBSTACLE_NUM_PROPERTIES;
    }

    public static boolean parseHouse(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Load.HOUSE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Load.HOUSE_COL]),
                    Integer.parseInt(properties[Load.HOUSE_ROW]));
            Entity entity = Factory.createHouse(properties[Load.HOUSE_ID], pt,
                    imageStore.getImageList(Load.HOUSE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == Load.HOUSE_NUM_PROPERTIES;
    }
}

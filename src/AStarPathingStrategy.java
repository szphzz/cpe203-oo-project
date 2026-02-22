import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;

class AStarPathingStrategy implements PathingStrategy
{
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        List<Point> path = new ArrayList<>();

        HashMap<Point, Node> openList = new HashMap<>();
        HashMap<Point, Node> closedList = new HashMap<>();
        List<Node> fVals = new ArrayList<>();

        Node curr = new Node(start, null, 0, 0);

        fVals.add(curr);
        openList.put(start, curr);

        // repeat until within reach of the goal
        while (!(withinReach.test(curr.p, end))) {
            List<Point> neighbors = potentialNeighbors.apply(curr.p)
                    .filter(canPassThrough)
                    .filter(pt -> !closedList.containsKey(pt))
                    .collect(Collectors.toList());

            // for each neighbor that is not on the closed list
            for (Point p : neighbors) {
                if (!(closedList.containsKey(p))) {
                    Node neighbor = new Node(p, curr, getF(curr, p, end), getG(p, curr));
                    int gVal = 0;

                    // determine g
                    if (!(closedList.get(p) == null)) {
                        gVal = getG(p, closedList.get(p));
                    }

                    // if node is already on open list & new g is better than old g
                    if (openList.containsKey(p) && gVal < curr.g) {
                        neighbor.g = gVal;
                        neighbor.f = getH(end, p) + gVal;
                        closedList.replace(p, neighbor);
                    }

                    // add new node to open list
                    if (!(openList.containsKey(p))) {
                        openList.put(p, neighbor);
                        fVals.add(neighbor);
                    }
                }
            }

            // move curr node to open list
            closedList.put(curr.p, curr);
            fComparator comp = new fComparator();
            Collections.sort(fVals, comp);

            // when cannot search anymore
            if (fVals.size() == 0) { return path; }

            // make curr node from open list w smallest f
            curr = fVals.get(0);
            fVals.remove(0);
            openList.remove(curr.p);
        }

        while (!(curr.p == start)){
            path.add((curr).p);
            closedList.remove(curr.p);
            curr = curr.prior;
        }

        Collections.reverse(path);
        return path;
    }

    private int getH(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y); // Manhattan
    }

    public int getG(Point p, Node curr){
        if (curr.p.x == p.x && curr.p.y - 1 == p.y ||
                curr.p.x == p.x && curr.p.y + 1 == p.y ||
                curr.p.x + 1 == p.x && curr.p.y == p.y ||
                curr.p.x - 1 == p.x && curr.p.y == p.y)
            return curr.g + 1;
        else
            return curr.g;
    }

    public int getF(Node curr, Point p1, Point end){
        return getG(p1, curr) + getH(p1, end);
    }

    public class Node {
        public Point p;
        public Node prior;
        public int f;
        public int g;

        Node(Point p, Node prior, int f, int g){
            this.p = p;
            this.prior = prior;
            this.f = f;
            this.g = g;
        }
    }

    public class fComparator implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.f - n2.f;
        }
    }
}
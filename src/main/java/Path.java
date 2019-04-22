// Job: single responsibility is to know cost between two nodes
public class Path implements Comparable<Path> {
    int steps;
    boolean reachable;
    int cost;
//    public static final CountStrategy COUNT_COST = path -> path.cost;
//    public static final CountStrategy COUNT_STEPS = path -> 1;

//    public static final UNREACHABLE = new Path() {
//        @Override
//                public int compareTo(path other) {
//            return 1;
//
//        }
//    }
//
//    @Override
//          public int compareTo()


    Path(boolean reachable, int steps, int cost) {
        this.steps = steps;
        this.reachable = reachable;
        this.cost = cost;
    }

    int getSteps() {
        return this.steps;
    }

    int getCost() {
        return this.cost;
    }

    boolean isReachable() {
        return this.reachable;
    }
}
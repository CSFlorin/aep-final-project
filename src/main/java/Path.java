// Job: single responsibility is to know cost between two nodes
public class Path {
    int steps;
    boolean reachable;
    int cost;
//    public static final CountStrategy COUNT_COST = path -> path.cost;
//    public static final CountStrategy COUNT_STEPS = path -> 1;


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
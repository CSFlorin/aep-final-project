import java.util.*;

// Knows whom it's connected to
public class Node {
    Map<Node, Set<Integer>> outboundNeighbors;
    ArrayList<Path> paths;

    Node(Map<Node, Set<Integer>> outboundNeighbors) {
        this.outboundNeighbors = outboundNeighbors;
        Set<Integer> addition = new HashSet<>();
        addition.add(0);
        this.outboundNeighbors.put(this, addition);
        this.paths = new ArrayList<>();
    }

    Node() {
        this(new HashMap<>());
    }

    public Path reaches(Node other) {
        if (this == other) {
            return new Path(true, 0, 0);
        }
        return this.reaches(other, new HashSet<>(), 1, getCost(other));
    }

    private int getCost(Node other) {
        if (this.outboundNeighbors.containsKey(other)) {
            Object[] nodeCosts = this.outboundNeighbors.get(other).toArray();
            Integer[] costs = Arrays.copyOf(nodeCosts, nodeCosts.length, Integer[].class);
            int[] intCosts = Arrays.stream(costs).mapToInt(Integer::intValue).toArray();
            int minCost = Integer.MAX_VALUE;
            for (int cost: intCosts) {
                if (cost < minCost) {
                    minCost = cost;
                }
            }
            return minCost;
        }
        return 0;
    }

    private Path reaches(Node other, Set<Node> visitedNodes, int numSteps, int cost) {
        visitedNodes.add(this);
        if (this.outboundNeighbors.containsKey(other)) {
            this.paths.add(new Path(true, numSteps, getCost(other)));
        }
        for (Node neighbor: this.outboundNeighbors.keySet()) {
            if (!visitedNodes.contains(neighbor)) {
                Path current = neighbor.reaches(other, visitedNodes, numSteps + 1, cost + getCost(neighbor));
                if (current.isReachable()) {
                    this.paths.add(new Path(true, numSteps + 1,  current.getCost() + getCost(neighbor)));
                }
            }
        }
        Path shortestPath = new Path(false, Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Path path: paths) {
            if (path.getCost() < shortestPath.getCost()) {
                shortestPath = path;
            }
        }
        return shortestPath;
    }

    public void add(Node other, int cost) {
        Set<Integer> addition = new HashSet<>();
        addition.add(cost);
        this.outboundNeighbors.putIfAbsent(other, addition);
        this.outboundNeighbors.get(other).add(cost);
    }
}

import java.util.*;


public class Graph {

    class Edge {

        int from;
        String cityFrom;
        int to;
        String cityTo;
        double cost;
        double time;
        double rank;

        public Edge(int from, String cityFrom, int to, String cityTo, double cost, double time) {

            this.from = from;
            this.cityFrom = cityFrom;
            this.to = to;
            this.cityTo = cityTo;
            this.cost = cost;
            this.time = time;
            rank = cost / time;
        }

    }

    public static class Node {
        int id;
        double value;

        public Node(int id, double value) {
            this.id = id;
            this.value = value;
        }
    }

    private int vertices;
    private MyArrayList<MyArrayList<Edge>> graph;
    double[] finVal;
    int[] previousCity;
    double normCost = 0;
    double normTime = 0;
    MyArrayList<Double> normCostList = new MyArrayList<>();
    MyArrayList<Double> normTimeList = new MyArrayList<>();
    MyArrayList<String> optimizedPath = new MyArrayList<>();



    public Graph(int vertices) {

        this.vertices = vertices;

        initGraph();

    }

    public void initGraph() {

        graph = new MyArrayList<>(vertices);

        for(int i = 0; i < vertices; i++)
            graph.add(new MyArrayList<>());
    }

    public void addEdge(int from, String cityFrom, int to, String cityTo, double cost, double time) {

        graph.get(from).add(new Edge(from, cityFrom, to, cityTo, cost, time));

    }

    public void printAllPaths(int s, int d) {

        boolean[] isVisited = new boolean[vertices];
        MyStackWithLL<String> pathList = new MyStackWithLL<>();

        pathList.push(graph.get(s).get(0).cityFrom);

        printAllPathsUtility(s, d, isVisited, pathList);

    }

    private void printAllPathsUtility(int source, int destination,
                                   boolean[] isVisited,
                                  MyStackWithLL<String> localPathList) {

        if (source == destination) {

            System.out.println(Arrays.toString(localPathList.toArray()));
            isVisited[source]= false;
            normCostList.add(normCost);
            normTimeList.add(normTime);
            normCost = 0;
            normTime = 0;
            optimizedPath.add(Arrays.toString(localPathList.toArray()));
            return;

        }

        for (Edge i : graph.get(source)) {

            if (!(isVisited[i.from] || isVisited[i.to])) {

                isVisited[source] = true;

                localPathList.push(i.cityTo);
                normCost += i.cost;
                normTime += i.time;
                printAllPathsUtility(i.to, destination, isVisited, localPathList);

                localPathList.pop();

            }

            isVisited[source] = false;

        }

    }

    class valueComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.value == o2.value)
                return 0;
            return o1.value - o2.value > 0 ? 1 : -1;
        }
    }

    public double dijakstraDistance(int source, int destination) {

        boolean[] isVisited = new boolean[vertices];
        finVal = new double[vertices];

        for(int i = 0; i < finVal.length; i++)
            finVal[i] = Double.POSITIVE_INFINITY;

        finVal[source] = 0;
        previousCity = new int[vertices];
        for(int i = 0; i < previousCity.length; i++)
            previousCity[i] = -1;

        PriorityQueue<Node> pq = new PriorityQueue<>(2 * vertices, new valueComparator());
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            isVisited[node.id] = true;

            if(finVal[node.id] < node.value) continue;

            for (Edge edge : graph.get(node.id)) {

                if(isVisited[edge.to]) continue;

                double newDist = finVal[edge.from] + edge.cost;

                if(newDist < finVal[edge.to]) {

                    finVal[edge.to] = newDist;
                    previousCity[edge.to] = edge.from;
                    pq.add(new Node(edge.to, finVal[edge.to]));

                }

            }

            if (node.id == destination) return finVal[destination];

        }

        return Double.POSITIVE_INFINITY;

    }

    public double dijakstraTime(int source, int destination) {

        boolean[] isVisited = new boolean[vertices];
        finVal = new double[vertices];

        for(int i = 0; i < finVal.length; i++)
            finVal[i] = Double.POSITIVE_INFINITY;

        finVal[source] = 0;
        previousCity = new int[vertices];
        for(int i = 0; i < previousCity.length; i++)
            previousCity[i] = -1;

        PriorityQueue<Node> pq = new PriorityQueue<>(2 * vertices, new valueComparator());
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            isVisited[node.id] = true;

            if(finVal[node.id] < node.value) continue;

            for (Edge edge : graph.get(node.id)) {

                if(isVisited[edge.to]) continue;

                double newDist = finVal[edge.from] + edge.time;

                if(newDist < finVal[edge.to]) {

                    finVal[edge.to] = newDist;
                    previousCity[edge.to] = edge.from;
                    pq.add(new Node(edge.to, finVal[edge.to]));

                }

            }

            if (node.id == destination) return finVal[destination];

        }

        return Double.POSITIVE_INFINITY;

    }

    public MyArrayList<String> dijkstraPathForDist(int source, int destination) {

        if (source < 0 || source >= vertices) throw new IllegalArgumentException("Invalid input");
        if (destination < 0 || destination >= vertices) throw new IllegalArgumentException("Invalid input");

        MyArrayList<String> path = new MyArrayList<>(vertices);
        double distance = dijakstraDistance(source, destination);

        if(distance == Double.POSITIVE_INFINITY) return path;

        for(int i = destination; i != -1; i = previousCity[i])
            path.add(graph.get(i).get(0).cityFrom);

        path = path.reverseArrayList();

        return path;

    }

    public MyArrayList<String> dijkstraPathForTime(int source, int destination) {

        if (source < 0 || source >= vertices) throw new IllegalArgumentException("Invalid input");
        if (destination < 0 || destination >= vertices) throw new IllegalArgumentException("Invalid input");

        MyArrayList<String> path = new MyArrayList<>(vertices);
        double time = dijakstraTime(source, destination);

        if(time == Double.POSITIVE_INFINITY) return path;

        for(int i = destination; i != -1; i = previousCity[i])
            path.add(graph.get(i).get(0).cityFrom);

        path = path.reverseArrayList();

        return path;

    }

    public String findBestFlight() {

        MyArrayList<Double> optimizedVal = new MyArrayList<>();

        double minCost = findMin(normCostList);
        double maxCost = findMax(normCostList);
        double rangeCost = maxCost - minCost;

        double minTime = findMin(normTimeList);
        double maxTime = findMax(normTimeList);
        double rangeTime = maxTime - minTime;

        for(int i = 0; i < normCostList.size(); i++) {

            normCostList.set(i, (normCostList.get(i)-minCost)/rangeCost);

        }

        for(int i = 0; i < normTimeList.size(); i++) {

            normTimeList.set(i, (normTimeList.get(i)-minTime)/rangeTime);

        }

        for (int i = 0; i < normCostList.size(); i++) {

            optimizedVal.set(i, normCostList.get(i) + normTimeList.get(i));

        }

        double optimalFlightRank = findMin(optimizedVal);
        int indexOfFlight=0;

        for(int i = 0; i < normTimeList.size(); i++) {

            if(optimalFlightRank == optimizedVal.get(i))
                indexOfFlight = i;

        }

        return optimizedPath.get(indexOfFlight);
    }

    public double findMin (MyArrayList<Double> list) {

        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < normTimeList.size(); i++) {

            if(list.get(i) < min)
                min = list.get(i);

        }

        return min;

    }

    public double findMax (MyArrayList<Double> list) {

        double max = Double.NEGATIVE_INFINITY;

        for(int i = 0; i < list.size(); i++) {

            if(list.get(i) > max)
                max = list.get(i);

        }

        return max;

    }

    public static void main(String[] args) {

        Graph g = new Graph(5);

        g.addEdge(0, "Baku", 1, "Moscow", 100, 180);
        g.addEdge(1, "Moscow", 0, "Baku", 100, 180);
        g.addEdge(0, "Baku", 2, "Berlin", 400, 480);
        g.addEdge(2, "Berlin", 0, "Baku", 400, 480);
        g.addEdge(2, "Berlin", 1, "Moscow", 200, 300);
        g.addEdge(1, "Moscow", 2, "Berlin", 200, 300);
        g.addEdge(3, "London", 2, "Berlin", 150, 110);
        g.addEdge(2, "Berlin", 3, "London", 150, 110);
        g.addEdge(4, "Saint Petersburg", 2, "Berlin", 500, 270);
        g.addEdge(2, "Berlin", 4, "Saint Petersburg", 500, 270);
        g.addEdge(4, "Saint Petersburg", 3, "London", 300, 205);
        g.addEdge(3, "London", 4, "Saint Petersburg", 300, 205);
        g.addEdge(4, "Saint Petersburg", 1, "Moscow", 50, 85);
        g.addEdge(1, "Moscow", 4, "Saint Petersburg", 50, 85);

        /*
        Baku 0
        Moscow 1
        Berlin 2
        London 3
        Saint Petersburg 4
         */


        int s = 4;
        int d = 2;

        System.out.println("Following are all different flights from "+g.graph.get(s).get(0).cityFrom+" to "+g.graph.get(d).get(0).cityFrom);
        g.printAllPaths(s, d);
        System.out.println();

        System.out.println("The cheapest flight is: " + g.dijkstraPathForDist(s,d));
        System.out.println("Cost: " + g.dijakstraDistance(s, d));
        System.out.println();

        System.out.println("The fastest flight is: " + g.dijkstraPathForTime(s,d));
        System.out.println("Time in min: " + g.dijakstraTime(s, d));
        System.out.println();

        System.out.println("The best flight is: " + g.findBestFlight());
        System.out.println();


    }
}

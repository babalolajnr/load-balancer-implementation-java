import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Progarm for the load balancer system this program uses a weighted round robin
 * implementation of a load balancer to distribute 16 requests to the available
 * nodes by examining the weights of each node
 * 
 */
public class Client {

    public static void main(String[] args) {

        int NUM_OF_REQUESTS = 16;
        Client client = new Client();

        // A hashMap of 4 separate nodes is created
        Map<String, Integer> nodePool = new HashMap<>();
        nodePool.put("192.168.0.1", 7);
        nodePool.put("192.168.0.2", 5);
        nodePool.put("192.168.0.3", 3);
        nodePool.put("192.168.0.4", 1);

        client.printNextTurn("Weighted-Round-Robin");
        LoadBalancer weightedRoundRobin = new WeightedRoundRobin(nodePool);
        client.simulateConcurrentClientRequest(weightedRoundRobin, NUM_OF_REQUESTS);

        System.out.println("Main exits");

    }

    private void simulateConcurrentClientRequest(LoadBalancer loadBalancer, int numOfCalls) {

        IntStream.range(0, numOfCalls).parallel().forEach(i -> System.out.println("IP: " + loadBalancer.getNode()
                + " --- Request from Client: " + i + " --- [Thread: " + Thread.currentThread().getName() + "]"));
    }

    private void printNextTurn(String name) {
        System.out.println("---");
        System.out.println("Clients starts to send requests to " + name + " Load Balancer");
        System.out.println("---");
    }

}
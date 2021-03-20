import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Client {

    public static void main(String[] args) {

        int NUM_OF_REQUESTS = 15;
        Client client = new Client();

        ArrayList<String> ipPool = new ArrayList<>();
        ipPool.add("192.168.0.1");
        ipPool.add("192.168.0.2");
        ipPool.add("192.168.0.3");
        ipPool.add("192.168.0.4");
        ipPool.add("192.168.0.5");

        Map<String, Integer> ipPoolWeighted = new HashMap<>();
        ipPoolWeighted.put("192.168.0.1", 7);
        ipPoolWeighted.put("192.168.0.2", 5);
        ipPoolWeighted.put("192.168.0.3", 3);
        ipPoolWeighted.put("192.168.0.4", 1);

        client.printNextTurn("Weighted-Round-Robin");
        LoadBalancer weightedRoundRobin = new WeightedRoundRobin(ipPoolWeighted);
        client.simulateConcurrentClientRequest(weightedRoundRobin, NUM_OF_REQUESTS);

        System.out.println("Main exits");

    }

    private void simulateConcurrentClientRequest(LoadBalancer loadBalancer, int numOfCalls) {

        IntStream.range(0, numOfCalls).parallel().forEach(i -> System.out.println("IP: " + loadBalancer.getIp()
                + " --- Request from Client: " + i + " --- [Thread: " + Thread.currentThread().getName() + "]"));
    }

    private void printNextTurn(String name) {
        System.out.println("---");
        System.out.println("Clients starts to send requests to " + name + " Load Balancer");
        System.out.println("---");
    }

}
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class WeightedRoundRobin extends LoadBalancer {

    private int counter = 0;
    private final ReentrantLock lock;

    public WeightedRoundRobin(Map<String, Integer> nodeMap) {

        super(nodeMap.keySet().stream().map(ip -> {
            List<String> tempList = new LinkedList<>();
            for (int i = 0; i < nodeMap.get(ip); i++) {
                tempList.add(ip);
            }
            return tempList;
        }).flatMap(Collection::stream).collect(Collectors.toList()));

        lock = new ReentrantLock();
    }

    // Get the next available node the counter points to and increment the counter
    // by one
    @Override
    public String getNode() {
        lock.lock();
        try {
            String ip = nodeList.get(counter);
            counter += 1;
            if (counter == nodeList.size()) {
                counter = 0;
            }
            return ip;
        } finally {
            lock.unlock();
        }
    }
}
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RoundRobin extends LoadBalancer {

    private int counter = 0;
    private final ReentrantLock lock;

    public RoundRobin(List<String> list) {
        super(list);
        lock = new ReentrantLock();
    }

    @Override
    public String getIp() {
        lock.lock();
        try {
            String ip = ipList.get(counter);
            counter += 1;
            if (counter == ipList.size()) {
                counter = 0;
            }
            return ip;
        } finally {
            lock.unlock();
        }
    }
}
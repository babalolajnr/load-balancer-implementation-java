import java.util.Collections;
import java.util.List;

public abstract class LoadBalancer {
    final List<String> nodeList;

    public LoadBalancer(List<String> nodeList) {
        this.nodeList = Collections.unmodifiableList(nodeList);
    }

    abstract String getNode();
}
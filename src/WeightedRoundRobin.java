import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeightedRoundRobin extends RoundRobin {

    public WeightedRoundRobin(Map<String, Integer> ipMap) {
        super(ipMap.keySet().stream().map(ip -> {
            List<String> tempList = new LinkedList<>();
            for (int i = 0; i < ipMap.get(ip); i++) {
                tempList.add(ip);
            }
            return tempList;
        }).flatMap(Collection::stream).collect(Collectors.toList()));
    }
}
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TableSorter {

    TableSorter() {
    }

    public Map<String, Integer> sortMapByValueDescending(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by value descending
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Handle duplicate keys (not likely here, but required for toMap)
                        LinkedHashMap::new // Maintain the sorted order
                ));
    }
}

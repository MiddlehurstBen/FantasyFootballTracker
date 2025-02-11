package Tools;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TableSorter {

    public TableSorter() {
    }

    public static Map<String, Integer> sortChipsPlayed(Map<String, Integer> chipsPlayed) {
        return chipsPlayed.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Handle duplicate keys (not likely here, but required for toMap)
                        LinkedHashMap::new // Maintain the sorted order
                ));
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

    public Map<String, Integer> returnTop3FromMap(Map<String, Integer> map) {
        return sortMapByValueDescending(map).entrySet().stream()
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}

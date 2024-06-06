import java.util.Map;

public class prettyPrinter {
    public static void printMinTimesBetween(Map<String, Long> CarrierMinTimeMap) {
        for (String key : CarrierMinTimeMap.keySet()) {
            System.out.printf("Minimum flight time for company %s: %d hour(s) %d minute(s)\n", key,
                    CarrierMinTimeMap.get(key) / 60, CarrierMinTimeMap.get(key) - CarrierMinTimeMap.get(key) / 60 * 60);
        }
    }

    public static void printAverageMinimumDiff(double averageMinimumDiff) {
        System.out.printf("Difference between medium and average prices is %f\n", averageMinimumDiff);
    }
}

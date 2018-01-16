package lt.wonderb0.bankzensur;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class StreamsBestAverage implements BestAverageScoreCalculator {
    @Override
    public Integer calculateBestAverage(String[][] scores) {
        Multimap<String, Integer> mapping = ArrayListMultimap.create();
        for (String[] row : scores) {
            String name = row[0];
            Integer score = Integer.valueOf(row[1]);

            mapping.put(name, score);
        }

        Map<String, Double> averages = new HashMap<>();

        for (String name : mapping.keys()) {
            Collection<Integer> allScores = mapping.get(name);
            IntSummaryStatistics statistics = allScores
                    .stream()
                    .mapToInt(Integer::intValue)
                    .summaryStatistics();

            averages.put(name, statistics.getAverage());
        }

        return averages.values()
                .stream()
                .map(Math::floor)
                .mapToInt(Double::intValue)
                .max()
                .orElse(0);
    }
}

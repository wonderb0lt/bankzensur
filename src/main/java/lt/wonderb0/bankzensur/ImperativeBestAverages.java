package lt.wonderb0.bankzensur;

import java.util.*;

public class ImperativeBestAverages implements BestAverageScoreCalculator {
    @Override
    public Integer calculateBestAverage(String[][] averages) {
        Map<String, List<Integer>> scoresByName = new HashMap<>();
        for (String[] tpl : averages) {
            Integer score = Integer.valueOf(tpl[1]);
            if (scoresByName.containsKey(tpl[0])) {
                scoresByName.get(tpl[0]).add(score);
            } else {
                List<Integer> integers = new LinkedList<>();
                integers.add(score);
                scoresByName.put(tpl[0], integers);
            }
        }

        Map<String, Double> averageScoresByName = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry: scoresByName.entrySet()) {
            int sum = 0;
            List<Integer> scores = entry.getValue();

            for (Integer score : scores) {
                sum += score;
            }

            averageScoresByName.put(entry.getKey(), (double) sum / (double) scores.size());
        }

        double max = 0.0;

        for (Double average : averageScoresByName.values()) {
            max = Math.max(max, average);
        }

        return (int)(Math.floor(max));
    }
}

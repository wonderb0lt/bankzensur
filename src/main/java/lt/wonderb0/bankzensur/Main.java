package lt.wonderb0.bankzensur;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

public class Main {
    private static final String[][] GOLDMAN_SACHS_DATA = new String[][]{
            {"Roberta", "87"},
            {"Carlos", "100"},
            {"Rick", "64"},
            {"Carlos", "22"}
    };

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }

    private static void runWithImplementation(String[][] data, BestAverageScoreCalculator impl, Integer expectedResult) {
        Integer calculatedResult = impl.calculateBestAverage(data);

        if (!expectedResult.equals(calculatedResult)) {
            throw new IllegalStateException("Expected " + expectedResult + ", got " + calculatedResult);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5)
    @Fork(5)
    public void bankzensurStreams() {
        runWithImplementation(GOLDMAN_SACHS_DATA, new StreamsBestAverage(), 87);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5)
    @Fork(5)
    public void bankzensurImperative() {
        runWithImplementation(GOLDMAN_SACHS_DATA, new ImperativeBestAverages(), 87);
    }
}

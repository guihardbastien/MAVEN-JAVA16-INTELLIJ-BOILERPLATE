package fr.guihardbastien.boilerplate;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = {"--add-modules", "jdk.incubator.vector"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@SuppressWarnings("static-method")
public class VectorComputationBenchMark {
    private final int[] array = new Random(0).ints(100_000, 0, 1_000).toArray();

    @Benchmark
    public int sum_loop_array(Blackhole blackhole) {
        var sum = 0;
        for (var value : array) {
            sum += value;
        }
        return sum;
    }

    @Benchmark
    public int sum_vector_array(Blackhole blackhole) {
        return VectorComputation.sum(array);
    }

}
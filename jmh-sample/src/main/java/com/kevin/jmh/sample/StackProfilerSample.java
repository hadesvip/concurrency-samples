package com.kevin.jmh.sample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * stackProfiler
 *
 * @author wangyong
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Group)
public class StackProfilerSample {

  private BlockingQueue<Integer> queue;


  @Setup
  public void init() {
    this.queue = new ArrayBlockingQueue<>(10);
  }

  @GroupThreads(5)
  @Group("blockQueue")
  @Benchmark
  public void put() throws InterruptedException {
    this.queue.put(Integer.MAX_VALUE);
  }

  @GroupThreads(5)
  @Group("blockQueue")
  @Benchmark
  public int take() throws InterruptedException {
    return this.queue.take();
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(StackProfilerSample.class.getSimpleName())
        .timeout(TimeValue.seconds(10))
        .addProfiler(StackProfiler.class)
        .build();

    new Runner(options).run();
  }




}

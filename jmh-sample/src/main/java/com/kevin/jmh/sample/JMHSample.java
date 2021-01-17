package com.kevin.jmh.sample;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author wangyong
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)

public class JMHSample {

  private static final String STR_DATA = "data";

  private List<String> strArrayList = null;
  private List<String> strLinkedList = null;

  @Setup(Level.Iteration)
  public void setUp() {
    strArrayList = Lists.newArrayList();
    strLinkedList = Lists.newLinkedList();
  }

  @Benchmark
  public List<String> strArrayListAdd(){
    this.strArrayList.add(STR_DATA);
    return strArrayList;
  }

  @Benchmark
  public List<String> strLinkedListAdd() {
    this.strLinkedList.add(STR_DATA);
    return strLinkedList;
  }


  public static void main(String[] args) throws RunnerException {

    Options options = new OptionsBuilder()
        .include(JMHSample.class.getSimpleName())
        .forks(1)
        .measurementIterations(10)
        .warmupIterations(10)
        .build();
    new Runner(options).run();

  }


}

package com.github.ratoshniuk.logging.stress.app.core

import java.time.temporal.ChronoUnit

import com.github.pshirshov.izumi.fundamentals.platform.time.IzTime
import com.github.ratoshniuk.logging.stress.app.loggers.LogstageRunner

object BenchmarkApp extends App {

  val numIterations = 5

  val runsPerIteration = 10000

  val async: Boolean = false

  //  val runner = new LogbackRunner
  val runner = new LogstageRunner
  runner.warmup(runsPerIteration)

  runTest(runner, numIterations, runsPerIteration)


  def runTest(runner: LogRunner, iterations: Int, runsPerIteration: Int): Unit = {
    val stressThreads = Runtime.getRuntime.availableProcessors()
    val stresser = new Stresser(stressThreads)
    stresser.start()

    val start = IzTime.utcNow

    println(s"starting stress testing $start")

    1 to iterations foreach {
      iteration =>
        runner.run(iteration, runsPerIteration)
    }

    stresser.stop()
    val end = IzTime.utcNow
    println(String.format(s"Test finished at $end"))

    val total = ChronoUnit.MILLIS.between(start, end)
    println(s"Total runtime -> $total")
  }
}

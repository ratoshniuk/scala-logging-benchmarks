package com.github.ratoshniuk.logging.stress.app.core

trait LogRunner {
  def run(iteration: Int, numRuns: Int): Unit

  def warmup(numRuns: Int): Unit = {
    System.out.println("Starting warmup.")
    this.run(0, numRuns)
    try
      Thread.sleep(5000)
    catch {
      case e: InterruptedException =>
        System.err.println(String.format("Error during warmup sleep: %s", e))
    }
    System.out.println("Finished warmup.")
  }

}




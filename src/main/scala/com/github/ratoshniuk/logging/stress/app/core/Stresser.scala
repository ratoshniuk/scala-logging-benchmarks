package com.github.ratoshniuk.logging.stress.app.core

import java.util.concurrent.atomic.AtomicReferenceArray

class Stresser(val numThreads: Int) {
  val threads = new AtomicReferenceArray[Thread](numThreads)

  def start(): Unit = {
    System.out.println("Starting stress test.")

    0 until numThreads foreach {
      i =>
        threads.set(i, new Thread(new StressRunnable()))
        threads.get(i).start()
    }
  }

  def stop(): Unit = {
    System.out.println("Stopping stress test.")
    0 until numThreads foreach {
      i => threads.get(i).interrupt()
    }
  }
}

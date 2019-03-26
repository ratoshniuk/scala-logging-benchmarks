package com.github.ratoshniuk.logging.stress.app.core

import com.github.pshirshov.izumi.fundamentals.platform.language.Quirks

import scala.collection.mutable

class StressRunnable extends Runnable {
  override def run(): Unit = generateUntilInterrupt()

  private def generateUntilInterrupt() : Unit = {
    var n = 1

    while (!Thread.currentThread().isInterrupted) {
      Quirks.discard(primesIterative(n))
      try {
        Thread.sleep(100)
      } catch {
        case _: InterruptedException =>
          return
      }
      n += 1
    }
  }


  def primesIterative(end: Int): List[Int] = {
    val primeIndices = mutable.ArrayBuffer.fill((end + 1) / 2)(1)

    val intSqrt = Math.sqrt(end).toInt
    for (i <- 3 to end by 2 if i <= intSqrt) {
      for (nonPrime <- i * i to end by 2 * i) {
        primeIndices.update(nonPrime / 2, 0)
      }
    }

    (for (i <- primeIndices.indices if primeIndices(i) == 1) yield 2 * i + 1).tail.toList
  }

}

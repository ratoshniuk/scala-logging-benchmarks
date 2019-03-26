package com.github.ratoshniuk.logging.stress.app.loggers

import com.github.ratoshniuk.logging.stress.app.core.LogRunner
import org.slf4j.LoggerFactory


object LogbackRunner {
  def setConfigurationFile(fileName: String): Unit = {
    System.setProperty("logback.configurationFile", fileName)
  }
}

class LogbackRunner extends LogRunner {
  final private val logger = LoggerFactory.getLogger(classOf[LogbackRunner])

  def run(iteration: Int, numRuns: Int): Unit = {
    1 to numRuns foreach {
      i => logger.info(s"iteration=$iteration, run=$i")
    }
  }
}

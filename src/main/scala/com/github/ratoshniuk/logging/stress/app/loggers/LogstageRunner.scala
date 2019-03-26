package com.github.ratoshniuk.logging.stress.app.loggers

import com.github.pshirshov.izumi.logstage.api.rendering.{RenderingOptions, StringRenderingPolicy}
import com.github.pshirshov.izumi.logstage.sink.file.models.FileRotation.DisabledRotation
import com.github.pshirshov.izumi.logstage.sink.file.models.{FileSinkConfig, FileSinkState}
import com.github.pshirshov.izumi.logstage.sink.file.{FileServiceImpl, FileSink}
import com.github.ratoshniuk.logging.stress.app.core.LogRunner
import logstage._

import scala.util.Try

class LogstageRunner extends LogRunner {

  val fileSink = new FileSinkSample()

  val sinks = List(fileSink)

  val logger: IzLogger = IzLogger(Trace, sinks)

  override def run(iteration: Int, numRuns: Int): Unit = {
    1 to numRuns foreach {
      i => logger.info(s"${iteration -> "iteration"}, ${i -> "run"}")
    }
  }
}

class FileSinkSample
()
  extends FileSink(
    new StringRenderingPolicy(RenderingOptions(withExceptions = false, withColors = false), None),
    new FileServiceImpl("logs/logstage2"),
    DisabledRotation, FileSinkConfig.inBytes(1000000 * 20)
  ) {
  override def recoverOnFail(e: String): Unit = {
    ()
  }

  override def performWriting(state: FileSinkState, payload: String): Try[FileSinkState] = {
    super.performWriting(state, payload)
//    Try(state)
  }
}

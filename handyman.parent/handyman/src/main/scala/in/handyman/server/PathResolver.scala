package in.handyman.server

import com.typesafe.scalalogging.LazyLogging
import in.handyman.HandymanException

object PathResolver extends LazyLogging {

  def resolvePath(processName: String, fileRelativePath: String, basePath: String): String = {

    val fullPath = new StringBuilder().append(basePath).
      append("/").append(fileRelativePath).toString()

    logger.info(s"Trying to find the process file :$fullPath")
    val finalPathUrl = this.getClass.getClassLoader.getResource(fullPath)
    if (finalPathUrl != null) {
      val finalPath = finalPathUrl.getPath
      finalPath
    } else
      throw new HandymanException("Process definition for " + fileRelativePath + " is absent")
  }
}
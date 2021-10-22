package in.handyman.server


import com.typesafe.scalalogging.LazyLogging
import in.handyman.DslStandaloneSetup
import in.handyman.parser.antlr.DslParser
import org.slf4j.MarkerFactory


case class RuntimeContext(process: in.handyman.dsl.Process, configMap: Map[String, String], path: String, instanceName: String)

object ProcessAST extends LazyLogging {

  val sparrowHero = new DslStandaloneSetup
  val guiceInjector = sparrowHero.createInjectorAndDoEMFRegistration
  val parser = guiceInjector.getInstance(classOf[DslParser]);
  val marker = "PROCESS-LOADER";
  val pMarker = MarkerFactory.getMarker(marker)

  def loadProcessAST(instanceName: String, json: String) = {
    logger.info(pMarker, "Retreiving configuration for process instance {}" + instanceName)
    val config: Map[String, String] = in.handyman.config.ConfigurationService.getAllConfig(instanceName)
    val inputConfig = config + ("myjson" -> json)
    val filePathConfig = config.get("filepath")


    if (filePathConfig.isEmpty)
      throw new Exception("File path configuration for process " + instanceName + " is missing, check spw_process_config or spw_instance_config")

    val fileRelativePath = filePathConfig.get;

    val basePath = config.get("basepath").get;
    //logger.info("basepath=" + basePath)
    logger.info(pMarker, "filepath {} for process instance {} with base path as {}", fileRelativePath, instanceName, basePath)
    val path = PathResolver.resolvePath(instanceName, fileRelativePath, basePath)


    val result = parser.parse(new java.io.FileReader(path))
    val eRoot = result.getRootASTElement()
    val process: in.handyman.dsl.Process = eRoot.asInstanceOf[in.handyman.dsl.Process]
    RuntimeContext(process, inputConfig, path, instanceName)
  }


  def loadProcessAST(instanceName: String, fileRelativePath: String, context: in.handyman.command.Context) = {
    logger.info("filepath=" + fileRelativePath)
    val basePath = context.getValue("basepath");
    logger.info("basepath=" + basePath)
    val path = PathResolver.resolvePath(instanceName, fileRelativePath, basePath)
    val result = parser.parse(new java.io.FileReader(path));
    val eRoot = result.getRootASTElement();
    val process: in.handyman.dsl.Process = eRoot.asInstanceOf[in.handyman.dsl.Process]
    RuntimeContext(process, context.getMeAsIs, path, instanceName)
  }

}

package in.handyman.server


import com.typesafe.scalalogging.LazyLogging
import in.handyman.DslStandaloneSetup
import in.handyman.parser.antlr.DslParser



case class RuntimeContext(process: in.handyman.dsl.Process, configMap: Map[String, String], path: String, instanceName: String)

object ProcessAST extends LazyLogging {

  val sparrowHero = new DslStandaloneSetup
  val guiceInjector = sparrowHero.createInjectorAndDoEMFRegistration
  val parser = guiceInjector.getInstance(classOf[DslParser]);

  def loadProcessAST(instanceName: String, json:String) = {
    val config: Map[String, String] = in.handyman.config.ConfigurationService.getAllConfig(instanceName)
    val inputConfig = config+("myjson"->json)
    val fileRelativePath = config.get("filepath").get;
    logger.info("filepath=" + fileRelativePath)
    val basePath = config.get("basepath").get;
    logger.info("basepath=" + basePath)
    val path = PathResolver.resolvePath(instanceName, fileRelativePath, basePath)

    val result = parser.parse(new java.io.FileReader(path));
    val eRoot = result.getRootASTElement();
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
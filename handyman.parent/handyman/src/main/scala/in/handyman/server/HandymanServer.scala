package in.handyman.server

import com.typesafe.scalalogging.LazyLogging
import org.restlet.Application
import org.restlet.Component
import org.restlet.data.Protocol
import org.restlet.routing.Router

object HandymanServer extends Application with LazyLogging{
  
  def main(args:Array[String]){
    
    logger.info("Starting the restlet server to take in commands, at port 2909")
    val component = new Component()
    val port = {
      if(args.isEmpty)
        "2909"
      else
        args.apply(0)
    }
    component.getServers().add(Protocol.HTTP, 2909)
    // Attach the sample application.
    component.getDefaultHost().attach(this)
    // Start the component.    
    component.start()
     logger.info("Successfully started the restlet server to take in commands, at port 2909")
    
  }
  override def createInboundRoot: Router = {
    val router = new Router(getContext())
    router.attach("/process/{instance}/{alias}/start",classOf[StartProcess])
    router.attach("/",classOf[ListProcess])
    router.attach("/process/{id}/stop", classOf[StopProcess])
    router
}
}
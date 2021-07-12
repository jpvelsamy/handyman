package in.handyman.server

import com.typesafe.scalalogging.LazyLogging
import org.knowm.sundial.SundialJobScheduler
import org.restlet.{Application, Component}
import org.restlet.data.Protocol
import org.restlet.routing.Router

object HandymanServer extends Application with LazyLogging{
  
  def main(args:Array[String]){
    
    logger.info("Starting the restlet server to take in commands,")
    val component = new Component()
    val port:Int = {
      if(args.isEmpty)
        2909
      else
        args.apply(0).toInt
    }
    component.getServers().add(Protocol.HTTP, port)
    // Attach the sample application.
    component.getDefaultHost().attach(this)
    // Start the component.    
    component.start()
    logger.info("Successfully started the restlet server to take in commands, at port 2909")
    logger.info("Bootstrapping the background scheduler")
    val schedulerThreadPoolCount:Int = {
       if(args.isEmpty)
        5
       else
         args.apply(1).toInt
    }
    val packageName:String = {
       if(args.isEmpty )
        "in.juno.jam"
       else
         args.apply(2)
    }
    val startDelay:Int = {
       if(args.isEmpty)
        10
       else
         args.apply(3).toInt
    }
    SundialJobScheduler.createScheduler(schedulerThreadPoolCount, packageName);
    logger.info("Starting the background scheduler")
    SundialJobScheduler.getScheduler().startDelayed(startDelay);
    
    
  }
  override def createInboundRoot: Router = {
    val router = new Router(getContext())
    router.attach("/process/{instance}/{alias}/start",classOf[StartProcess])
    router.attach("/",classOf[ListProcess])
    router.attach("/process/{id}/stop", classOf[StopProcess])
    router
}
}

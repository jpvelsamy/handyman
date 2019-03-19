package in.handyman.server

import com.typesafe.scalalogging.LazyLogging
import org.restlet.Application
import org.restlet.Component
import org.restlet.data.Protocol
import org.restlet.routing.Router
import org.knowm.sundial.SundialJobScheduler

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
    logger.info("Bootstrapping the background scheduler")
    val schedulerThreadPoolCount:Int = {
       if(args.isEmpty && args.length==1)
        5
       else
         args.apply(1).toInt
    }
    val packageName:String = {
       if(args.isEmpty && args.length==2)
        "in.juno.jam"
       else
         args.apply(2)
    }
    val startDelay:Int = {
       if(args.isEmpty && args.length==3)
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
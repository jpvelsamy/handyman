package in.handyman.command

import com.typesafe.scalalogging.LazyLogging
import in.handyman.HandymanException
import in.handyman.dsl.{Expression, RestPart}
import in.handyman.util.ParameterisationEngine
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.ecore.util.{EDataTypeEList, EObjectContainmentEList}

import java.lang.reflect.{InvocationHandler, Method, Proxy}

object CommandProxy extends LazyLogging {
  //val eList = classOf[EList[String]].getName
  def createProxy[I](proxee: I, interfaceClass: Class[I], context: Context): I = {

    assert(interfaceClass.isInterface, "interfaceClass should be an inteface class")
    Proxy.newProxyInstance(interfaceClass.getClassLoader, Array(interfaceClass), new InvocationHandler() {
      override def invoke(proxy: Object, method: Method, args: Array[Object]) = {

        method.getReturnType.getName match {

          case "org.eclipse.emf.common.util.EList" => {
            method.getName match {
              case "getParts" => {
                logger.info("(getParts) Invoking method in  action #{}", method.getName)
                method.invoke(proxee, args: _*)
                  .asInstanceOf[EObjectContainmentEList[RestPart]]
              }
              case _ => {
                val interim = method.invoke(proxee, args: _*)
                  .asInstanceOf[EDataTypeEList[String]]
                logger.info("(No getParts) Invoking method in  action #{}", method.getName)
                val output = new BasicEList[String]
                val iter = interim.iterator
                while (iter.hasNext) {
                  val value = iter.next
                  val result = ParameterisationEngine.resolve(value, context)
                  output.add(result)
                }
                output
              }
            }
          }
          case "in.handyman.dsl.Expression" => {
            method.getName match {
              case "getCondition" => {
                val interim = method.invoke(proxee, args: _*)
                  .asInstanceOf[Expression]
                logger.info("Invoking method in  action #{}", method.getName)
                if (interim != null && interim.getLhs != null && interim.getRhs != null) {
                  interim.setLhs(ParameterisationEngine.resolve(interim.getLhs, context))
                  interim.setRhs(ParameterisationEngine.resolve(interim.getRhs, context))
                }
                interim
              }
            }
          }
          case "java.lang.String" => {
            val inputString = method.invoke(proxee, args: _*).asInstanceOf[String]
            val result = ParameterisationEngine.resolve(inputString, context)
            result
          }
          case _ => {
            throw new HandymanException("Support for other types is not there yet")
          }

        }

      }
    }).asInstanceOf[I]
  }

}

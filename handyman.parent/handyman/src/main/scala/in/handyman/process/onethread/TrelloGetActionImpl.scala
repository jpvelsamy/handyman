package org.etl.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.dsl.Action
import in.handyman.command.Context
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine

class TrelloGetActionImpl extends in.handyman.command.Action with LazyLogging {
    val detailMap = new java.util.HashMap[String, String]
  def execute(context: Context, action: Action): Context = {
    
    val trelloGetAsIs: in.handyman.dsl.TrelloGET = action.asInstanceOf[in.handyman.dsl.TrelloGET]
    val trelloGet: in.handyman.dsl.TrelloGET = CommandProxy.createProxy(trelloGetAsIs, classOf[in.handyman.dsl.TrelloGET], context)

    val authInfo = trelloGet.getAuthtoken
    val board = trelloGet.getBoard
    val authKey = trelloGet.getKey
    val target = trelloGet.getTarget
    val user = trelloGet.getUseraccount
    val name = trelloGet.getName
    val sql = trelloGet.getValue
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
     val trelloGetAsIs: in.handyman.dsl.TrelloGET = action.asInstanceOf[in.handyman.dsl.TrelloGET]
    val trelloGet: in.handyman.dsl.TrelloGET = CommandProxy.createProxy(trelloGetAsIs, classOf[in.handyman.dsl.TrelloGET], context)

    val expression = trelloGet.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
      detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }
  
  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }
  
}
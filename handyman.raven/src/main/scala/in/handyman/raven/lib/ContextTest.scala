package in.handyman.raven.lib

import com.fasterxml.jackson.databind.JsonNode
import in.handyman.raven.lambda.action.{ActionContext, IActionContext}
import in.handyman.raven.lib.model.RestPart
import lombok.{Data, EqualsAndHashCode, NoArgsConstructor}

import java.util

@Data
@EqualsAndHashCode
@NoArgsConstructor
@ActionContext(actionName = "Test")
class ContextTest extends IActionContext {

  private var name: Option[String] = None

  private var source: Option[String] = None

  private var url: Option[String] = None

  private var method: Option[String] = None

  private var headers: Option[JsonNode] = None

  private var params: Option[JsonNode] = None

  private var bodyType: Option[String] = None

  private var value: util.ArrayList[RestPart] = new util.ArrayList[RestPart]

  private var condition: Boolean = true

  override def getName: String = this.name.orNull

   def getSource: String = this.source.orNull


}

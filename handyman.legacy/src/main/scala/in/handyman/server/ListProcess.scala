package in.handyman.server

import org.restlet.resource.{Get, ServerResource}

class ListProcess extends ServerResource {
  @Get("application/json")
  def represent(): String = {
    ""
  }
}

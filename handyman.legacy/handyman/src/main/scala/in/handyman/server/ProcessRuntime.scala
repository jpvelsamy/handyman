package in.handyman.server

import in.handyman.command.Context

trait ProcessRuntime {
  
  def execute(process:in.handyman.dsl.Process, context:Context):ProcessResponse
}
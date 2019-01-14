package in.handyman.process.onethread

import in.handyman.dsl.Catch
import in.handyman.command.Context

class CatchRuntime(val catchBlock:Catch, val context:Context) {
  
  def execute():Context = {
    
    context
  }
}
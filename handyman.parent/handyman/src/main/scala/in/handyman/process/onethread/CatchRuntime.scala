package in.handyman.process.onethread

import in.handyman.command.Context
import in.handyman.dsl.Catch

class CatchRuntime(val catchBlock:Catch, val context:Context) {
  
  def execute():Context = {
    
    context
  }
}

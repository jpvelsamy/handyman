package in.handyman.process.onethread

import in.handyman.command.Context
import in.handyman.dsl.Try

class TryRuntime(val tryBlock: Try, val context: Context) {

  def execute(): Context = {

    context
  }
}

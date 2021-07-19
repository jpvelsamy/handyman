package in.handyman.process.onethread

import in.handyman.command.Action

object CommandFactory {
                  
  def create(name:String) :Action={
      
    name.toLowerCase match {
      case "abort" =>  new AbortAction
      case "googlecal" => new GoogleCalendarAction
      case "transform" => new TransformAction
      case "assign" => new FetchVariableAction
      case "callprocess" => new CallProcessAction
      case "forkprocess" => new ForkProcessAction
      case "doozle"=>new DoozleAction     
      case "fbclead"=>new FBLeadCreateAction
      case "fbformdownload"=>new FBFormDownloadAction
      case "googlecontactput"=>new GContactCreateAction
      case "googlecalput"=>new GoogleCalendarAction     
      case "fetch"=>new FetchVariableAction
      case "smsleadsms"=>new SmsLeadsAction          
      case "sendmail"=>new MailAction
      case "firebasedatabaseput"=>new FirebaseDbUpdateAction
      case "firebasereactivenotification"=>new FirebaseMessagingAction
      case "execjava"=>new JavaAction  
      case "copydata"=>new CopyDataAction
    
    }
  }
}
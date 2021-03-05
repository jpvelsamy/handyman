package in.handyman.process.onethread

import scala.collection.mutable.HashSet

case class ColumnInARow(columnType: Int, columnTypeName: String, columnName: String, columnLabel: String, scale: Integer, 
    value: Object, columnFunction:String, isLastColumn:Boolean)
case class Row(rowId:Int, columnSet:HashSet[ColumnInARow])

object ObjectModelContainer {
  
}
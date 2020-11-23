package in.handyman.process.onethread

import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.util.ArrayList
import java.util.Properties

import scala.collection.JavaConversions.`deprecated asScalaBuffer`
import scala.collection.JavaConversions.`deprecated iterableAsScalaIterable`
import scala.collection.JavaConverters.asScalaBufferConverter

import com.aliasi.tokenizer.EnglishStopTokenizerFactory
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory
import com.aliasi.tokenizer.Tokenizer
import com.aliasi.tokenizer.TokenizerFactory
import com.opencsv.CSVReader
import com.typesafe.scalalogging.LazyLogging

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.util.CoreMap
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import org.json.JSONObject

class NLPTextPreprocessingAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  var columnData: String = null
  var pipeline: StanfordCoreNLP = null
  var props: Properties = null
  props = new Properties()
  props.put("annotators", "tokenize, ssplit, pos, lemma")
  pipeline = new StanfordCoreNLP(props)

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId: Integer): in.handyman.command.Context =
    {
      val nlpTextPreprocessingAsIs: in.handyman.dsl.NLPTextPreprocessing = action.asInstanceOf[in.handyman.dsl.NLPTextPreprocessing]
      val nlpTextPreprocessingData: in.handyman.dsl.NLPTextPreprocessing = CommandProxy.createProxy(nlpTextPreprocessingAsIs, classOf[in.handyman.dsl.NLPTextPreprocessing], context)
      val incomingJson = context.getValue("myjson")
      val fileDetails: JSONObject = new JSONObject(incomingJson)
      val inputFilePath: String = fileDetails.get("path").toString()
      println("obtained input file path  " + inputFilePath)
      println("Read CSV and Text preprocessing started")
      readCSV(inputFilePath)
      println("Read CSV and Text preprocessing completed successfully")

      def lemmatize(documentText: String): StringBuilder = {
        var lemmas: ArrayList[String] = new ArrayList[String]()
        var document: Annotation = new Annotation(documentText)
        pipeline.annotate(document)
        var sentences: List[CoreMap] = document.get(classOf[SentencesAnnotation]).asScala.toList
        sentences.foreach((sentence: CoreMap) => {
          for (token <- sentence.get(classOf[TokensAnnotation])) {
            lemmas.add(token.get(classOf[LemmaAnnotation]))

          }
        })

        var sb1: StringBuilder = new StringBuilder()
        for (str <- lemmas) {
          sb1.append(str + " ")
        }
        sb1
      }

      def readCSV(inputfileName: String): Unit = {
        val downloadPath: String = System.getProperty("user.dir") + "/CleansedTestData.csv"
        context.addValue("fileDownloadPath", downloadPath)
        val csvWriter: FileWriter = new FileWriter(downloadPath)
        try {
          var reader: CSVReader = new CSVReader(new FileReader(inputfileName), ',')
          var allRows: List[Array[String]] = reader.readAll().asScala.toList
          for (row <- allRows) {
            writeCSV(row, csvWriter)
          }
        } catch {
          case e: IOException => e.printStackTrace()

        } finally csvWriter.close()
      }

      def writeCSV(record: Array[String], writer: FileWriter): Unit = {
        for (column <- record) {
          var noises: Array[String] =
            Array("URL", "@USER", "\'ve", "n\'t", "\'s", "\'m", ",")
          for (noise <- noises) {
            columnData = column.replaceAll(noise, "")
          }
          var tokenizerFactory: TokenizerFactory =
            IndoEuropeanTokenizerFactory.INSTANCE
          tokenizerFactory = new EnglishStopTokenizerFactory(tokenizerFactory)
          val tokenizer: Tokenizer =
            tokenizerFactory.tokenizer(columnData.toCharArray(), 0, columnData.length)
          val sb1: StringBuilder = new StringBuilder()
          for (token <- tokenizer) {
            if (token.endsWith("_")) {
              sb1.append(token + "")
            } else {
              sb1.append(token + " ")
            }
          }
          val lemma: StringBuilder = lemmatize(sb1.toString)
          writer.append(lemma)
          writer.append(",")
        }
        writer.append("\n")
      }

      context
    }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean =
    {
      val nlpTextPreprocessingAsIs: in.handyman.dsl.NLPTextPreprocessing = action.asInstanceOf[in.handyman.dsl.NLPTextPreprocessing]
      val nlpTextPreprocessingData: in.handyman.dsl.NLPTextPreprocessing = CommandProxy.createProxy(nlpTextPreprocessingAsIs, classOf[in.handyman.dsl.NLPTextPreprocessing], context)

      val expression = nlpTextPreprocessingData.getCondition
      try {
        val output = ParameterisationEngine.doYieldtoTrue(expression)
        detailMap.putIfAbsent("condition-output", output.toString())
        output
      } finally {
        if (expression != null)
          detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

      }
    }

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }
}

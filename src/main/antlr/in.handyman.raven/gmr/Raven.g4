grammar Raven;

import JSON;

process:
	'process' name=STRING
	'{'
	   tryBlock=tryClause
	   catchBlock=catchClause
	   finallyBlock= finallyClause
	'}';

tryClause :
	'try'
	'{'
		(actions+=action)*
	'}'
;

finallyClause:
	'finally'
	'{'
		(actions+=action)*
	'}'
;

catchClause:
	'catch'
	'{'
		(actions+=action)*
	'}'
;

action:
	(abort
	|assign
    |callProcess
    |forkProcess
    |spawnProcess
    |dogLeg
	|copyData
	|dropFile
	|loadCsv
	|restApi
	|transform
	|multitude
	|exportCsv
	|createDirectory
    |createFile
    |deleteFileDirectory
    |transferFileDirectory
    |createTAR
    |extractTAR
    |importCsvToDB
    |producerConsumerModel
    |producer
    |consumer
    |pushJson
    |mapJsonContext
    |sharePoint
    |downloadAsset
    |paperItemization
    |autoRotation
    |blankPageRemover
    |qrAttribution
    |fileMerger
    |checksum
    |fileSize
    |ravenVmException
    |uploadAsset
    |docnetAttribution
    |createZip
    |extractZip
    |sorGroupDetails
    |ftpsUpload
    |ftpsDownload
    |sftpConnector
    |zeroShotClassifier
    |loadExtractedData
    |absentKeyFilter
    |triageAttribution
    |loadExtractedData
    |absentKeyFilter
    |sorFilter
    |tqaFilter
    |jsonToFile
    |textFilter
    |entityFilter
    |dirPath
    |fileDetails
    |urgencyTriage
    |docnetResult
    |setContextValue
    |evalPatientName
    |evalMemberId
    |evalDateOfBirth
    |thresholdCheck
    |wordcount
    |charactercount
    |datevalidator
    |alphavalidator
    |alphanumericvalidator
    |numericvalidator
    |nervalidator
    |donutDocQa
    |scalarAdapter
    |phraseMatchPaperFilter
    |zeroShotClassifierPaperFilter
    |dataExtraction
    |assetInfo
    |episodeOfCoverage
    |userRegistration
    |authToken
    |eocJsonGenerator
    |zipContentList
    |hwDetection
    |intellimatch
    |checkboxVqa
    |pixelClassifierUrgencyTriage
    |paperItemizer
    |nerAdapter
    |coproStart
    |coproStop
    |outboundDeliveryNotify
    |masterdataComparison
    |zipBatch
    |drugMatch
    |urgencyTriageModel
    |donutImpiraQa
    |trinityModel
    |templateDetection
    |fileBucketing
    |alchemyInfo
    |alchemyAuthToken
    |alchemyResponse
    |productResponse
    |tableExtraction
    |mailServer
    |alchemyKvpResponse
    |alchemyTableResponse
    |productOutboundZipfile
    |mailServer
    |fileMergerPdf
    |zipFileCreationOutbound
    |outboundKvpResponse
    |outboundTableResponse

    |integratedNoiseModelApi
    );


multitude:
    'multitude' 'as' name=STRING ('on' on= STRING)* 'using'
    '{'
		(actions+=action)*
    '}' ('on-condition' condition=expression)* ('fielding' writeThreadCount=NON_ZERO_DIGIT)*;

// this is for creating POJO WRT definition
//attribute:(startProcess);

copyData:
	('copydata' 'as' name=STRING 'from' source=STRING 'to' to=STRING  'using'
	'{'
		value=STRING
	'}' ('on-condition' condition=expression)* ('fielding' writeThreadCount=NON_ZERO_DIGIT)* ('with-fetch-batch-size' fetchBatchSize=NON_ZERO_DIGIT)* ('with-write-batch-size' writeBatchSize=NON_ZERO_DIGIT)*)
	 ;

transform:
	'transform' 'as' name= STRING 'on' on= STRING 'using'
	 '{'
		value+=STRING
	 '}' ('on-condition' condition=expression)* ('do-format' format=expression)*;

loadCsv:
	'loadcsv' 'as' name=STRING 'pid' pid=STRING 'from' source=STRING 'to' to=STRING 'with' delim=STRING 'by-batch'
	limit=STRING 'using'
	'{'
	value=STRING
	'}' ('on-condition' condition=expression)*;

abort:
	'abort' 'as' name=STRING
	'{'
		value=STRING
	'}' ('on-condition' condition=expression)*
;

callProcess:
	'callprocess' 'as' name=STRING 'with-target' target=STRING 'from-file' source=STRING 'using' datasource=STRING
	'for-every'
	'{'
	value=STRING
	'}' ('on-condition' condition=expression)* ('on-parallel-fielding' forkBatchSize=STRING)*;


forkProcess:
	'forkprocess' 'as' name=STRING 'with-target' target=STRING 'from-file' source=STRING 'using' datasource=STRING 'for-every'
	'{'
	value=STRING
	'}' ('watermark' forkBatchSize=STRING)* ('on-condition' condition=expression)*;


spawnProcess:
	'spawn' 'as' name=STRING 'with-target' target=STRING 'from-file' source=STRING  ('on-condition' condition=expression)*;


dogLeg:
	'dogleg' 'as' name=STRING 'use-parent-context' inheritContext=STRING 'using'
	'{'
		processList+=startProcess
	'}'   ('on-condition' condition=expression)*;

startProcess:
	'start-process' name=STRING 'with-file' target=STRING
;

assign:
	'assign' 'as' name=STRING 'source' source=STRING 'using'
	'{'
		value=STRING
	'}' ('on-condition' condition=expression)*;


dropFile:
	'dropfile' 'as' name=STRING 'in-path' target=STRING ('on-condition' condition=expression)*
;

restApi:
	'restapi' 'as' name=STRING 'source' source=STRING 'using' 'url' url=STRING 'method' method=STRING
	 ('with headers'  headers=json)* ('with params'  params=json )*
	 ('with body type' '{' bodyType=STRING '}')
	'{'
	(value+=restPart)*
	'}' ('on-condition' condition=expression)*;

restPart:
	'{ part' 'as' partName=STRING 'with' partData=STRING ('type as' type=STRING)* '}';


exportCsv:
    'exportCsv' 'as' name=STRING 'source' source=STRING 'executionSource' executionSource=STRING
    ('using' stmt= STRING)* 'targetLocation' targetLocation=STRING
    'using' '{''}'
     ('on-condition' condition=expression)* ('fielding' writeThreadCount=STRING)* ;

importCsvToDB:
	'importCsvToDB' 'as' name= STRING 'target' target=resource 'on' tableName=STRING 'using'
	 '{'
		value+=STRING
	'}' ('on-condition' condition=expression)* ('fielding' writeThreadCount=STRING)* ('batch' batchSize=STRING)*;

extractTAR:
	'extractTAR' 'as' name= STRING 'from' source=STRING 'destination' destination=STRING 'using'
	 '{''}' ('on-condition' condition=expression)*;

createTAR:
	'createTAR' 'as' name= STRING 'from' source=STRING 'destination' destination=STRING 'extension' extension=STRING 'using'
	 '{''}' ('on-condition' condition=expression)*;

createDirectory:
	'createDirectory' 'as' name= STRING 'using'
	 '{'
		directoryPath+=STRING
	 '}' ('on-condition' condition=expression)*;

createFile:
	'createFile' 'as' name= STRING 'location' location=STRING 'fileName' fileName=STRING 'extension' extension=STRING 'using'
	 '{'
		value=STRING
	'}' ('on-condition' condition=expression)*;

deleteFileDirectory:
	'deleteFileDirectory' 'as' name= STRING 'using'
	 '{'
		path+=STRING
	'}' ('on-condition' condition=expression)*;

transferFileDirectory:
	'transferFileDirectory' 'as' name= STRING 'from' source=STRING 'to' to=STRING 'operation' operation=STRING 'using'
    '{'
	'}' ('on-condition' condition=expression)*;

producerConsumerModel:
    'pcm' 'as'  name=STRING 'on-resource' source=resource
    'produce''{'
        produce+=producer
    '}' ('fielding' produceThreadCount=STRING)*
    'consume''{'
        consume+=consumer
    '}'('fielding' consumeThreadCount=STRING)*  ('on-condition' condition=expression)*;

producer:
    'producer''as' name=STRING ('push-result-at' push=STRING)*  ('on-resource' source=resource)*
     'for-every''{'
     	stmt=STRING
     '}'
     'execute''{'
       (actions+=action)*
     '}' ('on-condition' condition=expression)* ('fielding' threadCount=STRING)*;

consumer:
    'consumer''as' name=STRING  ( 'pop-event-from' event=STRING 'on-resource' source=resource )*  ('pop-result-from' pop=STRING 'limit' limit=STRING)* 'execute' '{'
       (actions+=action)*
       '}' ('on-condition' condition=expression)* ('fielding' threadCount=STRING)* ('on-standalone' standalone=expression)* ;


pushJson :
     'push-json-into-context' 'as' name=STRING 'with-key' key=STRING 'using-value'  '{'
       value=json
    '}' ('on-condition' condition=expression)* ;

mapJsonContext :
      'map-json-into-context' 'as' name=STRING  'using'  '{'
               value=STRING
                                                   '}' ('on-condition' condition=expression)* ;

sharePoint:
	'sharepoint' 'as' name=STRING 'client-id' shpClientId=STRING 'tenant-id' shpTenantId=STRING 'client-secret' shpClientSecret=STRING
	'org-name' orgName=STRING 'action-type' actionType=STRING 'site-url' siteUrl=STRING 'source-relative-path' sourceRelativePath=STRING
	'file-name' fileName=STRING 'target-relative-path' targetRelativePath=STRING 'using'
	'{'
		value=STRING
	'}' ('on-condition' condition=expression)* ;

expression :'if' (lhs=STRING operator=Operator rhs=STRING);

log:
   'log' 'as' 'level' level=STRING 'with' 'message' '{' message=STRING '}';

ravenVmException:
    'raise exception' 'as' name=STRING 'using' '{' message=STRING '}'('on-condition' condition=expression)* ;

checksum:
    'checksum' 'as' name=STRING 'using' '{' filePath=STRING '}' ('on-condition' condition=expression)* ;

fileSize:
    'fileSize' 'as' name=STRING 'using' '{' filePath=STRING '}' ('on-condition' condition=expression)* ;

downloadAsset:
    'download-asset' 'as' name=STRING 'from-url' url=STRING 'at' location=STRING  'using'  '{' '}' ('on-condition' condition=expression)* ;

paperItemization:
    'paper-itemization' 'as' name=STRING 'from-target-file' filePath=STRING 'using' outputDir=STRING  'using'  '{' '}' ('on-condition' condition=expression)* ;

autoRotation:
    'autoRotation' 'as' name=STRING
    'output-dir' outputDir=STRING
    'process-id' processId=STRING
    'copro-url' endPoint=STRING
    'resource-conn' resourceConn=STRING
    'using'  '{' querySet=STRING '}'
     ('on-condition' condition=expression)*  ;


blankPageRemover:
    'blankPageRemover' 'as' name=STRING  'output-dir' outputDir=STRING 'process-id' processId=STRING 'resource-conn' resourceConn=STRING  'using'  '{'  querySet=STRING '}' ('on-condition' condition=expression)* ;

qrAttribution:
    'qrAttribution' 'as' name=STRING 'using'  '{' filePath=STRING '}' ('on-condition' condition=expression)* ;

uploadAsset:
    'uploadAsset' 'as' name=STRING 'from-target-file' filePath=STRING 'template-id' templateId=STRING  'auth-token' token=STRING 'using'  '{' '}' ('on-condition' condition=expression)* ;

fileMerger:
    'fileMerger' 'as' name=STRING  'in' outputDir=STRING 'using'  '{' requestBody=STRING '}' ('on-condition' condition=expression)* ;

ftpsUpload:
       'ftps_upload' 'as' name=STRING 'with-remote-host' host=STRING 'port' port=STRING 'user-name' userName=STRING
       'password' password=STRING 'session-timeout' sessionTimeOut=STRING 'source-file-to-upload' sourceFile=STRING
       'in-destination-to-save' destDir=STRING
       'upload-check' uploadCheck=STRING
       'using' '{'     '}' ('on-condition' condition=expression)* ;

ftpsDownload:
       'ftps_download' 'as' name=STRING 'with-remote-host' host=STRING 'port' port=STRING 'user-name' userName=STRING
       'password' password=STRING 'session-timeout' sessionTimeOut=STRING 'source-file-to-download' sourceFile=STRING
       'in-destination-to-save' destDir=STRING
       'download-check' uploadCheck=STRING
       'using' '{'     '}' ('on-condition' condition=expression)* ;

sftpConnector:
       'sftp' 'as' name=STRING 'with-remote-host' host=STRING 'port' port=STRING 'user-name' userName=STRING 'password' password=STRING 'session-timeout' sessionTimeOut=STRING 'channel-timeout' channelTimeOut=STRING 'source-file-to-download' sourceFile=STRING 'in-destination-to-save' destDir=STRING
       'ftp' 'as' name=STRING 'with-remote-host' host=STRING 'port' port=STRING 'user-name' userName=STRING 'password' password=STRING 'session-timeout' sessionTimeOut=STRING 'source-file-to-download' sourceFile=STRING 'in-destination-to-save' destDir=STRING
       'using' '{'     '}' ('on-condition' condition=expression)* ;


createZip:
       'create-zip' name = STRING 'file-name' fileName = STRING 'from' source = STRING 'destination' destination=STRING 'using'
        '{' '}' ('on-condition' condition=expression)*;

extractZip:
        'extract-zip' name = STRING 'from' source = STRING 'destination' destination=STRING 'using'
        '{' '}' ('on-condition' condition=expression)*;

sorGroupDetails:
    'sorGroupDetails' 'as' name=STRING 'on' on=STRING 'keyfields' keyfields=STRING  'searchfields' searchfields=STRING
    'groupbyfields' groupbyfields=STRING  'targettable' targettable=STRING 'using'
	'{'
	    value=STRING
	'}' ('on-condition' condition=expression)*;


zeroShotClassifier:
    'zeroShotClassifier' 'as' name=STRING
    'with-candidate-labels' candidateLabels=STRING 'using'
    '{'  content=STRING  '}' ('on-condition' condition=expression)*;


loadExtractedData:
    'loadExtractedData' 'as' name=STRING
    'file-path' filePath=STRING
    'paper-no' paperNo=STRING
    'intics-reference-id' inticsReferenceId=STRING
    'batch-id' batchId=STRING
    'target-dir' targetDir=STRING  'using'
    '{' '}' ('on-condition' condition=expression)*;

absentKeyFilter:
    'absentKeyFilter' 'as' name=STRING
    'file-path' filePath=STRING
    'paper-no' paperNo=STRING
    'intics-reference-id' inticsReferenceId=STRING
    'batch-id' batchId=STRING
    'sor-list' sorList=STRING  'using'
    '{' '}' ('on-condition' condition=expression)*;

sorFilter:
    'sorFilter' 'as' name=STRING
    'file-path' filePath=STRING
    'intics-reference-id' inticsReferenceId=STRING
    'search-value' searchValue=STRING 'using'
    '{' '}' ('on-condition' condition=expression)*;

triageAttribution:
    'triage' 'as' name=STRING
    'in-output-dir' outputDir=STRING
    'with-labelled-classifier-model-path' labelledClassifierModelFilePath=STRING
    'with-handwritten-extractor-model-path' handwrittenClassifierModelFilePath=STRING
    'with-checkbox-extractor-model-path' checkboxClassifierModelFilePath=STRING
    'using-synonyms' synonyms=STRING
    'using-labelled-classifier-labels' labelledClassifierLabels=STRING
    'using-vilt-coco-labels' viltCocoLabels=STRING
    'add-vilt-question-config' viltConfigLabel=STRING
    'add-vilt-coco-overide-config' isViltCocoOverride=STRING
    'add-vilt-coco-threshold-config' viltCocoThreshold=STRING
    'add-config-vgg-img-width' vggImageWidth=STRING
    'add-config-vgg-img-height' vggImageHeight=STRING
    'save-response-as'  triageAttributionResponseName=STRING
    '{'  inputFilePath=STRING  '}' ('on-condition' condition=expression)*;

docnetAttribution:
      'docnetAttribution' 'as' name=STRING
      'in-output-dir' outputDir=STRING
      'save-response-as'  docnetAttributionAsResponse=STRING
      'for-input-file'
      '{' inputFilePath=STRING  '}'
      'on-resource-conn' resourceConn=STRING
      'using-attribute-questions'
      '{' attributeQuestionSql=STRING  '}'
       ('on-condition' condition=expression)* ;

tqaFilter:
   'tqa-filter' 'as' name=STRING
   'in-output-dir' outputDir=STRING
   'using-truth-extractor-url' truthExtractorUrl=STRING
   'add-config-max-doctr-inner-join-diff' maxDoctrDiff=STRING
   'add-config-max-question-spacing-diff' maxQuestionDiff=STRING
   'on-resource-conn' resourceConn=STRING
   'using-synonmys'
    '{'  synonymSqlQuery=STRING  '}'
     'using-input-files'
    '{'  inputFilePathSqlQuery=STRING  '}'
     ('on-condition' condition=expression)* ('fielding' threadCount=NON_ZERO_DIGIT)* ('with-fetch-batch-size' fetchBatchSize=NON_ZERO_DIGIT)* ('with-write-batch-size' writeBatchSize=NON_ZERO_DIGIT)*  ;

textFilter:
      'text-filtering' 'as' name=STRING
      'key-filtering'  filteringKeys =STRING
      'for-input-file'
      '{' inputFilePath=STRING  '}' ('on-condition' condition=expression)* ;


entityFilter:
      'text-filtering' 'as' name=STRING 'with-doc-id' docId=STRING
      'for-paper' paperNo=STRING 'group-id' groupId = STRING
      'on-resource-conn' resourceConn=STRING
      'entity-key-filtering'  entityKeysToFilter =STRING
      'with-mandatory-key-filtering' mandatoryKeysToFilter = STRING
      'for-input-file'
      '{' inputFilePath=STRING  '}' ('on-condition' condition=expression)* ;

thresholdCheck:
       'check-threshold' 'as' name=STRING
       'threshold-value'  threshold=STRING
       '{' input=STRING '}'
       ('on-condition' condition=expression)* ;

jsonToFile:
	'jsonToFile' 'as' name= STRING
	'export-into' filePath=STRING
    'on-resource-conn' resourceConn=STRING
    'using'
	 '{'
       jsonSql=STRING
	 '}' ('on-condition' condition=expression)*;

docnetResult:
    'docnetResult' 'as' name=STRING
    'on-resource-conn' resourceConn=STRING
    'using-copro'
    '{' coproResultSqlQuery=STRING '}'
    'using-weightage'
    '{' weightageSqlQuery=STRING '}'('on-condition' condition=expression)*;

setContextValue:
    'setContextValue' 'as' name=STRING
    'context-key' contextKey=STRING
    'context-value' contextValue=STRING
    'using'
    '{'
    '}' ('on-condition' condition=expression)*;

evalPatientName:
    'evalPatientName' 'as' name=STRING
    'patient-name' patientName=STRING
    'word-count-limit' wordCountLimit=STRING
    'char-count-limit' charCountLimit=STRING
    'ner-api' nerCoproApi=STRING
    'word-count-threshold' wordCountThreshold=STRING
    'char-count-threshold' charCountThreshold=STRING
    'ner-api-threshold' nerApiThreshold=STRING
    'using'
    '{' '}'('on-condition' condition=expression)*;

evalMemberId:
    'evalMemberId' 'as' name=STRING
    'member-id' memberID=STRING
    'word-count-limit' wordCountLimit=STRING
    'char-count-limit' charCountLimit=STRING
    'special-character' specialCharacter=STRING
    'word-count-threshold' wordCountThreshold=STRING
    'char-count-threshold' charCountThreshold=STRING
    'validator-threshold' validatorThreshold=STRING
    'using'
    '{'  '}'('on-condition' condition=expression)*;

evalDateOfBirth:
    'evalDateOfBirth' 'as' name=STRING
    'date-of-birth' dob=STRING
    'word-count-limit' wordCountLimit=STRING
    'char-count-limit' charCountLimit=STRING
    'word-count-threshold' wordCountThreshold=STRING
    'char-count-threshold' charCountThreshold=STRING
    'comparable-year' comparableYear=STRING
    'date-formats' dateFormats=STRING
    'validator-threshold' validatorThreshold=STRING
    'using'
    '{'  '}'('on-condition' condition=expression)*;


dirPath:
        'dirPath' 'as' name=STRING
        'on-resource-conn' resourceConn=STRING
        'using' '{' filePath=STRING '}' ('on-condition' condition=expression)* ;

fileDetails:
        'fileDetails' 'as' name=STRING
        'dirpath' dirpath=STRING
        'groupId' group_id=STRING
        'inboundId' inbound_id=STRING
        'on-resource-conn' resourceConn=STRING
        'using' '{' '}' ('on-condition' condition=expression)* ;

wordcount:
        'wordcount' 'as' name=STRING
        'word-threshold' thresholdValue=STRING
        'input-value' inputValue=STRING
        'word-limit' countLimit=STRING
        'using' '{'  '}' ('on-condition' condition=expression)* ;

charactercount:
        'charactercount' 'as' name=STRING
        'char-threshold' thresholdValue=STRING
        'input-value' inputValue=STRING
        'char-limit' countLimit=STRING
        'using' '{'  '}' ('on-condition' condition=expression)* ;

datevalidator:
        'datevalidator' 'as' name=STRING
        'validator-threshold' thresholdValue=STRING
        'input-value' inputValue=STRING
        'allowed-date-formats' allowedDateFormats=STRING
        'comparable-date' comparableDate=STRING
        'using' '{'  '}' ('on-condition' condition=expression)* ;

alphavalidator:
        'alphavalidator' 'as' name=STRING
        'validator-threshold' thresholdValue=STRING
        'input-value' inputValue=STRING
        'allowed-special-characters' allowedSpecialCharacters=STRING
        'using' '{'  '}' ('on-condition' condition=expression)* ;

alphanumericvalidator:
        'alphanumericvalidator' 'as' name=STRING
        'validator-threshold' thresholdValue=STRING
        'input-value' inputValue=STRING
        'allowed-special-characters' allowedSpecialCharacters=STRING
        'using' '{'  '}' ('on-condition' condition=expression)* ;

numericvalidator:
        'numericvalidator' 'as' name=STRING
        'validator-threshold' thresholdValue=STRING
        'input-value' inputValue=STRING
        'allowed-special-characters' allowedSpecialCharacters=STRING
        'using' '{'  '}' ('on-condition' condition=expression)* ;

nervalidator:
        'nervalidator' 'as' name=STRING
        'ner-threshold' nerThreshold=STRING
        'input-value' inputValue=STRING
        'using' '{'  '}' ('on-condition' condition=expression)* ;

urgencyTriage:
    'urgencyTriage' 'as' name=STRING
    'input-file-path' inputFilePath=STRING
    'binary-classifier-model-file-path' binaryClassifierModelFilePath=STRING
    'multi-classifier-model-file-path' multiClassifierModelFilePath=STRING
    'checkbox-classifier-model-file-path' checkboxClassifierModelFilePath=STRING
    'synonyms' synonyms=STRING
    'binary-classifier-labels' binaryClassifierLabels=STRING
    'multi-classifier-labels' multiClassifierLabels=STRING
    'checkbox-classifier-labels' checkboxClassifierLabels=STRING
    'output-dir' outputDir=STRING
    'binary-image-width' binaryImageWidth=STRING
    'binary-image-height' binaryImageHeight=STRING
    'multi-image-width' multiImageWidth=STRING
    'multi-image-height' multiImageHeight=STRING
    'checkbox-image-width' checkboxImageWidth=STRING
    'checkbox-image-height' checkboxImageHeight=STRING
    'using'  '{' '}' ('on-condition' condition=expression)* ;

donutDocQa:
     'donut-docqa' 'as' name=STRING
     'in-output-dir' outputDir=STRING
     'on-resource-conn' resourceConn=STRING
     'save-response-as' responseAs=STRING
     'using'  '{' questionSql=STRING '}'
     ('on-condition' condition=expression)*
     ('on-parallel-fielding' forkBatchSize=STRING)*;

scalarAdapter:
      'scalarAdapter' 'as' name=STRING
      'on-resource-conn' resourceConn=STRING
      'process-id' processID=STRING
      'using-docnut-result'
      '{' resultSet=STRING  '}'
       ('on-condition' condition=expression)*;

phraseMatchPaperFilter:
    'phrase-match-paper-filter' 'as' name=STRING
    'on-resource-conn' resourceConn=STRING
    'for-process-id' processID=STRING
    'copro-url' endPoint=STRING
    'thread-count' threadCount=STRING
    'read-batch-size' readBatchSize=STRING
    'write-batch-size' writeBatchSize=STRING
    'with-input-query'
    '{'
        querySet=STRING

     '}'('on-condition' condition=expression)* ;

zeroShotClassifierPaperFilter:
    'zero-shot-classifier-paper-filter' 'as' name=STRING
    'on-resource-conn' resourceConn=STRING
    'for-process-id' processID=STRING
    'thread-count' threadCount=STRING
    'copro-url' endPoint=STRING
    'read-batch-size' readBatchSize=STRING
    'write-batch-size' writeBatchSize=STRING
    'with-input-query'
    '{'
        querySet=STRING

     '}'('on-condition' condition=expression)* ;


assetInfo:
	'assetInfo' 'as' name=STRING
	'on-resource-conn' resourceConn=STRING
	'get-audit-table' auditTable=STRING
	'result-table' assetTable=STRING
	'using' '{' values=STRING '}' ('on-condition' condition=expression)* ;

dataExtraction:
	'dataExtraction' 'as' name=STRING
	'resource-conn' resourceConn=STRING
	'result-table' resultTable=STRING
	'copro-url' endPoint=STRING
	'process-id' processId=STRING
	'using' '{' querySet=STRING '}' ('on-condition' condition=expression)* ;

episodeOfCoverage:
  'episodeOfCoverage' 'as' name=STRING
  'on-resource-conn' resourceConn=STRING
  'origin-id' originId=STRING
  'group-id' groupId=STRING
  'total-pages' totalPages=STRING
  'output-table' outputTable=STRING
  'grouping-item'  eocGroupingItem=STRING
  'patient-eoc-count' eocIdCount=STRING
  'input-file-path' '{' filepath=STRING '}'
  'qr-grouping' '{' qrInput=STRING '}'
  'eoc-grouping' '{' value=STRING '}'
  'pnd-grouping' '{' pndValue=STRING '}'  ('on-condition' condition=expression)* ;

userRegistration:
        'userRegistration' 'as' name=STRING
        'on-resource-conn' resourceConn=STRING
        'using'  '{' '}' ('on-condition' condition=expression)* ;

authToken:
         'authToken' 'as' name=STRING
         'on-resource-conn' resourceConn=STRING
         'using'  '{' '}' ('on-condition' condition=expression)* ;

eocJsonGenerator:
          'eocJsonGenerator' 'as' name=STRING
          'on-resource-conn' resourceConn=STRING
          'document-id' documentId=STRING
          'eoc-id' eocId=STRING
          'origin-id' originId=STRING
          'group-id' groupId=STRING
          'auth-token' authtoken=STRING
          'using'  '{' '}' ('on-condition' condition=expression)* ;

zipContentList:
            'zipContentList' 'as' name=STRING
            'on-resource-conn' resourceConn=STRING
            'document-id' documentId=STRING
            'origin-id' originId=STRING
            'zip-file-path' zipFilePath=STRING
            'using'  '{' '}' ('on-condition' condition=expression)* ;


hwDetection:
    'hwDetection' 'as' name=STRING
    'on-resource-conn' resourceConn=STRING
    'outputDir' directoryPath=STRING
    'copro-url' endPoint=STRING
    'output-table' outputTable=STRING
    'modelPath' modelPath=STRING
    'using'  '{'
        querySet=STRING
    '}' ('on-condition' condition=expression)* ;


intellimatch:
     'intellimatch' 'as' name=STRING
     'on-resource-conn' resourceConn=STRING
     'match-result' matchResult=STRING
     'using' '{' inputSet=STRING '}' ('on-condition' condition=expression)*;

checkboxVqa:
    'checkbox-vqa' 'as' name=STRING
    'on-resource-conn' resourceConn=STRING
    'process-id' processID=STRING
    'cad-model-path' cadModelPath=STRING
    'cd-model-path' cdModelPath=STRING
    'cr-model-path' crModelPath=STRING
    'text-model' textModel=STRING
    'cr-width' crWidth=STRING
    'cr-height' crHeight=STRING
    'output-dir' outputDir=STRING
    'using' '{' querySet=STRING '}' ('on-condition' condition=expression)*;

pixelClassifierUrgencyTriage:
    'pixel-classifier-urgency-triage' 'as' name=STRING
    'on-resource-conn' resourceConn=STRING
    'process-id' processID=STRING
    'binary-classifier-model-file-path' binaryClassifierModelFilePath=STRING
    'multi-classifier-model-file-path' multiClassifierModelFilePath=STRING
    'checkbox-classifier-model-file-path' checkboxClassifierModelFilePath=STRING
    'synonyms' synonyms=STRING
    'binary-classifier-labels' binaryClassifierLabels=STRING
    'multi-classifier-labels' multiClassifierLabels=STRING
    'checkbox-classifier-labels' checkboxClassifierLabels=STRING
    'output-dir' outputDir=STRING
    'binary-image-width' binaryImageWidth=STRING
    'binary-image-height' binaryImageHeight=STRING
    'multi-image-width' multiImageWidth=STRING
    'multi-image-height' multiImageHeight=STRING
    'checkbox-image-width' checkboxImageWidth=STRING
    'checkbox-image-height' checkboxImageHeight=STRING
    'using'  '{' querySet=STRING '}' ('on-condition' condition=expression)* ;

qrExtraction:
	'qr-extraction' 'as' name=STRING
	'on-resource-conn' resourceConn=STRING
	'copro-url' endPoint=STRING
	'process-id' processId=STRING
	'output-table' outputTable=STRING
	'using' '{' querySet=STRING '}' ('on-condition' condition=expression)* ;

paperItemizer:
	'paperItemizer' 'as' name=STRING
	'outputDir' outputDir=STRING
	'copro-url' endpoint=STRING
	'result-table' resultTable=STRING
	'processId' processId=STRING
	'resource-conn' resourceConn=STRING
	'using' '{'
		querySet=STRING
	'}' ('on-condition' condition=expression)* ;

nerAdapter:
      'nerAdapter' 'as' name=STRING
      'on-resource-conn' resourceConn=STRING
      'result-table' resultTable=STRING
      'using-docnut-result'
      '{' resultSet=STRING  '}'
       ('on-condition' condition=expression)*;



coproStart:
	'coproStart' 'as' name=STRING
	'for' moduleName=STRING
	'copro-server-url' coproServerUrl=STRING
	'export-command' exportCommand=STRING
	'process-id' processID=STRING
	'resource-conn' resourceConn=STRING
	'using'
	'{' command=STRING '}'
	('on-condition' condition=expression)*;

coproStop:
	'coproStop' 'as' name=STRING
	'for' moduleName=STRING
	'copro-server-url' coproServerUrl=STRING
	'process-id' processID=STRING
	'resource-conn' resourceConn=STRING
	'using'
	'{' command=STRING '}'
	('on-condition' condition=expression)*;

 outboundDeliveryNotify:
     'outbound-delivery-notify' 'as' name=STRING
     'document-id' documentId=STRING
     'intics-zip-uri' inticsZipUri=STRING
     'checksum' zipChecksum=STRING
     'resource-conn' resourceConn=STRING
     'using'  '{'  querySet=STRING  '}' ('on-condition' condition=expression)*;

 masterdataComparison:
     'masterdataComparison' 'as' name=STRING
     'on-resource-conn' resourceConn=STRING
     'match-result' matchResult=STRING
     'using' '{' inputSet=STRING '}' ('on-condition' condition=expression)*;

zipBatch:
    'zipBatch' 'as' name=STRING
    'group-id' groupId=STRING
    'output-dir' outputDir=STRING
    'on-resource-conn' resourceConn=STRING
    'using'  '{' '}' ('on-condition' condition=expression)* ;

drugMatch:
    'drugMatch' 'as' name=STRING
    'on-resource-conn' resourceConn=STRING
    'drug-compare' drugCompare=STRING
    'using' '{' inputSet=STRING '}' ('on-condition' condition=expression)* ;

urgencyTriageModel:
    'urgencyTriageModel' 'as' name=STRING
    'output-dir' outputDir=STRING
    'copro-url' endPoint=STRING
    'output-table' outputTable=STRING
    'resource-conn' resourceConn=STRING
    'using'  '{'  querySet=STRING  '}' ('on-condition' condition=expression)*;


donutImpiraQa:
     'donut-impira-qa' 'as' name=STRING
     'in-output-dir' outputDir=STRING
     'on-resource-conn' resourceConn=STRING
     'save-response-as' responseAs=STRING
     'using'  '{' questionSql=STRING '}'
     ('on-condition' condition=expression)*
     ('on-parallel-fielding' forkBatchSize=STRING)*;

templateDetection:
	'templateDetection'  'as' name=STRING
	'copro-url' coproUrl=STRING
	'resource-conn' resourceConn=STRING
	'input-table' inputTable=STRING
	'process-id' processId=STRING
	'ouput-table' ouputTable=STRING
	'using'  '{'
	 querySet=STRING
	'}'('on-condition' condition=expression)*;

trinityModel:
     'trinity-docqa' 'as' name=STRING
     'in-output-dir' outputDir=STRING
     'on-resource-conn' resourceConn=STRING
     'save-response-as' responseAs=STRING
     'api-endpoint' requestUrl=STRING
     'using'  '{' questionSql=STRING '}'
     ('on-condition' condition=expression)*
     ('on-parallel-fielding' forkBatchSize=STRING)*;


fileBucketing:
     'file-bucketing' 'as' name=STRING
     'output-dir' outputDir=STRING
     'using'  '{' inputDirectory=STRING '}'
     ('on-condition' condition=expression)*;

alchemyInfo:
    'alchemyInfo' 'as' name=STRING
    'tenantId' tenantId=STRING
    'auth-token' token=STRING
    'on-resource-conn' resourceConn=STRING
    'using'  '{'
     querySet=STRING
    '}'('on-condition' condition=expression)*;

alchemyAuthToken:
    'alchemyAuthToken' 'as' name=STRING
    'on-resource-conn' resourceConn=STRING
    'using'  '{'
    '}' ('on-condition' condition=expression)* ;

alchemyResponse:
    'alchemyResponse' 'as' name=STRING
    'tenantId' tenantId=STRING
    'auth-token' token=STRING
    'on-resource-conn' resourceConn=STRING
    'using'  '{'
     querySet=STRING
    '}'('on-condition' condition=expression)*;

productResponse:
    'productResponse' 'as' name=STRING
    'tenantId' tenantId=STRING
    'auth-token' token=STRING
    'result-table' resultTable=STRING
    'on-resource-conn' resourceConn=STRING
    'using'  '{'
     querySet=STRING
    '}'('on-condition' condition=expression)*;


tableExtraction:
	'tableExtraction' 'as' name=STRING
	'outputDir' outputDir=STRING
	'result-table' resultTable=STRING
	'processId' processId=STRING
	'resource-conn' resourceConn=STRING
	'using' '{'
		querySet=STRING
	'}' ('on-condition' condition=expression)* ;

mailServer:
	'mailServer' 'as' name=STRING
	'result-table' resultTable=STRING
	'processId' processId=STRING
	'resource-conn' resourceConn=STRING
	'using' '{'
		querySet=STRING
	'}' ('on-condition' condition=expression)* ;

alchemyKvpResponse:
    'alchemyKvpResponse' 'as' name=STRING
    'result-table' resultTable=STRING
    'processId' processId=STRING
    'resource-conn' resourceConn=STRING
    'using' '{'
    		querySet=STRING
    	'}' ('on-condition' condition=expression)* ;

alchemyTableResponse:
    'alchemyTableResponse' 'as' name=STRING
    'result-table' resultTable=STRING
    'processId' processId=STRING
    'resource-conn' resourceConn=STRING
    'using' '{'
    		querySet=STRING
    	'}' ('on-condition' condition=expression)* ;

productOutboundZipfile:
    'productOutboundZipfile' 'as' name=STRING
    'result-table' resultTable=STRING
    'outputdirectory' outputDir=STRING
    'processId' processId=STRING
    'resource-conn' resourceConn=STRING
    'using' '{'
    		querySet=STRING
    	'}' ('on-condition' condition=expression)* ;

fileMergerPdf:
    'file-merger-pdf' 'as' name=STRING
    'result-table' outputTable=STRING
    'copro-url' endPoint=STRING
    'resource-conn' resourceConn=STRING
    'output-directory' outputDir=STRING
    'using' '{'
        		querySet=STRING
        	'}' ('on-condition' condition=expression)* ;


zipFileCreationOutbound:
    'outbound-Zipfile' 'as' name=STRING
    'result-table' resultTable=STRING
    'outputdirectory' outputDir=STRING
    'processId' processId=STRING
    'resource-conn' resourceConn=STRING
    'using' '{'
    		querySet=STRING
    	'}' ('on-condition' condition=expression)* ;

outboundKvpResponse:
    'outboundKvpResponse' 'as' name=STRING
    'result-table' resultTable=STRING
    'processId' processId=STRING
    'resource-conn' resourceConn=STRING
    'using' '{'
    		querySet=STRING
    	'}' ('on-condition' condition=expression)* ;

outboundTableResponse:
    'outboundTableResponse' 'as' name=STRING
    'result-table' resultTable=STRING
    'processId' processId=STRING
    'resource-conn' resourceConn=STRING
    'using' '{'
    		querySet=STRING
    	'}' ('on-condition' condition=expression)* ;


integratedNoiseModelApi:
	'qr-extraction' 'as' name=STRING
	'on-resource-conn' resourceConn=STRING
	'copro-url' endPoint=STRING
	'process-id' processId=STRING
	'output-table' outputTable=STRING
	'using' '{' querySet=STRING '}' ('on-condition' condition=expression)* ;


//rules

resource : STRING;
fragment DIGIT : [0-9];
fragment NON_Z_DIGIT : [1-9];
fragment TWODIGIT : DIGIT DIGIT;
fragment LETTER : [A-Za-z];
fragment
StringCharacters
	:	StringCharacter+
	;
fragment
StringCharacter
	:	~["\\]
	;
NON_ZERO_DIGIT: NON_Z_DIGIT+;
STRING :	'"' StringCharacters? '"';
CRLF : ('\r'? '\n' | '\r')-> channel(HIDDEN) ;
Operator:
	'<'|'>'|'=='|'contains'
;
WS  :  [ \t\r\n\u000C]+ -> channel(HIDDEN)
    ;
COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN)
    ;
LINE_COMMENT
    :   '//' ~[\r\n]* -> channel(HIDDEN)
    ;

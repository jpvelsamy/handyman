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
    |mapJsonContext);

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
	'}' ('on-condition' condition=expression)*
;


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

		value=STRING
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
    'producer''as' name=STRING 'push-result-at' push=STRING
     'for-every''{'
     	stmt=STRING
     '}'
     'execute''{'
       (actions+=action)*
     '}' ('on-condition' condition=expression)* ;

consumer:
    'consumer''as' name=STRING 'pop-result-from' pop=STRING 'limit' limit=NON_ZERO_DIGIT 'execute''{'
       (actions+=action)*
       '}' ('on-condition' condition=expression)* ;


pushJson :
     'push-json-into-context' 'as' name=STRING 'with-key' key=STRING 'using-value'  '{'
       value=json
    '}' ('on-condition' condition=expression)* ;

mapJsonContext :
      'map-json-into-context' 'as' name=STRING  'using'  '{'
                                                      value=STRING
                                                   '}' ('on-condition' condition=expression)* ;

expression :'if' (lhs=STRING operator=Operator rhs=STRING);

log:
   'log' 'as' 'level' level=STRING 'with' 'message' '{' message=STRING '}';

exception:
    'raise exception' '{' message=STRING '}';

resource : STRING;

//rules

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

grammar Raven;
@header {
package in.handyman.raven.compiler;
}

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
	|copyData
	|dropFile
	|fbFormDownload
	|fbLead
	|loadCsv
	|restApi
	|transform)
;

copyData:
	('copydata' 'as' name=STRING 'from' source=STRING 'to' to=STRING  'using'
	'{'
		value=STRING
	'}' 'on-condition' condition=expression 'fielding'
	 writeThreadCount=NON_ZERO_DIGIT 'with-fetch-batch-size'
	fetchBatchSize=NON_ZERO_DIGIT 'with-write-batch-size' writeBatchSize=NON_ZERO_DIGIT)
	 ;

transform:
	'transform' 'as' name= STRING 'on' on= STRING 'using'
	 '{'
		value+=STRING
	'}' 'on-condition' condition=expression
	;

loadCsv:
	'loadcsv' 'as' name=STRING 'pid' pid=STRING 'from' source=STRING 'to' to=STRING 'with' delim=STRING 'by-batch'
	limit=STRING 'using'
	'{'
	value=STRING
	'}' 'on-condition' condition=expression;

abort:
	'abort' 'as' name=STRING
	'{'
		value=STRING
	'}' 'on-condition' condition=expression
;

callProcess:
	'callprocess' 'as' name=STRING 'with-target' target=STRING 'from-file' source=STRING 'using' datasource=STRING
	'for-every'
	'{'
	value=STRING
	'}' 'on-condition' condition=expression;


assign:
	'assign' 'as' name=STRING 'source' source=STRING 'using'
	'{'
		value=STRING
	'}' 'on-condition' condition=expression
;

fbLead:
	'fblc' 'as' name=STRING 'secured-by' accessToken=STRING 'with-key' appSecret=STRING 'through-user' accountId=STRING 'for-campaign' campaignId=STRING 'into' target=STRING 'using'
	'{'
		value=STRING
	'}' 'on-condition' condition=expression
;


fbFormDownload:
	'fbfd' 'as' name=STRING 'secured-by' accessToken=STRING 'with-key' appSecret=STRING 'through-user' accountId=STRING 'for-form' formId=STRING 'into' target=STRING 'using'
	'{'
		value=STRING
	'}' 'on-condition' condition=expression
;

dropFile:
	'dropfile' 'as' name=STRING 'in-path' target=STRING 'on-condition' condition=expression
;

restApi:
	'restapi' 'as' name=STRING 'url' url=STRING 'method' method=STRING 'with headers' header=json
	'{'
	value=json
	'}' 'on-condition' condition=expression;

expression :'if' (lhs=STRING operator=Operator rhs=STRING);

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
CRLF : '\r'? '\n' | '\r' ;
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

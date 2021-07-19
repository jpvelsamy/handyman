grammar Raven;
@header {
package in.handyman.raven.compiler;
}

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
	|loadCsv
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
	'loadcsv' 'as' name= STRING 'from' source= STRING 'to' to= STRING 'with'  delim= STRING 'with-fetch-batch-size'
	 fetchBatchSize=NON_ZERO_DIGIT 'with-write-batch-size' writeBatchSize=NON_ZERO_DIGIT 'using'
	'{'
		value=STRING
	'}' 'on-condition' condition=expression 'fielding' writeThreadCount=NON_ZERO_DIGIT
	;

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

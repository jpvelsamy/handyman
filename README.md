
# Handyman

## Tech stack:
1. Java
2. SQL

## Framework:
1. ANTLR
2. JOOQ

## why ANTLR ?
ANTLR (ANother Tool for Language Recognition) is a versatile framework pivotal in developing Domain-Specific Language (DSL) applications. It simplifies the creation of parsers, interpreters, and analyzers, enabling concise expression of intricate concepts.

With ANTLR, DSLs become coherent and approachable. Developers define a DSL's grammar formally, ensuring accurate parsing and consistent interpretation. This approach streamlines development and maintains precision.

Key ANTLR features for DSL applications:

1. **Grammar Specification**: Developers outline DSL syntax and semantics using ANTLR's grammar notation, defining precise rules for input processing.

2. **Parsing and Lexing**: ANTLR efficiently generates parsers and lexers, translating input text into structured data aligned with DSL rules.

3. **Abstract Syntax Tree (AST)**: ANTLR parsers generate an AST, which reflects parsed input hierarchy, forming the foundation for interpretation.

4. **Visitor and Listener Patterns**: ANTLR supports Visitor and Listener patterns for traversing and analyzing AST nodes, enabling logic implementation.

5. **Error Handling**: ANTLR ensures robust error reporting and recovery, enhancing meaningful error messages and graceful input handling.

DSLs powered by ANTLR find utility across sectors:

- **Configuration Management**: ANTLR-driven DSLs define concise configuration files, enhancing settings specification.

- **Query Languages**: ANTLR DSLs succinctly articulate complex queries, streamlining data retrieval from various sources.

- **Mathematical Modeling**: ANTLR-based DSLs enable mathematical expression creation for analysis and simulation.

- **Rule Engines**: ANTLR DSLs serve as rule engines, defining decision logic and actions.

- **Scripting Languages**: ANTLR facilitates DSLs as lightweight scripting languages, augmenting larger applications.

To conclude, ANTLR empowers DSL application creation by simplifying grammar definition, parsing, and interpretation. This tool is crucial for crafting efficient, domain-specific languages in diverse fields. As specialized language solutions gain traction, ANTLR remains instrumental in tailoring DSLs to precise needs.



## To get the repository clone

 > git clone https://github.com/zucisystems-dev/handyman.git

## To see the list of branch in the repo (default master)
 > git branch -a
 
## To change to the particular branch
 > git checkout {{branch}}
 
## To Clean, Compile and build, run the below maven commands 
  1. In the project root dir
     > mvn clean antlr4:antlr4 test -Dtest=ActionGenerationTest#generate compile install
  
 ### Generated jar file available in target folder as *handyman-raven-vm-{{version}}.jar*

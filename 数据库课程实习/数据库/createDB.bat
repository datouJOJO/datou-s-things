sqlplus system/62591102@127.0.0.1:1521/orcl @.\scripts\createTableScripts\createTunit.sql>dblog.log

sqlplus system/62591102@127.0.0.1:1521/orcl @.\scripts\createTriggerScripts\createTrigger.sql>triggerlog.log

sqlplus system/62591102@127.0.0.1:1521/orcl @.\scripts\createProcedureScripts\procedureUnit.sql>procedure.log

java -jar .\loadPicImfo\loadpic0.1.jar orcl system 62591102>pic.log
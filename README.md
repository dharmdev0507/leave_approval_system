# leave_approval_system

Camunda BPMN workflow for leave approval.

-The application receives leave details.

-The service saves the leave request in the database.

-It starts a Camunda workflow.

-It passes leave details to the workflow.

-It saves the Camunda process instance ID.

-It returns the response to the client.

check application locally using below details

API 1: Apply for leave
------------------------------------------
Link - http://localhost:8080/api/leaves
Method - POST
Payload -

{
  "employeeName": "Rahul",
  "leaveType": "CASUAL",
  "numberOfDays": 3
}


API 2: Get leave details
------------------------
Link -  http://localhost:8080/api/leaves/1
Method - Get

Response:
{
  "id": 1,
  "employeeName": "Rahul",
  "leaveType": "CASUAL",
  "numberOfDays": 3,
  "status": "APPROVED",
  "processInstanceId": "some-process-instance-id"
}

API 3: Test rejection
------------------------
Link -   http://localhost:8080/api/leaves
Method - POST
Payload: 
{
  "employeeName": "Amit",
  "leaveType": "EARNED",
  "numberOfDays": 10
}

================================
Connect to DB
===============================

http://localhost:8080/h2-console/

Saved Settings : Generic H2(Embedded)
Setting Name : Generic H2(Embedded)

Driver Class : org.h2.Driver
JDBC URL : jdbc:h2:mem:leavedb
User Name : sa
Password : sa

Check leave request 
sql -> SELECT * FROM LEAVE_REQUEST;

-----------
For more Information Run:
sql -> SELECT * FROM ACT_RE_PROCDEF;
comment ->This checks deployed process definitions.

sql -> SELECT * FROM ACT_RU_EXECUTION;
comment -> This checks running process executions.

sql-> SELECT * FROM ACT_HI_PROCINST;
comment -> This checks process history, including completed instances.

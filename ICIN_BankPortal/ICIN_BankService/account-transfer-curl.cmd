curl.exe localhost:8080/api/bank-service/account-transfer -X POST ^
 -H 'Content-Type: application/json' ^
 -d '{"masterAccountId":6, "primaryAccountId":6,"secondaryAccountId":5, "transferAmount":13.00 }'

## Import DataSet

```
LOAD CSV WITH HEADERS FROM "file:///MOCK_DATA.csv" AS row
MERGE  (n:Person {id: row.id})
SET n += row
MERGE (t:Test {id: row.id})
SET t.date = row.test_date, 
t.result = 
    CASE 
        WHEN row.test_result = "false" 
        THEN "negative" 
        ELSE "positive" 
    END
MERGE (n)-[:TEST_RESULT]->(t)
MERGE (c:City {name: row.city})
MERGE (n)-[:LIVES_IN]->(c)
```


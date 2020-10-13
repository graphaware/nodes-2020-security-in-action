# Fine-Grained Access Control

## Overview 

### Roles

Lab : Should be able to create a new test and attach it to a Person, cannot read personal information such as NHS number

Doctor : Should be able to do the same as `Lab` but with the ability to see PI 

Secretary : Can only see name and city, cannot access Lab test results

IT Admin : Similar to the built-in admin role, but we want to restrict the access to PI information and he should not be able 
to create new Lab Test results

### Users

Lab 

- jane

Doctor 

- claire

Secretary 

- maria

IT Admin

- bob



### Queries

Create the users 

```
CREATE USER jane SET PASSWORD "pass" CHANGE NOT REQUIRED;
CREATE USER claire SET PASSWORD "pass" CHANGE NOT REQUIRED;
CREATE USER maria SET PASSWORD "pass" CHANGE NOT REQUIRED;
CREATE USER bob SET PASSWORD "pass" CHANGE NOT REQUIRED;

```

Grant access on the `nodes2020` database to the `PUBLIC` role, instead of each individual role.

```
GRANT ACCESS ON DATABASE nodes2020 TO PUBLIC;
```


Create copy of `admin` as new `itadmin` role

```
CREATE ROLE itadmin AS COPY OF admin;
```

```
DENY READ {nhs_number} ON GRAPH nodes2020 NODES Person TO itadmin;
DENY CREATE ON GRAPH nodes2020 RELATIONSHIPS TEST_RESULT TO itadmin;
```

Grant the `itadmin` role to `bob`

```
GRANT ROLE itadmin TO bob;
```


Create the Lab role 

```


// Create the role first
CREATE ROLE lab;
GRANT TRAVERSE
    ON GRAPH nodes2020
    NODES *
    TO lab;
GRANT TRAVERSE
    ON GRAPH nodes2020
    RELATIONSHIPS TEST_RESULT
    TO lab;
GRANT READ {*}
    ON GRAPH nodes2020
    NODES Test
    TO lab;
GRANT READ {id}
    ON GRAPH nodes2020
    NODES Person
    TO lab;
GRANT CREATE
    ON GRAPH nodes2020 
    NODES Test TO lab;
GRANT CREATE
    ON GRAPH nodes2020
    RELATIONSHIPS TEST_RESULT
    TO lab;
GRANT SET PROPERTY { * }
    ON GRAPH nodes2020 
    NODES Test
    TO lab;
```

GRANT the lab role to jane

```
GRANT ROLE lab TO jane;
```


Check that jane can create a new result and doesn't have access to PI information


```
MATCH (n:Person {id: "20"})
CREATE (t:Test {date: datetime(), result: 'negative', id: '7800'})
CREATE (n)-[:TEST_RESULT]->(t);
```



# Nodes 2020 - Neo4j Security in Action

Hands on talk describing how to harden your Neo4j server with an evaluation based
on the STRIDE model : 

S - Spoofing of Identity

T - Tampering of data

R - Repudiation

I - Information Disclosure

D - Denial of Service

E - Elevation of Privileges


## Scenario

We have one database that represents a graph of patients, the city they live in as well as their 
corresponding lab test.

Patients also have a sensitive NHS (National Health System) number as attribute.


## Hardening

### S - Spoofing of Identity

Stealing the identiy of someone and using it for accessing information.

By default, the Neo4j browser will retain connection credentials, disable it with 

```
browser.retain_connection_credentials=false
```


### T - Tampering of Data

- Enable TLS for your Neo4j Server
- Enable also TLS for intra-cluster encryption

See `ssl/ssl.md`

- Do not run Neo4j on standard ports (Change the default 7474 and 7687 ports to other ports known by you)


### R - Repudiation

- Enable Query Logging
- Add Transaction Metadata to your Neo4j queries with an official driver


### I - Information Disclosure

- Least amount of privileges ( see `rbac/RBAC.md`)

### D - Denial Of Service

- Monitor memory usage of transactions and which one can potentially cause denial of services

### E - Elevation of Privileges

- Monitor and parse security.log file






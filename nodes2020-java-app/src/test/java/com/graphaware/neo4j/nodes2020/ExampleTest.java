package com.graphaware.neo4j.nodes2020;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.*;

import java.util.Map;

public class ExampleTest {

    @Test
    public void testWriteToNeo4jWithoutMetadata() {
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "pass"))) {
            try (Session session = driver.session(SessionConfig.forDatabase("system"))) {
                session.run("DROP DATABASE production");
            }
        }
    }

    @Test
    public void testWriteToNeo4jWithMetadata() {
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "pass"))) {
            try (Session session = driver.session(SessionConfig.forDatabase("system"))) {
                session.writeTransaction((tx) -> {
                    tx.run("DROP DATABASE production");
                    return null;
                }, TransactionConfig.builder().withMetadata(getMetadata()).build());
            }
        }
    }

    Map<String, Object> getMetadata() {
        return Map.of(
                "application", "nodes2020",
                "user", "ikwattro",
                "reason", "administrative-cleanup"
        );
    }
}

package de.consol.dus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(GreetingResource.class)
class GreetingResourceTest {

  @Test
  void getGreeting() {
    // @formatter:off
    given()
        .when()
            .contentType(MediaType.APPLICATION_JSON)
            .get("John Doe")
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("salutation", is("Hello"))
            .body("name", is("John Doe"));
    // @formatter:on
  }

  @Test
  void persistAndGetGreeting() {
    // @formatter:off
    given()
        .when()
            .delete("John Doe")
        .then()
            .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    given()
        .when()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new Greeting().setSalutation("Hi").setName("John Doe"))
            .post()
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("salutation", is("Hi"))
            .body("name", is("John Doe"));
    given()
        .when()
            .delete("John Doe")
        .then()
            .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    // @formatter:on
  }

  @Test
  void getAllGreetings() {
    final List<Greeting> expected = List.of(
        new Greeting().setSalutation("Hello").setName("John Doe"),
        new Greeting().setSalutation("Hi").setName("Jane Doe"));
    expected.forEach(greeting -> {
      given()
          .when()
              .delete(greeting.getName())
          .then()
              .statusCode(Response.Status.NO_CONTENT.getStatusCode());
      given()
          .when()
              .contentType(MediaType.APPLICATION_JSON)
              .body(greeting)
              .post()
          .then()
              .statusCode(Response.Status.OK.getStatusCode())
              .body("salutation", is(greeting.getSalutation()))
              .body("name", is(greeting.getName()));
    });

    final List<Greeting> actual = given()
        .when()
            .contentType(MediaType.APPLICATION_JSON)
            .get()
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .extract()
                .as(new TypeReference<List<Greeting>>(){}.getType());
    actual.retainAll(expected);
    assertThat(actual, containsInAnyOrder(expected.toArray()));

    expected.stream()
        .map(Greeting::getName)
        .forEach(name ->
            given()
                .when()
                    .delete(name)
                .then()
                    .statusCode(Response.Status.NO_CONTENT.getStatusCode()));
  }

}
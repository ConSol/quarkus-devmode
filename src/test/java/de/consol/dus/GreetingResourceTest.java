package de.consol.dus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
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
            .get()
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body(is("Hello IBM!"));
    // @formatter:on
  }
}
package apiTestsStore;

import api.EndPoints;
import api.ProductDTO;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {

    Logger logger = Logger.getLogger(getClass());




    @Test
    public void getAllProducts() {

        ProductDTO[] responseBody = given()
                .spec(requestSpecification)
                .queryParam("category", "coffee")
                .when()
                .get(EndPoints.All_PRODUCTS)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ProductDTO[].class);

        logger.info("Number of products: " + responseBody.length);
        logger.info("Name of product 1: " + responseBody[0].getName());


        ProductDTO[] expectedResult = {
                ProductDTO.builder().category("coffee").name("Starbucks Coffee Variety Pack, 100% Arabica").inStock(true)
                        .build(),
                ProductDTO.builder().category("coffee").name("Ethical Bean Medium Dark Roast, Espresso").inStock(true)
                        .build(),
                ProductDTO.builder().category("coffee").name("Don Francisco Colombia Supremo Medium Roast").inStock(true)
                        .build()
        };

        Assert.assertEquals("Number of products: ",expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i], "id");
        }

        softAssertions.assertAll();
    }

    @Test
    public void getStatus() {
        String actualResponse = given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.STATUS)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();

        Assert.assertEquals("Status of API: ", "\"status\":\"UP\"", actualResponse.replace("{","").replace("}",""));
    }


    @Test
    public void getAllProductsSchema(){
        given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.All_PRODUCTS)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }


}

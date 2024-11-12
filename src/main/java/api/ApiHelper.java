package api;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import libs.Util;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private static String CLIENT_NAME = "Postman on User's computer";
    private static String CLIENT_EMAIL = "user" + Util.getDateAndTimeFormatted() + "@example.com";

    String cartId = getCartId();

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken() {
        return getToken(CLIENT_NAME, CLIENT_EMAIL);
    }

    public String getToken(String clientName, String clientEmail) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("clientName",clientName);
        requestParams.put("clientEmail", clientEmail);

        ResponseBody responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPoints.REGISTER)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response().getBody()
                ;
        return responseBody.jsonPath().getString("accessToken");
    }

    public String getCartId() {

        CartDTO responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .post(EndPoints.CREATE_NEW_CART)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response().as(CartDTO.class);

        Assert.assertEquals("Cart is not created: ", true, responseBody.getCreated());

        String cartId = responseBody.getCartId();
        return cartId;
    }

    public void addItemToCart(String cartId){
        HashMap<String, Integer> requestParamsItems = new HashMap<>();
        requestParamsItems.put("productId", 4646);

        AddItemToCartDTO responseBody =
                given()
                .spec(requestSpecification)
                        .pathParams("0", cartId)
                        .body(requestParamsItems)
                .when()
                .post(EndPoints.ADD_ITEM_TO_CART)
                .then()
                .statusCode(201)
                .log().all()
                .extract().response().as(AddItemToCartDTO.class);

        Assert.assertEquals("Item is not added to cart: ", true, responseBody.getCreated());

    }

}

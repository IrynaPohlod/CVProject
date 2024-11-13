package apiTestsStore;

import api.ApiHelper;
import api.EndPoints;
import api.OrderDTO;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CreateAnOrderByApi {
    ApiHelper apiHelper = new ApiHelper();

    private String cartId;


    @Before
    public void addItemToCart(){
        cartId = apiHelper.getCartId();
        apiHelper.addItemToCart(cartId);
    }

    @Test
    public void createAnOrderByApi() {
        String token = apiHelper.getToken();

        HashMap<String, String> params = new HashMap<>();
        params.put( "cartId", cartId);
        params.put("customerName", "randomFullName");

               OrderDTO responseBody =
                       given()
                               .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                               .log().all()
                .body(params)
                .when()
                .post(EndPoints.CREATE_ORDER)
                .then()
                               .log().body()
                .statusCode(201)
                .log().all()
                .extract().response().getBody().as(OrderDTO.class);


        Assert.assertEquals("Order is not created: ", true, responseBody.getCreated());


    }


}

package api;


public interface EndPoints {
    String baseUrlApi = "https://simple-grocery-store-api.glitch.me";
    String All_PRODUCTS = baseUrlApi + "/products";
    String STATUS = baseUrlApi + "/status";

    String REGISTER = baseUrlApi + "/api-clients";

    String CREATE_NEW_CART = baseUrlApi + "/carts";
    String CREATE_ORDER = baseUrlApi + "/orders";
    String ADD_ITEM_TO_CART = baseUrlApi + "/carts/{0}/items";
}

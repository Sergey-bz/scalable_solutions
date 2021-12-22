package http;

public interface Endpoints {

    String BASE_PATH = "/api/order";

    String GET_ORDER = BASE_PATH;

    String DELETE_ORDER = BASE_PATH;

    String CLEAN_ORDER = BASE_PATH + "/clean";

    String CREATE_ORDER = BASE_PATH + "/create";

    String MARKET_DATA = "/api/marketdata";
}

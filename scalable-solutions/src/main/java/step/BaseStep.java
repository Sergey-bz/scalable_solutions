package step;

import http.RequestExecutor;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.CleanRs;
import model.MarketData;
import model.Order;
import model.test_data.TestData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static http.Endpoints.*;
import static utils.ResponseUtil.getResponseBodyAsClass;

public class BaseStep {

    private RequestExecutor requestExecutor = new RequestExecutor();
    protected Response response;
    protected Order order;
    protected Order createRq;
    protected CleanRs cleanRs;

    private final static Logger LOGGER = LogManager.getLogger(BaseStep.class.getSimpleName());

    @Step("Получение заявки по \"id\"")
    public void sendRequestGetOrderById(String id) {
        LOGGER.info("Отправка запроса на получение заявки по \"id\"");
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        response = requestExecutor.sendGetRequest(GET_ORDER, queryParams);
    }

    @Step("Получение заявки без параметров")
    public void sendRequestGetOrderWithoutParams() {
        LOGGER.info("Отправка запроса на получение заявки без параметров");
        response = requestExecutor.sendGetRequest(BASE_PATH);
    }

    @Step("Удаление заявки по \"id\"")
    public void sendRequestDeleteOrderById(String id) {
        LOGGER.info("Отправка запроса на удаление заявки по \"id\"");
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        response = requestExecutor.sendDeleteRequest(DELETE_ORDER, queryParams);
    }

    @Step("Удаление заявки без параметров")
    public void sendRequestDeleteOrderWithoutParams() {
        LOGGER.info("Отправка запроса на удаление заявки без параметров");
        response = requestExecutor.sendDeleteRequest(BASE_PATH);
    }

    @Step("Очистка книги заказов")
    public void sendRequestCleanOrderBook() {
        LOGGER.info("Отправка запроса на очистку книги заказов");
        response = requestExecutor.sendGetRequest(CLEAN_ORDER);
    }

    @Step("Создание заказа")
    public void sendRequestCreateAnOrder(TestData testData) {
        LOGGER.info("Отправка запроса на создание заказа");
        createRq = testData.getOrder();
        response = requestExecutor.sendPostRequest(CREATE_ORDER, createRq);
    }

    @Step("Получение текущего состояния рынка")
    public void sendRequestGetSnapshotOfMarketdata() {
        LOGGER.info("Отправка запроса на получение текущего состояния рынка");
        response = requestExecutor.sendGetRequest(MARKET_DATA);
        getResponseBodyAsClass(response, MarketData.class);
    }

    @Step("Проверка успешности ответного сообщения")
    public void checkResponseOnSuccess() {
        LOGGER.info("Проверка успешности ответного сообщения");
        response.then().statusCode(200);
    }

    @Step("Проверка ответного сообщения на предмет ошибки \"400 - Bad Request\"")
    public void checkResponseOnBadRequest() {
        LOGGER.info("Проверка ответного сообщения на предмет ошибки \"400 - Bad Request\"");
        response.then().statusCode(400);
    }

    @Step("Проверка ответного сообщения на предмет ошибки \"404 - Not found\"")
    public void checkResponseNotFound() {
        LOGGER.info("Проверка ответного сообщения на предмет ошибки \"404 - Not found\"");
        response.then().statusCode(404);
    }
}

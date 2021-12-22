package step;

import io.qameta.allure.Step;
import model.CleanRs;
import model.Order;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.ResponseUtil;

public class OrderStep extends BaseStep {

    private final static Logger LOGGER = LogManager.getLogger(OrderStep.class.getSimpleName());

    @Step("Проверка маппинга запроса/ответа")
    public void checkRqRsMapping() {
        LOGGER.info("Проверка маппинга запроса/ответа");
        Order expected = createRq;
        Order actual = ResponseUtil.getResponseBodyAsClass(response, Order.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual.getId(), expected.getId(), "id в запросе и ответе отличается");
        softAssert.assertEquals(actual.getPrice(), expected.getPrice(), "price в запросе и ответе отличается");
        softAssert.assertEquals(actual.getQuantity(), expected.getQuantity(), "quantity в запросе и ответе отличается");
        softAssert.assertEquals(actual.getSide().toLowerCase(), expected.getSide().toLowerCase(), "side в запросе и ответе отличается");
        softAssert.assertAll();
    }

    @Step("Проверка сообщения ответа при очистке книги заказов")
    public void checkCleanRsMessage() {
        LOGGER.info("Проверка сообщения ответа при очистке книги заказов");
        String message = ResponseUtil.getResponseBodyAsClass(response, CleanRs.class).getMessage();
        Assert.assertEquals(message, "Order book is clean.", "Сообщение ответа не соответствует ожидаемому");
    }
}

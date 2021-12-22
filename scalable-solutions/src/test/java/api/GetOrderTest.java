package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import model.test_data.TestData;
import org.testng.annotations.Test;
import step.OrderStep;

@Epic("Биржевой стакан")
@Test(groups = {"api"})
public class GetOrderTest {

    @Story("Получение заявки")
    @Test(description = "Проверка получения заявки по \"id\"",
            dataProvider = "testData4checkGetOrderById",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkGetOrderById(TestData testData, OrderStep step) {
        step.sendRequestCreateAnOrder(testData);
        step.sendRequestGetOrderById(testData.getOrder().getId());
        step.checkResponseOnSuccess();
        step.checkRqRsMapping();
    }

    @Story("Получение заявки")
    @Test(description = "Проверка получения заявки | Отсутствует обязательный параметр",
            dataProvider = "testData4checkGetOrderById",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkGetOrderWithoutId(TestData testData, OrderStep step) {
        step.sendRequestGetOrderWithoutParams();
        step.checkResponseOnBadRequest();
    }

    @Story("Получение заявки")
    @Test(description = "Проверка получения заявки | Некорректный запрос",
            dataProvider = "testData4checkGetOrderWithIncorrectId",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkGetOrderWithIncorrectId(TestData testData, OrderStep step) {
        step.sendRequestCreateAnOrder(testData);
        step.sendRequestGetOrderById(testData.getOrder().getId() + ".0");
        step.checkResponseOnBadRequest();
    }

    @Story("Получение заявки")
    @Test(description = "Проверка получения заявки | Заявка не существует",
            dataProvider = "testData4checkGetOrderNotExist",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkGetOrderNotExist(TestData testData, OrderStep step) {
        step.sendRequestCleanOrderBook();
        step.sendRequestGetOrderById(testData.getOrder().getId());
        step.checkResponseNotFound();
    }
}

package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import model.test_data.TestData;
import org.testng.annotations.Test;
import step.OrderStep;

@Epic("Биржевой стакан")
@Test(groups = {"api"})
public class DeleteOrderTest {

    @Story("Удаление заявки")
    @Test(description = "Проверка удаления заявки по \"id\"",
            dataProvider = "testData4checkDeleteOrderById",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkDeleteOrderById(TestData testData, OrderStep step) {
        step.sendRequestCreateAnOrder(testData);
        step.sendRequestDeleteOrderById(testData.getOrder().getId());
        step.checkRqRsMapping();
        step.sendRequestGetOrderById(testData.getOrder().getId());
        step.checkResponseNotFound();
    }

    @Story("Удаление заявки")
    @Test(description = "Проверка удаления заявки | Отсутствует обязательный параметр",
            dataProvider = "testData4checkDeleteOrderById",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkDeleteOrderWithoutId(TestData testData, OrderStep step) {
        step.sendRequestDeleteOrderWithoutParams();
        step.checkResponseOnBadRequest();
    }

    @Story("Удаление заявки")
    @Test(description = "Проверка удаления заявки | Некорректный запрос",
            dataProvider = "testData4checkDeleteOrderById",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkDeleteOrderWithIncorrectId(TestData testData, OrderStep step) {
        step.sendRequestDeleteOrderById(testData.getOrder().getId() + "000");
        step.checkResponseOnBadRequest();
    }

    @Story("Удаление заявки")
    @Test(description = "Проверка удаления заявки | Заявка не существует",
            dataProvider = "testData4checkDeleteOrderById",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkDeleteOrderNotExist(TestData testData, OrderStep step) {
        step.sendRequestCleanOrderBook();
        step.sendRequestDeleteOrderById(testData.getOrder().getId());
        step.checkResponseNotFound();
    }
}

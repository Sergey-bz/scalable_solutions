package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import model.test_data.TestData;
import org.testng.annotations.Test;
import step.OrderStep;

@Epic("Биржевой стакан")
@Test(groups = {"api"})
public class CleanOrdersTest {

    @Story("Очистка книги заказов")
    @Test(description = "Проверка очистки книги заказов",
            dataProvider = "testData4checkCleanOrders",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkCleanOrders(TestData testData, OrderStep step) {
        step.sendRequestCleanOrderBook();
        step.checkResponseOnSuccess();
        step.checkCleanRsMessage();
    }

    @Story("Очистка книги заказов")
    @Test(description = "Проверка получения заявки после очистки книги заказов",
            dataProvider = "testData4checkCleanOrders",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkGetOrderAfterClean(TestData testData, OrderStep step) {
        step.sendRequestCleanOrderBook();
        step.sendRequestGetOrderById(testData.getOrder().getId());
        step.checkResponseNotFound();
    }
}

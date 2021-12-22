package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import model.test_data.TestData;
import org.testng.annotations.Test;
import step.OrderStep;

@Epic("Биржевой стакан")
@Test(groups = {"api"})
public class CreateTest {

    @Story("Создание заявки")
    @Test(description = "Проверка создания заявки",
            dataProvider = "testData4checkCreateOrder",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkCreateOrder(TestData testData, OrderStep step) {
        step.sendRequestCreateAnOrder(testData);
        step.checkResponseOnSuccess();
        step.checkRqRsMapping();
    }

    @Story("Создание заявки")
    @Test(description = "Проверка создания заявки без опциональных параметров",
            dataProvider = "testData4checkCreateOrderWithoutOptionalParams",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkCreateOrderWithoutOptionalParams(TestData testData, OrderStep step) {
        step.sendRequestCreateAnOrder(testData);
        step.checkResponseOnSuccess();
    }

    @Story("Создание заявки")
    @Test(description = "Проверка создания заявки без обязательных параметров",
            dataProvider = "testData4checkCreateOrderWithoutRequiredParams",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkCreateOrderWithoutRequiredParams(TestData testData, OrderStep step) {
        step.sendRequestCreateAnOrder(testData);
        step.checkResponseOnBadRequest();
    }

    @Story("Создание заявки")
    @Test(description = "Проверка создания заявки | Некорректный запрос",
            dataProvider = "testData4checkCreateOrderWithIncorrectParams",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkCreateOrderWithIncorrectParams(TestData testData, OrderStep step) {
        step.sendRequestCreateAnOrder(testData);
        step.checkResponseOnBadRequest();
    }
}

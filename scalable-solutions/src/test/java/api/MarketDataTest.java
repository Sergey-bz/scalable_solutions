package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import model.test_data.TestData;
import org.testng.annotations.Test;
import step.OrderStep;

@Epic("Биржевой стакан")
@Test(groups = {"api"})
public class MarketDataTest {

    @Story("Текущее состояние рынка")
    @Test(description = "Проверка получения текущего состояния рынка",
            dataProvider = "testData4checkGetSnapshotOfMarketData",
            dataProviderClass = DataProviderForOrderBook.class)
    public void checkGetOrderNotExist(TestData testData, OrderStep step) {
        step.sendRequestGetSnapshotOfMarketdata();
        step.checkResponseOnSuccess();
    }
}

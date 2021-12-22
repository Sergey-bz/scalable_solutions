package api;

import model.test_data.TestData;
import org.testng.annotations.DataProvider;
import step.OrderStep;

import java.util.Iterator;

import static utils.DataProviderUtil.getTestDataCollection;

public class DataProviderForOrderBook {

    private static final String DATA_FOR_ORDER_BOOK_TEST = "src/test/resources/data/api/TestDataOrderBook.json";

    @DataProvider(parallel = true)
    public static Iterator<Object[]> testData4checkCreateOrder() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider(parallel = true)
    public static Iterator<Object[]> testData4checkCreateOrderWithoutOptionalParams() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider(parallel = true)
    public static Iterator<Object[]> testData4checkCreateOrderWithoutRequiredParams() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider(parallel = true)
    public static Iterator<Object[]> testData4checkCreateOrderWithIncorrectParams() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider(parallel = true)
    public static Iterator<Object[]> testData4checkGetOrderById() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider
    public static Iterator<Object[]> testData4checkGetOrderWithIncorrectId() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider
    public static Iterator<Object[]> testData4checkGetOrderNotExist() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider
    public static Iterator<Object[]> testData4checkCleanOrders() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider
    public static Iterator<Object[]> testData4checkGetSnapshotOfMarketData() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider
    public static Iterator<Object[]> testData4checkDeleteOrderById() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }

    @DataProvider
    public static Iterator<Object[]> testData4checkDeleteOrderWithoutId() {
        return getTestDataCollection(DATA_FOR_ORDER_BOOK_TEST, TestData.class, OrderStep.class);
    }
}

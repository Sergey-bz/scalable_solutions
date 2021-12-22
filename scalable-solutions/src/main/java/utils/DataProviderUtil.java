package utils;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import model.test_data.TestData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DataProviderUtil {

    private final static Logger LOGGER = LogManager.getLogger(DataProviderUtil.class.getSimpleName());

    public static <T extends TestData>Iterator<Object[]> getTestDataCollection(String fileName, Class<T> testDataClazz, Class stepClazz) {
        String testDataMember = Thread.currentThread().getStackTrace()[2].getMethodName();
        List<T> dataList = readTestDataListFromJson(fileName, testDataClazz, testDataMember);
        Collection<Object[]> collectionForDataProvider = new ArrayList<>();
        for (T testData : dataList) {
            try {
                collectionForDataProvider.add(new Object[]{testData, stepClazz.newInstance()});
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return collectionForDataProvider.iterator();
    }

    private static <T extends TestData> List<T> readTestDataListFromJson(String fileName, Class<T> testDataClazz, String testDataMember) {
        return getElementsFromJson(testDataMember, fileName, testDataClazz);
    }

    private static <T extends TestData> List<T> getElementsFromJson(String dataMemberName, String fileName, Class<T> testDataClazz) {
        List<T> elements = new ArrayList<>();
        String testDataMember;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            testDataMember = objectMapper.readTree(new File(fileName)).path(dataMemberName).toString();
            elements = objectMapper.readValue(testDataMember, objectMapper.getTypeFactory().constructCollectionType(List.class, testDataClazz));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        }
        return elements;
    }
}

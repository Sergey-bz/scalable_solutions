package http;

import io.qameta.allure.Attachment;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;

public class LogFilter implements Filter {

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private Response lastResponse;
    private String lastRequest;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            lastRequest = logRequest(RequestPrinter.print(requestSpec, requestSpec.getMethod(), requestSpec.getURI(),
                    LogDetail.ALL, new HashSet<>(), printStream, true));
            lastResponse = ctx.next(requestSpec, responseSpec);
            logResponse(ResponsePrinter.print(lastResponse, lastResponse, printStream,
                    LogDetail.ALL, true, new HashSet<>()));
            return lastResponse;
        }
    }

    @Attachment(value = "Запрос")
    private String logRequest(String log) {
        return log;
    }

    @Attachment(value = "Ответ")
    private String logResponse(String log) {
        return log;
    }

    public Response getLastResponse() {
        return lastResponse;
    }

    public String getLastRequest() {
        return lastRequest;
    }
}

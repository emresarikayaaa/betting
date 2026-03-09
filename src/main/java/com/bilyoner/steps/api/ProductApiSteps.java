package com.bilyoner.steps.api;

import com.bilyoner.api.ProductApi;
import com.bilyoner.utils.DataStore;
import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductApiSteps {

    private List<Integer> allProductId;
    private final ProductApi productApi = new ProductApi();
    private Response response;

    @Step("GET isteği ile tüm ürünleri listeleme")
    public void getAllProducts() {

        response = productApi.getAllProducts();
        DataStore.set("allProductId", response.jsonPath().getList("products.id"));

    }

    @Step("Toplam ürün sayısını kontrol etme")
    public void checkProductCount() {
        List<Integer> productId = DataStore.get("allProductId");

        assertThat(productId.size())
                .as("Product Id")
                .isGreaterThan(0);
    }
}

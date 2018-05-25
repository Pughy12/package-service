package com.pug.ppm.service;

import com.pug.ppm.model.Product;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${product.api.username}")
    private String apiUsername;
    @Value("${product.api.password}")
    private String apiPassword;

    private final RestTemplate restTemplate;

    private static final String PRODUCT_API_PRODUCTS_ENDPOINT = "https://product-service.herokuapp.com/api/v1/products/";

    @Autowired
    public ProductServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getProducts() {
        final HttpEntity<String> requestEntity = new HttpEntity<>(buildAuthorizationHeader(apiUsername, apiPassword));

        final ResponseEntity<List> products = restTemplate.getForEntity(PRODUCT_API_PRODUCTS_ENDPOINT, List.class);

        return products.getBody();
    }

    private static HttpHeaders buildAuthorizationHeader(String username, String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        return headers;
    }
}

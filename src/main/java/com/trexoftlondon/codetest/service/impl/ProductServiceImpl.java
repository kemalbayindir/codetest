package com.trexoftlondon.codetest.service.impl;

import com.trexoftlondon.codetest.constant.Constants;
import com.trexoftlondon.codetest.constant.PriceLabelFormat;
import com.trexoftlondon.codetest.model.Product;
import com.trexoftlondon.codetest.model.ServiceResult;
import com.trexoftlondon.codetest.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ServiceResult getServiceResult(String categoryId) {
        RestTemplate restTemplate = new RestTemplate();
        String fullUrl = String.format(Constants.productServiceByCategory, categoryId);
        return restTemplate.getForObject(fullUrl, ServiceResult.class);
    }

    @Override
    public List<Product> getProducts(String categoryId, String labelType) {
        PriceLabelFormat labelFormat = null != labelType ? PriceLabelFormat.valueOf(labelType) : PriceLabelFormat.ShowWasNow;
        ServiceResult result = getServiceResult(categoryId);

        List<Product> filteredProducts = result.getProducts()
                                               .stream()
                                               .filter(f -> f.getPrice().getIsThereReduceEvent())
                                               .map(f -> f.arrangePriceLabelFormat(labelFormat))
                                               .collect(Collectors.toList());

        return filteredProducts;
    }
}

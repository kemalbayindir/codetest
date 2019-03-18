package com.trexoftlondon.codetest.service;

import com.trexoftlondon.codetest.model.Product;
import com.trexoftlondon.codetest.model.ServiceResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ServiceResult getServiceResult(String categoryId);

    List<Product> getProducts(String categoryId, String labelType);

}

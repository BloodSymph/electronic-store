package com.company.product.service.client;

import com.company.product.dto.client.category.CategoryClientResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryClientService {

    List<CategoryClientResponse> getAllCategories();

}

package com.company.product.service.admin.implementation;

import com.company.product.dto.admin.discount.DiscountAdminRequest;
import com.company.product.dto.admin.discount.DiscountAdminResponse;
import com.company.product.entity.DescriptionEntity;
import com.company.product.entity.DiscountEntity;
import com.company.product.entity.ProductEntity;
import com.company.product.exception.exceptions.discount.DiscountNotFoundException;
import com.company.product.exception.exceptions.discount.DiscountVersionNotValidException;
import com.company.product.exception.exceptions.product.ProductNotFoundException;
import com.company.product.repository.DiscountRepository;
import com.company.product.repository.ProductRepository;
import com.company.product.service.admin.DiscountAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.product.mapper.admin.DiscountAdminMapper.mapRequestToDiscountEntity;
import static com.company.product.mapper.admin.DiscountAdminMapper.mapToDiscountAdminResponse;
import static com.company.product.util.DiscountCalculationUtility.calculateDiscount;

@Service
@RequiredArgsConstructor
public class DiscountAdminServiceImpl implements DiscountAdminService {

    private final DiscountRepository discountRepository;

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public DiscountAdminResponse createDiscountForProduct(
            DiscountAdminRequest discountAdminRequest) {

        ProductEntity product = productRepository
                .findByUrlIgnoreCase(discountAdminRequest.getProductUrl())
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not product by current url: " + discountAdminRequest.getProductUrl() + " !"
                        )
                );

        DiscountEntity discount;
        discount = mapRequestToDiscountEntity(discountAdminRequest);
        discount.setProduct(product);
        discountRepository.save(discount);

        product.setPrice(calculateDiscount(product.getPrice(), discount.getDiscount()));

        return mapToDiscountAdminResponse(discount);
    }

    @Override
    @Transactional
    public DiscountAdminResponse updateDiscountForProduct(
            DiscountAdminRequest discountAdminRequest, String productUrl) {

        ProductEntity product = productRepository
                .findByUrlIgnoreCase(discountAdminRequest.getProductUrl())
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not product by current url: " + discountAdminRequest.getProductUrl() + " !"
                        )
                );

        DiscountEntity discount = discountRepository
                .findByProduct_Url(productUrl)
                .orElseThrow(
                        () -> new DiscountNotFoundException(
                                "Can not discount by product url: " + productUrl + " !"
                        )
                );

        if (!discount.getVersion().equals(discountAdminRequest.getVersion())) {
            throw new DiscountVersionNotValidException(
                    "Description Entity version: " + discountAdminRequest.getVersion() + " not valid!"
            );
        }


        discount = mapRequestToDiscountEntity(discountAdminRequest);
        discountRepository.save(discount);
        product.setPrice(calculateDiscount(product.getPrice(), discount.getDiscount()));

        return mapToDiscountAdminResponse(discount);
    }

    @Override
    @Transactional
    public void deleteCurrentDiscount(
            String productUrl, Long discountVersion) {
        if (!discountRepository.existsByProduct_Url(productUrl)) {
            throw new DiscountNotFoundException(
                    "Can not discount by product url: " + productUrl + " !"
            );
        }
        if (!discountRepository.existsByVersion(discountVersion)) {
            throw new DiscountVersionNotValidException(
                    "Discount Entity version: " + discountVersion + " not valid!"
            );
        }
        discountRepository.deleteByProduct_Url(productUrl);
    }

}

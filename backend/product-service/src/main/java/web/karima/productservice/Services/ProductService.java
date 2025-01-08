package web.karima.productservice.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.karima.productservice.Entities.Product;
import web.karima.productservice.Exceptions.ProductPurchaseException;
import web.karima.productservice.Mappers.ProductMapper;
import web.karima.productservice.Records.ProductPurchaseRequest;
import web.karima.productservice.Records.ProductPurchaseResponse;
import web.karima.productservice.Records.ProductRequest;
import web.karima.productservice.Records.ProductResponse;
import web.karima.productservice.Repositories.CategoryRepository;
import web.karima.productservice.Repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest request){
        var product = productRepository.save(productMapper.toProduct(request));
        return product.getId();
    }

    public void updateProduct(ProductRequest request){
        var product = productRepository.findById(request.id())
                .orElseThrow(
                        ()-> new EntityNotFoundException("No product found with ID: %s"+request.id())
                );
        var category = categoryRepository.findById(request.categoryId())
                        .orElseThrow(
                                ()-> new EntityNotFoundException("No category found with ID: %s"+ request.categoryId())
                        );

        product.setName(request.name());
        product.setDescription(request.description());
        product.setAvailableQuantity(request.availableQuantity());
        product.setPrice(request.price());
        product.setCategory(category);

        productRepository.save(product);
    }

    public ProductResponse findProductById(Integer id){
        var product = productRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("No product found with ID: %s"+ id)
        );
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> findAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {

            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();

            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);

            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

}

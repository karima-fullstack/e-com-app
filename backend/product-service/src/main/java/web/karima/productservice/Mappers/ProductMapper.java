package web.karima.productservice.Mappers;

import org.springframework.stereotype.Service;
import web.karima.productservice.Entities.Category;
import web.karima.productservice.Entities.Product;
import web.karima.productservice.Records.ProductPurchaseRequest;
import web.karima.productservice.Records.ProductPurchaseResponse;
import web.karima.productservice.Records.ProductRequest;
import web.karima.productservice.Records.ProductResponse;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request){
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(Category.builder()
                        .id(request.id())
                        .build())
                .build();
    }
    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }
    public ProductPurchaseResponse toProductPurchaseResponse(Product product, Double quantity){
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailableQuantity()
        );
    }

}

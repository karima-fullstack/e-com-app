package web.karima.productservice.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.karima.productservice.Records.ProductPurchaseRequest;
import web.karima.productservice.Records.ProductPurchaseResponse;
import web.karima.productservice.Records.ProductRequest;
import web.karima.productservice.Records.ProductResponse;
import web.karima.productservice.Services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }
    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductRequest request){
        productService.updateProduct(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(
            @PathVariable("id") Integer productId
    ) {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }
}
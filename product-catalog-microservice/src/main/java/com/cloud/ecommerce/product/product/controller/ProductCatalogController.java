package com.cloud.ecommerce.product.product.controller;

import com.cloud.ecommerce.product.product.model.Product;
import com.cloud.ecommerce.product.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductCatalogController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCatalogController.class);

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/products/recommendations", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> productRecommendations() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public void addProduct(@RequestBody Product product) throws Exception {
        productRepository.save(product);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public void updateProduct(@RequestBody Product product) throws Exception {
        productRepository.save(product);
    }

    @RequestMapping(value = "/products/{name}", method = RequestMethod.DELETE)
    public void removeProduct(@PathVariable("name") String name) throws Exception {
        LOG.info("Product with %s requested for deletion", name);
        final Product product = productRepository.findByName(name);
        productRepository.delete(product);
    }

    @GetMapping(value = "/productsBelow")
    public List<Product> getCheaperProducts(@RequestParam("price") float price) {
        return productRepository.findByPriceLessThan(price);
    }

    @GetMapping(value = "/productsAbove")
    public List<Product> getCostlierProducts(@RequestParam("price") float price) {
        return productRepository.findByPriceGreaterThan(price);
    }

    @ExceptionHandler(Exception.class)
    void handleExceptions(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error. We will be addressing this issue soon.");
    }
}

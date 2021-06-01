package com.example.exceptionhandlingexample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();

    static {
        Product test = new Product();
        test.setId("1");
        test.setName("Test");
        productRepo.put(test.getId(), test);

        Product test1 = new Product();
        test1.setId("2");
        test1.setName("Test1");
        productRepo.put(test1.getId(), test1);
    }

    @RequestMapping(value = "/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        if (!productRepo.containsKey(id)) throw new ProductNotfoundException();
        productRepo.remove(id);
//        productRepo.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is Updated Succesfully", HttpStatus.OK);
    }
}

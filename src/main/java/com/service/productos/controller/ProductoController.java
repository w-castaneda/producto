package com.service.productos.controller;

import com.service.productos.entity.Producto;
import com.service.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducts() {
        return ResponseEntity.ok(productoService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable String id) {
        Optional<Producto> producto = productoService.getProductById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> createProduct(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.saveProduct(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable String id, @RequestBody Producto producto) {
        if (!productoService.getProductById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        return ResponseEntity.ok(productoService.saveProduct(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        if (!productoService.getProductById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productoService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
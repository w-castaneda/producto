package com.service.productos.controller;

import com.service.productos.dto.ProductoDTO;
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
    public ResponseEntity<List<ProductoDTO>> getAllProducts() {
        return ResponseEntity.ok(productoService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductById(@PathVariable String id) {
        Optional<ProductoDTO> productoDTO = productoService.getProductById(id);
        return productoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProduct(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoGuardado = productoService.saveProduct(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProduct(@PathVariable String id, @RequestBody ProductoDTO productoDTO) {
        if (!productoService.getProductById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productoDTO.setId(id);

        ProductoDTO productoActualizado = productoService.saveProduct(productoDTO);
        return ResponseEntity.ok(productoActualizado);
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
package com.service.productos.service;

import com.service.productos.entity.Producto;
import com.service.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductById(String id) {
        return productoRepository.findById(id);
    }

    public Producto saveProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProduct(String id) {
        productoRepository.deleteById(id);
    }
}
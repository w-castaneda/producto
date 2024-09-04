package com.service.productos.service;

import com.service.productos.dto.ProductoDTO;
import com.service.productos.dto.ProductoMapper;
import com.service.productos.entity.Producto;
import com.service.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> getProductoById(String id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDTO);
    }

    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        Producto nuevoProducto = productoRepository.save(producto);
        return productoMapper.toDTO(nuevoProducto);
    }

    public void deleteProducto(String id) {
        productoRepository.deleteById(id);
    }
}
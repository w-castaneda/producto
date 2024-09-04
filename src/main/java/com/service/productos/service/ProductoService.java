package com.service.productos.service;

import com.service.productos.dto.ProductoDTO;
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

    public List<ProductoDTO> getAllProducts() {
        return productoRepository.findAll().stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> getProductById(String id) {
        return productoRepository.findById(id)
                .map(this::convertirAProductoDTO);
    }

    public ProductoDTO saveProduct(ProductoDTO productoDTO) {
        Producto producto = convertirAProducto(productoDTO);
        Producto productoGuardado = productoRepository.save(producto);
        return convertirAProductoDTO(productoGuardado);
    }

    public void deleteProduct(String id) {
        productoRepository.deleteById(id);
    }

    private ProductoDTO convertirAProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        return productoDTO;
    }

    private Producto convertirAProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setDescripcion(productoDTO.getDescripcion());
        return producto;
    }
}
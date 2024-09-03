package com.service.productos.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="productos")
@Data
public class Producto {
    @Id
    private String id; // MongoDB utiliza ObjectId, por lo que el ID es de tipo String
    private String name;
    private String description;
    private Double price;

    public void setId(String id) {
        this.id = id;
    }
}

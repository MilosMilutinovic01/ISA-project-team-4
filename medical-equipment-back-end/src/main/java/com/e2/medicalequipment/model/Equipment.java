package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.EquipmentDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "equipment")
public class Equipment {
    @Id
    @SequenceGenerator(name = "equipmentSeq", sequenceName = "equipmentSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipmentSeq")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private EquipmentType type;

    @Column(name = "price")
    private Double price;

    public Equipment() {

    }

    public Equipment(Long id, String name, String description, EquipmentType type, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public Equipment(EquipmentDTO equipmentDTO) {
        this.id = equipmentDTO.id;
        this.name = equipmentDTO.name;
        this.description = equipmentDTO.description;
        this.type = equipmentDTO.type;
        this.price = equipmentDTO.price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

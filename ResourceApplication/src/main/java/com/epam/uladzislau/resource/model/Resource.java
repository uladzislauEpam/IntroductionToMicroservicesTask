package com.epam.uladzislau.resource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
public class Resource {

    public Resource() {}

    public Resource(String name) {
        this.name = name;
    }

    public Resource(String name, int storageId) {
        this.name = name;
        this.storageId = (long) storageId;
    }

    public Resource(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "storage_id")
    private Long storageId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId){this.storageId = storageId;}


}

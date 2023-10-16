package com.epam.uladzislau.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "storage_type")
    String storageType;
    @Column(name = "bucket")
    String bucket;
    @Column(name = "path")
    String path;

    public Storage() {}

    public Storage(String storageType, String bucket, String path) {
        this.storageType = storageType;
        this.bucket = bucket;
        this.path = path;
    }

    public Storage(Long id, String storageType, String bucket, String path) {
        this.id = id;
        this.storageType = storageType;
        this.bucket = bucket;
        this.path = path;
    }

    public Long getId(){
        return id;
    }
    public String getStorageType(){
        return storageType;
    }
    public String getBucket(){
        return bucket;
    }
    public String getPath(){
        return path;
    }

    public String toString() {
        return storageType;
    }
}

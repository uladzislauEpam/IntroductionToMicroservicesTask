package com.epam.uladzislau.resource.dto;

public class StorageDto {

    public StorageDto(String storageType, String bucket, String path) {
        this.storageType = storageType;
        this.bucket = bucket;
        this.path = path;
    }

    public String storageType;
    public String bucket;
    public String path;
}

package com.epam.uladzislau.resource;

import java.util.List;

import com.epam.uladzislau.resource.repository.ResourceRepository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.epam.uladzislau.resource"})
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

    @Value("${resource.file.path}")
    private String filePath;
    @Value("${resource.file.name}")
    private String fileName;
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private AmazonS3 amazonS3;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
        createS3Bucket(bucketName);
        System.out.println("===========================================" + listBuckets());
    }

    public void createS3Bucket(String bucketName) {
        if(amazonS3.doesBucketExistV2(bucketName)) {
            System.out.println("Bucket name already in use. Try another name.");
            return;
        }
        amazonS3.createBucket(bucketName);
        System.out.println("CREATED BUCKET " + bucketName);
    }

    public void deleteBucket(String bucketName){
        try {
            amazonS3.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            System.out.println(e.getErrorMessage());
            return;
        }
    }

    public List<Bucket> listBuckets(){
        return amazonS3.listBuckets();
    }
}

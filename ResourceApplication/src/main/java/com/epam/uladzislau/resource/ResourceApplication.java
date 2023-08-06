package com.epam.uladzislau.resource;

import java.util.List;

import com.epam.uladzislau.resource.model.Resource;
import com.epam.uladzislau.resource.repository.ResourceRepository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@EnableJpaRepositories
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
    private AmazonS3 amazonS3Client;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
//        createS3Bucket(bucketName);

        MultipartFile multipartFile = new MockMultipartFile(fileName, getClass().getResourceAsStream(filePath));
        var resource = resourceRepository.findById(1L)
            .orElse(new Resource(1L, null, 1L));
        resource.setAudioBytes(multipartFile.getBytes());
        resourceRepository.save(resource);
//        System.out.println("===========================================" + listObjects(bucketName));
    }

    public void createS3Bucket(String bucketName) {
        if(amazonS3Client.doesBucketExist(bucketName)) {
            System.out.println("Bucket name already in use. Try another name.");
            return;
        }
        amazonS3Client.createBucket(bucketName);
        System.out.println("CREATED BUCKET " + bucketName);
    }

    public List<Bucket> listBuckets(){
        return amazonS3Client.listBuckets();
    }

    public void deleteBucket(String bucketName){
        try {
            amazonS3Client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            System.out.println(e.getErrorMessage());
            return;
        }
    }

    public List<S3ObjectSummary> listObjects(String bucketName){
        ObjectListing objectListing = amazonS3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }
}

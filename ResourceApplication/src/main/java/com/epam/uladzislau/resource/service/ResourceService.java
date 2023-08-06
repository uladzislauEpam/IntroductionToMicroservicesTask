package com.epam.uladzislau.resource.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.epam.uladzislau.resource.dto.SongDto;
import com.epam.uladzislau.resource.model.Resource;
import com.epam.uladzislau.resource.repository.ResourceRepository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;

@Service
public class ResourceService {

    @Value("${song.app.url}")
    private static String URL;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private AmazonS3 amazonS3Client;

    public Page<Resource> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return resourceRepository.findAll(pageRequest);
    }

    public Resource get(Long id) {
        return resourceRepository.getOne(id);
    }

    public List<Long> delete(List<Long> ids) {
        List<Long> deletedIds = new ArrayList<>();
        for (var id : ids) {
            var resource = resourceRepository.findById(id);
            if(resource.isPresent()) {
                resourceRepository.deleteById(id);
            }
        }
        return deletedIds;
    }

    public Long upload(MultipartFile file) throws Exception {
        try {
            InputStream stream = file.getInputStream();

            Parser pparser = new AutoDetectParser();
            ContentHandler contentHandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            pparser.parse(stream, contentHandler, metadata, new ParseContext());
            validateUploadFile(metadata);
            stream.close();

            Resource resource = new Resource(file.getBytes());

            File convFile = new File("/" + file.getOriginalFilename());
            file.transferTo(convFile);

            putObject(convFile);
            resourceRepository.save(resource);
            return resource.getId();
        } catch (Exception e) {
            System.out.println("Cannot process file: " + e.getMessage());
            throw new Exception("Cannot process file: " + e.getMessage());
        }
    }

    private void validateUploadFile(Metadata metadata){
        String format = metadata.get("Content-Type");
        if (format == null || format.isBlank() || !format.toLowerCase().contains("audio/mpeg")) {
            throw new RuntimeException("Invalid file provided");
        }
    }

    public List<S3ObjectSummary> listObjects(String bucketName){
        ObjectListing objectListing = amazonS3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }

    public void putObject(File file) throws IOException {
        try {
            var putObjectRequest =
                new PutObjectRequest(bucketName, file.getName(), file).withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(putObjectRequest);
        } catch (Exception e){
            System.out.println("Some error has ocurred.");
        }
    }
}

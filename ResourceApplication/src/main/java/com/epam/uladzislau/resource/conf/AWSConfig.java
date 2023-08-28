package com.epam.uladzislau.resource.conf;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Bean
    public AmazonS3 amazonS3() {

        String sqsEndpoint = "http://aws:4566";
//        String sqsEndpoint = "http://localhost:4566";

        AmazonS3 s3 = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsEndpoint, "us-east-1"))
            .withPathStyleAccessEnabled(true)
            .build();

        return s3;

    }

}
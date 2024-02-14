package com.ayiko.backend.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

@Component
public class AWSImageUploader {

    public static void main(String[] args) {
        AWSCredentials credentials = new BasicAWSCredentials(
                "access_key", "aws_secret_key");

        AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
        String base64Data = "";
        byte[] bI = java.util.Base64.getDecoder().decode(base64Data);
        InputStream fis = new ByteArrayInputStream(bI);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bI.length);
        metadata.setContentType("image/png");
        metadata.setCacheControl("public, max-age=31536000");
        PutObjectResult result = amazonS3Client.putObject("test", "test", fis, metadata);
        amazonS3Client.setObjectAcl("test", "test", CannedAccessControlList.PublicRead);
    }
}

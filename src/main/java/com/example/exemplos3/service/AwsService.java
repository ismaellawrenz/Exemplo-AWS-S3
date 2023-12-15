package com.example.exemplos3.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AwsService {

    private final String bucket = "testeismael";
    private S3Client s3;

    public AwsService() {
        s3 = S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(getCredenciais())
                .build();
    }

    private AwsCredentialsProvider getCredenciais() {

        AwsCredentials credentials = AwsBasicCredentials.create(System.getenv("s3accesskey"), System.getenv("s3secretkey"));
        return StaticCredentialsProvider.create(credentials);

    }

    public String enviar(MultipartFile file) throws IOException {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(file.getOriginalFilename())
                .build();

        PutObjectResponse response = s3.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        return file.getOriginalFilename() + " enviado.";
    }


    public byte[] buscar(String nome) throws IOException,NoSuchKeyException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(nome)
                .build();

        ResponseInputStream<GetObjectResponse> getObjectResponse = null;

          getObjectResponse = s3.getObject(getObjectRequest);


        // Ler os bytes do InputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = getObjectResponse.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        return outputStream.toByteArray();

    }

    public void excluir(String nome) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucket).key(nome).build();
        s3.deleteObject(deleteObjectRequest);
    }

    public List<String> listar(){
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder().bucket(bucket).build();
        var retorno = s3.listObjectsV2(listObjectsV2Request);

        return retorno.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }


}

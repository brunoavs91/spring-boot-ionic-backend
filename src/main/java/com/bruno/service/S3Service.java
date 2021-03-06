package com.bruno.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bruno.service.exception.FileException;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public URI uploadFile(MultipartFile multipartFile) {

		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is;
			byte[] bytes = multipartFile.getBytes();
			is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, fileName, contentType, bytes);
		} catch (IOException e) {
			throw new FileException("Erro de IO " + e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contentType, byte... bytes) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(bytes.length);
			meta.setContentType(contentType);

			LOG.info("Iniciando upload ...");
			s3client.putObject(bucketName, fileName, is, meta);
			LOG.info("Upload finalizado");
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		} catch (AmazonS3Exception ex) {
			throw new AmazonS3Exception(ex.getMessage());
		}

	}
}

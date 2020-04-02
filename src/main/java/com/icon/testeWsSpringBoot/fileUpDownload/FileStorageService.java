package com.icon.testeWsSpringBoot.fileUpDownload;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.icon.testeWsSpringBoot.configs.FileStorageConfig;

@Service
public class FileStorageService {

	private Path finalStorageLocation;

	@Autowired
	public FileStorageService(FileStorageConfig config) {

		this.finalStorageLocation = Paths.get(config.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.finalStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Não foi possivel criar o diretorio: " + this.finalStorageLocation, e);
		}
	}
	
	public String getFileName(MultipartFile file) {
		
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println("filename:"+fileName);

		try {
			
			if(fileName.contains("..")) {
				throw new FileStorageException("Não foi possivel armazenar o arquivo, pois o arquivo possui caracteres invalidos (ex. '..': " + fileName);
			}
			
			Path targetLocation = this.finalStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			System.out.println("target: "+targetLocation);
	
			return fileName;
		} catch (Exception e) {
			throw new FileStorageException("Não foi possivel armazenar o arquivo: " + fileName, e);
		}
	}

}

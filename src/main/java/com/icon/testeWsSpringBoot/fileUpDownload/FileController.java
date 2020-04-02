package com.icon.testeWsSpringBoot.fileUpDownload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/file")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping("/gett")
	public String uploadFile() {


		return "dddddd";
	}

	@PostMapping("/uploadFile")
	public FileUploadDTO uploadFile(@RequestParam("file") MultipartFile file) {

		String fileName = fileStorageService.getFileName(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
				.path(fileName).toUriString();

		return new FileUploadDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());

	}

}

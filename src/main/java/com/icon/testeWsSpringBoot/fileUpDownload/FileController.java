package com.icon.testeWsSpringBoot.fileUpDownload;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping("/gett")
	public String uploadFile() {

		return "dddddd";
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

		Resource r = fileStorageService.loadFileAsResource(fileName);

		String contentType = null;

		try {
			contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("Não consegui determino o tipo do arquivo");
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename:\"" + r.getFilename() + "\"").body(r);
	}

	@PostMapping("/uploadFile")
	public FileUploadDTO uploadFile(@RequestParam("file") MultipartFile file) {

		String fileName = fileStorageService.salvaArquivo(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
				.path(fileName).toUriString();

		return new FileUploadDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());

	}

	@PostMapping("/uploadMultipleFiles")
	public List<FileUploadDTO> uploadFile(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

}

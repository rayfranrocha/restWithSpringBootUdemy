package com.icon.testeWsSpringBoot.fileUpDownload;

import java.io.Serializable;

public class FileUploadDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private Long size;

	public FileUploadDTO(String fileName, String fileDownloadUri, String fileType, Long size) {
		super();
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}

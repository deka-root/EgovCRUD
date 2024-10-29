package com.lyr.main.service;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	
	private int fileNo;
    private String fileName;
    private String filePath;
    public String uploadDate;
    private int boardNo;
    private MultipartFile uploadFile;
    
    
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "FileVO [fileNo=" + fileNo + ", fileName=" + fileName + ", filePath=" + filePath + ", uploadDate="
				+ uploadDate + ", boardNo=" + boardNo + ", uploadFile=" + uploadFile + "]";
	}
}

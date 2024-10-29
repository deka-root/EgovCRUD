package com.lyr.main.service;

import java.time.LocalDateTime;

public class BoardVO {
	
	private Integer boardNo;
	private String title;
	private String pass;
	public String author;
	public String content;
	public int viewCount;
	public String  createdDate;
	public String  updatedDate;
	
	private int start;    // 페이징 시작점
    private int pageSize; // 한 페이지에 보여줄 게시물 수
    
    /* ListBoard 검색 */
    private String searchBox;
    private String searchText;
    
    /* 파일 업로드 */
    private FileVO file;
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	public Integer getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/* ListBoard 검색 */
	public String getSearchBox() {
		return searchBox;
	}
	public void setSearchBox(String searchBox) {
		this.searchBox = searchBox;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	
	public FileVO getFile() {
		return file;
	}
	public void setFile(FileVO file) {
		this.file = file;
	}
	
	
	@Override
	public String toString() {
	    return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", pass=" + pass + ", author=" + author
	            + ", content=" + content + ", viewCount=" + viewCount + ", createdDate=" + createdDate
	            + ", updatedDate=" + updatedDate + ", file=" + (file != null ? file.toString() : "null") + "]";
	}
	
}

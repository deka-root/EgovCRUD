package com.lyr.main.service;

import java.util.List;

public interface BoardService {
	
	/* 게시글 등록 */
	public String insertBoard(BoardVO vo) throws Exception;
	
	/* 게시글 파일 등록 */
	public void insertFile(FileVO fileVo);
	
	/* 게시글 조회 */
	public List<?> selectBoardList(BoardVO vo) throws Exception;

	/* 게시글 total */
	public int selectBoardTotal(BoardVO vo) throws Exception;
	
	/* 게시글 상세 */
	public BoardVO selectBoardDetail(int boardNo) throws Exception;
	
	/* 게시글 조회수 */
	public int updateBoardCount(int boardNo) throws Exception;

	/* 게시글 수정 */
	public int updateBoard(BoardVO vo) throws Exception;
	
	/* 파일 수정 */
	public int updateFile(FileVO fileVo) throws Exception;
	
	/* 게시글 수정 전 암호 확인 */
	public int selectBoardPass(BoardVO vo) throws Exception;
	
	/* 게시글 삭제 */
	public int deleteBoard(BoardVO vo) throws Exception;

	public FileVO selectFile(int boardNo) throws Exception;
	
	/* 파일 삭제 */
	public int deleteFile(int fileNo) throws Exception;

	
	
}

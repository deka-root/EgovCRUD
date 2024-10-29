package com.lyr.main.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractDAO;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lyr.main.service.BoardVO;
import com.lyr.main.service.FileVO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO {

    // 게시글 삽입 메소드
    public String insertBoard(BoardVO vo) {
        // 게시글 삽입
        insert("boardDAO.insertBoard", vo);
        
        // 자동 증가 번호 반환
        Integer lastInsertedId = (Integer) select("boardDAO.getLastInsertedBoardNo"); // ID 반환
        System.out.println("마지막으로 삽입된 ID: " + lastInsertedId);
        return String.valueOf(lastInsertedId); // int를 String으로 변환하여 반환
    }
	
    public void insertFile(FileVO file) {
        // 파일 정보를 출력
        System.out.println("삽입할 파일 정보: " + file.toString());
        
        // 파일 삽입
        insert("boardDAO.insertFile", file);
        System.out.println("파일 삽입 완료");

        // 자동 증가 번호 반환
        Integer lastInsertedId = (Integer) select("boardDAO.getLastInsertedFileNo"); // ID 반환
        System.out.println("마지막 삽입된 파일 번호: " + lastInsertedId); // 파일 번호 출력

        file.setFileNo(lastInsertedId); // 파일 번호 설정
    }

	public List<?> selectBoardList(BoardVO vo) {
		return list("boardDAO.selectBoardList", vo);
	}

	public int selectBoardTotal(BoardVO vo) {
		return (int) select("boardDAO.selectBoardTotal", vo);
	}

	public BoardVO selectBoardDetail(int boardNo) {
		return (BoardVO) select("boardDAO.selectBoardDetail", boardNo);
	}

	public int updateBoardCount(int boardNo) {
		return update("boardDAO.updateBoardCount", boardNo);
	}

	public int updateBoard(BoardVO vo) {
		return update("boardDAO.updateBoard", vo);
	}
	
	public int updateFile(FileVO fileVo) {
	    // 기존 파일 정보를 가져옵니다.
	    FileVO existingFileVo = selectFile(fileVo.getBoardNo());

	    // 기존 파일이 존재하는 경우
	    if (existingFileVo != null) {
	        // 파일 정보를 업데이트합니다. (기존 파일 번호를 사용)
	        fileVo.setFileNo(existingFileVo.getFileNo()); // 기존 파일 번호를 설정
	        return update("boardDAO.updateFile", fileVo);
	    } else {
	        System.out.println("기존 파일 정보가 없습니다. 파일 추가가 필요합니다.");
	        // 필요한 경우 파일 추가 로직을 여기에서 처리할 수 있습니다.
	        return 0; // 추가적인 처리가 필요 없다면 0 반환
	    }
	}

	public int selectBoardPass(BoardVO vo) {
		return (int) select("boardDAO.selectBoardPass", vo);
	}

	public int deleteBoard(BoardVO vo) {
		return delete("boardDAO.deleteBoard", vo);
	}

	public FileVO selectFile(int boardNo) {
		return (FileVO) select("boardDAO.selectFile", boardNo);
	}

	public Integer getLastInsertedFileNo() {
		return (Integer) select("boardDAO.getLastInsertedFileNo");
	}

	public int deleteFile(int fileNo) {
	    // 파일 정보 조회
	    FileVO fileVo = selectFile(fileNo);
	    if (fileVo != null) {
	        // 파일 경로로 Path 객체 생성
	        Path filePath = Paths.get(fileVo.getFilePath());
	        try {
	            // 파일 삭제
	            Files.delete(filePath);
	            // DB에서 파일 정보 삭제
	            delete("boardDAO.deleteFile", fileNo); // iBATIS 쿼리 호출
	            return 1; // 파일 삭제 성공
	        } catch (IOException e) {
	            System.out.println("파일 삭제 실패: " + e.getMessage());
	        }
	    }
	    return 0; // 파일 정보가 없거나 삭제 실패
	}

	

	

	

	
}

package com.lyr.main.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyr.main.service.BoardService;
import com.lyr.main.service.BoardVO;
import com.lyr.main.service.FileVO;

// 컨트롤러 연결 설정
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource(name="boardDAO")
	public BoardDAO boardDAO;

	/* 게시글 등록 */
	@Override
	@Transactional  // 트랜잭션 관리 추가
	public String insertBoard(BoardVO vo) throws SQLException {
	    // 게시글 삽입
	    String boardNo = boardDAO.insertBoard(vo); // 게시글 삽입 후 boardNo 반환
	    
	    // 파일이 null이 아닐 경우에만 파일을 삽입
	    FileVO file = vo.getFile();
	    System.out.println("null이니?" + vo.getFile());
	    if (file != null) {
	        file.setBoardNo(Integer.parseInt(boardNo)); // boardNo 설정
	        insertFile(file); // 파일 삽입
	        System.out.println("null이니????????" + file);
	    } else {
	        System.out.println("업로드할 파일이 없습니다."); // 파일이 null인 경우
	    }
	    
	    return boardNo; // 게시글 번호 반환
	}

    @Override
    public void insertFile(FileVO fileVo) {
    	System.out.println("================" + fileVo);
        if (fileVo == null) {
            throw new IllegalArgumentException("fileVo cannot be null");
        }
        boardDAO.insertFile(fileVo);
    }

	/* 게시글 조회 */
	@Override
	public List<?> selectBoardList(BoardVO vo) throws Exception {
		return boardDAO.selectBoardList(vo);
	}

	@Override
	public int selectBoardTotal(BoardVO vo) throws Exception {
		return boardDAO.selectBoardTotal(vo);
	}

	@Override
	public BoardVO selectBoardDetail(int boardNo) throws Exception {
	    // 게시글 상세 조회
	    BoardVO board = boardDAO.selectBoardDetail(boardNo);
	    
	    // 게시글이 존재하지 않는 경우 예외 처리
	    if (board == null) {
	        throw new Exception("해당 게시글이 존재하지 않습니다. 게시글 번호: " + boardNo);
	    }
	    
	    System.out.println("게시글 정보 확인: " + board.toString());
	    
	    // 파일 정보 조회
	    FileVO file = boardDAO.selectFile(boardNo);
	    if (file == null) {
	        System.out.println("해당 게시글에 파일이 없습니다. 게시글 번호: " + boardNo);
	    } else {
	        System.out.println("조회된 파일 정보: " + file.toString());
	    }

	    // 파일 정보를 게시글에 설정
	    board.setFile(file);

	    return board;
	}


	@Override
	public int updateBoardCount(int boardNo) throws Exception {
		return boardDAO.updateBoardCount(boardNo);
	}

	@Override
	@Transactional // 트랜잭션 관리 추가
	public int updateBoard(BoardVO vo) throws Exception {
	    // 게시글 업데이트
	    int result = boardDAO.updateBoard(vo);
	    System.out.println("게시글 업데이트 결과: " + result);

	    FileVO fileVo = vo.getFile();
	    System.out.println("fileVo Impl : " + fileVo);

	    if (fileVo != null) {
	        fileVo.setBoardNo(vo.getBoardNo());

	        // 기존 파일 정보를 가져옵니다.
	        FileVO existingFileVo = boardDAO.selectFile(vo.getBoardNo());
	        if (existingFileVo != null) {
	            // 기존 파일 정보를 업데이트합니다.
	            fileVo.setFileNo(existingFileVo.getFileNo()); // 기존 파일 번호를 설정
	            System.out.println("업데이트할 파일 정보: " + fileVo); // 로그로 파일 정보 확인
	            boardDAO.updateFile(fileVo);  // 파일 업데이트
	            System.out.println("파일 정보 업데이트 완료: " + fileVo.toString());
	        } else {
	            // 기존 파일이 없는 경우 새로운 파일 등록
	            boardDAO.insertFile(fileVo);
	            System.out.println("새 파일 정보 삽입 완료: " + fileVo.toString());
	        }
	    } else {
	        System.out.println("파일 정보가 없습니다.");
	    }

	    return result;
	}

	
	@Override
	public int updateFile(FileVO fileVo) throws Exception {
		return boardDAO.updateFile(fileVo);
	}

	@Override
	public int selectBoardPass(BoardVO vo) throws Exception {
		return boardDAO.selectBoardPass(vo);
	}

	@Override
	public int deleteBoard(BoardVO vo) throws Exception {
		return boardDAO.deleteBoard(vo);
	}

	@Override
	public FileVO selectFile(int boardNo) throws Exception {
	    if (boardNo <= 0) {
	        throw new IllegalArgumentException("유효하지 않은 게시글 번호입니다: " + boardNo);
	    }

	    System.out.println("파일 조회 요청. 게시글 번호: " + boardNo);

	    FileVO file = boardDAO.selectFile(boardNo);

	    if (file == null) {
	        System.out.println("파일이 존재하지 않습니다. 게시글 번호: " + boardNo);
	    } else {
	        System.out.println("조회된 파일 정보: " + file.toString());
	    }

	    return file;
	}

	@Override
	public int deleteFile(int fileNo) throws Exception {
		return boardDAO.deleteFile(fileNo);
	}

	
	

	



}

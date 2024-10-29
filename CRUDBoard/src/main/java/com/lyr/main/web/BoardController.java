package com.lyr.main.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lyr.main.service.BoardService;
import com.lyr.main.service.BoardVO;
import com.lyr.main.service.FileVO;


@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite() {
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/boardWriteSave.do", method = RequestMethod.POST)
	@ResponseBody
	public String insertBoard(BoardVO vo) throws Exception {
	    System.out.println("내용: " + vo.toString());

	    // 파일 업로드 처리
	    MultipartFile uploadFile = vo.getFile() != null ? vo.getFile().getUploadFile() : null;

	    if (uploadFile != null && !uploadFile.isEmpty()) {
	        // 디렉토리 확인 및 생성
	        File uploadDir = new File("D:\\upload");
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }

	        // 파일 정보를 여기에 선언
	        String originalFileName = uploadFile.getOriginalFilename();
	        String ext = FilenameUtils.getExtension(originalFileName);
	        UUID uuid = UUID.randomUUID();
	        String fileName = uuid + "." + ext;

	        try {
	            uploadFile.transferTo(new File(uploadDir, fileName)); // 파일 저장

	            // FileVO 객체 생성
	            FileVO fileVo = new FileVO();
	            fileVo.setFileName(originalFileName);
	            fileVo.setFilePath("D:\\upload\\" + fileName);
	            vo.setFile(fileVo); // BoardVO에 FileVO 설정
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "fail"; // 실패 메시지 반환
	        }
	    } else {
	        System.out.println("업로드된 파일이 없습니다.");
	        vo.setFile(null); // 혹은 vo에 빈 FileVO 설정
	    }

	    // 데이터베이스에 삽입
	    try {
	        // 게시글 삽입 (여기서 파일도 삽입됨)
	        String result = boardService.insertBoard(vo);
	        if (result != null) {
	            return "ok"; // 성공 메시지 반환
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "fail"; // 게시글 삽입 실패 시
	}
	
	
	
	@RequestMapping("/boardList.do")
	public String selectBoardList(BoardVO vo, ModelMap model,
			@RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
		
		int unit = 10;
		
		// 게시글 총 데이터 개수
	    int total = boardService.selectBoardTotal(vo);
	    
	    // 게시글 페이지 수
	    int totalPage = (int) Math.ceil((double) total / unit);
	    
	    // 게시글 행번호 역순 계산
	    int reverseRowNo = total - (page - 1) * unit;
	    
	    if(page > totalPage || page < 1) {
	    	page = 1;
	    }
	    
	    // 현재 페이지 설정
	    vo.setPageSize(unit); // 페이지 크기 설정
	    vo.setStart((page - 1) * unit); // 시작 인덱스 설정  
	    
	    List<?> list = boardService.selectBoardList(vo);
	    System.out.println("List: " + list);

	    model.addAttribute("total", total);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("resultList", list);
	    model.addAttribute("reverseRowNo", reverseRowNo);
		
		return "board/boardList";
	}
	
//	@RequestMapping("/boardDetail.do")
//	public String selectBoardDetail(BoardVO vo, ModelMap model) throws Exception {
//	    
//	    /* 조회수 증가 */
//	    boardService.updateBoardCount(vo.getBoardNo());
//	    
//	    /* 상세보기 */
//	    BoardVO boardVO = boardService.selectBoardDetail(vo.getBoardNo());
//	    System.out.println("Board Number: " + vo.getBoardNo());
//	    System.out.println("BoardVO: " + boardVO); // 객체의 모든 정보를 확인
//	    
//	    // FileVO가 null인 경우, 기본 객체를 생성하여 설정
//     if (boardVO.getFile() == null) {
//	        boardVO.setFile(new FileVO()); // 또는 필요한 기본값을 설정
//	    }
//	    
//	    model.addAttribute("boardVO", boardVO);
//	    
//	    return "board/boardDetail";
//	} 
	
	@RequestMapping("/boardDetail.do")
	public String selectBoardDetail(BoardVO vo, ModelMap model) {
	    try {
	        // 게시글 번호 유효성 검사
	        if (vo.getBoardNo() == null) {
	            throw new IllegalArgumentException("게시글 번호가 유효하지 않습니다.");
	        }

	        // 조회수 증가
	        boardService.updateBoardCount(vo.getBoardNo());

	        // 게시글 상세보기
	        BoardVO boardVO = boardService.selectBoardDetail(vo.getBoardNo());
	        if (boardVO == null) {
	            System.out.println("게시글이 존재하지 않습니다. 게시글 번호: " + vo.getBoardNo());
	            model.addAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
	            return "error"; // 에러 페이지로 이동
	        }

	        // 파일 정보 가져오기
	        FileVO fileVO = boardService.selectFile(vo.getBoardNo());
	        if (fileVO != null) {
	            System.out.println("기존 파일 정보: " + fileVO.toString());
	            boardVO.setFile(fileVO); // 게시글 VO에 파일 정보 추가
	        } else {
	            System.out.println("파일 정보가 없습니다. 게시글 번호: " + vo.getBoardNo());
	        }

	        // 모델에 게시글 및 파일 정보 추가
	        model.addAttribute("boardVO", boardVO);

	        return "board/boardDetail";

	    } catch (Exception e) {
	        // 예외 발생 시 로그 출력
	        System.err.println("게시글 상세 조회 중 오류 발생: " + e.getMessage());
	        e.printStackTrace(); // 스택 트레이스를 출력하여 자세한 오류 정보 확인
	        model.addAttribute("errorMessage", "게시글 상세 조회 중 오류가 발생했습니다.");
	        return "error"; // 에러 페이지로 이동
	    }
	}

	
	@RequestMapping("/boardModify.do")
	public String selectBoardModify(BoardVO vo, ModelMap model) throws Exception {
		
		BoardVO boardVO = boardService.selectBoardDetail(vo.getBoardNo());
		// 파일 정보 조회
	    FileVO fileVO = boardService.selectFile(vo.getBoardNo());

	    // 모델에 게시글 및 파일 정보 추가
	    model.addAttribute("boardVO", boardVO);
	    model.addAttribute("fileVO", fileVO);  // 파일 정보 추가
		
		
		return "board/boardModify";
	}
	
	
	@RequestMapping(value = "/boardModifySave.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateBoard(BoardVO vo) throws Exception {
	    // 업로드된 파일 정보가 있을 때 설정
	    MultipartFile uploadFile = vo.getFile() != null ? vo.getFile().getUploadFile() : null;

	    if (uploadFile != null && !uploadFile.isEmpty()) {
	        FileVO fileVo = new FileVO();
	        String originalFileName = uploadFile.getOriginalFilename(); // 원본 파일 이름
	        String ext = FilenameUtils.getExtension(originalFileName); // 파일 확장자
	        UUID uuid = UUID.randomUUID(); // 고유 ID 생성
	        String fileName = uuid + "." + ext; // 저장할 파일 이름 설정
	        
	        // 파일 저장 경로 설정
	        File uploadDir = new File("D:\\upload");
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs(); // 디렉토리 생성
	        }
	        
	        // 파일 저장
	        File savedFile = new File(uploadDir, fileName);
	        uploadFile.transferTo(savedFile); // 파일 저장
	        
	        fileVo.setFileName(originalFileName); // 원본 파일 이름 설정
	        fileVo.setFilePath(savedFile.getPath()); // 파일 경로 설정
	        vo.setFile(fileVo); // BoardVO에 새 FileVO 설정
	    }

	    // 게시글 업데이트 호출
	    int result = boardService.updateBoard(vo);
	    return String.valueOf(result);
	}

	//passWrite
	@RequestMapping("/passWrite.do")
	public String passWrite(int boardNo, ModelMap model) {
		
		model.addAttribute("boardNo", boardNo);
		
		return "board/passWrite"; 
	}
	
	@RequestMapping("/boardDelete.do")
	@ResponseBody
	public String deleteBoard(BoardVO vo) throws Exception {
	    int result = 0;
	    // 암호 확인
	    int countPass = boardService.selectBoardPass(vo); // countPass = 1;

	    if (countPass == 1) {
	        // 게시글 삭제 전 파일 정보 가져오기
	        FileVO fileVo = boardService.selectFile(vo.getBoardNo());
	        
	        // 게시글 삭제
	        result = boardService.deleteBoard(vo); // 성공: result = 1; 실패: result = 0;

	        // 게시글 삭제가 성공적으로 이루어진 경우
	        if (result == 1 && fileVo != null) {
	            // 파일 삭제 로직 추가
	            int fileDeleteResult = boardService.deleteFile(fileVo.getFileNo());
	            if (fileDeleteResult == 1) {
	                System.out.println("파일 삭제 성공: " + fileVo.getFileName());
	            } else {
	                System.out.println("파일 삭제 실패: " + fileVo.getFileName());
	            }
	        }
	    } else if (countPass == 0) {
	        result = -1;
	    }

	    return result + "";
	}


	
}

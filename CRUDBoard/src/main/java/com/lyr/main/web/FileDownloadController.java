package com.lyr.main.web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lyr.main.service.BoardService;
import com.lyr.main.service.FileVO;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
public class FileDownloadController {
	
	@Autowired
    private BoardService boardService; // 서비스 클래스를 주입합니다.

    @RequestMapping("/download.do")
    public void downloadFile(@RequestParam("fileNo") int fileNo, HttpServletResponse response) throws Exception {
        // 파일 정보 가져오기
        FileVO fileVo = boardService.selectFile(fileNo); // 파일 정보를 가져오는 메소드 호출
        if (fileVo != null) {
            // 파일 경로와 이름 설정
            File file = new File(fileVo.getFilePath());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileVo.getFileName() + "\"");
            response.setContentLength((int) file.length());

            // 파일을 스트림으로 전송
            try (FileInputStream inStream = new FileInputStream(file);
                 OutputStream outStream = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            // 파일을 찾을 수 없는 경우 처리 (예: 404 에러)
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}

create database board;
drop table board;
drop table files;

CREATE TABLE board (
    board_no INT AUTO_INCREMENT PRIMARY KEY,         -- 게시글 번호 (Primary Key, 자동 증가)
    title VARCHAR(255) NOT NULL,               -- 제목
    pass VARCHAR(100) NOT NULL,            -- 암호
    author VARCHAR(100) NOT NULL,              -- 글쓴이
    content TEXT NOT NULL,                     -- 내용
    view_count INT DEFAULT 0,                  -- 조회수 (기본값 0)
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,      -- 등록일 (날짜 및 시간)
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정일 (날짜 및 시간)
    
);
select * from board;

CREATE TABLE files (
    file_no INT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    board_no INT,
    FOREIGN KEY (board_no) REFERENCES board(board_no) ON DELETE CASCADE
);
select * from files;
DESCRIBE files;

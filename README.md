## 👨‍🏫프로젝트 소개
#### eGovFramework를 사용한 CRUD 게시판

본 프로젝트는 전자정부프레임워크를 사용하여 게시판 기능을 구현하는데 목표를 두었습니다.

1. 게시글 작성 및 관리
사용자는 제목, 작성자, 내용 등의 정보를 입력하여 게시글을 작성할 수 있습니다. (게시글 작성 시 파일 첨부 기능도 지원)

2. 게시글 조회 및 검색
사용자는 작성된 게시글을 조회하고 검색할 수 있는 기능을 제공합니다. 이를 통해 원하는 정보를 쉽게 찾을 수 있으며, 게시글 목록에서는 등록일과 작성자 등의 정보가 함께 표시됩니다.

3. 파일 다운로드 기능
게시글에 첨부된 파일은 다운로드할 수 있는 기능이 추가되어 있어, 사용자가 첨부된 파일을 손쉽게 다운로드하여 이용할 수 있습니다. 파일 다운로드는 안전하고 효율적으로 처리되도록 구현되었습니다.

4. 게시글 수정 및 삭제
작성자는 자신이 작성한 게시글을 수정하거나 삭제할 수 있는 기능을 제공합니다. 이를 통해 게시글의 내용을 최신 정보로 유지할 수 있습니다.

## 🕰️개발기간
**2024.10.21 ~ 2024.10.29 (주말 제외 총 7일)**
- 2024.10.21: 프로젝트 개발환경 세팅
- 2024.10.22 ~ 10.24: CRUD 게시판 기능 구현
- 2024.10.24 ~ 10.29: 파일 업로드 및 다운로드 처리

## ⚙️개발 환경
| **항목**          | **내용**                   |
|:-----------------:|:---------------------------:|
| 운영체제          | Windows 10 64bit            |
| 개발도구          | Eclipse                     |
| 빌드툴            | Maven                       |
| 프로그래밍 언어    | Java, JSP                  |
| 프레임워크        | 전자정부 프레임워크         |
| 데이터베이스      | MySQL                       |
|ORM/DB 접근 기술   | iBATIS                      |

---

## 😂실행결과
- index.jap 파일 내에 기본 실행 페이지를 게시글 리스트 페이지로 설정
![image](https://github.com/user-attachments/assets/6bac28f9-7b78-402e-a312-e0d9f94a7568)
- 결과
![image](https://github.com/user-attachments/assets/58a1c3f8-c5db-4efa-ab6e-ffc955b0842b)

---

- 게시글 작성 페이지
![image](https://github.com/user-attachments/assets/9d485b51-7a25-444e-a9e2-1a34615c4b9a)
![image](https://github.com/user-attachments/assets/fb3330dc-1715-4897-9024-9db9c324e8af)
![image](https://github.com/user-attachments/assets/1a94e34c-0c2c-4c61-894b-65a692c70efb)
- DB 조회
![image](https://github.com/user-attachments/assets/0c83912b-1238-4db0-a6bf-6527a920a8e4)
![image](https://github.com/user-attachments/assets/69c96689-9f62-42e1-840d-8759f992f889)
- 파일 저장
![image](https://github.com/user-attachments/assets/3ecab81b-12e2-4873-8aec-3a6bb7fee37b)
D드라이브에 upload 파일에 저장하도록 구현
![image](https://github.com/user-attachments/assets/909821c7-c347-4975-98fa-9f04a7d75574)
파일 저장 확인

---

- 게시글 상세 페이지
![image](https://github.com/user-attachments/assets/7f3cfb59-290b-4b05-88d6-32006ebabf9e)
- 게시글 수정 중에 암호를 입력하지 않으면 업데이트를 할 수 없게 구현
![image](https://github.com/user-attachments/assets/9bff2d10-68d8-414d-8091-d2de6b56e919)
- 게시글 수정 후
![image](https://github.com/user-attachments/assets/41d9b4ae-c2d3-4fce-bbdf-48387c9fa715)
- DB 조회
![image](https://github.com/user-attachments/assets/90d54160-f625-4a3f-82c8-641c09d5500a)
게시글의 경우 수정 시, 수정한 날짜가 별도로 저장되게 구현
![image](https://github.com/user-attachments/assets/eaa5bb43-e83e-408e-a49b-40d37dd2a3d9)

---

- 파일 다운로드
![image](https://github.com/user-attachments/assets/8db38d45-58e9-4180-aff3-c1e69a3fda0d)
![image](https://github.com/user-attachments/assets/97cdaf70-84c6-4b5d-af71-9ead47cb7230)

---

- 게시글 삭제
![image](https://github.com/user-attachments/assets/5a1b14eb-5e46-44f4-9a2d-08f2c7717756)
![image](https://github.com/user-attachments/assets/c9e1f7e7-855b-43a8-bdc3-0443ea10ff28)
게시글 작성 시 입력했던 암호를 확인하여 삭제가 가능하도록 구현
- DB 조회
![image](https://github.com/user-attachments/assets/eb630d43-c85d-400d-8cc4-ee09b55af833)
![image](https://github.com/user-attachments/assets/94c0ceea-bd31-45e6-8747-2c3433ddcde7)
삭제 확인

---

- 검색
![image](https://github.com/user-attachments/assets/bb7b5653-9dc8-4622-9fa5-3927de1d4023)
11이 들어간 제목 조회
![image](https://github.com/user-attachments/assets/eeee50fd-a50d-4383-996b-93ca01ff7097)

- 페이징 처리
![image](https://github.com/user-attachments/assets/b4f106f4-5759-486c-b7cd-e565d81239cc)
![image](https://github.com/user-attachments/assets/090f462e-0b0b-47a4-90a8-746878e1dc56)
게시글 번호가 10 이상으로 넘어갈 경우 10개씩 화면에 출력되도록 조정

---

- 게시글 내용에 개행처리
![image](https://github.com/user-attachments/assets/7a5fcf3b-f4e8-44e8-88c2-0324b02bbd99)
![image](https://github.com/user-attachments/assets/d1386486-3807-4d27-a248-e95855266872)

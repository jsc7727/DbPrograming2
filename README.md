# DbPrograming2

1. 위의 login.html과 입력폼이 동일한 login 페이지로 사용자가 접속하여 ID와 PW를 입력하여 "로그인"을 누를 수 있다.
![image](https://user-images.githubusercontent.com/20225380/122685805-b325a880-d248-11eb-9fcd-a24cabedd16f.png)


1.1)	기 저장된 ID와 PW가 일치하지 않으면 output.html 페이지를 참고하여 비밀번호가 일치하지 않거나 존재하지 않는 ID임을 말해준다.
![image](https://user-images.githubusercontent.com/20225380/122685810-b7ea5c80-d248-11eb-9337-9bb306d031ed.png)


1.2)	ID와 PW가 일치하면 list.html을 참고하여 현재 DB에 저장된 학생 정보들을 모두 받아와 list를 보여준다.
![image](https://user-images.githubusercontent.com/20225380/122685816-bfaa0100-d248-11eb-9b2c-9fe22ab99b74.png)

1.2.1) 위의 list 페이지에서 사용자가 학생들을 (디폴트는 전체 보여주기) 학과별로 볼 수 있도록 학과를 선택해서 검색을 누르면 그 학과에 해당하는 학생들 정보만 list 화면에 보여주도록 한다.
![image](https://user-images.githubusercontent.com/20225380/122685821-c5074b80-d248-11eb-90f0-094543fa6272.png)



1.2.2) 위의 list 페이지에서 사용자가 특정 학생 ID를 클릭하면 detail.html을 참고하여 해당 학생의 정보를 PW를 제외하고 모두 보여주도록 한다.
![image](https://user-images.githubusercontent.com/20225380/122685823-cb95c300-d248-11eb-8a08-dcde16dee417.png)



1.2.2.1) 위의 detail 페이지에서 "목록" 버튼 클릭 시, 다시 list 페이지로 돌아가고
 ![image](https://user-images.githubusercontent.com/20225380/122685826-cfc1e080-d248-11eb-9d39-5b007a4bb180.png)

"수정" 버튼 클릭시 pwcheck.html 페이지를 참고하여 해당 학생의 PW를 확인하는 페이지를 보여주도록 한다.
![image](https://user-images.githubusercontent.com/20225380/122685827-d2bcd100-d248-11eb-8f61-3b245a9a5465.png)



1.2.2.1.1) 위의 pw check 페이지에서 올바른 pw를 입력했다면 member.html 페이지를 참고하여 ID와 학생 이름은 변경할 수 없고 (이미 저장되어 있는 DB 정보를 그대로 이용하고), 나머지 모든 정보들을 수정할 수 있도록 한다.
+![image](https://user-images.githubusercontent.com/20225380/122685828-d6505800-d248-11eb-81f7-2c19fcbc7287.png)



1.2.2.1.1.1) 수정 완료후 제출하면 DB에 업데이트 한 후 다시 detail 페이지를 보여주도록 한다.
![image](https://user-images.githubusercontent.com/20225380/122685836-dd776600-d248-11eb-93b1-bd028e3d87d0.png)



1.2.2.1.2) 위의 pwcheck 페이지에서 틀린 pw를 입력했다면 output.html 페이지를 참고하여 pw가 틀려서 수정할 수 없음을 말해준다. 
![image](https://user-images.githubusercontent.com/20225380/122685838-dfd9c000-d248-11eb-96fc-39b4f43aed69.png)

2. 위의 login.html과 입력폼이 동일한 login 페이지로 사용자가 접속하여 "가입"을 누를 수 있다.
![image](https://user-images.githubusercontent.com/20225380/122685842-e36d4700-d248-11eb-9e14-b15f45af7c90.png)




 2.1) 사용자가 모든 정보를 입력 후 전송 버튼을 클릭했을 때, ID가 기 존재하는 ID이면 output.html 페이지를 참고해서 사용자에게 ID가 이미 존재한다는 것을 말해준다.
![image](https://user-images.githubusercontent.com/20225380/122685845-e9fbbe80-d248-11eb-95d9-834cfc431cf5.png)


2.2) 사용자가 모든 정보 입력 후 전송 버튼을 클릭하면 DB에 해당 학생 정보를 저장하고, output.html 페이지를 참고해서 저장이 완료되었음을 말해준다.
![image](https://user-images.githubusercontent.com/20225380/122685849-ebc58200-d248-11eb-8b7d-3bbc0f183c74.png)


2nd DbPrograming assignments

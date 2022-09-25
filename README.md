# realme-backend

## 기술 스택
<p align="center">
<img width="848" alt="스크린샷 2022-09-25 오전 11 28 13" src="https://user-images.githubusercontent.com/101321313/192125613-9056af43-3416-4661-b4c5-c8c77ccf8aeb.png" style="width: 60%">
</p>

## 브랜치 
`develop`에서 각 기능별 `feature/기능이름` 으로 브랜치를 나누어 개발 후 완료되면 `develop`에 PR한다

## 커밋 컨벤션
```
type: Subject

body
```
- type: 어떤 의도로 커밋했는지를 type에 명시
- body : 긴 설명이 필요한 경우에 작성합니다. 어떻게 했는지가 아니라, 무엇을 왜 했는지를 작성합니다. 최대 75자를 넘기지 않도록 합니다. 
- 예시
  ```
    feat: 로그인 기능 추가한다
  ```

### type
| 태그 이름 | 설명 |
| :-: | :-: |
| feat | 새로운 기능 추가
| fix | 버그를 고친 경우 |
| style |  코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우|
| refactor | 프로덕션 코드 리팩토링 |
| Comment |필요한 주석 추가 및 변경 | 
| Docs| 문서를 수정한 경우| 
| Test| 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X)| 
| Chore| 빌드 태스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X)| 
| Rename| 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우| 
| Remove | 파일을 삭제하는 작업만 수행한 경우 |

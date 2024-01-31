# Team5-teemoteemo

## 🙆 Team
|이치호|장영서|김예지|김태형|이진우|
|:---:|:---:|:---:|:---:|:---:|
|AOS|AOS|BE|BE|BE|
|[@ThinkingDobby](https://github.com/ThinkingDobby)|[@youngseojang01](https://github.com/youngseojang01)|[@isExample](https://github.com/isExample)|[@johan1103](https://github.com/johan1103)|[@binaryrain97](https://github.com/binaryrain97)|
|<img src="https://github.com/softeerbootcamp-3rd/Team5-teemoteemo/assets/93423346/34d9a9a7-088b-42e2-af93-b53d0816d409" width="150" height="150">|<img src="https://github.com/softeerbootcamp-3rd/Team5-teemoteemo/assets/93423346/18f13309-f94d-41a0-a915-6fdf197ae5c4" width="150" height="150">|<img src="https://github.com/softeerbootcamp-3rd/Team5-teemoteemo/assets/93423346/c13a9bea-7c08-4f6c-8af2-85498229ac34" width="150" height="150">|<img src="https://github.com/softeerbootcamp-3rd/Team5-teemoteemo/assets/93423346/21fd5e53-66d8-4025-a5db-225d56c1246b" width="150" height="150">|<img src="https://github.com/softeerbootcamp-3rd/Team5-teemoteemo/assets/93423346/918fb836-6b5b-4676-a2cd-025e91c66090" width="150" height="150">|
|안드로이드 개발은 정말 재밌어|즐겁게 코딩합시다:)|오늘도 레거시를 만든다|백엔드 너무 재밌다|나의 코드 한 줄이 현대를 바꾼다|

## 📌 Branch Strategy
GitHub Flow 브랜치 전략
개발 프로세스를 간소화하고 지속적인 통합 및 배포를 용이하게 하는 브랜치 전략

**main 브랜치**
- 백엔드 개발의 메인 브랜치는 BE입니다.
- 안드로이드 개발의 메인 브랜치는 AOS입니다.
- 각 메인 브랜치는 항상 배포 가능한 상태를 유지해야 합니다.

**새로운 브랜치 생성**
- 새로운 기능을 개발하거나 버그를 수정할 때는 메인에서 직접 작업하지 않고, 새로운 브랜치를 생성합니다.
- 브랜치 이름은 작업 내용을 잘 나타내야 합니다.
  - 예: feature/add-login, fix/login-error, refactor/cleanup-database-connection

**Pull Request (PR) 생성 및 병합**
- 작업이 완료되면, 작업 브랜치에서 메인(BE or AOS) 브랜치로 Pull Request를 생성합니다.
- 모든 리뷰가 완료되고 코드가 메인 브랜치와 충돌이 없다면, PR을 메인 브랜치에 병합합니다.

**리뷰 및 피드백**
- 동료 개발자들은 PR을 리뷰하고 피드백을 제공합니다.
- 필요한 경우 추가 커밋을 통해 피드백을 반영합니다.

**배포**
- 메인(BE or AOS) 브랜치에 병합된 변경 사항은 즉시 또는 정해진 배포 일정에 따라 프로덕션 환경에 배포됩니다.

## ⚒️ Collaboration Tools
협업 툴과 플랫폼의 종류는 최소화
- GitHub Issues & GitHub Projects: 프로젝트 작업 관리
- GitHub Wiki: 트러블 슈팅
- Slack & Slack Webhook: 프로젝트 관련 소통
- Notion: 회의록

## 🤝 Ground Rules
### 목표
성장과 즐거움의 균형 유지하기
- 기능의 수보다는 각 기능의 완성도에 집중
    - 새로운 기술의 이해와 적용, 그리고 응용
    - 한 가지라도 깊게 학습
        - 누구나 하는 것, 잘 알려진 것을 그대로 따르진 말자
- 도전에 의미를 두고 즐겁게 하자

## 규칙
- 편하게 질문하고 편하게 답하기 (편질편답)
- 소통 많이하기
    - 변경 사항은 바로바로 공유
    - 의사결정과 사담 모두 적극적으로
- 파트간 코드리뷰를 성실하게 하기
- 함께 공부하는 시간 두기
    - 기술 선정과 적용 전에 함께 자세하게 알아보는 시간
- 문서화 적극적으로 하기
    - 트러블 슈팅이나 진행상황 등
- 왜? 라는 질문 서로 많이 하기
    - 개발과 관련해서 항상 근거를 고민하기

## 정기 회의
- 매일 아침 오는 시간에, **15분 스크럼**
    - 전날 구현한 내용(퇴근 후 잔업 위주로), 오늘 구현할 내용, 이슈 공유
- 하루 끝에, **30분 팀회고**
    - 오늘 구현한 내용, 퇴근 후 계획, 이슈 공유
- 팀회고 전, **20분 개인 회고**
- **필요에 따른 회의**
      

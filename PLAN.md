# Modernization Plan

## 현재 상태

- 현재 브랜치: `modern-codex` (`master`의 `37978fe412d0b91f9502b213b58c5d18dc5d92da`에서 생성)
- 작업 트리: 2단계 완료 상태
- Java 소스: 83개
- 빌드 시스템: 없음; `javac`/`java` 직접 사용
- 로컬 JDK: `javac 21.0.11`
- 구조 변경 전 기준선 전체 소스: `javac 21.0.11 -encoding UTF-8 -Xlint:all` 컴파일 성공
  - class 파일 230개 생성
  - removal, rawtypes, unchecked, overrides, static, cast 항목에서 경고 40건
- `refactoring_a_case.txt`: UTF-8 변환 및 LF 정규화 완료
- legacy 구조 분리: 완료; modern 소스 구성과 현대화는 미착수

## 작업 단계

### 1. 브랜치와 기준선 확정 — 완료

- `master`에서 `modern-codex`를 생성하고 checkout했다.
- 기준 커밋은 `37978fe412d0b91f9502b213b58c5d18dc5d92da`다.
- 기준선은 추적 파일 88개, Java 소스 83개, 기타 파일 5개다.
- 정렬한 추적 파일 목록의 SHA-256은 `f6e86bd5eddf1d09476a8a7ef27f354c0adfb7018e5df579afb3978992425933`다.
- 파일별 SHA-256 목록 파일의 SHA-256은 `7919537a6e86b526bc8fc1da542d3a1e6e0101845b32f312a7d0b9ac9af17b52`다.
- 기준선 전체가 Java 21에서 컴파일되며 경고 40건이 있음을 확인했다. 경고는 현대화 대상 판단에 활용하되, 교육 목적상 의도된 코드는 무조건 제거하지 않는다.

### 2. 인코딩 및 legacy 구성 — 완료

- `refactoring_a_case.txt`를 EUC-KR에서 UTF-8로 변환하고 LF로 정규화했다.
- 기존 README, 소스와 자료 88개를 `legacy/java8/`로 이동했다.
- 기준선과 비교해 파일 누락이 없고, 인코딩 대상 외 87개 파일의 내용이 동일함을 확인했다.
- Java 소스 83개를 `javac --release 8 -encoding UTF-8 -Xlint:all`로 컴파일해 class 파일 234개 생성을 확인했다.
- 기존 코드 및 JDK 21의 Java 8 대상 옵션에서 경고 42건이 발생하지만 컴파일은 성공했다.

### 3. modern 기준 트리 구성 — 대기

- legacy 기준선을 `modern/java21/`에 복제한다.
- Java 소스를 `modern.java21.*` package로 변경한다.
- import, static import와 리소스 경로를 정리한다.
- 동작 리팩터링을 섞지 않은 상태에서 Java 21 전체 컴파일을 통과시킨다.

### 4. 예제 관계 분석 및 분류 — 대기

- README 설명과 같은 package의 소스를 함께 읽는다.
- 각 파일 또는 묶음을 `유지`, `기존 파일 변경`, `후속 단계 추가`로 분류한다.
- 단계형 예제는 묶음 전체의 서사와 실행 결과를 기록한다.
- 분류와 이유를 `modern/java21/README.md` 및 `DECISION.md`에 반영한다.

### 5. 영역별 현대화 — 대기

다음 순서로 작은 검증 단위로 진행한다.

1. `datetime/`
2. `file/`
3. `collection/`
4. `lang/`
5. `ood/`
6. `string/`, `number/`, 루트와 `test/`

각 영역에서 deprecated API 제거 자체보다 예제의 원래 주제 보존을 우선한다. 저수준 API가 주제인 `NioRw.java`나 `RandomAccessFileDemo.java` 등은 상위 편의 API로 단순 치환하지 않는다.

### 6. README와 최종 검증 — 대기

- 기존 README를 `legacy/java8/README.md`로 보존한다.
- 새 루트 `README.md`와 `modern/java21/README.md`를 작성한다.
- legacy/modern 전체 컴파일, 대표 실행 비교, 파일 누락, 잘못된 기존 package 참조를 검사한다.

## 다음 단계

3단계 modern 기준 트리 구성을 진행한다.

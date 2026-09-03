# Contributor Guide

## Communication

- 사용자에게 한국어 해요체로 응답한다.
- 코드, 식별자, 명령어, 로그와 파일 경로는 원문 그대로 유지한다.

## Project Rules

- 현대화의 유일한 기준선은 `master`의 기존 소스다.
- `origin/modern-opencode`의 코드, 문서, 커밋은 조회하거나 참고하지 않는다.
- `legacy/java8/`에는 기존 예제와 자료를 보존한다.
- `modern/java21/`에는 `modern.java21` 하위 패키지로 옮긴 Java 21 버전을 둔다.
- 한 주제의 개선 과정을 보여주는 파일 묶음은 개별 파일이 아니라 묶음 전체의 교육 목적을 먼저 파악한다.
- 기존 단계를 억지로 고치지 않는다. Java 21 방식이 자연스러운 다음 단계라면 기존 파일을 보존하고 새 후속 예제를 추가한다.
- 유지·변경·추가 판단과 이유는 `modern/java21/README.md`에 파일 또는 묶음 단위로 기록하고, 중요한 판단은 `DECISION.md`에도 기록한다.
- package/import 변경처럼 모든 modern 소스에 공통인 기계적 변경은 파일별로 반복 기록하지 않는다.

## Validation and Git

- 구조 변경 전후로 소스와 자료 파일의 누락 여부를 확인한다.
- legacy는 `javac --release 8 -encoding UTF-8`, modern은 `javac --release 21 -encoding UTF-8 -Xlint:all`을 기본 검증으로 삼는다.
- 변경 범위에 맞는 대표 예제를 실행하고, 동작 보존이 필요한 경우 legacy와 modern 결과를 비교한다.
- 완결된 작업 단위가 커밋할 시점이면 사용자에게 커밋 여부를 묻는다. 사용자가 커밋을 요청했다면 다시 묻지 않는다.
- 커밋 메시지는 간결한 한글로 작성하고, push는 명시적으로 요청받은 경우에만 한다.
- secret, 생성물, 임시 파일과 무관한 사용자 변경은 커밋하지 않는다.

## Living Documents

- `PRD.md`: 목표, 범위와 요구사항
- `PLAN.md`: 계획, 수행 결과, 현재 상태와 다음 단계
- `DECISION.md`: 주요 결정, 대안과 근거
- material한 진행이나 결정 뒤에는 관련 문서를 현행화한다.

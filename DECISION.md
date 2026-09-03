# Decisions

## D-001: `master`만 현대화 기준으로 사용

- 상태: 확정
- 결정: 현재 `master`의 코드와 README만 분석 기준으로 삼고 `origin/modern-opencode`는 조회하거나 참고하지 않는다.
- 근거: 독립적인 현대화 방향과 판단을 유지해야 한다.

## D-002: legacy snapshot과 modern 구현을 공존시킴

- 상태: 확정
- 결정: 원본은 `legacy/java8/`, 현대화 버전은 `modern/java21/`에 둔다.
- 보존 예외: `refactoring_a_case.txt`는 요구사항에 따라 UTF-8 및 LF로 정규화한다.
- 근거: 원래 예제의 역사적·교육적 의미를 보존하면서 Java 21 버전과 직접 비교할 수 있다.

## D-003: 단계형 예제는 덮어쓰지 않고 필요하면 다음 단계를 추가

- 상태: 확정
- 결정: `EnumDemo0`에서 이어지는 예제처럼 개선 과정을 보여주는 묶음은 기존 파일을 유지한다. Java 21 방식이 실질적인 다음 단계가 될 때만 새 파일을 추가한다.
- 검토한 대안: 모든 modern 파일을 최신 문법으로 직접 변경.
- 기각 근거: 중간 단계가 사라져 예제가 설명하는 변화 과정과 비교 가치가 훼손된다.

## D-004: 파일별 판단은 modern README에 기록

- 상태: 확정
- 결정: 유지 사유, 기존 파일의 변경 방향, 새 파일과 기존 묶음의 관계를 `modern/java21/README.md`에 기록한다. package/import 같은 공통 기계적 변경은 한 번만 설명한다.
- 근거: 소스 주석을 불필요하게 늘리지 않으면서 후임 작업자가 판단 근거를 추적할 수 있다.

## D-005: README 역할을 분리

- 상태: 확정
- 결정: 현재 README는 `legacy/java8/README.md`로 이동하고, 루트에는 전체 프로젝트용 README를 새로 만든다.
- 근거: 현재 README는 기존 경로와 레거시 예제만 설명하므로 분리 후 프로젝트 전체 안내로 사용하기 어렵다.

## D-006: 변환 대상 텍스트의 줄바꿈을 LF로 정규화

- 상태: 확정
- 결정: `refactoring_a_case.txt`를 UTF-8로 변환하면서 CRLF를 LF로 정규화하고 trailing whitespace를 제거한다.
- 근거: 텍스트 내용은 유지하면서 저장소 diff 및 후속 UTF-8 검증을 안정적으로 만든다.

## D-007: modern 기준 트리에서 legacy README를 제외

- 상태: 확정
- 결정: `legacy/java8/README.md`는 legacy에만 보존하고, 나머지 소스와 자료를 modern 트리에 복제한다. modern README는 파일별 분류가 확정된 뒤 별도로 작성한다.
- 근거: 기존 README는 legacy 경로와 내용에 국한되어 있어 modern 기준 트리에 그대로 복제하면 역할이 혼동된다.

## D-008: modern 리소스 경로를 package와 함께 이동

- 상태: 확정
- 결정: 상대 경로 동작을 설명하는 사례는 유지하고, 성공해야 하는 절대 경로 및 class loader 경로만 `modern/java21` package 위치에 맞춘다.
- 근거: package 이동 후에도 `ResourceAsStreamDemo`가 설명하는 성공·실패 차이를 동일하게 재현해야 한다.

## D-009: 현대화 처리를 네 종류로 분류

- 상태: 확정
- 결정: 파일 또는 묶음을 `유지`, `제한 변경`, `기존 파일 변경`, `후속 추가`로 분류하고 상세 목록은 `modern/java21/README.md`에서 관리한다.
- 근거: 경고 제거와 최신 문법 적용을 일률적으로 수행하면 언어 동작이나 API 차이를 의도적으로 보여주는 예제를 훼손할 수 있다.

## D-010: 단계형 묶음의 Java 21 후속 예제

- 상태: 확정
- 결정: enum, collection iteration, anonymous/lambda, clone/copy, for-each/stream, reflection delegation, immutable collection 묶음에 기존 파일을 변경하지 않는 후속 예제를 추가한다.
- 근거: 이 영역은 기존 단계의 실패나 한계가 학습 내용이므로 직접 치환보다 다음 단계를 나란히 제시하는 편이 비교에 적합하다.

## 추후 결정 필요

- 빌드 스크립트 또는 테스트 프레임워크를 추가할지, 직접 `javac` 실행을 유지할지

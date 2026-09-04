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

## D-011: datetime 기준값과 파싱 정책

- 상태: 확정
- 결정: 유틸리티 인스턴스는 생성 시점의 immutable `LocalDate` 또는 초 단위 `LocalTime`을 기준값으로 보관한다. 사용자 지정 pattern 파싱은 round-trip이 일치하지 않는 입력을 거부한다.
- 검토한 대안: 호출할 때마다 현재 시각 조회, `DateTimeFormatter.ofPattern`의 기본 SMART resolver 유지.
- 기각 근거: 호출 중 날짜/시간이 바뀌면 한 인스턴스의 결과가 불일치할 수 있고, SMART resolver는 존재하지 않는 날짜나 `24:00:00`을 조용히 보정한다.

## D-012: file 예제의 저수준 API와 오류 전파 정책

- 상태: 확정
- 결정: `FileChannel`, direct `ByteBuffer`, memory mapping, `RandomAccessFile`은 각 예제의 학습 주제이므로 유지한다. 텍스트 변환에는 UTF-8을 명시하고 모든 close는 try-with-resources 또는 `AutoCloseable`로 관리하며, I/O 실패는 null이나 로그로 숨기지 않고 `IOException`으로 호출자에게 전파한다.
- 검토한 대안: `Files.readAllBytes`/`write`로 저수준 구현 전체 치환, 기존의 null 반환과 `printStackTrace` 유지.
- 기각 근거: 편의 API 치환은 채널과 random access의 동작을 가리고, 실패를 정상적인 null 결과로 표현하면 호출자가 원인을 구분하거나 복구하기 어렵다.

## D-013: collection 단계 보존과 컬렉션 노출 정책

- 상태: 확정
- 결정: 반복 중 구조 변경의 실패와 iterator 기반 성공을 보여주는 기존 예제는 그대로 두고 bulk operation과 stream을 별도 후속 예제에서 비교한다. cascading option builder는 내부 mutable 목록과 외부 반환 목록을 분리하고 외부에는 `List.copyOf` 결과만 노출한다.
- 검토한 대안: 기존 실패 사례를 `removeIf`로 교체, builder의 내부 목록을 계속 직접 반환.
- 기각 근거: 실패 사례를 지우면 iterator 규칙을 관찰할 수 없고, 내부 목록 노출은 호출자가 중복 제거와 정렬 규칙을 우회하게 만든다.

## D-014: lang 일반 예제의 후속 단계와 reflection 경계

- 상태: 확정
- 결정: 익명 클래스와 `Object.clone()` 예제는 유지하고 lambda/method reference 및 immutable record/명시적 copy를 별도 후속 파일로 추가한다. reflection 검증은 명시적으로 선언된 필드와 그 값의 변환에 한정한다.
- 검토한 대안: 기존 익명 클래스와 clone 구현을 직접 교체, `getDeclaredFields()`가 반환하는 compiler-generated 필드까지 Java 8과 동일하게 고정.
- 기각 근거: 직접 교체하면 언어 기능의 변화 단계를 비교할 수 없다. Java 21 컴파일러는 outer instance를 사용하지 않는 inner class에 Java 8의 synthetic `this$0` 필드를 생성하지 않을 수 있으므로 compiler-generated 필드는 안정적인 예제 계약이 아니다.

## D-015: enum과 for-each 후속 예제의 비교 기준

- 상태: 확정
- 결정: `EnumDemo5`는 enum에 symbol 상태를 캡슐화하고 exhaustive switch expression으로 연산하며 `EnumDemo4`와 같은 출력을 낸다. `StreamDemo`는 기존 사용자 정의 `Array` API를 수정하지 않고 표준 stream과 Java 21 `List.reversed()`로 `ForEachDemo`의 전체 출력을 같은 순서로 재현한다.
- 검토한 대안: 기존 `EnumDemo3`의 switch를 직접 변경, `Array`의 callback interface를 표준 functional interface로 치환.
- 기각 근거: 기존 파일을 바꾸면 int 상수에서 enum과 사용자 정의 고차 함수로 발전하는 중간 단계를 잃는다. 동일 출력의 별도 후속 예제는 API와 구현 차이를 직접 비교할 수 있다.

## D-016: membership의 token 및 null 정책

- 상태: 확정
- 결정: CSV membership은 쉼표로 나눈 token을 trim해 정확히 비교하며 substring은 허용하지 않는다. null CSV 또는 CSV에서 null 검색은 false이고, 빈 문자열은 명시적인 빈 token과 일치한다. varargs는 `Objects.equals`를 사용해 명시적 null 원소와 null 검색값이 일치하게 한다.
- 검토한 대안: 기존 `String.contains`와 boxed number의 `==` 비교 유지, CSV token을 숫자 타입별로 parse.
- 기각 근거: substring과 reference equality는 membership에 거짓 양성·거짓 음성을 만들며, 이미 타입이 정해진 검색값에는 token 문자열의 정확한 표현 비교가 단순하고 잘못된 숫자 token도 안전하게 false로 처리한다.

## 추후 결정 필요

- 빌드 스크립트 또는 테스트 프레임워크를 추가할지, 직접 `javac` 실행을 유지할지

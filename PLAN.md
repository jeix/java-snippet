# Modernization Plan

## 현재 상태

- 현재 브랜치: `modern-codex` (`master`의 `37978fe412d0b91f9502b213b58c5d18dc5d92da`에서 생성, upstream은 `origin/modern-codex`, 로컬 커밋 push 대기)
- 작업 상태: 5단계 영역별 현대화 중 `ood/`까지 완료
- Java 소스: legacy 83개, modern 90개
- 빌드 시스템: 없음; `javac`/`java` 직접 사용
- 로컬 JDK: `javac 21.0.11`
- 구조 변경 전 기준선 전체 소스: `javac 21.0.11 -encoding UTF-8 -Xlint:all` 컴파일 성공
  - class 파일 230개 생성
  - removal, rawtypes, unchecked, overrides, static, cast 항목에서 경고 40건
- `refactoring_a_case.txt`: UTF-8 변환 및 LF 정규화 완료
- legacy 구조 분리, modern 기준 트리 구성과 예제 관계 분석: 완료
- 영역별 동작 현대화: `datetime/`, `file/`, `collection/`, `lang/`, `ood/` 완료
- 2026-09-04 전체 재검증:
  - legacy: Java 소스 83개, class 파일 234개, Java 8 대상 컴파일 성공(기존 경고 43건)
  - modern: Java 소스 90개, class 파일 245개, Java 21 대상 컴파일 성공(기존 보존 예제 경고 15건)
  - legacy/modern의 `refactoring_a_case.txt`가 모두 유효한 UTF-8임을 확인
  - 루트 `README.md`는 아직 없으며 6단계에서 작성 예정

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

### 3. modern 기준 트리 구성 — 완료

- legacy README를 제외한 소스와 자료 87개를 `modern/java21/`에 복제했다.
- Java 소스 83개를 `modern.java21.*` package로 변경하고 내부 import와 static import를 정리했다.
- modern 트리의 텍스트 파일을 LF로 정규화하고 trailing whitespace를 제거했다.
- `ResourceAsStreamDemo`의 절대·class loader 리소스 경로를 새 package에 맞췄다.
- `javac --release 21 -encoding UTF-8 -Xlint:all` 컴파일에 성공해 class 파일 230개 생성을 확인했다. 기존 코드 경고는 40건이다.
- `modern.java21.ExpectTest`와 `modern.java21.file.ResourceAsStreamDemo` 실행에 성공했다. 리소스 예제의 의도된 null 사례도 3건으로 유지됐다.

### 4. 예제 관계 분석 및 분류 — 완료

- legacy README 설명과 같은 package의 선언, 테스트 메서드와 사용 API를 함께 검토했다.
- 각 파일 또는 묶음을 `유지`, `제한 변경`, `기존 파일 변경`, `후속 추가`로 분류했다.
- enum, collection iteration, anonymous/lambda, clone/copy, for-each/stream, reflection delegation, immutable collection에 후속 예제를 추가하기로 했다.
- 전체 분류, 변경 방향과 보존 근거를 `modern/java21/README.md`에 기록했다.

### 5. 영역별 현대화 — 진행 중

다음 순서로 작은 검증 단위로 진행한다.

1. `datetime/` — 완료
2. `file/` — 완료
3. `collection/` — 완료
4. `lang/` — 완료
5. `ood/` — 완료
6. `string/`, `number/`, 루트와 `test/`

각 영역에서 deprecated API 제거 자체보다 예제의 원래 주제 보존을 우선한다. 저수준 API가 주제인 `NioRw.java`나 `RandomAccessFileDemo.java` 등은 상위 편의 API로 단순 치환하지 않는다.

#### datetime 완료 결과

- 6개 파일을 `java.time` 기반으로 변경하고 legacy 날짜/시간 API 의존성을 제거했다.
- 전체 Java 21 컴파일과 datetime 6개 main 실행에 성공했다.
- `DateDiff`, `DateUtil`, 월 단위 기간 출력이 legacy 기준선과 일치함을 확인했다.
- 윤년, 월말 보정, 일수 차이, 자정 순환 및 잘못된 입력 실패를 별도 경계 검증으로 확인했다.

#### file 완료 결과

- 6개 Java 파일에 `Path`, `Files`, 명시적 UTF-8과 try-with-resources를 적용했다.
- `NioRw`는 `FileChannel`, direct `ByteBuffer`, memory mapping 학습 목적을 유지하면서 partial write, 빈 mapped file, 기존 출력 파일 축소와 2 GiB 초과 입력을 안전하게 처리한다.
- `RandomAccessFileDemo`는 파일 포인터 출력은 유지하고 임시 파일을 항상 정리하며, `ResourceAsStreamDemo`는 `Class`와 `ClassLoader`의 성공·실패 경로를 그대로 보존한다.
- `PropertiesTest`의 최초 파일 생성과 재로딩, `TextFileReader`/`TextFileWriter`의 한글 왕복을 임시 디렉토리에서 검증했다.
- 전체 Java 21 컴파일과 `NioRw` 6개 모드의 바이트 동일성 및 빈 파일 처리를 확인했다.

#### collection 완료 결과

- `ListToArrayTester`에 배열 생성자 참조를 받는 `toArray(String[]::new)` 비교 단계를 추가했다.
- `ArraysSortTest`의 non-comparable 실패와 `Comparable` 정렬 경로를 보존하고 `Comparator.comparing`으로 일반 객체를 정렬하는 성공 경로를 추가했다.
- `CascadingOptionsBuilderDemo`는 `computeIfAbsent`로 내부 컬렉션 생성을 모으고 `List.copyOf`로 외부 변경을 차단하며, 정렬 결과는 comparator와 stream으로 만든 불변 목록으로 반환한다.
- `RemoveDuringIterationTest`는 변경하지 않고 `removeIf`, `removeAll`, 비변경 stream filtering을 비교하는 `RemoveDuringIterationModern`을 후속 단계로 추가했다.
- 나머지 배열 및 insertion-order 단계형 예제는 수정하지 않았으며 legacy와 동일한 출력을 확인했다.

#### lang 1차 완료 결과

- `AnonymousTester`와 `CloneTester`를 보존하고 각각의 다음 단계인 `LambdaTester`, `CopyTester`를 추가했다.
- `NullType`의 null `instanceof`/cast 출력을 유지하면서 raw collection 경고를 제거했다.
- `ReflectField`는 `Field.getType()`과 `trySetAccessible()`을 사용하며 명시적으로 선언된 세 필드의 출력과 변환 결과를 유지한다.
- `VarArgsDemo`는 varargs의 배열 전달/개별 전달 비교를 유지하고 `Calendar`/`Date` 대신 한 번 캡처한 `Instant`를 두 호출에 전달한다.
- `ReturnOExitDemo`, `StaticMethodCallDemo`는 각각 종료/finally와 static dispatch가 주제이므로 변경하지 않았다.
- 선택 파일 컴파일과 8개 main 실행, 보존 예제의 대표 출력 비교에 성공했다.

#### lang 2차 완료 결과

- `EnumDemo0`~`EnumDemo4`를 변경하지 않고 enum의 symbol 상태와 exhaustive switch expression을 결합한 `EnumDemo5`를 추가했다.
- `Array`, `ForEachDemo`를 변경하지 않고 표준 stream pipeline을 사용하는 `StreamDemo`를 추가했다.
- 기존 enum 5개 예제는 legacy와 동일한 출력을 유지하고 `EnumDemo5`는 `EnumDemo4`와 동일한 계산 결과를 낸다.
- `StreamDemo`는 `ForEachDemo`의 for-each/every/some/filter/map/reduce/reduce-right 결과를 같은 순서로 재현한다.

#### lang 3차 완료 결과

- `IsIn`의 CSV membership을 substring 검색에서 trim한 token의 정확한 비교로 바꾸고 varargs membership은 `Objects.equals`와 `anyMatch`로 통일했다.
- null CSV/검색값, 명시적 null 원소, 빈 token, 잘못된 숫자 token과 boxed integer cache 범위 밖 값을 경계 사례로 검증했다.
- `UntilNotVoid` 구현과 반환 규칙은 변경하지 않고 `UntilNotVoidDemo`의 typed overload 결과 주변에 남은 불필요한 cast만 제거했다.
- `IsInDemo`, `UntilNotVoidDemo`의 assertion이 모두 통과했고 `UntilNotVoidDemo`의 출력은 legacy와 동일하다.
- `annotation/`, `inner_class/`, `void_/AvoidNullCheck.java`, `void_/NullProof.java`는 각 묶음의 언어 구조와 null object 실험을 보존하기 위해 변경하지 않았다.

#### ood 완료 결과

- reflection과 문자열 기반 호출을 보여주는 `InstancelessDelegation`을 변경하지 않고 `Supplier<String>`, `BinaryOperator<String>` 및 method reference 기반 `TypeSafeDelegation`을 추가했다.
- `WrapperOverCollection_1*`~`WrapperOverCollection_4*`를 변경하지 않고 중첩 record, `List.copyOf`, `Map.copyOf` 기반 `WrapperOverCollection_5_Record`를 추가했다.
- type-safe delegation은 기존 예제와 같은 `foo`, `foobar` 출력을 내며 새 immutable 예제는 원본 컬렉션 변경 격리와 반환 컬렉션의 변경 거부를 검증했다.
- `template_method/` 네 파일은 private override 실패부터 protected working method까지의 단계 전체가 학습 내용이므로 변경하지 않았다.
- 기존 ood main 14개 실행과 신규 main 2개 실행, 전체 Java 21 컴파일에 성공했다.

### 6. README와 최종 검증 — 대기

- 기존 README를 `legacy/java8/README.md`로 보존한다.
- 새 루트 `README.md`를 작성하고 현재 `modern/java21/README.md`를 최종 상태와 대조한다.
- legacy/modern 전체 컴파일, 대표 실행 비교, 파일 누락, 잘못된 기존 package 참조를 검사한다.

## 다음 단계

`string/`, `number/`, 루트와 `test/` 현대화를 진행한다. 이후 새 루트 `README.md` 작성과 최종 검증으로 마무리한다.

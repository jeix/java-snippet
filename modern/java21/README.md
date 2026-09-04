# Java 21 Modernization Guide

이 디렉토리는 `legacy/java8/` 예제의 Java 21 버전이다. package 이동 외의 변경은 예제의 학습 목적을 유지하는 범위에서만 수행한다.

## 분류 기준

- 모든 Java 소스는 `modern.java21` 하위 package, UTF-8, LF를 사용한다.
- `유지`: package/import/공백 정규화 외에는 바꾸지 않는다.
- `제한 변경`: 기존 파일에서 주제와 무관한 낡은 구현이나 결함만 고친다.
- `기존 파일 변경`: 같은 공개 동작과 학습 목적을 Java 21 API로 구현한다.
- `후속 추가`: 기존 단계를 보존하고 Java 21 관점의 다음 예제를 별도 파일로 만든다.

## Root와 test

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `test/Expect.java`, `ExpectTest.java` | 제한 변경 | 호출 관계와 비교 의미를 유지하고 null 안전성, 잘못된 실패 메시지 등 구현 결함만 고친다. |
| `test/Expect2.java`, `Expect2Test.java` | 기존 파일 변경 | fluent API 구조를 유지하고 주석으로만 남은 matcher를 구현해 Java 타입별 의미를 검증한다. |
| `NpidCheck.java` | 제한 변경 | 검증 알고리즘과 결과를 고정하고 명명, 상수와 입력 검증만 정리한다. |
| `lsc.java` | 기존 파일 변경 | line separator 변환 목적을 유지하며 `Path`, `Files`, `StandardCharsets`와 명확한 오류 전파를 적용한다. |
| `_list.txt`, `refactoring_a_case.txt` | 유지 | 각각 원본 예제 목록과 gradual refactoring 과정 자체가 교육 자료다. |

### Root와 test 완료 결과

- `test/Expect.java`는 `Objects.equals`와 `String.valueOf`로 null-safe하게 만들고 `BigDecimal` 실패 메시지의 기대값 오류를 고쳤다. `ExpectTest.java`에 null 비교를 추가했다.
- `test/Expect2.java`는 기존 `expect(...).not`/`nay()` 구조를 유지하면서 `to_match`, `to_be_null`, `to_be_truthy`, `to_be_falsy`, `to_be_less_than`, `to_be_greater_than`, `to_be_close_to`, `to_throw`를 구현했다. `to_contain`은 문자열, iterable, 객체 및 primitive 배열을 지원한다.
- 사용자가 지칭한 `ExpectTest2.java`의 실제 기존 파일명은 `Expect2Test.java`이므로 이름을 바꾸지 않고 이 파일에서 모든 추가 matcher의 긍정·부정 경로를 실행한다.
- `NpidCheck.java`는 checksum 알고리즘과 기존 유효 사례를 유지하며 상수, ASCII digit 변환과 malformed input 검사를 정리했다.
- `lsc.java`는 반복적인 배열 재할당과 null stream 오류를 제거하고 `Files.readAllBytes/write`, `ByteArrayOutputStream`, UTF-8 newline 상수와 switch expression을 사용한다. 빈 파일은 그대로 두고 I/O 오류는 전파한다.
- `test/Expect2.java`, `Expect2Test.java`는 사용자 요구로 기존 `유지` 분류에서 `기존 파일 변경`으로 조정했다. `_list.txt`, `refactoring_a_case.txt`는 기존 교육 자료이므로 변경하지 않았다.

## collection

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `ArrayInitializeTester.java`, `ArrayExtendTester.java` | 유지 | 배열 크기 결정과 확장의 제약이 주제이므로 `List`로 치환하지 않는다. |
| `ListToArrayTester.java` | 기존 파일 변경 | `toArray(String[]::new)`를 기존 형태와 비교한다. |
| `ArraysSortTest.java` | 제한 변경 | `Comparable` 경로를 유지하고 `Comparator.comparing` 대안을 추가한다. |
| `RemoveDuringIterationTest.java` | 유지 + 후속 추가 | iterator의 실패/성공 사례를 보존하고 `removeIf`, `removeAll`, stream 비교를 `RemoveDuringIterationModern.java`에 추가한다. |
| `OrderedKeyValPairsDemo.java`, `OrderedKeyValPairsDemo2.java`, `LinkedHashMapDemo.java` | 유지 | 두 컬렉션에서 `LinkedHashMap`으로 이어지는 단계형 예제다. |
| `CascadingOptionsBuilderDemo.java` | 기존 파일 변경 | cascading 규칙은 유지하며 `computeIfAbsent`, `List.copyOf`, comparator로 구현을 정리한다. |

### collection 완료 결과

- `ListToArrayTester.java`는 기존 `toArray(T[])` 출력 뒤에 `toArray(String[]::new)` 출력을 추가해 두 API를 직접 비교한다.
- `ArraysSortTest.java`는 non-comparable 객체의 실패와 `Comparable` 호출 과정을 유지하고, `Comparator.comparing`과 null name 정책을 명시한 성공 사례를 추가한다.
- `CascadingOptionsBuilderDemo.java`는 출력과 중복 제거 규칙을 유지하면서 `computeIfAbsent`, `List.copyOf`, comparator와 stream을 적용한다. 반환 목록을 불변 snapshot으로 만들어 내부 상태를 외부에 노출하지 않는다.
- `RemoveDuringIterationTest.java`는 실패와 iterator removal의 차이를 보여주므로 변경하지 않았다. `RemoveDuringIterationModern.java`를 다음 단계로 추가해 `removeIf`, `removeAll`, 원본을 유지하는 stream filtering과 map entry removal을 비교한다.
- `ArrayInitializeTester.java`, `ArrayExtendTester.java`는 고정 크기 배열의 제약을 보여주므로 변경하지 않았다.
- `OrderedKeyValPairsDemo.java`, `OrderedKeyValPairsDemo2.java`, `LinkedHashMapDemo.java`는 insertion order 구현의 발전 과정을 보여주는 묶음이므로 변경하지 않았다.

## datetime

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `DateStringTest.java`, `TimeStringTest.java` | 기존 파일 변경 | `java.sql.Date`/`Calendar` 처리를 `LocalDate`/`LocalTime`과 `DateTimeFormatter`로 옮기고 출력 의미를 비교한다. |
| `DateUtil.java`, `DateUtilB.java`, `TimeUtilB.java` | 기존 파일 변경 | `Date`, `Calendar`, `SimpleDateFormat`을 적절한 `java.time` 타입으로 교체하고 월말 등 경계 동작을 고정한다. |
| `DateDiff.java` | 기존 파일 변경 | millisecond 나눗셈 대신 `ChronoUnit`, `Period`, 필요 시 `Duration`으로 단위를 명시한다. |

### datetime 완료 결과

- 여섯 파일에서 `Date`, `Calendar`, `java.sql.Date/Time`, `DateFormat`, `SimpleDateFormat` 의존성을 제거했다.
- `DateUtil`과 `DateUtilB`는 immutable `LocalDate`, `TimeUtilB`와 `TimeStringTest`는 초 단위 `LocalTime`을 기준값으로 사용한다.
- `DateDiff`는 `LocalDate`를 보관하는 record로 바꾸고 `ChronoUnit.DAYS` 기반 `daysUntil`을 제공한다.
- 입력 문자열은 parse 후 format 결과가 원문과 다르면 `DateTimeParseException`을 던져 SMART resolver의 자동 보정을 허용하지 않는다.
- 3월 31일의 이전/다음 달, 윤년 2월, 자정 순환, 잘못된 날짜·시간 입력을 검증했다.

## file

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `NioRw.java` | 제한 변경 | `FileChannel`, `ByteBuffer`, memory mapping을 유지하고 `Path`와 try-with-resources만 적용한다. |
| `RandomAccessFileDemo.java` | 제한 변경 | random access와 file pointer 동작을 보존하고 리소스 수명만 개선한다. |
| `ResourceAsStreamDemo.java` | 제한 변경 | `Class`/`ClassLoader`의 경로 차이를 유지하고 `Class<?>`, UTF-8, try-with-resources를 적용한다. |
| `PropertiesTest.java` | 기존 파일 변경 | `Path`, `Files` stream과 try-with-resources를 적용하되 `Properties` 동작은 유지한다. |
| `TextFileReader.java`, `TextFileWriter.java` | 기존 파일 변경 | `Files.newBufferedReader/newBufferedWriter`와 명시적 UTF-8을 사용한다. |
| `cfg/foo.txt` | 유지 | `ResourceAsStreamDemo` 실행 자료다. |

### file 완료 결과

- `NioRw.java`는 채널, direct buffer와 memory mapping 구조를 유지하고 `Path`, `FileChannel.open`, try-with-resources를 적용했다. partial write를 끝까지 처리하고 mapped 출력의 기존 잔여 바이트와 빈 파일 경계도 바로잡았다.
- `RandomAccessFileDemo.java`는 기존 파일 포인터 순서와 최종 내용을 유지하되 격리된 임시 파일을 사용하고 항상 삭제한다.
- `ResourceAsStreamDemo.java`는 성공하는 세 경로와 실패하는 세 경로를 그대로 두고 generic `Class<?>`, UTF-8과 try-with-resources를 적용했다.
- `PropertiesTest.java`는 없는 `.cfg`/`.xml` 파일을 생성한 뒤 저장·재로딩하는 동작을 유지하고 I/O 실패를 `IOException`으로 전파한다.
- `TextFileReader.java`, `TextFileWriter.java`는 기존 메서드 이름을 유지하면서 `AutoCloseable`, `Files.newBufferedReader/newBufferedWriter`와 UTF-8을 사용한다. `read_line()`은 `ready()`를 EOF 판정으로 오용하지 않고 `readLine()` 결과를 직접 반환한다.
- `cfg/foo.txt`는 리소스 경로 비교에 필요한 빈 fixture이므로 변경하지 않았다.

## lang

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `AnonymousTester.java` | 유지 + 후속 추가 | 익명 클래스 예제를 보존하고 lambda/method reference 비교를 `LambdaTester.java`로 추가한다. |
| `Autoboxing.java`, `BoundedWildcard.java` | 유지 | 각각 boxing과 bounded wildcard의 언어 동작이 주제다. |
| `CloneTester.java` | 유지 + 후속 추가 | `Object.clone()` 특성을 보존하고 record와 명시적 copy를 쓰는 `CopyTester.java`를 추가한다. |
| `NullType.java` | 제한 변경 | null의 `instanceof`/cast 의미는 유지하고 주제와 무관한 raw collection만 generic으로 바꾼다. |
| `ReflectField.java` | 제한 변경 | field reflection은 유지하고 generic 타입 안전성과 최신 접근 API를 검토한다. |
| `ReturnOExitDemo.java` | 유지 | `return`과 `System.exit()`의 `finally` 실행 차이가 핵심이다. |
| `StaticMethodCallDemo.java` | 유지 | instance를 통한 static 호출 경고 자체가 static dispatch를 설명한다. |
| `SystemSetPropertyDemo.java` | 유지 | line separator property와 실제 출력의 관계를 보여준다. |
| `VarArgsDemo.java` | 제한 변경 | varargs 동작은 유지하고 날짜 예시의 `Calendar`만 `java.time`으로 바꾼다. |

### lang 1차 완료 결과

- `AnonymousTester.java`는 interface/abstract class/concrete class를 익명 구현하는 차이가 주제이므로 변경하지 않았다. `LambdaTester.java`를 추가해 functional interface의 lambda와 method reference를 비교한다.
- `CloneTester.java`는 mutable 객체의 깊은 `Object.clone()` 구현 과정이 주제이므로 변경하지 않았다. `CopyTester.java`를 추가해 immutable record, `List.copyOf`, 명시적 `copy`/`with` 메서드로 복사와 변경을 표현한다.
- `NullType.java`는 null의 `instanceof` 및 cast 출력을 유지하고 `List<Object>`와 diamond operator로 주제와 무관한 raw/unchecked 경고만 제거했다.
- `ReflectField.java`는 `Field.getType()`으로 불필요한 cast를 없애고 `trySetAccessible()` 실패를 명시적으로 처리한다. Java 8에서 보이던 synthetic outer-reference field는 Java 21 compiler가 생성하지 않을 수 있으므로 명시적 필드만 동작 검증 대상으로 삼는다.
- `VarArgsDemo.java`는 `Calendar.getInstance().getTime()`을 한 번 캡처한 `Instant.now()`로 바꿔 두 varargs 호출에 동일한 값을 전달한다.
- `ReturnOExitDemo.java`와 `StaticMethodCallDemo.java`는 의도된 process exit와 static 호출 경고가 각각 학습 내용이므로 변경하지 않았다.

### lang 하위 묶음

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `annotation/Ann.java`, `BisInDie.java`, `Target.java` | 유지 | annotation 선언, 적용과 reflection 확인이 하나의 예제다. |
| `enum_/EnumDemo0.java` ~ `EnumDemo4.java` | 유지 + 후속 추가 | int 상수 → enum → 상태 → switch → constant-specific method 흐름을 보존하고 switch expression과 상태 캡슐화를 `EnumDemo5.java`에 추가한다. |
| `for_each/Array.java`, `ForEachDemo.java` | 유지 + 후속 추가 | 사용자 정의 for-each/filter/map을 보존하고 표준 stream 비교를 `StreamDemo.java`로 추가한다. |
| `inner_class/InnerClassFactory.java`, `InnerClassFactoryDemo.java`, `InnerClassInstantiationDemo.java` | 유지 | inner/static nested class의 인스턴스 관계와 factory 사용을 함께 보여준다. |
| `is_in/IsIn.java`, `IsInDemo.java` | 기존 파일 변경 | 호출 관계를 유지하면서 parsing, `anyMatch`, null/빈 입력 정책을 명확히 한다. |
| `void_/AvoidNullCheck.java`, `NullProof.java` | 유지 | null object와 null 회피에 대한 서로 다른 실험을 보존한다. |
| `void_/until_not_void/UntilNotVoid.java`, `UntilNotVoidDemo.java` | 제한 변경 | generic helper 동작을 유지하고 불필요한 cast만 정리한다. |

### lang 2차 완료 결과

- `EnumDemo0.java`~`EnumDemo4.java`는 int 상수 → enum → 상태 → switch statement → constant-specific method로 이어지는 단계 전체가 학습 대상이므로 변경하지 않았다.
- `EnumDemo5.java`를 다음 단계로 추가해 symbol을 enum 상태로 캡슐화하고 모든 상수를 포괄하는 switch expression으로 연산한다. 출력은 `EnumDemo4.java`와 동일해 구현을 직접 비교할 수 있다.
- `for_each/Array.java`, `ForEachDemo.java`는 Java 표준 stream 이전에 callback 기반 for-each/filter/map/reduce를 직접 구성한 예제이므로 변경하지 않았다.
- `for_each/StreamDemo.java`를 다음 단계로 추가해 `map`, `allMatch`, `anyMatch`, `filter`, `reduce`, `flatMap`과 Java 21 `List.reversed()`를 사용한다. 출력 순서와 값은 `ForEachDemo.java`와 동일하다.

### lang 3차 완료 결과

- `is_in/IsIn.java`는 CSV를 trim한 token 단위로 정확히 비교해 substring 오판을 제거하고, 배열은 `Objects.equals`와 `anyMatch`로 비교해 boxed number의 reference equality 문제를 없앴다.
- `is_in/IsInDemo.java`에는 substring, 공백, 빈 token, null, 잘못된 숫자 token과 integer cache 범위 밖 값의 경계 검사를 추가했다.
- CSV에서 null은 membership 대상이 아니지만 명시적인 varargs null 원소는 null 검색값과 일치한다. 빈 문자열은 CSV에 명시적인 빈 token이 있을 때만 일치한다.
- `void_/until_not_void/UntilNotVoid.java`는 다양한 타입에서 첫 non-void 값을 찾고 모두 void이면 마지막 값을 반환하는 규칙을 보존하기 위해 변경하지 않았다. `UntilNotVoidDemo.java`에서는 typed overload 호출에 불필요한 cast만 제거했다.
- `annotation/`, `inner_class/`, `void_/AvoidNullCheck.java`, `void_/NullProof.java`는 각각 annotation/inner class 관계와 null object 실험 자체가 주제이므로 변경하지 않았다.

## ood

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `delegation/InstancelessDelegation.java` | 유지 + 후속 추가 | reflection 기반 구현을 보존하고 type-safe method reference 또는 `MethodHandle` 대안을 별도 예제로 추가한다. |
| `immutable/WrapperOverCollection_*.java` | 유지 + 후속 추가 | Nothing/Map/JSON/clone/freeze 선택지를 보존하고 `record`, `List.copyOf`, `Map.copyOf` 기반 `WrapperOverCollection_5_Record.java`를 추가한다. |
| `immutable/_data_type.txt` | 유지 | immutable 묶음의 자료 구조 설명이다. |
| `template_method/*.java` | 유지 | private override 실패에서 protected working method까지의 비교 흐름 전체가 학습 대상이다. |

### ood 완료 결과

- `delegation/InstancelessDelegation.java`는 문자열 기반 field/method 탐색, cast와 reflection 실패 처리의 한계를 보여주므로 변경하지 않았다. `TypeSafeDelegation.java`를 추가해 `Supplier<String>`, `BinaryOperator<String>`와 method reference로 같은 출력을 compile-time type safety 아래 재현한다.
- `immutable/WrapperOverCollection_1*.java`~`WrapperOverCollection_4*.java`는 직접 노출, map/JSON 변환, clone, freeze로 이어지는 선택지와 한계가 학습 내용이므로 변경하지 않았다.
- `immutable/WrapperOverCollection_5_Record.java`를 다음 단계로 추가해 중첩 값을 record로 만들고 각 생성 경계에서 `List.copyOf`와 `Map.copyOf`를 적용한다. 원본 컬렉션의 후속 변경이 snapshot에 반영되지 않고 반환 컬렉션을 변경할 수 없음을 실행 결과로 확인한다.
- `immutable/_data_type.txt`는 wrapper 묶음의 자료 구조 설명이므로 변경하지 않았다.
- `template_method/*.java`는 private method가 override되지 않는 실패부터 final template/protected working method 조합까지 이어지는 단계형 묶음이므로 변경하지 않았다.

## string과 number

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `StringSplit.java`, `StringSplitDemo.java` | 유지 | `String.split`과 `StringTokenizer`의 빈 토큰 처리 차이를 보여준다. |
| `StringReplaceDemo.java`, `StringReplaceAllDemo.java`, `EscapeSequenceReplaceDemo.java` | 유지 | literal replacement와 regex replacement의 차이를 함께 설명한다. |
| `MethodParamterSignatureFormatter.java` | 유지 | regex formatting이 주제이며 기존 파일명도 참조 안정성을 위해 유지한다. |
| `FormatterFormatDemo.java` | 제한 변경 | formatter 출력은 유지하고 제거 예정인 `new Float(double)`만 교체한다. |
| `IntegerParseIntTest.java`, `IntegerValueOfTest.java`, `BigDecimalDemo.java` | 유지 | radix/접두사 및 `BigDecimal` 고유 동작을 보여준다. |
| `DecimalPoint.java`, `HumanReadable.java` | 제한 변경 | 계산·출력 규칙을 고정하고 generic, 불변 반환과 formatter 사용만 정리한다. |
| `NumberFormatDemo.java` | 제한 변경 | 동작을 유지하고 환경 locale 의존성을 제거한다. |

### string과 number 완료 결과

- `FormatterFormatDemo.java`는 `new Float(double)`를 autoboxing으로 교체하고 `Formatter`에 `Locale.ROOT`와 try-with-resources를 적용했다. 기존 formatter 출력은 유지된다.
- `DecimalPoint.java`는 내부에 남겨 둔 v1/v2/v3 구현 비교와 기존 반올림·한글 단위 규칙을 유지하면서 상수 목록을 `List.of`로 만들고 `StringBuilder`, `String.repeat`, locale 고정 formatter를 적용했다.
- `HumanReadable.java`는 단위 prefix를 `List.of`로 보호하고 `P`, `E` 범위를 추가했으며 `DecimalFormatSymbols`에 `Locale.ROOT`를 명시했다.
- `NumberFormatDemo.java`는 `NumberFormat.getInstance(Locale.ROOT)`로 실행 환경과 무관하게 기존 예상 문자열을 만든다.
- 변경 대상은 기본 locale과 `de_DE`에서 동일하게 동작한다. 기존 string/number main 13개의 정상 locale 출력도 legacy와 동일하다.
- `StringSplit.java`, `StringSplitDemo.java`, replace/regex 예제군, `MethodParamterSignatureFormatter.java`, integer parsing 예제와 `BigDecimalDemo.java`는 API별 차이와 실패 자체가 학습 내용이므로 변경하지 않았다.

## 검증 원칙

- 기존 파일 변경 전 대표 출력과 경계 사례를 기록한다.
- `유지` 파일은 공통 변경 외 내용 변경이 없는지 확인한다.
- 후속 예제는 바로 이전 단계와 나란히 실행해 개선점이 드러나야 한다.
- 전체 소스는 `javac --release 21 -encoding UTF-8 -Xlint:all`로 컴파일한다.

## 최종 검증

- legacy의 원본 자료·소스 87개와 modern 기준 트리의 상대 경로가 일치하며, 문서화한 후속 Java 예제 7개만 추가되었다.
- modern Java 소스 90개가 `--release 21 -encoding UTF-8 -Xlint:all`로 컴파일되어 class 파일 246개가 생성되었다.
- 경고 14건은 학습 목적으로 유지한 `CloneTester.java`, `StaticMethodCallDemo.java`, `InstancelessDelegation.java`에서만 발생한다.
- modern의 Java, Markdown과 text 자료는 모두 유효한 UTF-8이고, 모든 Java 파일의 package 및 내부 import가 `modern.java21` 구조와 일치한다.
- 의도적으로 process를 종료하는 `ReturnOExitDemo.java`를 제외한 `main` 81개, file 예제 경계 사례와 후속 예제의 이전 단계 출력 비교를 통과했다.

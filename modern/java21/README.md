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
| `test/Expect2.java`, `Expect2Test.java` | 유지 | `Expect`에서 fluent API로 이어지는 별도 시도의 장단점을 보존한다. |
| `NpidCheck.java` | 제한 변경 | 검증 알고리즘과 결과를 고정하고 명명, 상수와 입력 검증만 정리한다. |
| `lsc.java` | 기존 파일 변경 | line separator 변환 목적을 유지하며 `Path`, `Files`, `StandardCharsets`, try-with-resources를 적용한다. |
| `_list.txt`, `refactoring_a_case.txt` | 유지 | 각각 원본 예제 목록과 gradual refactoring 과정 자체가 교육 자료다. |

## collection

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `ArrayInitializeTester.java`, `ArrayExtendTester.java` | 유지 | 배열 크기 결정과 확장의 제약이 주제이므로 `List`로 치환하지 않는다. |
| `ListToArrayTester.java` | 기존 파일 변경 | `toArray(String[]::new)`를 기존 형태와 비교한다. |
| `ArraysSortTest.java` | 제한 변경 | `Comparable` 경로를 유지하고 `Comparator.comparing` 대안을 추가한다. |
| `RemoveDuringIterationTest.java` | 유지 + 후속 추가 | iterator의 실패/성공 사례를 보존하고 `removeIf`, `removeAll`, stream 비교를 `RemoveDuringIterationModern.java`에 추가한다. |
| `OrderedKeyValPairsDemo.java`, `OrderedKeyValPairsDemo2.java`, `LinkedHashMapDemo.java` | 유지 | 두 컬렉션에서 `LinkedHashMap`으로 이어지는 단계형 예제다. |
| `CascadingOptionsBuilderDemo.java` | 기존 파일 변경 | cascading 규칙은 유지하며 `computeIfAbsent`, `List.copyOf`, comparator로 구현을 정리한다. |

## datetime

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `DateStringTest.java`, `TimeStringTest.java` | 기존 파일 변경 | `java.sql.Date`/`Calendar` 처리를 `LocalDate`/`LocalTime`과 `DateTimeFormatter`로 옮기고 출력 의미를 비교한다. |
| `DateUtil.java`, `DateUtilB.java`, `TimeUtilB.java` | 기존 파일 변경 | `Date`, `Calendar`, `SimpleDateFormat`을 적절한 `java.time` 타입으로 교체하고 월말 등 경계 동작을 고정한다. |
| `DateDiff.java` | 기존 파일 변경 | millisecond 나눗셈 대신 `ChronoUnit`, `Period`, 필요 시 `Duration`으로 단위를 명시한다. |

## file

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `NioRw.java` | 제한 변경 | `FileChannel`, `ByteBuffer`, memory mapping을 유지하고 `Path`와 try-with-resources만 적용한다. |
| `RandomAccessFileDemo.java` | 제한 변경 | random access와 file pointer 동작을 보존하고 리소스 수명만 개선한다. |
| `ResourceAsStreamDemo.java` | 제한 변경 | `Class`/`ClassLoader`의 경로 차이를 유지하고 `Class<?>`, UTF-8, try-with-resources를 적용한다. |
| `PropertiesTest.java` | 기존 파일 변경 | `Path`, `Files` stream과 try-with-resources를 적용하되 `Properties` 동작은 유지한다. |
| `TextFileReader.java`, `TextFileWriter.java` | 기존 파일 변경 | `Files.newBufferedReader/newBufferedWriter`와 명시적 UTF-8을 사용한다. |
| `cfg/foo.txt` | 유지 | `ResourceAsStreamDemo` 실행 자료다. |

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

## ood

| 파일 또는 묶음 | 처리 | 변경 방향과 근거 |
|---|---|---|
| `delegation/InstancelessDelegation.java` | 유지 + 후속 추가 | reflection 기반 구현을 보존하고 type-safe method reference 또는 `MethodHandle` 대안을 별도 예제로 추가한다. |
| `immutable/WrapperOverCollection_*.java` | 유지 + 후속 추가 | Nothing/Map/JSON/clone/freeze 선택지를 보존하고 `record`, `List.copyOf`, `Map.copyOf` 기반 `WrapperOverCollection_5_Record.java`를 추가한다. |
| `immutable/_data_type.txt` | 유지 | immutable 묶음의 자료 구조 설명이다. |
| `template_method/*.java` | 유지 | private override 실패에서 protected working method까지의 비교 흐름 전체가 학습 대상이다. |

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

## 검증 원칙

- 기존 파일 변경 전 대표 출력과 경계 사례를 기록한다.
- `유지` 파일은 공통 변경 외 내용 변경이 없는지 확인한다.
- 후속 예제는 바로 이전 단계와 나란히 실행해 개선점이 드러나야 한다.
- 전체 소스는 `javac --release 21 -encoding UTF-8 -Xlint:all`로 컴파일한다.

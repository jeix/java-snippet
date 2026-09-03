# java-snippet

간단한 자바 코드 조각 모음이다. 원래는 평면 구조였는데, Java 21로 모던화하는 작업을 위해
`legacy/java8/`(원본 보존)과 `modern/java21/`(Java 21로 다시 쓴 버전)로 나눴다.

## 구조

- `legacy/java8/` — 원본 그대로. package 선언도 원본 유지. 각 파일이 무엇을 보여주는지는
  [legacy/java8/README.md](legacy/java8/README.md) 참고.
- `modern/java21/` — 같은 내용을 `modern.java21.*` 패키지로 옮기고, 주제를 해치지 않는 선에서만
  Java 21 스타일로 다시 썼다. 모든 파일을 무조건 고치진 않았다.

## 왜 이렇게 나눴나

`legacy/java8/`의 코드 상당수는 "예전엔 이렇게 썼고, 지금은 이렇게 바꾼다"를 나란히 보여주는
점진적 개선 과정이다(예: `lang/enum_/EnumDemo0` → … → `EnumDemo4`). 작성 당시엔 그 정도가
첨단에서 살짝 뒤처진 수준이었을 뿐, 일부러 낡은 코드를 박제해 둔 게 아니다. 그래서 이번 모던화도
그 개선 흐름을 끊지 않는 방향으로 진행한다 — 시리즈의 각 단계는 그대로 두고, Java 21에서 정말
새로운 답이 생긴 경우에만 다음 단계를 이어 붙인다.

자세한 판단 기준은 [PLAN.md](PLAN.md), 개별 판단과 근거는 [DECISIONS.md](DECISIONS.md)에 있다.

## 컴파일 / 실행

빌드 파일은 없다. 저장소 루트가 곧 소스 루트다.

```bash
# legacy
javac -encoding UTF-8 -d out/legacy -sourcepath legacy/java8 $(find legacy/java8 -name '*.java')
java -cp out/legacy <package>.<Class>

# modern
javac -encoding UTF-8 -d out/modern -sourcepath . $(find modern -name '*.java')
java -cp out/modern modern.java21.<package>.<Class>
```

## legacy ↔ modern 대응표

모던화는 영역별로 나눠서 전부 끝났다(datetime → file → lang → collection → ood →
number/string → test 하네스/최상위 → 시리즈 새 단계 추가). 아래는 파일마다 무엇을 바꿨는지,
왜 그대로 뒀는지 한 줄 요약이다. 괄호 안 `D숫자`는 [DECISIONS.md](DECISIONS.md)의 해당
판단 번호, 단계별 실행 기록은 [PLAN.md](PLAN.md)의 체크리스트 참고.

범례: 🔧 모던화됨 · 📌 원형 유지(그 자체가 주제) · 🔀 이름 변경 · ➕ modern에만 있는 신규 파일

### 최상위 · test/

| legacy | modern | |
|---|---|---|
| `ExpectTest.java` | `ExpectTest.java` | 패키지만 이동, 로직 무변경 |
| `Expect2Test.java` | `Expect2Test.java` | 🔧 신규 매처 8개 호출 추가 (D26) |
| `lsc.java` | `LineSeparatorConverter.java` | 🔀🔧 클래스명 정리 + `Files.readAllBytes`/`write`로 재작성 (D28) |
| `NpidCheck.java` | `NpidCheck.java` | 🔧 `IntStream` + text block, 상수 공유 (D29) |
| `test/Expect.java` | `test/Expect.java` | 🔧 `Objects.equals`, `BigDecimal` 메시지 버그 수정 (D27) |
| `test/Expect2.java` | `test/Expect2.java` | 🔧 주석만 있던 매처 8개 신규 구현 (D26) |

### datetime/ — 전부 모던화

| legacy | modern | |
|---|---|---|
| `DateDiff.java` | 같음 | 🔧 `java.sql.Date` → `LocalDate` |
| `DateStringTest.java` | 같음 | 🔧 `LocalDate`/`DateTimeFormatter` (D8) |
| `DateUtil.java` | 같음 | 🔧 `LocalDate`/`DateTimeFormatter` |
| `DateUtilB.java` | 같음 | 🔧 `LocalDate`/`DateTimeFormatter`, nested `Period` 이름은 유지 (D9, D10) |
| `TimeStringTest.java` | 같음 | 🔧 `LocalTime`/`DateTimeFormatter` |
| `TimeUtilB.java` | 같음 | 🔧 `LocalTime`/`DateTimeFormatter` (D10) |

### file/ — 전부 모던화

| legacy | modern | |
|---|---|---|
| `NioRw.java` | 같음 | 🔧 `FileChannel.open(Path)` + try-with-resources, 저수준 버퍼 로직은 유지 (D13) |
| `PropertiesTest.java` | 같음 | 🔧 `Path`/`Files` + try-with-resources |
| `RandomAccessFileDemo.java` | 같음 | 🔧 `RandomAccessFile` → `FileChannel` |
| `ResourceAsStreamDemo.java` | 같음 | 🔧 raw `Class` → `Class<?>`, 리소스 경로만 새 위치로 수정 (D7) |
| `TextFileReader.java` | 같음 | 🔧 `Files.lines()` 기반 재작성 |
| `TextFileWriter.java` | 같음 | 🔧 `Files.newBufferedWriter()`, 명시적 UTF-8 |

### lang/

| legacy | modern | |
|---|---|---|
| `Autoboxing.java` | 같음 | 📌 오토박싱·캐스팅 의미론이 주제 |
| `NullType.java` | 같음 | 📌 null의 타입 시스템 동작이 주제(raw type도 주제의 일부) |
| `StaticMethodCallDemo.java` | 같음 | 📌 static 디스패치 의미론이 주제 |
| `ReturnOExitDemo.java` | 같음 | 📌 finally/exit 의미론이 주제 |
| `SystemSetPropertyDemo.java` | 같음 | 📌 JVM 초기화 순서 이슈가 주제 |
| `AnonymousTester.java` | 같음 | 🔧 함수형 인터페이스 케이스만 람다로, 나머지 둘은 구조상 불가 (D14) |
| `BoundedWildcard.java` | 같음 | 🔧 `sealed`/`var` |
| `CloneTester.java` | 같음 | 🔧 `Cloneable`/`clone()` → 복사 생성자 (D17) |
| `ReflectField.java` | 같음 | 🔧 switch 식 + `RecordComponent` 데모 추가 (D15) |
| `VarArgsDemo.java` | 같음 | 🔧 `Calendar` → `Instant` |
| `annotation/Ann.java` | 같음 | 🔧 `AnnotationTarget` 참조, 멀티 catch |
| `annotation/BisInDie.java` | 같음 | 📌 어노테이션 선언 문법이 주제 |
| `annotation/Target.java` | `annotation/AnnotationTarget.java` | 🔀 `java.lang.annotation.Target`과 이름 충돌 소지, record 미적용(D15) |
| `enum_/EnumDemo0.java`~`EnumDemo4.java` | 같음 | 📌 enum 도입 시리즈, 각 단계 보존 |
| — | `enum_/EnumDemo5.java` | ➕ `switch(this)` → switch 식 (D30) |
| `for_each/Array.java` | (삭제) | 🔧 표준 `Stream`이 하던 일과 같아서 삭제 (D14) |
| `for_each/ForEachDemo.java` | 같음 | 🔧 `Stream`/`List#replaceAll`/`List#reversed()` |
| `inner_class/*.java`(3) | 같음 | 📌 inner vs static nested 클래스 판단 기준이 주제 |
| `is_in/IsIn.java` | 같음 | 🔧 `Integer` 참조 비교·CSV 부분일치 버그 수정, `Set.of`+`equals` (D16) |
| `is_in/IsInDemo.java` | 같음 | 무변경(호출부) |
| `void_/AvoidNullCheck.java` | 같음 | 🔧 `Optional.ofNullable().orElse()` |
| `void_/NullProof.java` | 같음 | 🔧 `Optional` + `LocalDate` |
| `void_/until_not_void/UntilNotVoid.java` | 같음 | 🔧 instanceof 사슬 → 패턴 매칭 switch (D18) |
| `void_/until_not_void/UntilNotVoidDemo.java` | 같음 | 🔧 이미 불필요하다고 자체 증명된 중복 캐스팅 블록 삭제 (D18) |

### number/ · string/

| legacy | modern | |
|---|---|---|
| `number/IntegerParseIntTest.java` | 같음 | 📌 진법·형식 파싱 동작이 주제 |
| `number/IntegerValueOfTest.java` | 같음 | 📌 위와 동일 |
| `number/BigDecimalDemo.java` | 같음 | 📌 `BigDecimal` API 동작이 주제 |
| `number/NumberFormatDemo.java` | 같음 | 📌 소수 자릿수 설정 동작이 주제 |
| `number/HumanReadable.java` | 같음 | 🔧 `StringBuffer` → 단순 연결/`StringBuilder` |
| `number/DecimalPoint.java` | 같음 | 🔧 죽어있던 v1/v2 대안 구현 삭제, `StringBuilder`, 오타 수정 (D24) |
| `string/StringSplit.java` | 같음 | 📌 `split()` vs `StringTokenizer` 대비가 주제 |
| `string/StringSplitDemo.java` | 같음 | 📌 위와 동일 |
| `string/StringReplaceDemo.java` | 같음 | 📌 `replace()`가 리터럴을 받는다는 게 주제 |
| `string/StringReplaceAllDemo.java` | 같음 | 📌 `replaceAll()`이 정규식을 받는다는 게 주제 |
| `string/EscapeSequenceReplaceDemo.java` | 같음 | 📌 이스케이프 처리 차이가 주제 |
| `string/FormatterFormatDemo.java` | 같음 | 🔧 `new Float(10.4)`(제거 예정) → `10.4f` (D25) |
| `string/MethodParamterSignatureFormatter.java` | `string/MethodParameterSignatureFormatter.java` | 🔀 오타(Paramter) 수정 |

### collection/

| legacy | modern | |
|---|---|---|
| `ArrayExtendTester.java` | 같음 | 🔧 `Arrays.copyOf`/`toString` |
| `ArrayInitializeTester.java` | 같음 | 🔧 `Arrays.copyOf`/`toString` |
| `ArraysSortTest.java` | 같음 | 🔧 `List#sort(null)`(복사본에 정렬해 예외 시 원본 무변경 유지) (D19) |
| `CascadingOptionsBuilderDemo.java` | 같음 | 🔧 `Option` → record, `computeIfAbsent` (D20) |
| `ListToArrayTester.java` | 같음 | 🔧 `List.of` + `toArray(String[]::new)` |
| `RemoveDuringIterationTest.java` | 같음 | 🔧 기존 케이스 유지 + `removeIf` 케이스 추가 (D21) |
| `OrderedKeyValPairsDemo.java` | 같음 | 📌 List+Map 조합 구현이 주제(시리즈) |
| `OrderedKeyValPairsDemo2.java` | 같음 | 📌 List+List 조합 구현이 주제(시리즈) |
| `LinkedHashMapDemo.java` | 같음 | 📌 표준 라이브러리 사용이 결론(시리즈) |
| — | `SequencedMapDemo.java` | ➕ `LinkedHashMap`의 `SequencedMap`(JEP 431) (D30) |

### ood/

| legacy | modern | |
|---|---|---|
| `delegation/InstancelessDelegation.java` | 같음 | 🔧 raw `Class`→`Class<?>`, 배열 복사 제거, 멀티 catch (D23) |
| `immutable/WrapperOverCollection_1.java` | 같음 | 📌 "보호 없음" 전략(시리즈, D22) |
| `immutable/WrapperOverCollection_1_Nothing.java` | 같음 | 📌 위 전략의 변형(시리즈, D22) |
| `immutable/WrapperOverCollection_2.java` | 같음 | 📌 "Map 파생 뷰" 전략(시리즈, D22) |
| `immutable/WrapperOverCollection_2_Map.java` | 같음 | 📌 위 전략의 변형(시리즈, D22) |
| `immutable/WrapperOverCollection_2_JSON.java` | 같음 | 📌 "JSON 파생 뷰" 전략(시리즈, D22) |
| `immutable/WrapperOverCollection_3.java` | 같음 | 📌 "매번 방어적 clone" 전략(시리즈, D22) |
| `immutable/WrapperOverCollection_3_Clone.java` | 같음 | 📌 위 전략의 변형(시리즈, D22) |
| `immutable/WrapperOverCollection_4.java` | 같음 | 📌 "freeze" 전략(시리즈, D22) |
| `immutable/WrapperOverCollection_4_Freeze.java` | 같음 | 📌 위 전략의 변형, 원작자 버그 있음(시리즈, D22) |
| — | `immutable/ImmutableRuleSetDemo.java` | ➕ record + `List.copyOf`가 9가지 전략을 대체 (D22) |
| `template_method/OverridePrivateMethodOnlyTest.java` | 같음 | 📌 잘못된 오버라이드 사례(시리즈) |
| `template_method/OverrideTemplateMethodDemo.java` | 같음 | 📌 대안 1(시리즈) |
| `template_method/OverrideWorkingMethodDemo.java` | 같음 | 📌 권장 방식(시리즈) |
| `template_method/OverrideProtectedMethodTest.java` | 같음 | 📌 변형(시리즈) |
| — | `template_method/SealedTemplateMethodDemo.java` | ➕ sealed interface + 패턴 매칭 switch (D30) |

### legacy 전용 문서

`README.md`, `_list.txt`, `refactoring_a_case.txt`(EUC-KR → UTF-8 변환, D2)는 `legacy/java8/`
에만 있다. `_list.txt`는 이 `README.md`의 조상 문서라 modern 쪽에 대응하는 문서가 없다.

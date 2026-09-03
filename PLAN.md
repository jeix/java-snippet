# legacy/java8 보존 + modern.java21 모던화 계획

작업 브랜치: `modern-claude` (master=37978fe 에서 분기)

## 배경

이 저장소는 83개 `.java` 파일로 된 "코드 조각 노트"다. 빌드 파일 없이 저장소 루트를 소스 루트 삼아
`javac`로 직접 컴파일하는 구조이고, 파일 하나가 곧 실행 단위다. 코드는 Java 5/6 스타일에 머물러 있다.

문제는 이 저장소의 상당수 파일이 그냥 "낡은 코드"가 아니라는 점이다. 작성 당시 기준으로는 그 정도가
첨단에서 살짝 뒤처진 수준이었을 뿐이고, 의도가 있었다면 "이렇게 쓰던 건 이렇게 바꿔라"는 개선 흐름을
보여주려는 것에 가깝다. `lang/enum_/EnumDemo0`(int 상수) → `EnumDemo1`(enum) → … →
`EnumDemo4`(상수별 메서드 본문)처럼 점진적 개선 과정을 보여주는 시리즈가 여럿 있고,
`legacy/java8/refactoring_a_case.txt` 첫 줄의 "gradual, iterative, evolving coding"이 저장소
전체의 편집 철학이다. 그래서 일괄 모던화는 그 개선 흐름을 끊어버려서 자료의 가치를 깎는다.

목표: 원본을 손실 없이 보존한 채 Java 21 판본을 나란히 둔다.

- `legacy/java8/` — 지금 상태 그대로의 스냅샷 (package 선언까지 무변경)
- `modern/java21/` — `modern.java21.*` 패키지로 옮긴 뒤, 주제를 해치지 않는 선에서만 Java 21 화

세부 판단 근거는 [DECISIONS.md](DECISIONS.md)를 참고한다.

## 레이아웃

```
/
├── README.md                    ← 6단계에서 신규 작성 예정
├── legacy/java8/                ← 소스 루트. package 선언 무변경
└── modern/java21/                ← package modern.java21.*  (소스 루트 = 저장소 루트)
```

컴파일:

```bash
javac -encoding UTF-8 -d out/legacy -sourcepath legacy/java8 $(find legacy/java8 -name '*.java')
javac -encoding UTF-8 -d out/modern -sourcepath .            $(find modern      -name '*.java')
```

## 모던화 판단 기준

한 문장 원칙: **그 파일이 가르치려는 주제는 건드리지 않고, 주제와 무관한 주변 코드만 모던화한다.**

1. 주제가 그 시절의 표준적인 작성법 자체(지금은 대체된 관용구)면 → 손대지 않는다 (`EnumDemo0`의 int 상수 등).
2. 주제가 JVM·언어 의미론이면 → 손대지 않는다 (`ReturnOExitDemo`, `StaticMethodCallDemo` 등).
3. 주제가 레거시 API의 동작 설명이고 시리즈가 아니면 → 과감히 대체한다 (`datetime/*`, `file/*`).
4. 주제가 표현 방식이면 → 표현만 Java 21로 바꾼다 (익명 클래스 → 람다 등).
5. 시리즈는 각 단계를 그대로 두고, Java 21에서 정말로 새 답이 생긴 경우에만 마지막에 단계를 추가한다.
6. 파일명·클래스명은 최대한 유지한다. 바꾸는 건 이름 자체가 문제일 때만.
7. 들여쓰기는 탭을 유지한다.
8. 검증 기준: legacy와 modern의 실행 결과가 최대한 같아야 한다. 불가피하게 달라지면 그 커밋
   메시지에 왜 다른지 남긴다. assertion(`System.err`)이 있는 파일은 stderr가 비어야 통과다.

모던화하지 않는 파일은 package 선언 아래에 `// 원형 유지: <이유>` 주석을 남긴다 (grep 가능하도록
접두어 고정). 다른 파일에 Java 21 판이 있으면 `// Java 21 판: <경로>` 를 이어 붙인다. legacy
쪽에는 이 주석을 넣지 않는다.

## 실행 단계

- [x] 1. 분기 & 베이스라인 — `modern-claude` 브랜치 생성. 현재 상태가 JDK 21로 컴파일되는지 확인
      (에러 0, 경고 40개만 확인됨).
- [x] 2. `legacy/java8/` 스냅샷 — 커밋 `c1dae7d`. `refactoring_a_case.txt`는 EUC-KR → UTF-8 변환.
- [x] 3. `modern/java21/` 이동 (내용 무변경, 패키지만 `modern.java21.*`) — 커밋 `eb1f74a`.
- [ ] 4. 모던화 커밋 (영역별로 진행, 그 영역의 원형 유지 파일에 유지 사유 주석도 같은 커밋에 포함)
  - [x] 4-1. datetime — 커밋 `c765a51`
  - [x] 4-2. file
  - [x] 4-3. lang (함수형·null 처리·기타)
  - [ ] 4-4. collection
  - [ ] 4-5. ood
  - [ ] 4-6. number/string
  - [ ] 4-7. test 하네스 + 최상위 (`ExpectTest`, `Expect2Test`, `lsc`, `NpidCheck`)
- [ ] 5. 시리즈에 새 단계 추가 (아래 표, "Java 21에서 정말 새 답이 생긴 경우"만)
- [x] 6a. 루트 `README.md` 초안 작성 (구조·컴파일법·진행 상황 표). 이후 영역별 모던화 커밋마다
      진행 상황을 갱신한다 (D12).
- [ ] 6b. 전체 완료 후 legacy↔modern 대응표 완성, `legacy/java8/README.md`의 GitHub 링크 경로를
      새 위치에 맞게 조정.

## 파일별 분류 (요약)

### A. 원형 유지 — 모던화하지 않음 (24개)

`lang/enum_/EnumDemo0~4`, `ood/template_method/*`(4), `collection/OrderedKeyValPairsDemo`·`…Demo2`·
`LinkedHashMapDemo`, `string/StringSplit`·`StringSplitDemo`·`StringReplaceDemo`·`StringReplaceAllDemo`·
`EscapeSequenceReplaceDemo`, `number/IntegerParseIntTest`·`IntegerValueOfTest`·`BigDecimalDemo`·
`NumberFormatDemo`, `lang/Autoboxing`·`NullType`·`StaticMethodCallDemo`·`ReturnOExitDemo`·
`SystemSetPropertyDemo`, `lang/inner_class/*`(3), `lang/annotation/BisInDie`.

각 파일별 정확한 유지 사유 문구는 `~/.claude/plans/goofy-bouncing-barto.md`(이 계획을 처음 승인받은
Claude Code 계획 파일)에 있다. 4단계 진행 시 그 문구를 그대로 헤더 주석에 옮긴다.

### B. 적극 모던화 (파일명 유지)

| 영역 | 파일 | 변경 방향 |
|---|---|---|
| datetime (6, 완료) | `DateDiff` `DateStringTest` `DateUtil` `DateUtilB` `TimeStringTest` `TimeUtilB` | `java.sql.Date`/`Calendar`/`SimpleDateFormat` → `LocalDate`/`LocalTime`/`DateTimeFormatter` |
| file (6) | `NioRw` `PropertiesTest` `RandomAccessFileDemo` `ResourceAsStreamDemo` `TextFileReader` `TextFileWriter` | `java.io.File` → `Path`/`Files`, try-with-resources, `Files.lines()` |
| lang 함수형 (3) | `AnonymousTester` `for_each/Array` `for_each/ForEachDemo` | 익명 클래스 → 람다, 자체 제작 `Array.*` → `Function`/`Predicate`/Stream |
| lang null 처리 (3) | `void_/AvoidNullCheck` `void_/NullProof` `void_/until_not_void/*` | `Optional`, `Stream.findFirst()` |
| lang 기타 (3) | `is_in/IsIn`(+Demo) `CloneTester` `ReflectField` | `Set.of().contains()`; `clone()` → record/복사 생성자; 리플렉션 → record. `IsIn`의 `Integer` 참조 비교 버그도 수정 |
| collection (6) | `ArrayExtendTester` `ArrayInitializeTester` `ArraysSortTest` `CascadingOptionsBuilderDemo` `ListToArrayTester` `RemoveDuringIterationTest` | `List.of`, `Comparator.comparing`, record, `removeIf` |
| ood/immutable (9) | `WrapperOverCollection_*` | 도메인 5클래스 → record + `List.copyOf`. 9개 파일의 도메인 중복은 의도적으로 유지 (각 파일 독립 실행 가능해야 전략 비교가 됨) |
| ood/delegation (1) | `InstancelessDelegation` | raw `Class` → `Class<?>` |
| number/string 표현 (4) | `HumanReadable` `DecimalPoint` `FormatterFormatDemo` `MethodParamterSignatureFormatter` | `StringBuffer` → `formatted`/text block. `new Float(10.4)`(제거 예정 API) 교체 |
| test 하네스 (4) | `test/Expect` `test/Expect2` `ExpectTest` `Expect2Test` | `Objects.equals` 기반 NPE 안전화. `Expect`의 BigDecimal 메시지 버그 수정 |
| 최상위 (1) | `NpidCheck` | `IntStream` + text block |

### C. 이름이 바뀌는 파일

| modern | ← legacy | 이유 |
|---|---|---|
| `LineSeparatorConverter.java` | `lsc.java` | 소문자 클래스명 |
| `string/MethodParameterSignatureFormatter.java` | `string/MethodParamterSignatureFormatter.java` | 오타(Paramter) 수정 |
| `lang/annotation/AnnotationTarget.java` | `lang/annotation/Target.java` | `java.lang.annotation.Target`과 이름 충돌 소지 |

### D. 시리즈에 추가할 새 단계

| 신규 파일 | 보여줄 것 |
|---|---|
| `lang/enum_/EnumDemo5.java` | `switch(this)` → switch expression(`->`) |
| `collection/SequencedMapDemo.java` | `SequencedCollection`/`SequencedMap` |
| `ood/template_method/SealedTemplateMethodDemo.java` | sealed interface + permits + 패턴 매칭 switch |

## 검증 방법

```bash
javac -Xlint:all -encoding UTF-8 -d out/legacy -sourcepath legacy/java8 $(find legacy/java8 -name '*.java')
javac -Xlint:all -encoding UTF-8 -d out/modern -sourcepath .            $(find modern      -name '*.java')

java -cp out/legacy <package>.<Class>      > /tmp/l.txt
java -cp out/modern modern.java21.<package>.<Class> > /tmp/m.txt
diff /tmp/l.txt /tmp/m.txt
```

실행 시 주의: `RandomAccessFileDemo`(`raf_test.txt` 생성), `TextFileWriter`(파일 씀),
`ReturnOExitDemo`(`System.exit`), `NullProof`(`-ea` 필요), `LineSeparatorConverter`/`NpidCheck`(인자
필요).

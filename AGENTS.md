# java-snippet — AGENTS.md

## What this is
A collection of standalone Java code snippets/demos for learning. No build system — compile and run files directly with `javac`/`java`.

Two versions coexist:
- `legacy/java8/` — Original source (Java 6/7/8 style), preserved for comparison
- `modern/java21/` — Refactored target (Java 21+ features)

## 작업 규칙 (Working Rules)
- **응답 언어**: 한국어 해요체('-해요/-했어요/-예요')로 응답. 코드·식별자·명령어·로그·파일 경로는 원문 그대로
- **커밋 타이밍**: 작업 단위가 완결되어 커밋할 시점이라 판단되면 커밋할지 먼저 물어본다
- **커밋 메시지**: 한글로 작성
- **푸시**: 사용자가 명시적으로 요청할 때만 수행
- **지속적 문서화**: 단일 세션으로 모든 작업이 끝나지 않으므로 후임 세션을 위해 AGENTS.md, PRD, PLAN, DECISION 같은 파일을 지속 업데이트
  - 프로젝트 그라운드 룰
  - 무엇을 만드는지
  - 계획·결과·다음 단계
  - 결정 내용과 근거

## 작업 수행 규칙 (Work Execution Rules)

코드를 작성하기 전에 반드시 순서대로 따른다.

### 1. 계획 우선
- 코드를 작성하기 전에 먼저 계획을 텍스트로 제시한다. 계획 승인 전에는 어떤 파일도 수정하지 않는다.
- 계획에는 무엇을(What), 왜(Why), 어떤 순서로(How) 할지를 포함한다.

### 2. 아키텍처 먼저 확정
- 세부 태스크에 들어가기 전, 전체 구조(모듈 경계, 데이터 흐름, 주요 인터페이스)를 한 장으로 정리해 제시한다.
- 이 구조를 선택한 이유를 함께 밝힌다.
- 승인된 아키텍처는 이후 단계에서 임의로 변경하지 않는다. 변경이 필요하면 먼저 알리고 승인을 받는다.

### 3. 태스크 분해
- 전체 작업을 각각 하나의 세션/커밋 안에 끝낼 수 있는 크기의 단계로 쪼갠다.
- 분할 기준은 아래를 따른다:
  - 기본값: **기능 단위**로 분할한다. 각 단계가 끝나면 사람이 눈으로 확인 가능한 동작하는 결과물이 나와야 한다.
  - 예외: 공통 스키마·타입·인증처럼 다른 모든 기능이 의존하는 기반은 0단계로 먼저 **레이어 단위**로 확정한다.
- 각 단계마다 다음을 명시한다:
  - 입력 / 출력
  - 수정할 파일 목록 (이 목록 밖의 파일은 건드리지 않는다)
  - 완료 기준(Definition of Done) — 예/아니오로 판정 가능한 문장으로 쓴다 (예: "입력 X → 출력 Y", "빌드 에러 없음")

### 4. 계획 검토
- 전체 계획을 다 세운 뒤, 구현을 시작하기 전에 계획과 함께 이 계획의 리스크(무엇이 틀릴 수 있는지)를 제시하고 승인을 기다린다.
- 승인 없이 구현으로 넘어가지 않는다.

### 5. 실행 순서
- 단계 간 의존성이 없다고 명시적으로 확인되지 않는 한, 단계는 하나씩 순차적으로 진행한다.
- 병렬로 진행해도 되는지 스스로 판단하지 않는다. 병렬 가능 여부가 불확실하면 순차로 진행한다.

### 6. 단계별 테스트
- 각 단계 구현이 끝나면 정상 케이스 1개 이상, 경계/실패 케이스 1개 이상을 검증하는 테스트를 작성하고 직접 실행한다.
- 실행 로그(실제 통과/실패 결과)를 제시한다. "테스트를 통과했다"고 결과 없이 서술하지 않는다.
- 기존 테스트 스위트가 여전히 통과하는지 함께 확인한다(회귀 확인).

### 7. 단계별 사용자 확인
- 각 단계가 끝나면 코드 작성만으로 끝내지 않고, 사람이 직접 실행/확인할 수 있는 상태로 만든다.
- 문제가 발견되면 다음 단계로 넘어가지 않고 그 자리에서 고친다.

### 8. 막히면 멈춘다
- 에러나 애매한 상황을 만나면 임의로 우회하거나 스코프를 바꾸지 않는다. 즉시 멈추고 상황과 선택지를 보고한 뒤 지시를 기다린다.
- 완료 기준을 만족시키기 위해 하드코딩, 조건 우회, 목업으로 대체하는 방식을 쓰지 않는다.

### 9. 기록과 커밋
- 각 단계가 끝나면 무엇을 했고 무엇이 남았는지 한두 문단으로 요약해 문서(설계 문서, 체크리스트, 커밋 메시지)에 남긴다. 대화창에만 남기지 않는다.
- 단계 완료마다 작은 단위로 커밋한다.
- 전체 계획은 체크리스트 형태로 관리하고, 매 단계 완료 시 표시를 갱신한다.

### 10. 컨텍스트 관리
- 아래 신호가 나타나면 즉시 알린다: 이미 결정한 내용을 다시 묻는 경우, 앞서 쓰던 용어와 다르게 말하는 경우, 이미 정정된 사실이 문서에는 틀린 채 남아 있는 경우.
- 위 신호가 나타나거나 한 단계가 끝나면, 컨텍스트가 무거워지기 전에 새 세션으로 넘어갈 것을 먼저 제안한다.
- 새 세션이 이어받을 수 있도록, 인수인계에 필요한 내용(진행 상황, 남은 작업, 결정 사항)을 문서에 정리해 둔다.

## Project Structure
```
legacy/java8/          # Original source (preserved)
  collection/
  datetime/
  file/
  lang/
  number/
  ood/
  string/
  test/
  *.java (root level)

modern/java21/         # Refactored target (Java 21+)
  collection/
  datetime/
  file/
  lang/
  number/
  ood/
  string/
  test/
  *.java (root level)

legacy/java8/README.md   # Original README (GitHub links point to legacy)
README.md                # This project's overview (new)
```

## Build & Run
```bash
# Compile all (separate outputs)
mkdir -p out/legacy out/modern
javac -d out/legacy  $(find legacy/java8 -name "*.java")
javac -d out/modern  $(find modern/java21 -name "*.java")

# Run legacy (original package names)
java -cp out/legacy lang.enum_.EnumDemo0

# Run modern (modern.java21.* packages)
java -cp out/modern modern.java21.lang.enum_.EnumDemo0
```

## Refactoring Plan (Modernization to Java 21)

### Phase 1: Date/Time API (datetime/)
- `DateUtil.java`, `DateUtilB.java`, `DateStringTest.java`, `DateDiff.java`, `TimeUtilB.java`, `TimeStringTest.java`
- Replace `Calendar`/`SimpleDateFormat`/`Date` → `LocalDate`/`LocalTime`/`LocalDateTime`/`DateTimeFormatter`/`TemporalAdjusters`/`ChronoUnit`/`Period`/`Duration`

### Phase 2: File I/O (file/)
- `NioRw.java`, `ResourceAsStreamDemo.java`, `TextFileReader.java`, `TextFileWriter.java`, `RandomAccessFileDemo.java`, `PropertiesTest.java`
- Replace manual `FileChannel`/`ByteBuffer`/explicit `close()` → `Files.readAllBytes`/`Files.write`/`Files.newBufferedReader`/`Files.readString`/`Files.writeString`/try-with-resources

### Phase 3: Collections & Streams (collection/)
- `RemoveDuringIterationTest.java`, `ArrayInitializeTester.java`, `ArrayExtendTester.java`, `ArraysSortTest.java`, `ListToArrayTester.java`, `OrderedKeyValPairsDemo*.java`, `LinkedHashMapDemo.java`, `CascadingOptionsBuilderDemo.java`
- Use `removeIf`, `stream().filter().collect()`, `List.of`, `toArray(IntFunction)`, `Comparator.comparing`, `Map.ofEntries`

### Phase 4: Language Features (lang/ except enum_)
- `AnonymousTester.java`, `CloneTester.java`, `NullProof.java`, `AvoidNullCheck.java`, `VarArgsDemo.java`, `ReflectField.java`, `BoundedWildcard.java`, `is_in/`, `void_/`, `inner_class/`
- Lambdas, method references, `record`, `Optional`, `StringBuilder`, `Objects.requireNonNullElse`, pattern matching, `switch` expressions

### Phase 5: OOD Patterns (ood/)
- `immutable/WrapperOverCollection_*.java`, `template_method/*.java`, `delegation/InstancelessDelegation.java`
- `record`, `List.copyOf`/`Map.copyOf`/`Collections.unmodifiableList`, `sealed` classes, interface default methods

### Phase 6: String, Number, Root Utilities
- `string/*.java`, `number/*.java`, `lsc.java`, `NpidCheck.java`
- Text blocks, `switch` expressions, pattern matching, `StandardCharsets`

### ⚠️ DO NOT MODIFY
- `lang/enum_/EnumDemo0~4.java` — Educational progression: int constants → basic enum → enum with fields/methods → switch enum → constant-specific methods

## Key Conventions
- **legacy/java8/**: Keep original package declarations (e.g., `package lang.enum_;`) — compile to `out/legacy`
- **modern/java21/**: Update package declarations to `modern.java21.*` — compile to `out/modern`
- Korean comments/docs in README.md and _list.txt
- Files with `Demo`/`Test` suffix typically have `main()` for direct execution
- Test files (ExpectTest, Expect2Test) use custom assertion helpers in `test/`

## Gotchas
- No build tool, no dependency management — all standard library
- Some files reference missing demos (e.g., `demo/HttpClientGetTester.java` not present)
- `Expect.java`/`Expect2.java` are simple assertion helpers, not JUnit
- Resource files (e.g., `file/cfg/foo.txt`) must be accessed from correct working directory
- Legacy and modern must compile to separate output dirs to avoid class collisions
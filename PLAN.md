# java-snippet 리팩토링 계획 (Modernization to Java 21)

## 프로젝트 개요
- **목적**: `legacy/java8/` (Java 6/7/8 스타일) → `modern/java21/` (Java 21+ 기능) 리팩토링
- **빌드 시스템 없음**: `javac`/`java`로 직접 컴파일·실행
- **두 버전 공존**: legacy(원본 보존) / modern(리팩토링 대상)

---

## 단계별 계획 (Phases)

| 단계 | 영역 | 대상 패키지 | 주요 변경 내용 | 상태 |
|------|------|-------------|----------------|------|
| **Phase 1** | Date/Time API | `datetime/` | `Calendar`/`SimpleDateFormat`/`Date` → `LocalDate`/`LocalTime`/`LocalDateTime`/`DateTimeFormatter`/`TemporalAdjusters`/`ChronoUnit`/`Period`/`Duration` | ✅ **완료** |
| **Phase 2** | File I/O | `file/` | 수동 `FileChannel`/`ByteBuffer`/명시적 `close()` → `Files.readAllBytes`/`Files.write`/`Files.newBufferedReader`/`Files.readString`/`Files.writeString`/try-with-resources | ✅ **완료** |
| **Phase 3** | Collections & Streams | `collection/` | `removeIf`, `stream().filter().collect()`, `List.of`, `toArray(IntFunction)`, `Comparator.comparing`, `Map.ofEntries` | ⬜ 대기 |
| **Phase 4** | Language Features | `lang/` (단, `enum_` 제외) | Lambdas, method references, `record`, `Optional`, `StringBuilder`, `Objects.requireNonNullElse`, pattern matching, `switch` expressions | ⬜ 대기 |
| **Phase 5** | OOD Patterns | `ood/` | `record`, `List.copyOf`/`Map.copyOf`/`Collections.unmodifiableList`, `sealed` classes, interface default methods | ⬜ 대기 |
| **Phase 6** | String, Number, Root Utilities | `string/`, `number/`, 루트 파일들 | Text blocks, `switch` expressions, pattern matching, `StandardCharsets` | ⬜ 대기 |

---

## ⚠️ 수정 금지 파일 (교육용 진행 과정 보존)
`lang/enum_/EnumDemo0~4.java` — int constants → basic enum → enum with fields/methods → switch enum → constant-specific methods

---

## Phase 1 상세: Date/Time API (완료)

### 대상 파일 (6개)
| 파일 | Legacy → Modern 주요 변경 |
|------|---------------------------|
| `DateUtil.java` | `Calendar`/`SimpleDateFormat` → `LocalDate`/`DateTimeFormatter` |
| `DateUtilB.java` | 동일 |
| `DateStringTest.java` | `SimpleDateFormat` → `DateTimeFormatter` |
| `DateDiff.java` | `Calendar` 날짜 차이 → `ChronoUnit.DAYS.between` / `Period` |
| `TimeUtilB.java` | `Calendar`/`SimpleDateFormat` → `LocalTime`/`DateTimeFormatter` |
| `TimeStringTest.java` | `SimpleDateFormat` → `DateTimeFormatter` |

### 검증 완료 사항
- [x] `modern/java21/datetime/*.java` 모두 컴파일 성공
- [x] 각 클래스의 `main()` 메서드 실행 테스트 통과
- [x] Legacy 버전과 출력 결과 일치 확인

---

## Phase 2 상세: File I/O (완료)

### 대상 파일 (6개)
| 파일 | Legacy → Modern 주요 변경 |
|------|---------------------------|
| `NioRw.java` | `FileChannel`/`ByteBuffer` 수동 루프 → `Files.readAllBytes`/`Files.write`/`Files.readString`/`Files.writeString` + try-with-resources |
| `TextFileReader.java` | `BufferedReader`/`FileReader` 수동 open/close → `Files.newBufferedReader` + try-with-resources |
| `TextFileWriter.java` | `BufferedWriter`/`FileWriter` 수동 open/close → `Files.newBufferedWriter` + try-with-resources |
| `ResourceAsStreamDemo.java` | 수동 `InputStreamReader`/`BufferedReader` close → try-with-resources |
| `RandomAccessFileDemo.java` | `RandomAccessFile` → `Files.newByteChannel` + `SeekableByteChannel` + try-with-resources |
| `PropertiesTest.java` | `FileInputStream`/`FileOutputStream` → `Files.newInputStream`/`Files.newOutputStream` + try-with-resources |

### 검증 완료 사항
- [x] `modern/java21/file/*.java` 모두 컴파일 성공
- [x] 각 클래스의 `main()` 실행 시 에러 없음
- [x] Legacy 버전과 동일 기능 동작 확인 (파일 읽기/쓰기/복사/seek/Properties 테스트)
- [x] try-with-resources로 리소스 자동 해제 검증

---

## Phase 3~6: 상세 계획 (추후 세분화)

> Phase 2 완료 후 각 단계별로 별도 세부 계획 수립 예정

---

## 작업 규칙 (AGENTS.md 준수)

1. **계획 우선**: 코드 작성 전 텍스트 계획 제시 → 승인 후 진행
2. **아키텍처 먼저**: 전체 구조(모듈 경계, 데이터 흐름) 확정 후 세부 진입
3. **태스크 분해**: 기능 단위로 분할, 각 단계 완료 시 확인 가능한 결과물 산출
4. **단계별 테스트**: 정상 케이스 1개 + 경계/실패 케이스 1개 이상 검증, 실행 로그 제시
5. **단계별 사용자 확인**: 사람이 직접 실행/확인 가능 상태에서 다음 단계 진행
6. **막히면 멈춤**: 에러/애매한 상황 시 임의 우회 금지 → 보고 후 지시 대기
7. **기록과 커밋**: 단계 완료 시 요약 문서화 + 작은 단위 커밋
8. **컨텍스트 관리**: 신호(반복 질문, 용어 변경, 문서 불일치) 감지 시 새 세션 제안

---

## 빌드·실행 명령어

```bash
# 컴파일 (분리된 출력 디렉토리)
mkdir -p out/legacy out/modern
javac -d out/legacy  $(find legacy/java8 -name "*.java")
javac -d out/modern  $(find modern/java21 -name "*.java")

# 실행 예시
java -cp out/legacy datetime.DateUtil          # Legacy
java -cp out/modern modern.java21.datetime.DateUtil  # Modern
```

---

## 진행 현황 요약

- **완료**: Phase 1 (datetime/ — 6개 파일), Phase 2 (file/ — 6개 파일)
- **진행 예정**: Phase 3 (collection/ — 8개 파일)
- **대기**: Phase 4~6

> 마지막 업데이트: 2026-09-02  
> 다음 세션 인수인계: Phase 3 Collections & Streams 리팩토링부터 시작
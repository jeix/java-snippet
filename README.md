# java-snippet

Java 코드 스니펫/데모 모음 — 학습용. 빌드 시스템 없이 `javac`/`java`로 직접 컴파일·실행.

두 버전 공존:
- **legacy/java8/** — 원본 소스 (Java 6/7/8 스타일), 비교·참조용 보존
- **modern/java21/** — 리팩토링 대상 (Java 21+ 기능 적용)

## 프로젝트 구조
```
legacy/java8/          # 원본 소스 (패키지 선언 유지: lang.enum_ 등)
  collection/
  datetime/
  file/
  lang/
  number/
  ood/
  string/
  test/
  *.java (루트 레벨)

modern/java21/         # 모던화 대상 (패키지: modern.java21.*)
  collection/
  datetime/
  file/
  lang/
  number/
  ood/
  string/
  test/
  *.java (루트 레벨)
```

## 빌드 및 실행

```bash
# 전체 컴파일 (출력 분리)
mkdir -p out/legacy out/modern
javac -d out/legacy  $(find legacy/java8 -name "*.java")
javac -d out/modern  $(find modern/java21 -name "*.java")

# Legacy 실행 (원본 패키지명)
java -cp out/legacy lang.enum_.EnumDemo0

# Modern 실행 (modern.java21.* 패키지)
java -cp out/modern modern.java21.lang.enum_.EnumDemo0
```

## 리팩토링 진행 현황

| 단계 | 영역 | 상태 | 대상 파일 |
|------|------|------|-----------|
| 1 | Date/Time API | ⏳ 대기 | datetime/*.java |
| 2 | File I/O | ⏳ 대기 | file/*.java |
| 3 | Collections & Streams | ⏳ 대기 | collection/*.java |
| 4 | Language Features | ⏳ 대기 | lang/* (enum_ 제외) |
| 5 | OOD Patterns | ⏳ 대기 | ood/*.java |
| 6 | String, Number, Root | ⏳ 대기 | string/, number/, lsc.java, NpidCheck.java |

## ⚠️ 수정 금지 (교육용 진행 과정 보존)
- `lang/enum_/EnumDemo0~4.java` — int 상수 → 기본 enum → 필드/메서드 enum → switch enum → 상수별 메서드 enum 진화 과정

## 문서
- [Legacy README](legacy/java8/README.md) — 원본 상세 설명 (GitHub 링크는 legacy 경로)
- [AGENTS.md](AGENTS.md) — 작업 규칙, 리팩토링 계획, 실행 가이드
- [_list.txt](_list.txt) — 스니펫 인덱스 (한국어)

## 참고
- 빌드 도구 없음, 의존성 없음 — 표준 라이브러리만 사용
- `Expect.java`/`Expect2.java`는 심플 어설션 헬퍼 (JUnit 아님)
- 리소스 파일(`file/cfg/foo.txt`)은 올바른 작업 디렉토리에서 접근 필요
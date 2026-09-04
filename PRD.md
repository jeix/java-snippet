# Java Snippet Java 21 Modernization

## 목표

기존 Java 예제를 Java 8 기준선과 Java 21 현대화 버전으로 분리한다. 원본의 교육적 맥락과 단계별 개선 과정을 보존하면서, Java 21에서 실질적으로 더 나은 API와 표현을 사용할 수 있는 예제를 현대화한다.

## 결과 구조

```text
README.md                 프로젝트 전체 안내
legacy/java8/             기존 소스, 자료와 기존 README.md
modern/java21/            modern.java21 하위 패키지의 Java 21 소스
modern/java21/README.md   파일·묶음별 유지/변경/추가 기록
```

## 요구사항

1. `modern-codex` 브랜치에서 작업한다. 브랜치가 없으면 `master`에서 생성한다.
2. `origin/modern-opencode`는 참고하지 않는다.
3. `master` 기준의 EUC-KR 계열 `refactoring_a_case.txt`를 UTF-8로 변환하고 내용을 검증한다.
4. `master` 기준 소스와 실행 자료 전체를 `legacy/java8/`에 보존한다.
5. `master` 기준 `README.md`는 레거시 설명이므로 `legacy/java8/README.md`로 이동한다.
6. 새 루트 `README.md`에서 프로젝트 목적, legacy/modern 구조와 빌드·실행법을 설명한다.
7. 모든 Java 21 소스는 `modern/java21/` 아래로 옮기고 `modern.java21` 하위 package를 사용한다.
8. 같은 패키지의 참조, static import, 자료 경로와 실행 관계를 함께 수정한다.
9. 기존 예제의 학습 목적을 훼손하는 일괄 리팩터링을 하지 않는다.
10. 단계형 예제는 기존 단계를 유지하고, 명확한 교육적 가치가 있는 경우 Java 21 후속 단계를 새 파일로 추가한다.
11. 유지한 파일의 이유, 변경한 파일의 변경 방향, 추가한 파일과 기존 묶음의 관계를 문서화한다.

## 주요 보존 묶음

- `lang/enum_/EnumDemo0.java` ~ `EnumDemo4.java`
- `ood/immutable/WrapperOverCollection_*.java`
- `ood/template_method/*.java`
- `collection/OrderedKeyValPairsDemo*.java`, `LinkedHashMapDemo.java`
- `test/Expect.java`, `Expect2.java`와 루트의 대응 테스트
- 문자열 split 및 replace 비교 예제군

파일·묶음별 유지/변경/추가 판단과 구현 결과는 `modern/java21/README.md`에서 관리한다.

## 완료 조건

- legacy와 modern 트리에 필요한 소스와 자료가 누락 없이 존재한다.
- `refactoring_a_case.txt`가 유효한 UTF-8이다.
- legacy가 Java 8 대상으로, modern이 Java 21 대상으로 각각 컴파일된다.
- 주요 실행 예제의 의도된 동작이 유지된다.
- 루트 및 modern README에서 구조와 파일별 판단을 추적할 수 있다.

2026-09-04 최종 검증에서 위 조건을 모두 충족했다. legacy 원본 자료·소스 87개가 modern 기준 트리에 대응하고 후속 Java 예제 7개만 추가되었으며, legacy Java 8 및 modern Java 21 전체 컴파일과 대표 실행 검증을 통과했다.

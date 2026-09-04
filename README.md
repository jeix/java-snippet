# Java Snippet

작은 Java 예제를 원본 Java 8 기준선과 Java 21 현대화 버전으로 함께 제공한다. 단순히 최신 문법으로 일괄 치환하지 않고, 특정 주제의 실패·개선 과정을 보여주는 예제는 기존 단계를 보존한다.

## 디렉토리

```text
legacy/java8/             Java 8 기준의 기존 예제와 자료
legacy/java8/README.md    기존 프로젝트 설명
modern/java21/            modern.java21 하위 package의 Java 21 예제
modern/java21/README.md   파일·묶음별 유지, 변경과 후속 추가 기록
```

`refactoring_a_case.txt`는 두 트리 모두 UTF-8과 LF를 사용한다. 현대화 판단의 기준은 `master`에 있던 원본이며 `origin/modern-opencode`는 참고하지 않았다.

## 요구 환경

- legacy 컴파일: JDK 8 이상
- modern 컴파일 및 실행: JDK 21
- 별도 build system이나 외부 dependency 없음

## 컴파일

저장소 루트에서 다음과 같이 직접 컴파일할 수 있다. class 파일은 작업 트리 밖에 생성한다.

```sh
mkdir -p /tmp/java-snippet-legacy
find legacy/java8 -name '*.java' -print0 \
  | xargs -0 javac --release 8 -encoding UTF-8 -d /tmp/java-snippet-legacy

mkdir -p /tmp/java-snippet-modern
find modern/java21 -name '*.java' -print0 \
  | xargs -0 javac --release 21 -encoding UTF-8 -Xlint:all -d /tmp/java-snippet-modern
```

일부 소스는 raw type, static dispatch, 잘못된 override 같은 동작을 의도적으로 보여주므로 `-Xlint:all` 경고가 남아 있다.

## 실행

legacy와 modern은 package가 다르다.

```sh
java -cp /tmp/java-snippet-legacy ExpectTest
java -cp /tmp/java-snippet-modern modern.java21.ExpectTest

java -cp /tmp/java-snippet-legacy lang.enum_.EnumDemo4
java -cp /tmp/java-snippet-modern modern.java21.lang.enum_.EnumDemo5
```

`ResourceAsStreamDemo`처럼 classpath resource가 필요한 예제는 컴파일 후 자료도 class 출력 경로에 복사한다.

```sh
mkdir -p /tmp/java-snippet-modern/modern/java21/file/cfg
cp modern/java21/file/cfg/foo.txt \
  /tmp/java-snippet-modern/modern/java21/file/cfg/foo.txt
java -cp /tmp/java-snippet-modern modern.java21.file.ResourceAsStreamDemo
```

각 예제의 원래 설명은 [legacy/java8/README.md](legacy/java8/README.md), 파일별 현대화 여부와 보존 이유는 [modern/java21/README.md](modern/java21/README.md)에서 확인할 수 있다.

## 주요 Java 21 후속 예제

- `collection/RemoveDuringIterationModern.java`: bulk removal과 stream filtering
- `lang/LambdaTester.java`: 익명 클래스 다음 단계의 lambda와 method reference
- `lang/CopyTester.java`: `Object.clone()` 다음 단계의 immutable record와 명시적 copy
- `lang/enum_/EnumDemo5.java`: enum 상태와 exhaustive switch expression
- `lang/for_each/StreamDemo.java`: 사용자 정의 iteration helper와 표준 stream 비교
- `ood/delegation/TypeSafeDelegation.java`: reflection 대신 functional interface delegation
- `ood/immutable/WrapperOverCollection_5_Record.java`: record와 immutable collection snapshot

## 문서

- [PRD.md](PRD.md): 목표, 범위와 요구사항
- [PLAN.md](PLAN.md): 단계별 진행 및 검증 결과
- [DECISION.md](DECISION.md): 주요 결정과 대안
- [AGENTS.md](AGENTS.md): contributor 작업 규칙

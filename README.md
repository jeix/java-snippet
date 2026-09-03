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

## 진행 상황

모던화는 영역별로 나눠서 진행 중이다.

| 영역 | 상태 |
|---|---|
| datetime | 완료 |
| file | 완료 |
| lang | 완료 |
| collection | 완료 |
| ood | 진행 예정 |
| number/string | 진행 예정 |
| test 하네스 + 최상위 | 진행 예정 |
| 시리즈 새 단계 추가 (EnumDemo5 등) | 진행 예정 |

전체가 끝나면 이 표 대신 legacy↔modern 파일별 대응표로 바꾼다. 지금까지의 단계별 실행 기록은
[PLAN.md](PLAN.md)의 체크리스트를 참고.

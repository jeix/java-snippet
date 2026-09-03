# 결정 기록

append-only. 번호를 매겨 쌓는다. 이미 적은 항목은 고치지 않고, 뒤집을 때는 새 번호로 덧붙인다.
전체 배경과 실행 단계는 [PLAN.md](PLAN.md) 참고.

## D1. 이전 시도(modern-opencode 브랜치)는 참고하지 않는다

같은 저장소를 다른 도구로 모던화했던 이전 시도가 `modern-opencode` 브랜치(883a908)에 보존돼 있지만,
이번 작업은 그 결과물을 참고하지 않고 `modern-claude` 브랜치(master=37978fe에서 분기)에서 처음부터
새로 진행한다. 사용자가 명시적으로 참고하지 말라고 지시했다.

## D2. legacy 소스는 package 선언을 원본 그대로 유지한다

`legacy/java8/collection/Foo.java`의 package 선언은 `package collection;` 그대로 둔다.
바꾸지 않는 이유: 원본 보존이 이 디렉토리의 유일한 목적이고, 어떤 변경도 diff를 만들어 "완전한
스냅샷"이라는 가치를 깎는다. 대신 컴파일 시 `legacy/java8`를 소스 루트(`-sourcepath`)로 잡아
package 구조와 디렉토리 구조를 일치시킨다.

## D3. 시리즈 파일은 일괄 모던화하지 않고, 필요할 때만 새 단계를 추가한다

`lang/enum_/EnumDemo0~4`, `ood/template_method/*`, `ood/immutable/WrapperOverCollection_*`,
`collection/OrderedKeyValPairsDemo`류처럼 같은 주제를 점진적으로 발전시키는 시리즈는 각 단계를
원형 그대로 두고, Java 21에서 정말로 새로운 답이 생긴 경우에만 마지막에 새 파일(`EnumDemo5` 등)을
추가한다.
이유: 이 저장소의 편집 철학 자체가 "점진적·반복적 개선 과정을 보여주는 것"
(`legacy/java8/refactoring_a_case.txt`의 "gradual, iterative, evolving coding")이다. 각 단계는
"일부러 낡게 박제해 둔 코드"가 아니라 작성 당시 기준으로는 그 나름대로 최신에 가까웠던 코드이고,
있었다면 의도는 "이렇게 쓰던 건 이렇게 바꿔라"는 개선 흐름을 보여주는 쪽에 가깝다. 중간 단계를
고치면 그 개선 흐름(무엇이 어떻게 나아졌는지) 자체가 사라진다.

## D4. 모던화하지 않는 파일에는 유지 사유를 헤더 주석으로 남긴다

원형을 유지하는 modern 쪽 파일은 package 선언 바로 아래에 `// 원형 유지: <이유>` 로 시작하는 주석을
남긴다(첫 줄은 항상 이 접두어로 고정해서 grep 가능하게 한다). 같은 시리즈에 Java 21 답이 별도
파일로 있으면 `// Java 21 판: <경로>` 줄을 이어 붙인다. legacy 쪽에는 이 주석을 넣지 않는다 —
legacy는 원본 스냅샷이라 문서 3개(README.md, `_list.txt`, `refactoring_a_case.txt`) 외에는
무변경이 원칙이다.
이유: "아직 손 안 댄 파일"과 "모던화하지 않기로 판단한 파일"을 구분할 수 있어야, 나중에 이어서
작업하는 사람(또는 세션)이 판단을 다시 하지 않아도 된다.

## D5. legacy 이동과 modern 이동을 서로 다른 커밋으로 분리한다

`legacy/java8/`로의 이동(커밋 `c1dae7d`)과 `modern/java21/`로의 이동(커밋 `eb1f74a`)은 각각
별도 커밋이고, `modern/java21/` 이동 커밋에서는 패키지 선언 변경 외의 내용 변경을 하지 않았다.
실제 모던화(코드 내용 변경)는 그 다음부터 영역별 커밋으로 나눠 진행한다.
이유: 나중에 diff를 볼 때 "구조가 바뀐 것"과 "내용이 바뀐 것"을 구분해서 읽을 수 있어야 한다.

## D6. 검증 기준: legacy와 modern의 실행 결과가 최대한 같아야 한다

각 영역 모던화 후에는 legacy와 modern 양쪽을 컴파일하고 실행해서 출력을 대조한다. 모던화로 인해
불가피하게 출력이 달라지는 경우(레거시 API 구조 자체가 바뀌는 경우 등)는 왜 다른지 커밋 메시지에
남긴다. `System.err`로 실패를 보고하는 assertion 스타일 파일(`DateUtil.test_oneday()` 등)은
stderr가 비어야 통과로 본다.

## D7. `ResourceAsStreamDemo`의 하드코딩 경로는 절대/클래스패스-루트 상대 경로만 고친다

`file/ResourceAsStreamDemo.java`가 참조하는 `"/file/cfg/foo.txt"`류 경로 중, 패키지-상대 경로
(`"cfg/foo.txt"`)는 그대로 두고, 절대 경로(`"/file/cfg/foo.txt"`)와 클래스패스-루트 상대 경로
(`"file/cfg/foo.txt"`)만 `"modern/java21/file/cfg/foo.txt"`로 바꿨다.
이유: 패키지-상대 경로는 클래스의 새 패키지 위치(`modern.java21.file`)에 따라 컴파일러가 자동으로
올바른 경로를 계산하므로 손댈 필요가 없다. 나머지 두 형태만 새 위치에 맞게 고쳐야 이 데모가 원래
보여주려던 "절대 vs 상대, `Class` vs `ClassLoader`" 4×2 성공/실패 매트릭스가 새 위치에서도 legacy와
동일하게 재현된다. 실제로 legacy와 modern 실행 결과를 1~6번 케이스 전부 대조해서 확인했다.

## D8. datetime: `java.util.Date`+`java.sql.Date`(`Time`) 이원 구조를 `LocalDate`(`LocalTime`)
     하나로 합친다

`DateStringTest`/`TimeStringTest`는 원래 "포맷/파싱 가능한 `java.util.Date`"와 "`yyyy-MM-dd`(또는
`HH:mm:ss`) 문자열로 자연스럽게 표시되는 `java.sql.Date`(`Time`)" 두 타입을 나란히 비교하는 데모였다.
`java.time.LocalDate`(`LocalTime`)는 이 두 타입의 역할을 모두 갖고 있어서 굳이 나눌 이유가 없다.
그 결과 "변환 데모" 부분의 출력 라벨과 줄 수가 legacy와 달라진다(날짜/시간 산술 오프셋 계산 부분은
legacy와 완전히 동일). 레거시 API 자체가 주제였던 이 이원 구조가 modern에서는 자연히 사라지는 것으로
보고, 되살리지 않기로 했다.

## D9. `DateUtilB`/`TimeUtilB`의 nested `Period` 클래스는 이름을 그대로 둔다

`DateUtilB.Period`가 `java.time.Period`와 이름이 겹치지만, 이 파일에서 `java.time.Period` 타입을
import하거나 사용하지 않으므로 실제 컴파일 충돌이 없다. 원본 이름을 보존하는 쪽을 우선했다.

## D10. `DateUtilB`/`TimeUtilB` 테스트 픽스처는 `LocalDate.of`/`LocalTime.of` 대신 문자열 파싱을
      쓴다

`test_march_31()`의 기준 날짜, `test_twelve_forty()`의 기준 시각을 `LocalDate.parse("2011-03-31")`,
`LocalTime.parse("12:40:05")`처럼 문자열을 파싱해서 만든다(원래는 `LocalDate.of(2011, 3, 31)`,
`LocalTime.of(12, 40, 5)`였다). 사용자 지적을 반영: `DateUtilB`/`TimeUtilB` 자체가 문자열 기반
날짜/시간 유틸리티를 보여주는 파일이라, 테스트 픽스처도 문자열 값을 받아 처리하는 모습으로
일관되게 두는 게 자연스럽다.

## D11. legacy 코드를 "일부러 낡게 보여주는 교재"로 프레이밍하지 않는다

PLAN.md 초안과 D3에서 legacy의 시리즈/개별 파일들을 "낡은 코드를 일부러 보여주는 교재"·"안티패턴"
같은 말로 설명했는데, 사용자가 정정했다: 작성 당시엔 그 정도가 첨단에서 살짝 뒤처진 수준이었을
뿐이고, 의도가 있었다면 "이렇게 쓰던 건 이렇게 바꿔라"는 개선 흐름을 보여주려는 것에 가깝다.
실행 계획(시리즈 원형 유지, 새 단계는 필요할 때만 추가)은 바뀌지 않지만, 그 이유를 설명하는 언어는
"일부러 보존한 박제"가 아니라 "당시 기준으로 자연스러웠던 단계이며, 이후 단계와의 개선 흐름을
비교하는 게 목적"으로 쓴다. `// 원형 유지:` 주석과 문서에서 "안티패턴"·"일부러" 같은 표현은
피한다.

## D12. 루트 `README.md`는 6단계까지 미루지 않고 지금 작성해서 틈틈이 갱신한다

원래 계획(PLAN.md 6단계)은 모든 영역 모던화가 끝난 뒤 대응표까지 완성해서 README.md를 한 번에
쓰는 것이었다. 사용자 요청으로 지금 초안을 만들고, 이후 영역별 모던화 커밋마다 진행 상황 표를
갱신하기로 했다. 완전한 legacy↔modern 대응표는 여전히 6단계에서 마무리한다.

## D13. file 영역: 저수준 API가 주제인 파일은 그 API 자체를 유지하고 자원 관리만 모던화한다

`NioRw`(FileChannel/ByteBuffer 저수준 NIO)와 `RandomAccessFileDemo`(seek·파일 포인터 추적)는
API 자체가 주제라서 `Files.readAllBytes()` 같은 상위 레벨 API로 뭉뚱그리지 않았다. 대신:
- `NioRw`: `File`+`FileInputStream/RandomAccessFile`로 채널을 여는 부분만
  `FileChannel.open(Path, StandardOpenOption...)` + try-with-resources로 교체했다. 버퍼 연산
  로직(direct `ByteBuffer`, mapped read/write)은 그대로 뒀다.
- `RandomAccessFileDemo`: `RandomAccessFile`을 `FileChannel`로 바꿨다. `channel.position(long)`/
  `channel.position()`이 `raf.seek()`/`raf.getFilePointer()`와 동일한 의미론을 가지므로 출력이
  legacy와 완전히 일치한다(대조 완료).
`TextFileReader`/`TextFileWriter`(단순 텍스트 파일 읽고 쓰기)는 주제가 API가 아니라 "파일 다루는
법"이라, `Files.lines()`/`Files.newBufferedWriter()` 기반으로 더 크게 손봤다. 두 파일 다 문자
인코딩을 플랫폼 기본값 대신 명시적 UTF-8로 지정했다 — 이건 동작이 달라질 수 있는 변경이지만
(플랫폼 기본 인코딩에 의존하던 잠재적 지뢰를 없앤 것), Java 9+ 권장 관행이라 반영했다.
`PropertiesTest`는 `Properties` API는 그대로 두고 `File`/`FileInputStream`/`FileWriter`만
`Path`/`Files`로 교체했다.
검증: 6개 파일 전부 legacy와 modern을 같은 임시 디렉터리 구조에서 실행해 대조했다 —
`RandomAccessFileDemo` 표준출력 완전 일치, `TextFileWriter`+`TextFileReader` 라운드트립 결과
파일 바이트 동일, `PropertiesTest`는 최초 생성 경로와 기존 파일 로드 경로 둘 다 파일 내용 동일,
`NioRw`는 6가지 모드(`b`/`bm`/`t`/`tm`/`tu`/`tum`) 전부 결과 파일 바이트 동일.

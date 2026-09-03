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

## D14. lang 영역: 익명 클래스는 함수형 인터페이스를 구현할 때만 람다로 바꾼다

`AnonymousTester`는 같은 인터페이스(`FooHoo`)를 인터페이스 구현/추상 클래스 상속/구체 클래스
상속 세 가지 방식의 익명 클래스로 만드는 데모다. 람다는 함수형 인터페이스만 구현할 수 있고
클래스를 상속할 수는 없어서, 인터페이스 구현 케이스만 람다로 바꾸고 나머지 둘은 익명 클래스로
남기며 그 이유를 인라인 주석으로 남겼다. `lang/for_each/Array.java`(자체 제작
`ForEach`/`Filter`/`Map`/`Reduce` 함수형 인터페이스 + `Converter`/`Reducer`)는 표준
`Stream` API가 이미 제공하는 것과 정확히 같은 일을 하고 있어서 파일 자체를 삭제하고
`ForEachDemo`를 `Stream`/`List#replaceAll`/`List#reversed()`(Java 21 SequencedCollection)로
다시 썼다. `for_each` 삭제와 재작성 모두 legacy와 정확히 같은 출력을 냈다(대조 완료).

## D15. lang 영역: 필드를 리플렉션으로 덮어써야 하는 데모는 record로 바꾸지 않는다

`ReflectField`(필드 값을 뒤집어 쓰는 데모)와 `AnnotationTarget`(어노테이션이 붙은 필드 값을
리플렉션으로 덮어쓰는 데모, 이전 이름 `Target`)은 최초 조사에서 "record 후보"로 분류됐지만,
record는 모든 필드가 final이라 `Field#set()`으로 값을 바꿀 수 없다 — 두 데모 모두 값을 바꾸는
것 자체가 주제라 record로 바꾸면 데모가 성립하지 않는다. 그래서 두 클래스는 그대로 두고,
`ReflectField`에만 구조가 같은 `record FooRecord`를 추가해서 `RecordComponent`(Java 16+)로
필드 대신 컴포넌트를 읽는 것만 보여주는 `test_record_components()`를 덧붙였다(기존 출력 뒤에
추가되는 형태라 legacy 대비 출력이 늘어나는 것은 의도된 차이).

## D16. lang 영역: `is_in`/`isin`의 `Integer`/`Long` 값 비교 버그를 고치며 CSV 부분일치 버그도
      함께 고친다

`lang/is_in/IsIn.java`에는 두 세대의 구현이 함께 있었다 — 좁은 범위의 `is_in(...)`(String/Integer
전용)과 이를 일반화한 `isin(...)`(String/Integer/Long/Float/Double, 내부적으로 하나의
`isin(Object, Object...)` 엔진에 위임). 계획에서 이미 알려진 `Integer i == arg` 참조 비교
버그(캐시 범위 밖 값에서 값이 같아도 `false`가 될 수 있음) 외에, 조사 중 CSV 문자열을
`String#contains()`로 부분 일치시키는 버그도 발견했다(`is_in(1, "10,20,100")`가 "1"이
"10"/"100"의 부분 문자열이라 잘못 `true`가 될 수 있음). 둘 다 진짜 정확성 버그이지 "그 시절의
작성법"이 아니라서 legacy처럼 보존하지 않고 modern에서 고쳤다: CSV는 `String#split(",")` 후
`Set#contains()`로 정확히 토큰 단위 비교하고, 값 비교는 모든 경우에 `Object#equals()`를 쓴다
(`Float`/`Double`도 원래 `.compare()`로 특별 취급했지만 `Float#equals()`/`Double#equals()`가
내부적으로 같은 비트 비교를 하므로 불필요했다 — `isin(Object, Object...)` 엔진의 타입별 분기가
전부 `arg.equals(x)` 한 줄로 줄었다). `IsInDemo`의 모든 검증값이 legacy와 동일하게 통과하는
것으로 확인했다(이 버그들은 데모가 쓰는 값 범위에서는 우연히 드러나지 않는다).

## D17. `CloneTester`: `Cloneable`/`clone()` 대신 복사 생성자를 쓴다

`Foo`/`Bar`는 값을 바꿔도 원본에 영향이 없어야 한다는 걸 보여주는 게 목적이라 불변(record)으로는
바꿀 수 없지만, `clone()`+`Cloneable`+`CloneNotSupportedException` 처리는 Effective Java가
권고하지 않는 낡은 패턴이다. `private` 복사 생성자 + `copy()` 팩토리 메서드로 바꿨다 — 깊은 복사
의미론(내부 `Bar` 리스트도 각각 복사)은 그대로 유지했다. legacy와 동일하게 OOPS 로그 없이
통과하는 것으로 확인했다.

## D18. `UntilNotVoid`/`UntilNotVoidDemo`: 타입 판별 instanceof 사슬을 패턴 매칭 switch로 바꾸고,
      이미 불필요하다고 밝혀진 캐스팅 블록은 삭제한다

`UntilNotVoid.unv(Object...)`의 "이 값이 falsy인가"를 판별하는 긴 `instanceof` 사슬을 Java 21
패턴 매칭 switch(`case Integer i -> i != 0` 등, JEP 441)로 다시 썼다 — 레거시 코드가 오래
기다려온 정확한 예시다. `UntilNotVoidDemo.java`는 원래 같은 값을 세 번 시험했다: 전용 메서드
(`until_not_void`), 캐스팅을 붙인 `unv(...)`, 캐스팅 없는 `unv(...)`. 세 번째 블록이 이미
"캐스팅이 필요 없다"는 걸 증명하고 있어서(오버로드 해석이 정확한 타입을 돌려준다) 캐스팅 붙은
중간 블록은 완전히 중복이었다 — 그 블록을 지우고 대신 인라인 주석으로 이유를 남겼다. 그 결과
modern의 출력은 legacy보다 구분선(`"----------"`/`"--------------------"`) 줄 수가 적다(의도된
차이). `expect()` 실패 출력은 legacy·modern 둘 다 없어서(모든 값이 기대와 일치) 패턴 매칭 switch
버전이 원본과 동일하게 동작함을 확인했다.

## D19. collection 영역: `list.sort(null)`이 `Arrays.sort()`+수동 재조립과 예외 상황에서
      다르게 동작한다는 걸 발견해서 복사본에 정렬하는 방식으로 고쳤다

`ArraysSortTest.sort_tomato()`를 처음에는 `list.sort(null);`로 단순화했는데, legacy 대비 대조
과정에서 실제 동작 차이를 발견했다: 원본은 `list.toArray()`로 뜬 별도 배열을 `Arrays.sort()`로
정렬하다 예외(Comparable을 구현하지 않은 원소)가 나면 원본 `list`는 전혀 건드리지 않은 채
남는다. 반면 `ArrayList#sort()`는 리스트의 backing array를 직접(제자리) 정렬하므로, 정렬 도중
예외가 나면 원본 리스트가 부분적으로 뒤섞인 상태로 남는다 — 즉 같은 "정렬 실패" 상황에서 두
구현이 서로 다른 사후 상태를 남긴다. 이 파일은 정렬 실패 자체(병합 정렬이 계약 위반을 어떻게
다루는지)를 보여주는 게 목적이라 이 차이가 무시할 수 없었다. 그래서 `new ArrayList<>(list)`로
복사본을 만들어 그 복사본만 정렬하고, 성공하면 원본에 반영하는 방식으로 고쳤다 — 원본이
`toArray()` 스냅숏을 정렬하던 것과 같은 "실패 시 원본 무변경" 성질을 유지하면서도 수동 배열
재조립보다 짧다. 값(정렬 결과, 예외 발생 지점의 부분 상태)은 legacy와 완전히 일치하는 것으로
확인했다 — 스택트레이스의 내부 프레임과 줄 번호만 구현이 달라져서 다르다(의도된 차이).
이 경험 때문에, `list.sort(null)`/`Collections.sort()`처럼 "제자리 정렬"을 쓰는 다른 파일에서도
예외 경로의 사후 상태가 바뀔 수 있다는 걸 유념하기로 했다.

## D20. collection 영역: `Option`은 record로, `CascadingOptionsBuilder`는 그대로 둔다

`CascadingOptionsBuilderDemo`의 `Option`(불변 k/v 쌍, `Comparable`)은 record로 바꿨다 —
`toString()`/`compareTo()`를 record 본문에서 오버라이드하고, 접근자는 `getK()`/`getV()` 대신
표준 `k()`/`v()`로 바꿨다. `CascadingOptionsBuilder`는 두 개의 `HashMap`을 계속 채워나가는
가변 빌더라 `Map.of()`로 바꿀 수 없어 그대로 뒀지만, `null` 체크 후 새로 만들어 넣는 관용구는
`Map#computeIfAbsent()`로, 배열 왕복 정렬은 `List#sort(null)`로 단순화했다(이쪽은 예외 시
원본이 훼손돼도 되는 자리라 D19의 우려가 적용되지 않는다 — 정렬 실패를 시연하는 목적이 아니라
그냥 정렬이 잘 되는 경우만 쓰인다).

## D21. collection 영역: `RemoveDuringIterationTest`는 기존 케이스를 그대로 두고 `removeIf` 를
      새 케이스로 추가한다

이 파일은 반복 중 삭제가 안 되는 경우("not works")와 되는 경우("works")를 여러 방식으로
나열하는 게 목적이라, 기존 메서드는 하나도 고치지 않고 `List#removeIf()`/
`Map#entrySet().removeIf()`(둘 다 Java 8+, `ConcurrentModificationException` 없이 안전하게
조건부 삭제)를 "works" 그룹 끝에 새 메서드로 추가했다. legacy 대비 modern 출력은 그만큼 뒤에
줄이 늘어난 것 말고는 완전히 같다.

## D22. `ood/immutable/WrapperOverCollection_*` 9개는 전부 원형 유지로 뒤집는다

PLAN.md 초안은 이 9개 파일의 도메인 클래스(`ChangeOverview`/`BizRuleSet`/`BizRuleGroup`/
`BizRuleType`)를 각 파일에서 개별적으로 record + `List.copyOf`로 바꾸는 것으로 정했었다.
실제로 9개를 전부 읽어 보니 문제가 있었다: 이 시리즈는 "내부 컬렉션을 어떻게 보호할까"에 대한
네 가지 서로 다른 전략을 비교하는 게 목적이다 — `_1`(그대로 노출), `_2`(Map/JSON 파생 뷰만
노출), `_3`(getter/setter 호출마다 방어적으로 clone), `_4`(다 만든 뒤 freeze). record로
통일하면 `_1`은 자연스럽게 유지되지만(record도 방어적 복사 없이 그대로 저장/반환할 수 있다),
`_3`(매번 clone)과 `_4`(freeze)는 애초에 record가 불변이라 그 전략 자체가 필요 없어져서
구현이 서로 거의 똑같아진다 — "네 가지 전략을 비교한다"는 시리즈의 존재 이유가 사라진다.
사용자에게 확인해서(PLAN.md의 "열어둔 판단"에 이미 예정돼 있던 재상의) 9개 전부 원형 그대로
두기로 뒤집었다. 대신 Java 21 판은 9개 중 하나를 고치는 게 아니라 별도 신규 파일
`ood/immutable/ImmutableRuleSetDemo.java`로 만들어서, record + `List.copyOf`가 네 전략을
어떻게 한 번에 대체하는지 보여준다(`BizRuleType`에 값을 바꾸는 setter 대신 새 인스턴스를
돌려주는 "wither" 메서드 `asSelected()`를 두고, `List.copyOf()`로 감싼 리스트가
`UnsupportedOperationException`을 던지는 것까지 실제로 시연한다).
덧붙여 `_4_Freeze.java`에는 원작자가 스스로 `// TODO not works cuz already frozen`이라고
남긴 실제 버그(그룹에 타입을 추가하면서 개별 타입을 바로 freeze해버려서, 그 뒤의
`markAsSelected()` 호출이 반영되지 않는다)가 있는데, legacy 원형 보존이 우선이라 고치지
않고 헤더 주석에만 남겼다.

## D23. `ood/delegation/InstancelessDelegation`: raw `Class` → `Class<?>`, 배열 복사·다중
      catch 정리

리플렉션으로 인스턴스 없이 static 필드/메서드에 접근하는 데모라 리플렉션 API 자체는 그대로
두고, raw `Class`를 `Class<?>`로 고쳤다. `callMethod()`의 `String... params`를
`Object[] varargs`로 한 칸씩 복사하던 루프는 불필요했다 — 배열은 공변이라(`String[]`은
`Object[]`의 하위 타입) `params`를 그대로 넘기면 된다. `invoke()`의
`IllegalAccessException`/`InvocationTargetException` catch 블록은 본문이 똑같아서
멀티 catch로 합쳤다.

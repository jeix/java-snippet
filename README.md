Java Snippet
============

simple java codelet

Contents
--------

https://github.com/jeix/java-snippet/tree/master/datetime
https://github.com/jeix/java-snippet/blob/master/lsc.java
https://github.com/jeix/java-snippet/blob/master/lang/NullType.java

### String

* `java.util.Formatter#format()` 데모
  @see [string/FormatterFormatDemo.java](https://github.com/jeix/java-snippet/blob/master/string/FormatterFormatDemo.java)
* `String#split()`과 `java.util.StringTokenizer`는 둘 다
  문자열의 끝에 나타나는 구분자들을 잘라내 버린 후 나눈다
  하지만 중간에 나타나는 빈 문자열은 각각 다르게 처리하는데
  `String#split()`은 남겨두지만 `java.util.StringTokenizer`는 버린다
  @see [string/StringSplit.java](https://github.com/jeix/java-snippet/blob/master/string/StringSplit.java)
* `String#split()`과 `java.util.StringTokenizer`는 둘 다
  구분자들로만 이루어진 문자열을 처리하지 못한다
  `String#split()`은 빈 문자열을 처리할 수 있지만
  `java.util.StringTokenizer`는 처리하지 못하다
  @see [string/StringSplitDemo.java](https://github.com/jeix/java-snippet/blob/master/string/StringSplitDemo.java)
* `String#replaceAll()`과 `String#replace()`의 차이는
  바꾸는 횟수가 아니라
  찾을 문자열로 정규식을 받느냐 아니냐이다
  @see [string/StringReplaceAllDemo.java](https://github.com/jeix/java-snippet/blob/master/string/StringReplaceAllDemo.java)
  @see [string/StringReplaceDemo.java](https://github.com/jeix/java-snippet/blob/master/string/StringReplaceDemo.java)
* `String#replaceAll()` 데모 2 또는 정규표현식 데모
  comma separated method parameter (method signature) 를 포맷하는 데모
  @see [string/MethodParamterSignatureFormatter.java](https://github.com/jeix/java-snippet/blob/master/string/MethodParamterSignatureFormatter.java)

### Number

* `Integer.parseInt()`는
  디폴트 radix는 10이다
  radix 파라미터에 의해 결정된 진수 체계에서 지원되는 수를 나타내는 문자가 아닌 문자가 있는 경우 `NumberFormatException`을 일으킨다
  "010"처럼 선행되는 "0"은 결과적으로 무시된다
  그래서
  * `Integer.parseInt("010")`은 8이 아니고 10을 리턴한다
  * `Integer.parseInt("0x10")`은 `NumberFormatException`을 일으킨다
  * `Integer.parseInt("10", 8)`은 8을 리턴한다
  * `Integer.parseInt("00FF", 16)`은 255를 리턴한다
  @see [number/IntegerParseIntTest.java](https://github.com/jeix/java-snippet/blob/master/number/IntegerParseIntTest.java)
* `Integer.valueOf()`도
  디폴트 radix는 10이다
  `Integer.parseInt()`와 비슷하다
  @see [number/IntegerValueOfTest.java](https://github.com/jeix/java-snippet/blob/master/number/IntegerValueOfTest.java)

### File

* `java.io.RandomAccessFile` 데모
  @see [file/RandomAccessFileDemo.java](https://github.com/jeix/java-snippet/blob/master/file/RandomAccessFileDemo.java)
* `Class.getResourceAsStream()`은 절대 경로와 상대 경로를 모두 지원한다
  `ClassLoader.getResourceAsStream()`는 절대 경로만 지원하는데 /로 시작해서는 안 된다
  @see [file/ResourceAsStreamDemo.java](https://github.com/jeix/java-snippet/blob/master/file/ResourceAsStreamDemo.java)

### Other

* `return`은 `finally` 블럭을 수행하고 리턴하지만
  `Syatem.exit()`는 즉시 나간다
  @see [lang/ReturnOExitDemo.java](https://github.com/jeix/java-snippet/blob/master/lang/ReturnOExitDemo.java)
* `System.setProperty()`는 `System.out.println()`에 영향을 주지 못한다
  출력을 redirection해서 Hexa 에디터로 확인해야 한다
  @see [lang/SystemSetPropertyDemo.java](https://github.com/jeix/java-snippet/blob/master/lang/SystemSetPropertyDemo.java)

- 숫자를 휴먼 리더블하게 보이기
  @see number/HumanReadable.java
- 알고 있는 몇 가지 값들 중 하나와 일치하는가
  @see lang/is_in/IsIn.java
- 리스트/맵에서 요소들을 순회하면서 아이템을 삭제하기
  @see collection/RemoveDuringIterationTest.java
- Arrays.sort()를 사용하려면 요소들이 Comparable 이어야 한다
  @see collection/ArraysSortTest.java
- 자바 배열은 선언할 때 요소들을 알고 있거나 요소의 갯수라도 알고 있어야 한다
  @see collection/ArrayInitializeTester.java
- "yyyy-mm-dd" 스타일을 선호한다면 java.sql.Date를 사용하자
  - java.util.Date#toString()과 달리 java.sql.Date#toString()은
    "yyyy-mm-dd" 스타일로 날짜 문자열을 리턴한다
  - java.text.DateFormat#parse()와 달리 java.sql.Date#valueOf()은
    "yyyy-mm-dd" 스타일만을 받아들인다 (내부적으로 deprecated된 java.util.Date 생성자를 사용한다)
  - DateFormat#format()을 사용하지 않는
    "yyyy-mm-dd" 스타일의 오늘/어제/올제, 저번주/다음주, 저번달/다음달
  @see datetime/DateStringTest.java
  @see datetime/TimeStringTest.java
- 몇 가지 날짜 유틸리티 : 어제/오늘/올제, 일주일 전/후, 한달 전/후
  @see datetime/DateUtil.java
- @see datetime/DateUtilB.java
  @see datetime/TimeUtilB.java
- FileChannel과 ByteBuffer나 MappedByteBuffer를 이용한 nio
  @see file/NioRw.java
- 제네릭 타입의 List.toArray()를 사용하면
  배열을 선언할 때 요소의 갯수를 몰라도 되지만
  이미 요소의 갯수를 알고 있다
  @see collection/ListToArrayTester.java
- null is NOT an instance of the given type NOR Object.
  null is enable to be casted to any type in the inheritance hierarchy without ClassCastException.
    (to Object then to any type)
  (maybe) because null has no type information even though it was declared as a type.
  @see lang/NullType.java
- java.util.Properties 데모
  @see file/PropertiesTest.java
- bounded wildcard
  @see lang/BoundedWildcard.java

- Apache Commons HttpClient 스파이크
  @see demo/HttpClientGetTester.java

- anonymous inner class 데모
  @see lang/AnonymousTester.java
- 이스케이프 시쿼스를 \x 형태로 변환하려면
  String#replace("\t", "\\t") 나
  String#replaceAll("\\t", "\\\\t") 같이 하면 된다.
  @see string/EscapeSequenceReplaceDemo.java
  - String#replaceAll() 은 Matcher#replaceAll() 을 호출하고
    Matcher#replaceAll() 은 Matcher#appendReplacement() 을 호출하는데
    - Matcher#appendReplacement() 는 '\\'를 만나면 skip하고 다음 문자를 쓴다.
      그 결과 "\\\\" 가 "\\" 로 줄어든다.
  - String#replace() 는 Matcher#replaceAll() 을 호출하기 전에
    Matcher.quoteReplacement() 을 호출하는 데
    Matcher.quoteReplacement() 는 "\\" 를 "\\\\" 로 늘인다.
    - 그 결과 String#replace("\t", "\\t") 하면
      Matcher#replaceAll("\\\\t") 이 호출되고
      다시 Matcher#quoteReplacement() 에서는 "\\t"로 줄어든다.

- Object#clone() 오버라이드 데모
  Object#equals() 오버라이드 데모
  @see lang/CloneTester.java

- public 템플릿 메서드에서 private 메서드를 부를 때
  private 메소드만 오버라이드해서는 안 된다
  - 템플릿 메서드도 오버라이드하거나
    @see ood/template_method/OverridePrivateMethodOnlyTest.java
    (abstract 템플릿 메서드로 오버라이드를 강제할 수는 있겠다만
     템플릿 메서드를 몇 번 씩 중복해서 작성하는 것은
     어쨌든 좋은 생각이 아니다 - raison d'etre)
    @see ood/template_method/OverrideTemplateMethodDemo.java
  - 대신 템플릿 메서드에서 사용되는 작업 메서드들을
    protected 메서드로 만들어 오버라이드해야 한다
    @see ood/template_method/OverrideWorkingMethodDemo.java
  - abstract 작업 메서드 오버라이드를 강제할 것이 아니라
    필요하면 알아서 오버라이드하면 된다면
    @see ood/template_method/OverrideProtectedMethodTest.java

- 문자열을 비교할 때
  "string".equals(null) 형태를 많이 쓰는데
  그와 달리
  "string".contains(null) 나 "string".indexOf(null) 는
  NullPointerException 을 일으킨다
  "string".endsWith(null) 와 "string".startsWith(null) 역시
  <<NO_SAMPLE>>
- String 의 메서드 중에서
  NullPointerException 을 일으키지 않는 메서드는
  equals(), equalsIgnoreCase(), valueOf() 뿐이다.
  <<NO_SAMPLE>>

- 오랫만에 주민등록번호 체크
  @see NpidCheck.java

- 키/값 짝 모임에서 순서가 의미있을 때
  - List와 Map을 쓴
    @see collection/OrderedKeyValPairsDemo.java
  - 2개의 List를 쓴
    @see collection/OrderedKeyValPairsDemo2.java
  - LinkedHashMap
    @see collection/LinkedHashMapDemo.java
  - 교훈 : 라이브러리를 알고 써라 (47 Know and use the libraries, from Effective Java)

- BigDecimal
  @see number/BigDecimalDemo.java

- annotation 데모
  @see lang/annotation/Ann.java

- 필드 리플렉션 데모
  @see lang/ReflectField.java

- null check 하는 대신 null을 리턴하지 않기
  @see lang/void_/AvoidNullCheck.java

{=
- inner 클래스의 인스턴스를 생성하는 적당한 이름의 메서드들 두기
  @see lang/inner_class/InnerClassFactoryDemo.java
- outer 클래스의 인스턴스와 엮인다면 inner 클래스로
  그렇지 않다면 static nested 클래스로
=}
- outer 클래스의 인스턴스와 엮인다면 inner 클래스로
  그렇지 않다면 static nested 클래스로
  @see lang/inner_class/InnerClassInstantiationDemo.java

- instance 메서드는 실제 타입의 메서드가 호출되지만
  static 메서드는 선언된 타입이나 명시적으로 캐스팅한 타입의 메서드가 호출된다
  또는
  static 메서드는
  클래스를 통해서 접근해야지
  인스턴스를 통해서 접근해서는 안 된다
  @see lang/StaticMethodCallDemo.java

- line separator convert
  @see lsc.java

- 케스케이딩 옵션 데모
  @see collection/CascadingOptionsBuilderDemo.java

- enum 데모
  @see lang/enum_/EnumDemo0.java
  @see lang/enum_/EnumDemo1.java
  @see lang/enum_/EnumDemo2.java
  @see lang/enum_/EnumDemo3.java
  @see lang/enum_/EnumDemo4.java
- 가변 인자 데모
  @see lang/VarArgsDemo
- boxing, unboxing, autoboxing
  @see lang/Autoboxing.java

- x = p || q || "";
  @see [lang/void_/until_not_void/UntilNotVoid.java](https://github.com/jeix/java-snippet/blob/master/lang/void_/until_not_void/UntilNotVoid.java)
  @see [lang/void_/until_not_void/UntilNotVoidDemo.java](https://github.com/jeix/java-snippet/blob/master/lang/void_/until_not_void/UntilNotVoidDemo.java)

- collection 속성을 노출하지 않기
  - immutable을 강제하려면 외부에는 clone을 수행하는 getter/setter를 제공해야 한다고들 한다.
  - 하지만 내부에서 사용하는 경우에는 gettter로 collection을 리턴받아 collection에 직접 추가/제거하는 게 더 편하다.
  - collection 뿐만 아니라 다른 객체도 마찬가지다.
  - 아마도 한쪽 끝에는 "Paranoid"가 있고, 다른 한쪽 끝에는 "Play at Your Own Risk"가 있는 식일 텐데
    - clone을 수행하거나 전달용 타입으로 변환하는 getter만 제공하고 setter는 제공하지 않기
    - setter를 제공하기는 하되 clone을 수행하거나 타입을 변환하기
      또는 setter 대신 adder를 제공하기
    - 내부의 getter/setter는 신경쓸 것 없이 clone을 수행하거나 타입을 변환해서 전달하기
    - 그냥 전달하기 (꼭 immutable일 필요가 있는가?)
    - 그냥 전달하기 (immutable이긴 해야 하지만)
  - 내부에서는 collection 속성을 바로 쓸 수 있게 하고, 외부에는 collection 속성을 노출하지 않으려면 어떻게 해야할까?
  - "Paranoid" version
    @see ood/immutable/WrapperOverCollection_3.java
  - "Play at Your Own Risk" or "copied value object" version
    @see ood/immutable/WrapperOverCollection_1.java
  - JSON-like map 리턴
    @see ood/immutable/WrapperOverCollection_2.java
  - "freeze" version
    @see ood/immutable/WrapperOverCollection_4.java
- collection 속성을 노출하지 않기 revisited
  - 받을 때도 줄 때도 신경 끄기
    cuz 널 믿어
    @see ood/immutable/WrapperOverCollection_1_Nothing
  - 받을 때는 그냥 받고, 줄 때는 JSON-like map으로
    cuz 외부에선 받을 일 없어 or 이상한 걸 주면 네 탓이야
    @see ood/immutable/WrapperOverCollection_2_Map
  - 받을 때는 그냥 받고, 줄 때는 JSON으로
    받을 때 JSON이나 Map을 받을 수도 있겠다
    @see ood/immutable/WrapperOverCollection_2_JSON
  - 받을 때도 clone, 줄 때도 clone
    @see ood/immutable/WrapperOverCollection_3_Clone
  - 받을 때 freeze
    @see ood/immutable/WrapperOverCollection_4_Freeze

- x in (p,q,r)
  @see lang/is_in/IsIn.java
  @see lang/is_in/IsInDemo.java

- null-proof 데모
  - trim(), string(), nada()
  - Nil<T>
  @see lang/void_/NullProof.java

- 정수부/소수부
  @see number/DecimalPoint.java
- NumberFormat 데모
  @see number/NumberFormatDemo.java

- forEach, filter, map
  @see lang/for_each/ForEachDemo.java

- 날짜 비교
  @see datetime/DateDiff.java

- 심플 텍스트 파일 읽고 쓰기
  @see file/TextFileReader.java
  @see file/TextFileWriter.java

- 빈 맵은 null 키를 포함하지 않는다  // false <-- map.containsKey(null)
  빈 리스트는 null을 포함하지 않는다 // false <-- list.contains(null)
  <<NO_SAMPLE>>

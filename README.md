Java Snippet
============

simple java codelet

Contents
--------

https://github.com/jeix/java-snippet/tree/master/datetime
https://github.com/jeix/java-snippet/blob/master/lsc.java
https://github.com/jeix/java-snippet/blob/master/lang/NullType.java

### String

* `java.util.Formatter#format()` ����
  @see [string/FormatterFormatDemo.java](https://github.com/jeix/java-snippet/blob/master/string/FormatterFormatDemo.java)
* `String#split()`�� `java.util.StringTokenizer`�� �� ��
  ���ڿ��� ���� ��Ÿ���� �����ڵ��� �߶� ���� �� ������
  ������ �߰��� ��Ÿ���� �� ���ڿ��� ���� �ٸ��� ó���ϴµ�
  `String#split()`�� ���ܵ����� `java.util.StringTokenizer`�� ������
  @see [string/StringSplit.java](https://github.com/jeix/java-snippet/blob/master/string/StringSplit.java)
* `String#split()`�� `java.util.StringTokenizer`�� �� ��
  �����ڵ�θ� �̷���� ���ڿ��� ó������ ���Ѵ�
  `String#split()`�� �� ���ڿ��� ó���� �� ������
  `java.util.StringTokenizer`�� ó������ ���ϴ�
  @see [string/StringSplitDemo.java](https://github.com/jeix/java-snippet/blob/master/string/StringSplitDemo.java)
* `String#replaceAll()`�� `String#replace()`�� ���̴�
  �ٲٴ� Ƚ���� �ƴ϶�
  ã�� ���ڿ��� ���Խ��� �޴��� �ƴϳ��̴�
  @see [string/StringReplaceAllDemo.java](https://github.com/jeix/java-snippet/blob/master/string/StringReplaceAllDemo.java)
  @see [string/StringReplaceDemo.java](https://github.com/jeix/java-snippet/blob/master/string/StringReplaceDemo.java)
* `String#replaceAll()` ���� 2 �Ǵ� ����ǥ���� ����
  comma separated method parameter (method signature) �� �����ϴ� ����
  @see [string/MethodParamterSignatureFormatter.java](https://github.com/jeix/java-snippet/blob/master/string/MethodParamterSignatureFormatter.java)

### Number

* `Integer.parseInt()`��
  ����Ʈ radix�� 10�̴�
  radix �Ķ���Ϳ� ���� ������ ���� ü�迡�� �����Ǵ� ���� ��Ÿ���� ���ڰ� �ƴ� ���ڰ� �ִ� ��� `NumberFormatException`�� ����Ų��
  "010"ó�� ����Ǵ� "0"�� ��������� ���õȴ�
  �׷���
  * `Integer.parseInt("010")`�� 8�� �ƴϰ� 10�� �����Ѵ�
  * `Integer.parseInt("0x10")`�� `NumberFormatException`�� ����Ų��
  * `Integer.parseInt("10", 8)`�� 8�� �����Ѵ�
  * `Integer.parseInt("00FF", 16)`�� 255�� �����Ѵ�
  @see [number/IntegerParseIntTest.java](https://github.com/jeix/java-snippet/blob/master/number/IntegerParseIntTest.java)
* `Integer.valueOf()`��
  ����Ʈ radix�� 10�̴�
  `Integer.parseInt()`�� ����ϴ�
  @see [number/IntegerValueOfTest.java](https://github.com/jeix/java-snippet/blob/master/number/IntegerValueOfTest.java)

### File

* `java.io.RandomAccessFile` ����
  @see [file/RandomAccessFileDemo.java](https://github.com/jeix/java-snippet/blob/master/file/RandomAccessFileDemo.java)
* `Class.getResourceAsStream()`�� ���� ��ο� ��� ��θ� ��� �����Ѵ�
  `ClassLoader.getResourceAsStream()`�� ���� ��θ� �����ϴµ� /�� �����ؼ��� �� �ȴ�
  @see [file/ResourceAsStreamDemo.java](https://github.com/jeix/java-snippet/blob/master/file/ResourceAsStreamDemo.java)

### Other

* `return`�� `finally` ���� �����ϰ� ����������
  `Syatem.exit()`�� ��� ������
  @see [lang/ReturnOExitDemo.java](https://github.com/jeix/java-snippet/blob/master/lang/ReturnOExitDemo.java)
* `System.setProperty()`�� `System.out.println()`�� ������ ���� ���Ѵ�
  ����� redirection�ؼ� Hexa �����ͷ� Ȯ���ؾ� �Ѵ�
  @see [lang/SystemSetPropertyDemo.java](https://github.com/jeix/java-snippet/blob/master/lang/SystemSetPropertyDemo.java)

- ���ڸ� �޸� �������ϰ� ���̱�
  @see number/HumanReadable.java
- �˰� �ִ� �� ���� ���� �� �ϳ��� ��ġ�ϴ°�
  @see lang/is_in/IsIn.java
- ����Ʈ/�ʿ��� ��ҵ��� ��ȸ�ϸ鼭 �������� �����ϱ�
  @see collection/RemoveDuringIterationTest.java
- Arrays.sort()�� ����Ϸ��� ��ҵ��� Comparable �̾�� �Ѵ�
  @see collection/ArraysSortTest.java
- �ڹ� �迭�� ������ �� ��ҵ��� �˰� �ְų� ����� ������ �˰� �־�� �Ѵ�
  @see collection/ArrayInitializeTester.java
- "yyyy-mm-dd" ��Ÿ���� ��ȣ�Ѵٸ� java.sql.Date�� �������
  - java.util.Date#toString()�� �޸� java.sql.Date#toString()��
    "yyyy-mm-dd" ��Ÿ�Ϸ� ��¥ ���ڿ��� �����Ѵ�
  - java.text.DateFormat#parse()�� �޸� java.sql.Date#valueOf()��
    "yyyy-mm-dd" ��Ÿ�ϸ��� �޾Ƶ��δ� (���������� deprecated�� java.util.Date �����ڸ� ����Ѵ�)
  - DateFormat#format()�� ������� �ʴ�
    "yyyy-mm-dd" ��Ÿ���� ����/����/����, ������/������, ������/������
  @see datetime/DateStringTest.java
  @see datetime/TimeStringTest.java
- �� ���� ��¥ ��ƿ��Ƽ : ����/����/����, ������ ��/��, �Ѵ� ��/��
  @see datetime/DateUtil.java
- @see datetime/DateUtilB.java
  @see datetime/TimeUtilB.java
- FileChannel�� ByteBuffer�� MappedByteBuffer�� �̿��� nio
  @see file/NioRw.java
- ���׸� Ÿ���� List.toArray()�� ����ϸ�
  �迭�� ������ �� ����� ������ ���� ������
  �̹� ����� ������ �˰� �ִ�
  @see collection/ListToArrayTester.java
- null is NOT an instance of the given type NOR Object.
  null is enable to be casted to any type in the inheritance hierarchy without ClassCastException.
    (to Object then to any type)
  (maybe) because null has no type information even though it was declared as a type.
  @see lang/NullType.java
- java.util.Properties ����
  @see file/PropertiesTest.java
- bounded wildcard
  @see lang/BoundedWildcard.java

- Apache Commons HttpClient ������ũ
  @see demo/HttpClientGetTester.java

- anonymous inner class ����
  @see lang/AnonymousTester.java
- �̽������� �������� \x ���·� ��ȯ�Ϸ���
  String#replace("\t", "\\t") ��
  String#replaceAll("\\t", "\\\\t") ���� �ϸ� �ȴ�.
  @see string/EscapeSequenceReplaceDemo.java
  - String#replaceAll() �� Matcher#replaceAll() �� ȣ���ϰ�
    Matcher#replaceAll() �� Matcher#appendReplacement() �� ȣ���ϴµ�
    - Matcher#appendReplacement() �� '\\'�� ������ skip�ϰ� ���� ���ڸ� ����.
      �� ��� "\\\\" �� "\\" �� �پ���.
  - String#replace() �� Matcher#replaceAll() �� ȣ���ϱ� ����
    Matcher.quoteReplacement() �� ȣ���ϴ� ��
    Matcher.quoteReplacement() �� "\\" �� "\\\\" �� ���δ�.
    - �� ��� String#replace("\t", "\\t") �ϸ�
      Matcher#replaceAll("\\\\t") �� ȣ��ǰ�
      �ٽ� Matcher#quoteReplacement() ������ "\\t"�� �پ���.

- Object#clone() �������̵� ����
  Object#equals() �������̵� ����
  @see lang/CloneTester.java

- public ���ø� �޼��忡�� private �޼��带 �θ� ��
  private �޼ҵ常 �������̵��ؼ��� �� �ȴ�
  - ���ø� �޼��嵵 �������̵��ϰų�
    @see ood/template_method/OverridePrivateMethodOnlyTest.java
    (abstract ���ø� �޼���� �������̵带 ������ ���� �ְڴٸ�
     ���ø� �޼��带 �� �� �� �ߺ��ؼ� �ۼ��ϴ� ����
     ��·�� ���� ������ �ƴϴ� - raison d'etre)
    @see ood/template_method/OverrideTemplateMethodDemo.java
  - ��� ���ø� �޼��忡�� ���Ǵ� �۾� �޼������
    protected �޼���� ����� �������̵��ؾ� �Ѵ�
    @see ood/template_method/OverrideWorkingMethodDemo.java
  - abstract �۾� �޼��� �������̵带 ������ ���� �ƴ϶�
    �ʿ��ϸ� �˾Ƽ� �������̵��ϸ� �ȴٸ�
    @see ood/template_method/OverrideProtectedMethodTest.java

- ���ڿ��� ���� ��
  "string".equals(null) ���¸� ���� ���µ�
  �׿� �޸�
  "string".contains(null) �� "string".indexOf(null) ��
  NullPointerException �� ����Ų��
  "string".endsWith(null) �� "string".startsWith(null) ����
  <<NO_SAMPLE>>
- String �� �޼��� �߿���
  NullPointerException �� ����Ű�� �ʴ� �޼����
  equals(), equalsIgnoreCase(), valueOf() ���̴�.
  <<NO_SAMPLE>>

- �������� �ֹε�Ϲ�ȣ üũ
  @see NpidCheck.java

- Ű/�� ¦ ���ӿ��� ������ �ǹ����� ��
  - List�� Map�� ��
    @see collection/OrderedKeyValPairsDemo.java
  - 2���� List�� ��
    @see collection/OrderedKeyValPairsDemo2.java
  - LinkedHashMap
    @see collection/LinkedHashMapDemo.java
  - ���� : ���̺귯���� �˰� ��� (47 Know and use the libraries, from Effective Java)

- BigDecimal
  @see number/BigDecimalDemo.java

- annotation ����
  @see lang/annotation/Ann.java

- �ʵ� ���÷��� ����
  @see lang/ReflectField.java

- null check �ϴ� ��� null�� �������� �ʱ�
  @see lang/void_/AvoidNullCheck.java

{=
- inner Ŭ������ �ν��Ͻ��� �����ϴ� ������ �̸��� �޼���� �α�
  @see lang/inner_class/InnerClassFactoryDemo.java
- outer Ŭ������ �ν��Ͻ��� ���δٸ� inner Ŭ������
  �׷��� �ʴٸ� static nested Ŭ������
=}
- outer Ŭ������ �ν��Ͻ��� ���δٸ� inner Ŭ������
  �׷��� �ʴٸ� static nested Ŭ������
  @see lang/inner_class/InnerClassInstantiationDemo.java

- instance �޼���� ���� Ÿ���� �޼��尡 ȣ�������
  static �޼���� ����� Ÿ���̳� ��������� ĳ������ Ÿ���� �޼��尡 ȣ��ȴ�
  �Ǵ�
  static �޼����
  Ŭ������ ���ؼ� �����ؾ���
  �ν��Ͻ��� ���ؼ� �����ؼ��� �� �ȴ�
  @see lang/StaticMethodCallDemo.java

- line separator convert
  @see lsc.java

- �ɽ����̵� �ɼ� ����
  @see collection/CascadingOptionsBuilderDemo.java

- enum ����
  @see lang/enum_/EnumDemo0.java
  @see lang/enum_/EnumDemo1.java
  @see lang/enum_/EnumDemo2.java
  @see lang/enum_/EnumDemo3.java
  @see lang/enum_/EnumDemo4.java
- ���� ���� ����
  @see lang/VarArgsDemo
- boxing, unboxing, autoboxing
  @see lang/Autoboxing.java

- x = p || q || "";
  @see [lang/void_/until_not_void/UntilNotVoid.java](https://github.com/jeix/java-snippet/blob/master/lang/void_/until_not_void/UntilNotVoid.java)
  @see [lang/void_/until_not_void/UntilNotVoidDemo.java](https://github.com/jeix/java-snippet/blob/master/lang/void_/until_not_void/UntilNotVoidDemo.java)

- collection �Ӽ��� �������� �ʱ�
  - immutable�� �����Ϸ��� �ܺο��� clone�� �����ϴ� getter/setter�� �����ؾ� �Ѵٰ�� �Ѵ�.
  - ������ ���ο��� ����ϴ� ��쿡�� gettter�� collection�� ���Ϲ޾� collection�� ���� �߰�/�����ϴ� �� �� ���ϴ�.
  - collection �Ӹ� �ƴ϶� �ٸ� ��ü�� ����������.
  - �Ƹ��� ���� ������ "Paranoid"�� �ְ�, �ٸ� ���� ������ "Play at Your Own Risk"�� �ִ� ���� �ٵ�
    - clone�� �����ϰų� ���޿� Ÿ������ ��ȯ�ϴ� getter�� �����ϰ� setter�� �������� �ʱ�
    - setter�� �����ϱ�� �ϵ� clone�� �����ϰų� Ÿ���� ��ȯ�ϱ�
      �Ǵ� setter ��� adder�� �����ϱ�
    - ������ getter/setter�� �Ű澵 �� ���� clone�� �����ϰų� Ÿ���� ��ȯ�ؼ� �����ϱ�
    - �׳� �����ϱ� (�� immutable�� �ʿ䰡 �ִ°�?)
    - �׳� �����ϱ� (immutable�̱� �ؾ� ������)
  - ���ο����� collection �Ӽ��� �ٷ� �� �� �ְ� �ϰ�, �ܺο��� collection �Ӽ��� �������� �������� ��� �ؾ��ұ�?
  - "Paranoid" version
    @see ood/immutable/WrapperOverCollection_3.java
  - "Play at Your Own Risk" or "copied value object" version
    @see ood/immutable/WrapperOverCollection_1.java
  - JSON-like map ����
    @see ood/immutable/WrapperOverCollection_2.java
  - "freeze" version
    @see ood/immutable/WrapperOverCollection_4.java
- collection �Ӽ��� �������� �ʱ� revisited
  - ���� ���� �� ���� �Ű� ����
    cuz �� �Ͼ�
    @see ood/immutable/WrapperOverCollection_1_Nothing
  - ���� ���� �׳� �ް�, �� ���� JSON-like map����
    cuz �ܺο��� ���� �� ���� or �̻��� �� �ָ� �� ſ�̾�
    @see ood/immutable/WrapperOverCollection_2_Map
  - ���� ���� �׳� �ް�, �� ���� JSON����
    ���� �� JSON�̳� Map�� ���� ���� �ְڴ�
    @see ood/immutable/WrapperOverCollection_2_JSON
  - ���� ���� clone, �� ���� clone
    @see ood/immutable/WrapperOverCollection_3_Clone
  - ���� �� freeze
    @see ood/immutable/WrapperOverCollection_4_Freeze

- x in (p,q,r)
  @see lang/is_in/IsIn.java
  @see lang/is_in/IsInDemo.java

- null-proof ����
  - trim(), string(), nada()
  - Nil<T>
  @see lang/void_/NullProof.java

- ������/�Ҽ���
  @see number/DecimalPoint.java
- NumberFormat ����
  @see number/NumberFormatDemo.java

- forEach, filter, map
  @see lang/for_each/ForEachDemo.java

- ��¥ ��
  @see datetime/DateDiff.java

- ���� �ؽ�Ʈ ���� �а� ����
  @see file/TextFileReader.java
  @see file/TextFileWriter.java

- �� ���� null Ű�� �������� �ʴ´�  // false <-- map.containsKey(null)
  �� ����Ʈ�� null�� �������� �ʴ´� // false <-- list.contains(null)
  <<NO_SAMPLE>>

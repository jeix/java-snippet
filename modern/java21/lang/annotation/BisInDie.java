package modern.java21.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 원형 유지: 어노테이션 선언 문법(@Retention, @Target, 어노테이션 안에 어노테이션을 중첩하는
// 방식) 자체가 주제다. Ann.java, AnnotationTarget.java와 함께 본다.

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BisInDie {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Simple {
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Parameter {
		String prefix();
		String suffix();
	}
}
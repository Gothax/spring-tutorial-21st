# spring-tutorial-21st

<br>
면접용 정리 - 경어체로 작성<br>
나만의 정리 - 평어체로 작성<br>




## 1. Spring Framework

Spring이 지원하는 다양한 기술들을 알아보자.


### Ioc / DI / IoC Container

**IoC란?**
- IoC란 객체 생성, 생명주기 제어를 개발자가 아닌 외부에 위임하는 것을 뜻합니다.<br>
  Spring에서는 DI를 통해 IoC를 구현합니다.

**IoC Container란?**
- IoC 컨테이너란 Spring에서 bean을 생성하고 의존성 및 생명주기를 관리하는 컨테이너 입니다.<br>
  개발자는 DI 방식으로 bean을 주입받아 사용합니다.

**DI란?**
- DI란 객체를 직접 생성하지 않고 외부에서 받아 사용하는 것을 뜻합니다.<br>
   Spring에서는 스프링 IoC 컨테이너가 객체를 생성하고 의존성을 주입합니다.

**DI 종류?**
- DI 종류는 3가지 setter 주입, 필드 주입, 생성자 주입이 있습니다.<br>
  안정성 문제로 setter 지양<br>
  필드 주입은 테스트의 어려움<br>
  등의 이유로 생성자 주입이 가장 권장됩니다.

(final 선언 가능, 순환 참조 언급도 좋음)

**필드 주입은 왜 테스트가 어려움?**
- 필드 주입은 프레임워크에 가장 큰 의존성을 가지기 때문에 테스트가 어렵습니다.<br>
  구체적으로 Spring 컨테이너 없이 JUnit에서 테스트 하기 어렵습니다.


**DI가 그래서 왜 좋은데?**
- 클래스간 결합을 느슨하게 할 수 있습니다. 이로인해 의존성이 줄어들고, 재사용성이 높아집니다.<br>
   예를 들어 service에서 repository 구현체를 의존하면, 구현체가 바뀔때마다 service의 코드를 수정해야합니다.<br>
   하지만 service에서 repository의 interface를 의존했다면 주입해주는 구현체만 바꾸면 되기 때문에 재사용성이 높아집니다.


**생성자 주입시 왜 private final?**
- final 키워드는 생성시 불변, 선언시 초기화를 시켜주는 것을 강제합니다.<br>
  선언시 초기화 시켜주는 것을 강제해서 스프링이 컨테이너에 있는 bean을 주입해 줄 수 있습니다.


### JPA

**JPA란?**
- JPA는 Java Persistence API의 약자로 자바 ORM 기술 표준입니다.<br>
  ORM이란 객체와 관계형 데이터베이스의 데이터를 자동으로 매핑해주는 기술입니다.

**N+1 문제?**
- 근본적인 원인은 관계형 데이터베이스와 객체지향 언어간 차이때문에 발생합니다.<br>
 객체는 연관관계가 있다면 메모리 내에서 접근할 수 있지만<br>
 데이터베이스는 select 쿼리로만 조회할 수 있기 때문입니다.

**해결은?**
- fetch join으로 해결할 수 있습니다.<br>
  하지만 페이징시, to many 관계에서 문제가 발생할 수 있습니다.


**어떤 문제가 발생하는데?**
- out of memory 문제가 발생할 수 있습니다<br>
  JPA는 어떤 데이터를 기준으로 paging할지 모르기 때문에<br>
  모든 데이터를 메모리에 올리고 paging을 수행합니다.<br>
  이를 해결하기 위해 batch size를 설정할 수 있습니다.

**영속성 컨텍스트란?**
- 엔티티를 영구히 저장하는 환경을 뜻합니다<br>
장점으로는 1차캐시, 쓰기 지연, 변경 감지, 지연 로딩, 동일성 보장이 있습니다.


**1차캐시란?**
- 조회가 가능한 객체이며 없다면 db에서 조회합니다.

**쓰기 지연이란?**
- 트랜잭션을 지원하는 기능으로 쿼리를 바로 보내는 것이 아니라 모아서 보낼 수 있습니다

**변경감지란?**
- 1차캐시 데이터를 스냅샷 찍고, 커밋 시점의 엔티티와 비교해 update sql을 생성합니다

**지연로딩이란?**
- 연관관계가 있는 엔티티를 바로 read하는게 아니라 접근할때 쿼리를 보내 로드하는 것입니다.<br>
이때 사용하는 것이 프록시 객체입니다

**프록시 객체가 뭔데?**
- 지연 로딩과 관련 있습니다.<br>
영속성 컨텍스트는 연관관계가 있는 객체를 데이터베이스에서 바로 가져오는 것이 아니라<br>
가짜 객체인 프록시 객체로 참조하고 있다가 그 값을 사용하려 할 때 데이터 베이스에 접근합니다.

**동일성은 뭐고 동등성이랑 뭐가 달라?**
- 동일성은 객체의 주소값이 같은지를 비교하는 것이고<br>
  동등성은 객체의 값이 같은지를 비교하는 것입니다

### PSA (Portable Service Abstraction)

추상화를 사용해 기술을 내부에 숨겨 개발자에게 편의성을 제공하는 것이 Service Abstraction.<br>
환경에 상관 없이 **일관성 있게** 기술을 사용할 수 있도록 제공하는 것이 Portable Service Abstraction.<br>

예를 들어

DB에 접근하기 위해 JDBC를 사용할수도, JPA를 사용할수도 있다.<br>
하지만 **두 경우 모두** @Transactional 어노테이션 사용 가능.

구체적으로

아래 그림처럼
@Transactional 어노테이션은<br>
PlatFormTransactionManager **Interface에** 의존

<img width="500" alt="Image" src="https://github.com/user-attachments/assets/fb6ab27e-8dc0-4b17-b026-6fad540f2ff6" />

PlatformTransactionManager 구현체를 살펴보면<br>
<img width="797" alt="Image" src="https://github.com/user-attachments/assets/45ba01f8-9858-43fb-9039-e427eeab5b4e" />

<br>

간단하게 코딩을 해보자.<br>

가짜 TransactionManager 2개를 만들고<br>
TransactionManager 인터페이스에 의존하는 서비스에서<br>
다른 구현체를 주입받고<br>
@Transactional 어노테이션을 사용해보겠다.

<br>

가짜 트랜잭션 매니저를 하나 만들어 보고
```java
public class DummyTransactionManager implements PlatformTransactionManager {

    private final String name;

    public DummyTransactionManager(String name) {
        this.name = name;
    }
    
    ...
}
```

인터페이스에 의존하는 Service<br>
메서드에 Transactional 어노테이션을 달아줬다.

```java
@Service
@RequiredArgsConstructor
public class CompareTransactionService {

    private final PlatformTransactionManager transactionManager;

    @Transactional
    public void executeTransactional(String managerName, Runnable action) {
        
        TransactionTemplate txTemplate = new TransactionTemplate(transactionManager);
        txTemplate.execute(status -> {
            action.run();
            return null;
        });
    }
}
```


단위테스트
```java

    @Test
    public void testJpaTransactionManager() {
        // "JPA" DummyTransactionManager 주입
        DummyTransactionManager jpaTM = new DummyTransactionManager("JPA");
        CompareTransactionService service = new CompareTransactionService(jpaTM);

        System.out.println("== JPA Transaction Manager Test 시작 ==");
        service.executeTransactional("JPA", () -> {
            System.out.println("Action executed using JPA TM");
        });
        System.out.println("== JPA Transaction Manager Test 종료 ==");
    }

    @Test
    public void testJdbcTransactionManager() {
        // "JDBC" DummyTransactionManager 주입
        DummyTransactionManager jdbcTM = new DummyTransactionManager("JDBC");
        CompareTransactionService service = new CompareTransactionService(jdbcTM);

        System.out.println("== JDBC Transaction Manager Test 시작 ==");
        service.executeTransactional("JDBC", () -> {
            System.out.println("Action executed using JDBC TM");
        });
        System.out.println("== JDBC Transaction Manager Test 종료 ==");
    }
```
실행결과
```
== JPA Transaction Manager Test 시작 ==
[JPA] Transaction started.
Action executed using JPA TM
[JPA] Transaction committed.
== JPA Transaction Manager Test 종료 ==
== JDBC Transaction Manager Test 시작 ==
[JDBC] Transaction started.
Action executed using JDBC TM
[JDBC] Transaction committed.
== JDBC Transaction Manager Test 종료 ==
```

실제 TransactionManager는 훨씬 복잡하겠지만<br>
어쨋든 두개의 구현체 모두 @Transactional 어노테이션을 사용할 수 있는걸 확인했다.

<br>


위에서 알아본 DI가 빛을 발하는 순간.<br>
    + 뒤에서 알아볼 AOP도 적용된 어노테이션이다.



Spring Batch에서 TransactionManager를 직접 설정했던 작업,<br>
다중 DB를 구축할때 직접 주입했던 작업이 떠오르며<br>
조금 더 명확하게 이해가 되었다.


좋았던 글<br>
<https://sabarada.tistory.com/127>



### AOP

**AOP란?**
- AOP란 비즈니스 로직에 공통적으로 적용되는 기능을 분리하여 관리하는 것을 뜻합니다.<br>
  특히 인증, 로깅, 트랜잭션에서 유용합니다.








## 2. Spring Bean, Life Cycle

Spring Bean이란?
- IoC 컨테이너 안에 들어있는 객체를 spring bean이라고 합니다<br>
  개발자는 이 bean 객체를 DI로 주입받아 사용가능합니다.

Bean Scope란?
- 스프링은 빈이라는 개념으로 객체를 관리합니다<br>
  이 객체들의 생명주기를 Ioc 컨테이너에서 관리하는데<br>
  이 범위를 bean scope라고 합니다<br>
  추가적으로 빈 스코프 기본 전략은 싱글톤입니다.

싱글톤이 뭔데?
- 하나의 객체를 공유하는 디자인 패턴입니다<br>
  이를 사용할 때 같은 객체를 공유하기 때문에 값도 공유한다는 점을 유의해 개발해야 합니다

싱글톤이 뭐가 좋은데?
- DI를 사용하며 주입받은 객체를 계속해서 생성한다면 많은 메모리를 차지할 것입니다<br>
    하지만 싱글톤은 객체를 한번만 생성하고 계속해서 사용하기 때문에 메모리를 효율적으로 사용할 수 있습니다


## 3. Spring Annotation

코드에 부가적인 기능을 수행하도록 하는 기술.<br>
Spring에서 다양한 설정을 간편하게 처리하는데,<br>
주로 reflection, proxy패턴을 사용해 동작을 구현한다.

Proxy는 AOP에서 살펴보았고,<br>
reflection은 구체적인 클래스 타입을 알지 못해도<br>
클래스 메서드, 타입, 변수에 접근하게 해주는 JAVA 기술이다.<br>

### Reflection




참고한 글<br>
<https://cocoyong.tistory.com/entry/Spring-Reflection-%EA%B0%84%EB%8B%A8%ED%9E%88-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0>








## 4. Unit Test / Integration Test



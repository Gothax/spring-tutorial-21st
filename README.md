# spring-tutorial-21st

<br>
면접용 정리 - 경어체로 작성<br>
나만의 정리 - 평어체로 작성<br>

이 글의 하이라이트는 맨 마지막에 있습니다


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



### 🔥 AOP 

AOP란 비즈니스 로직에 공통적으로 적용되는 기능을 분리하여 관리하는 것을 뜻한다.<br>
특히 인증, 로깅, 트랜잭션에서 유용하다.

구체적으로

애플리케이션 로직은 **핵심 기능**과 **부가 기능**으로 나눌 수 있다.<br>
그리고 부가 기능은 보통<br>
여러 곳에서 동일하게 사용된다.<br>
그리고 이를 횡단 관심사라고 한다.

![Image](https://github.com/user-attachments/assets/19570869-0f24-4b67-bdff-2a89db0d90ee)

사진에 있는 로그 추적 기능을 100개의 클래스에서 사용하면<br>
100개의 클래스에 로그 추적 코드를 넣어야 한다.<br>
그리고 로그 추적 기능을 변경한다면<br>
100개의 클래스를 수정해야 한다.

AOP는 이런 문제를 해결하기 위해 등장했다.<br>

![Image](https://github.com/user-attachments/assets/3074b331-dcce-426a-9e0b-45bfee861ef0)


<br>

우선 Spring에서 AOP를 적용하는 방식을 **이론적으로** 알아보자.<br>
지금은 용어 정리, 원리를 간단하게 파악하고<br>

어노테이션에 대해 알아본 후<br>
**실질적으로** 적용하는 것은 마지막에.<br>



### AOP 적용 방식

3가지 방식이 있다.
- 컴파일 시점 (weaving)
- 클래스 로딩 시점 (weaving)
- 런타임 시점 (프록시)

이것만 해도 방대한 양이고<br>
완벽히 이해하지 못해서 결론만 적겠다.<br>

AOP의 대표적인 구현으로 AspectJ 프레임워크가 있다.<br>
그리고 컴파일 시점, 클래스 로딩 시점에 적용하는 AOP 방식은 AspectJ를 직접 사용해야 한다.<br>

구체적으로 JAVA를 실행할때 복잡한 옵션을 걸어주거나<BR>
클래스 로더 조작기를 설정해야한다.<br>
(어렵다는 뜻)<br>


Spring 컨테이너, DI, 프록시, bean post processor 개념을 모두 사용해<br>
쉽게 사용할 수 있도록 만들어 놓은 것이 Spring AOP이고<br>
프록시를 사용한 런타임 시점에 적용하는 방식을 사용한다.<BR>

정확하게 AspectJ 문법을 차용하고, 프록시 방식으로 AOP를 적용한다.<br>


![Image](https://github.com/user-attachments/assets/39fb5984-8725-4d5f-97c2-9463d5ed0dcb)
![Image](https://github.com/user-attachments/assets/a1c8e3e8-da94-42be-8786-c3c36be9192c)


결론은<br>
쉽게 사용할 수 있도록 만들어 놓은거 쓰자.

### Spring AOP 용어 정리


![Image](https://github.com/user-attachments/assets/328b966f-c2bd-43e9-9d3e-e22e6007aa99)


알아야 할것만 정리해 보면<br>
- JoinPoint : 어드바이스가 적용될 수 있는 지점 (메서드 실행 지점)
- Pointcut : JoinPoint 중 실제로 어드바이스가 적용되는 지점
- Advice : 실제로 어드바이스가 하는 일<br>
   커스텀 예외 처리 해봤으면 RestControllerAdvice를 본적이 있을거다


어드바이스 종류
- @Around: 메서드 호출 전후에 수행, 가장 강력한 어드바이스, 조인 포인트 실행 여부 선택, 반환 값 변환, 예외변환 등이 가능
- @Before:: 조인 포인트 실행 이전에 실행
- @AfterReturning: 조인 포인트가 정상 완료후 실행
- @AfterThrowing: 메서드가 예외를 던지는 경우 실행
- @After: 조인 포인트가 정상 또는 예외에 관계없이 실행(finally)

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


## 🔥 3. Spring Annotation

annotation이란 코드에 부가적인 기능을 수행하도록 하는 기술.<br>
Spring에서 다양한 설정을 간편하게 처리하는데,<br>
주로 reflection, proxy패턴을 사용해 동작을 구현한다.

프록시 패턴은<br>
JPA 프록시 객체, 프록시 서버를 들어봤을 텐데 이와 비슷한 개념이다.<br>
간단하게 가짜 참조, 즉 대리자를 이용하는 거라고 생각하면 될 듯.

reflection, proxy만 해도 너무 방대한 양이고<br>
설명할 정도까지 이해를 못해서 넘어가겠다.

### 커스텀 어노테이션

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Trace {

}
```

@Target
Annotation이 어디에 위치할 수 있는지 제한한다
- ElementType.FIELD : 클래스의 필드에 적용
- ElementType.METHOD : 메서드에 적용
- ElementType.CONSTRUCTOR : 생성자에 적용

@Retention
Annotation의 유지 기간을 정의한다.
- RetentionPolicy.SOURCE : runtime때 제거
- RetentionPolicy.CLASS : (디폴트) 컴파일 후 .class 파일에 유지, runtime때 제거
- RetentionPolicy.RUNTIME : runtime때 유지





## 4. Unit Test / Integration Test

단위 테스트는 전체 코드 중 작은 부분을 테스트하는 것이다. <br>
통합 테스트는 시스템들이 서로 어떻게 상호작용하고 제대로 작동하는지 테스트하는 것을 의미한다.

실질적으로 어떻게 작성하는지 보자


대부분 작성해본 테스트 코드가 Unit Test일거다.<br>
test 환경 데이터베이스는 따로 설정하는 경우도 있고<br>
Mock 객체를 사용하는 경우도 있고<br>
데이터가 아직 안들어가 있다면 그냥 테스트를 돌리는 경우도 있다.

개인적으로 테스트환경용<br>
h2 인메모리 데이터베이스를 설정하는게 좋다고 생각한다.<br>
![Image](https://github.com/user-attachments/assets/357f889c-9d46-44f2-8d05-57bf0acb72b0)<br>
간단하게 테스트 쪽에도 환경변수 설정해주면 된다
```java
    @Test
    @DisplayName("모든 로또 조회")
    void findAllLottos() {
        // given
        Lotto lotto = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        // when
        lottoRepository.addLotto(lotto);
        // then
        assertThat(lottoRepository.findAllLottos()).contains(lotto);
        assertThat(lottoRepository.getLottoCount()).isEqualTo(1);

    }
```


튜토리얼에 나온 코드가 통합 테스트이다.<br>
mock request를 보내 응답을 확인
```java
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("HelloController 호출 시 Greetings 메세지 출력")
    void getHello() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(content().string("Greetings from Spring Boot!"));

    }

}
```

## 5. 🔥 Spring AOP + Annotation 응용 🔥

가상의 상황을 생각해 보고<br>
그 상황을 해결하기 위해 AOP를 사용해 보자.

### RETRY

응답 처리 중<br>
외부 API를 호출하는 로직 있다고 가정하자.<br>
그런데 5번 요청 중 1번 꼴로 요청이 실패하면 어떻게 해결해야 할까?<br>

~~그런 경험이 있는지 생각해보니<br>
Open AI API를 사용할때 30번에 1번정도 실패했던 것 같다.<br>~~

#### 문제상황 코딩

5번에 한번 DB에 저장하는 작업이 실패
```java
@Repository
public class ExamRepository {

    private static int sequence = 0;
    
    public String save(String itemId) {
        sequence++;
        if (sequence % 5 == 0) {
            throw new IllegalArgumentException("Invalid item id: " + itemId);
        }
        return "ok";
    }

}
```
```java
@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
```

테스트 코드
```java
@SpringBootTest
@Slf4j
class ExamServiceTest {

    @Autowired
    ExamService examService;

    @Test
    void test(){
        for (int i = 1; i < 6; i++) {
            log.info("client request: {}", i);
            examService.request("item" + i);
        }
    }

}
```

![Image](https://github.com/user-attachments/assets/1329af92-c58e-4987-bca9-f0124cbdd91b)

당연히 5번째 작업에서 실패한다

#### 해결

실패했을 경우<br>
다시 시도하는 로직을 만들면 해결할 수 있을 것 같다.

그리고 

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {

    int value() default 3;

}
```

```java
@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry: {}", joinPoint.getSignature(), retry);

        int maxValue = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1; retryCount < maxValue; retryCount++) {
            try {
                log.info("[retry] trying {} times, Max retry: {}", retryCount, maxValue);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }


}
```

@Retry 추가
```java
@Repository
public class ExamRepository {

    private static int sequence = 0;


    @Trace
    @Retry(value = 4)
    public String save(String itemId) {
        sequence++;
        if (sequence % 5 == 0) {
            throw new IllegalArgumentException("Invalid item id: " + itemId);
        }
        return "ok";
    }

}
```

재시도 로직을 만들어서 성공<br>
![Image](https://github.com/user-attachments/assets/3e731a94-850b-4219-834c-e3d628906f56)

![Image](https://github.com/user-attachments/assets/43158bb5-503a-4a4f-bdf0-9a7857bf0354)

실제 상황에서 꽤 유용할 것 같다.


### 사용자 구분 LOGGING

실제 운영 환경에서는<BR>
동시에 요청이 오는데, 이때 로그는 순차적으로 쌓이지 않는다.

![Image](https://github.com/user-attachments/assets/89d5f10e-1812-43be-ae2c-e4165f9a479a)

이를 해결하기 위해 MDC를 사용할 수 있다. <br>
MDC란 Mapped Diagnostic Context로<br>
Map 형식을 사용해 클라이언트 특징적인 데이터를 저장하기 위한 메커니즘이다.

추가적으로<br>
Slf4j, logback 등 우리가 사용하는 로거에서 MDC를 지원한다.<BR>

간단하게 코딩을 해보자
MDC 설정 후 AOP 적용, 필터 적용 2가지 방법 모두 해보겠다.<BR>

우선 출력 형식 설정<br>
logback.xml
```xml
<configuration>
    <appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>[%d{yyyy.MM.dd HH:mm:ss.SSS}] - [%-5level] - [%X{request_id}] - [%logger{5}] - %msg%n</pattern>
        </encoder>
    </appender>
    <root level="info">
        <appender-ref ref="consoleAppender"/>
    </root>
</configuration>

```

#### AOP 적용


```java
@Slf4j
@Aspect
@Component
public class TestAspect {

    @Pointcut("execution(* com.ceos21.spring_boot.controller.*.*(..))")
    public void controllerAdvice() {

    }

    @Before("controllerAdvice()")
    public void requestLogging(JoinPoint joinPoint) {
        MDC.put("traceId", UUID.randomUUID().toString());

        log.info("REQUEST TRACING_ID : {}", MDC.get("traceId"));
    }

    @AfterReturning(pointcut = "controllerAdvice()", returning = "result")
    public void responseLogging(JoinPoint joinPoint, Object result) {
        log.info("RESPONSE TRACING_ID : {}", MDC.get("traceId"));
        MDC.clear();
    }
}

```

![Image](https://github.com/user-attachments/assets/fa5d6ef9-4e27-478e-be3a-18891d8f7b58)


똑똑한 인텔리제이가 AOP advice를 적용했다고 알려준다.

<br>


적용 전
![Image](https://github.com/user-attachments/assets/38d2688c-b349-41da-8903-e7e0a998cf68)



적용 후 로그

![Image](https://github.com/user-attachments/assets/b1bec33c-837a-4f39-ad3b-b5d80531d816)

#### 필터 적용


```java
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class MDCLoggingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final UUID uuid = UUID.randomUUID();
        MDC.put("request_id", uuid.toString());
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }
}
```

적용 후 로그
![Image](https://github.com/user-attachments/assets/f689264a-484b-4b9d-83c9-d36de1306268)

<br>

#### 추가

운영환경에서 Nginx를 많이 사용하는데<br>
nginx 로그, tomcat 로그를 함께 봐야하는 경우가 많다.

이때 동일한 request_id를 공유하면 로그 파악에 더 도움이 된다고 한다.<br>

![Image](https://github.com/user-attachments/assets/1ca17a53-c823-4df9-b321-a70a83dfe1bc)


참고<br>
<https://dev-jwblog.tistory.com/126>

https://0soo.tistory.com/246

https://mangkyu.tistory.com/266

<https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B3%A0%EA%B8%89%ED%8E%B8>


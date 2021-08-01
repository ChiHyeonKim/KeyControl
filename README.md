# 카카오페이 사전과제 2 - KEY 발급 시스템

## 개발 환경
- 기본 환경
    - IDE: IntelliJ
    - OS: WINDOWS
    - GIT
    - POSTMAN
- Server
    - Java
    - Spring Boot 
    - Gradle
    - Junit


API 명세
---
### KEY 정보를 등록하는 API

#### 요청

| 항목 | 값             |
| ---- | -------------- |
| URI  | `POST` /api/key/register |

`항목`

| 이름       |  타입  | 필수 | 설명                                                         |
| ---------- | :----: | :---: | ------------------------------------------------------------ |
| key     | string |  ○   | 등록할 KEY 명                                          |
| type      | string  |  ○   | KEY의 종류 (문자형,숫자형)                                           |
| generator      | string  |     | DB의 종류 선택                                           |
| min_length      | int  |     | 숫자형 KEY인 경우 자리수                                          |

요청 예시

```json
{
  "key": "policy-number",
  "description": "보험 증서 번호에 사용할 KEY 값으로 테이블 PK 로 사용",
  "type": "number",
  "generator": "mysql",
  "min_length": 10
}
```

#### 응답

응답 예시
```
200 OK
```


### 각 KEY 별로 새로운 KEY를 하나 발급 받는 API

#### 요청

| 항목 | 값             |
| ---- | -------------- |
| URI  | `GET` /api/key/요청 KEY |

요청 예시
```
GET /api/key/claim-number
```

#### 응답

응답 예시
```
"value": "UCAA-E22A-OOKP-0021"
```


## 기능 요구사항
### 필수사항
1. KEY 정보를 등록하는 API 구현
2. 각 KEY 별로 새로운 KEY를 하나 발급 받는 API

### 제약사항
1. 숫자형 KEY 정보를 등록할 때, 'min-length' 대신 'min_length' 로 바꿔 입력한다.

(Client에서 전달 받은 JSON 데이터를 자바 객체로 변환할 때 객체의 변수명을 선언하는데, 
자바 변수명에는 '-' 특수문자는 입력이 불가능하다. JSON과 JAVA 객체의 MAPING 을 위해 - 대신 __ 로 입력받는다.

2. 숫자형 KEY 생성 시, min_length 의 최대 값은 10

(원래는 min_length 의 자료형을 Long 타입으로 하여 min_length 를 10 이상으로 하려고 했다.
하지만 double 형으로 생성한 데이터를 long 형으로 변환하는데 어려움이 있어 
min_length 의 자료형을 부득이하게 int로 정하여서 최대 값을 10으로 제약하였다.

RANDOM 숫자형 KEY 생성 시 Math.random() 을 이용하여 생성하게 되는데, 
Math.random() 함수에서 추출하는 값의 타입은 double 타입 이다.
double 타입을 long 타입으로 변환하려 방법을 찾아봤지만 변환에 실패하였다.)

## 핵심 문제 해결 전략
### 문자형, 숫자형 KEY 발급 기능 구현
- 발급 KEY의 종류가 문자형 인 경우 GenerateStringKey 클래스를 이용하여 발급 가능하게 구현
- 발급 KEY의 종류가 숫자형 인 경우 GenerateIntKey    클래스를 이용하여 발급 가능하게 구현

###  KEY 는 DB TABLE 의 PK 로 이용 할 수 있는 NOT NULL, UNIQUE 특징 기능 구현
- KEY를 새롭게 생성할 떄 기존에 저장된 KEY와 중복이 있는지 검사하기 위해 isOverlap 메서드를 이용해 구현
- 중복된 KEY가 없을 때 까지 무한 반복

### 총평
이번 과제 해결을 위해 Spring Framework, Spring Boot, REST API에 대해 깊이있게 공부할 수 있었다. 회사에서는 Spring 이 아닌 다른 상용 Framework 를 사용하고 있어서 Spring Framework를 사용할 일이 잘 없었는데 이번 과제 해결을 위해서 처음으로 사용하여 보았다.

MVP 패턴, Controller -> Service -> Repository 로 로직 나누기 등 Spring 전체적인 개발 컨셉은 현재 회사에서 사용하는 Framework 와 비슷했다.

또한 RESPful API 의 개념과 구현에 대해서도 확실하게 공부할 수 있었다.

새로운 것을 배운다는 것은 힘들지만 재미있다.
언어에 대한 인식을 바꾸게 해준 너무 좋은 경험.

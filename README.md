# Infra Post API Server

Infra Development에 사용되는 포스트 서비스 API 입니다

---
## 📝 개발 스펙
| ORM  | Client     | DB 버전관리   | 보조      |
|------|------------|-----------|---------|
| JPA  | RestClient | liquibase | vavr.io |

---
## 관련 저장소
* **인프라**\
https://github.com/seculoper235/Kubernetes_Development


* **Frontend**\
https://github.com/seculoper235/infra-test-web

---
## 💡 관련 글
* [어떻게 데이터베이스를 설계할 것인가?]()


* [서비스 간에 어떻게 통신할 것인가? - RestClient]()


* [DB) id와 uid, uuid의 차이?]()


* [HTTP/1.1 header parser received no bytes 에러]()


* [Option 객체 직렬화 오류]()

---
## 📝 backend Image 및 DB 서버
### Backend 이미지 생성
```shell
// 소스코드 빌드
gradle build

// 이미지 생성
docker build -t post-service:1.0 -f ./docker/Dockerfile .
```

### DB 서버 생성
```shell
// 컨테이너 생성
docker-compose -f ./docker/docker-compose.yml up -d
```

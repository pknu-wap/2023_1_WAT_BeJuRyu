## API

### 로그인

* GET /auth/login?token={카카오토큰}
* Response

```json
{
  "access": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzM0OTYyMzgwIiwiaWF0IjoxNjgzNDQ3MTMxLCJleHAiOjE2ODM0NTQzMzF9.V16ATqEv-v5mns9-i6XE686kCA3MTern1B7JVsjgp9Y",
  "refresh": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODM0NDcxMzEsImV4cCI6MTY4NDA1MTkzMX0.KA9cLJ4uBINVvIsQzwv-w8QtrMe8yxpjU4caYWKdTLs"
}
```

### 토큰 재발행

* POST /auth/refresh
* Request

```json
{
  "access": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzM0OTYyMzgwIiwiaWF0IjoxNjgzNDQ3MTMxLCJleHAiOjE2ODM0NTQzMzF9.V16ATqEv-v5mns9-i6XE686kCA3MTern1B7JVsjgp9Y",
  "refresh": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODM0NDcxMzEsImV4cCI6MTY4NDA1MTkzMX0.KA9cLJ4uBINVvIsQzwv-w8QtrMe8yxpjU4caYWKdTLs"
}
```

* Response

```json
{
  "access": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzM0OTYyMzgwIiwiaWF0IjoxNjgzNDQ3NjQxLCJleHAiOjE2ODM0NTQ4NDF9.yCqU6b7P-JJH82qkst7rYQPlSRSUZ8qcR8qEXB6qee8",
  "refresh": null
}
```

## 토큰이 필요한 API 호출 시

헤더에 **"Authorization":"Bearer 토큰어쩌고"** access 토큰을 같이 보낸다.

만약 토큰이 없으면 400(Bad Request), 토큰이 만료됐으면 401(Unauthorized)

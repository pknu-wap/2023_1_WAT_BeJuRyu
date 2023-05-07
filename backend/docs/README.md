## 📑 작업 사항

JWT를 사용한 로그인 API를 구현한다.

## To-do

- [x] 카카오 API 호출 클라이언트 (uid, nickname 받아오기)
- [x] JwtTokenProvider
- [x] LoginService
    - [x] kakao uid로 JwtToken 발행(엑세스, 리프레시)
    - [x] 액세스, 리프레시 토큰 받아서 JwtToken 리프레시 및 리프레시 토큰 저장
        - 리프레시 토큰은 DB에 저장하지 않음
        - 단순히 유효성만 검증한 후 액세스 토큰을 기반으로 새로 토큰 생성해서 줌
- [ ] AuthInterceptor implements HandlerInterceptor
- [ ] JwtArgumentResolver implements HandlerMethodArgumentResolver
- [ ] AuthConfig implements WebMvcConfigurer
    - [ ] url 기반 인터셉터 추가
    - [ ] 어노테이션 기반 ArgumentResolver 추가
- [ ] 전역 ExceptionHandler
    - [ ] 토큰이 만료됐으면 401
    - [ ] 토큰이 없으면 400

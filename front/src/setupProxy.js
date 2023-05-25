const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    createProxyMiddleware("/api", {
      target: "http://141.164.49.27",
      // target: "http://localhost:8080", // 서버 URL
      pathRewrite: { "^/api": "" },
      changeOrigin: true,
      secure: false,
      headers: {
        Host: "141.164.49.27", // 추가된 부분
        "Access-Control-Allow-Origin": "*", // 모든 도메인을 허용하도록 설정합니다.
      },
    })
  );

  app.use(
    createProxyMiddleware("/auth/login", {
      target: "http://141.164.49.27",
      changeOrigin: true,
      secure: false,
      headers: {
        Host: "141.164.49.27",
        "Access-Control-Allow-Origin": "*", // 모든 도메인을 허용하도록 설정합니다.
      },
    })
  );
};

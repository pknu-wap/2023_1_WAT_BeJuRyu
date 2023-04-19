const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "http://localhost:8080", // 서버 URL or localhost:포트번호. 임시로 8080
      changeOrigin: true,
      secure: false,
    })
  );
};

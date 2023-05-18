const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "141.164.49.27:3000",
      // target: "http://localhost:8080", // 서버 URL
      secure: false,
    })
  );
};

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
      },
    })
  );
};

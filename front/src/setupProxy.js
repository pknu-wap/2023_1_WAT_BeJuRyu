const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    createProxyMiddleware("/api", {
      target: "141.164.49.27:3000",
      // target: "http://localhost:8080", // 서버 URL
      pathRewrite: { "^/api": "" },
      changeOrigin: true,
      secure: false,
    })
  );
};

const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })

module.exports = defineConfig({
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",

  devServer: {
      proxy: {
          '/': {
              target: 'http://localhost:8080/',
              pathRewrite: { '^/': '' },
              changeOrigin: true,
              secure: false,
              ws: false,
          }
      }
  }
});
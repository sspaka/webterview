const { defineConfig } = require("@vue/cli-service");
// module.exports = defineConfig({
//   transpileDependencies: true,
//   // publicPath: process.env.NODE_ENV === 'production' ? './' : '/'
//   publicPath: ''
// })

module.exports = defineConfig({
  publicPath: process.env.NODE_ENV === "production" ? "/" : "/",
  devServer: {
    disableHostCheck: true,
  },
});

// module.exports = defineConfig({
//   publicPath: '',
//   devServer: {

//         disableHostCheck: true
//     }

//       proxy: {
//           '/': {
//               target: 'https://i7c205.p.ssafy.io/api/',
//               // target: 'http://localhost:3000/api/',
//             //   target: 'https://i7c205.p.ssafy.io/api/',
//               target: 'http://localhost:3000/api/',
//               pathRewrite: { '^/': '' },
//               changeOrigin: true,
//               secure: false,
//               ws: false,
//           }
//       }
//   }

// });

// module.exports = defineConfig({
//   outputDir: "../src/main/resources/static",
//   indexPath: "../static/index.html",

//   devServer: {
//       proxy: {
//           '/': {
//               target: 'https://i7c205.p.ssafy.io/api/',
//               // target: 'http://localhost:3000/api/',
//               pathRewrite: { '^/': '' },
//               changeOrigin: true,
//               secure: false,
//               ws: false,
//           }
//       }
//   }
// });

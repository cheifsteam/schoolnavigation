module.exports = {
  devServer: {
    host: 'localhost',
    port: 8080,
    proxy:{
      '/api':{
        target:'http://10.24.4.42:8080',
        secure:false,
        changeOrigin:true,
        pathRewrite:{
          '^/api':'/'
        }
      }
    }
  }
}

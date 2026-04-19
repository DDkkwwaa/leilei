import Vue from 'vue'
import App from './App'
import router from './router';
import element from 'element-ui' 
import 'element-ui/lib/theme-chalk/index.css' // 导入组件样式
import store from '@/store';   //状态管理
 import * as echarts from 'echarts'; //引入echart
 Vue.prototype.$echarts = echarts
import axios  from 'axios';

Vue.config.productionTip = false
Vue.use(element)  // 全局使用
 
Vue.prototype.$http = axios
axios.defaults.baseURL="http://localhost:8086"
// axios配置 拦截器
axios.interceptors.request.use(config =>{
  var token =sessionStorage.getItem('token')
  config.headers['Authorization'] = token  //调试的时候可以直接写死 写token
  return config;
},error =>{
  return Promise.reject(error);
})

// 配置前置守卫
router.beforeEach((to, from, next) => {
  const username =sessionStorage.getItem('username')
  if(to.name !='Login' && !username) {
    next({name:'Login'})
  }else{next() }  
})

 /* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store ,  //挂载后所有的组件都能使用
  render: h => h(App)
}).$mount('#app')  //挂载


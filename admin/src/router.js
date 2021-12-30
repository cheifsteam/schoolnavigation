import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Admin from './views/admin.vue'
import Welcome from './views/admin/welcome.vue'
import School from './views/admin/school.vue'
import Category from './views/admin/category.vue'
import Member from './views/admin/member.vue'
import User from './views/admin/user.vue'
import Schoolinfo from './views/admin/schoolInfo.vue'

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [{
    path: "*",
    redirect: "/login",
  }, {
    path: "",
    redirect: "/login",
  }, {
    path: "/login",
    component: Login
  }, {
    path: "/",
    name: "admin",
    component: Admin,
    meta: {
      loginRequire: true
    },
    children: [{
      path: "welcome",
      name: "welcome",
      component: Welcome,
    }, {
      path: "admin/school",
      name: "admin/school",
      component: School,
    }, {
      path: "admin/category",
      name: "admin/category",
      component: Category
    }, {
      path: "admin/member",
      name: "admin/member",
      component: Member
    }, {
      path: "admin/user",
      name: "admin/user",
      component: User
    }, {
      path: "admin/schoolinfo",
      name: "admin/schoolinfo",
      component: Schoolinfo
    }]
  }]
})

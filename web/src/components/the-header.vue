<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="/">
          <i class="ace-icon fa fa-table"></i>&nbsp;学校导航系统
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <router-link class="nav-link" to="/list">全部学校</router-link>
            </li>
          </ul>

          <nav class="navbar navbar-light bg-dark">
            <form class="form-inline">
              <input type="text" v-model="keywords" class="form-control mr-sm-2"  placeholder="查询" aria-label="Search">
              <router-link v-bind:to="'/list?keywords=' + keywords" @click.native="flushCom" class="btn btn-outline-success my-2 my-sm-0">查询</router-link>
            </form>
          </nav>



          <span v-show="loginUser.id" class="text-white pr-3">您好： {{loginUser.nickname}}</span>
          <button v-show="loginUser.id" v-on:click="logout()" class="btn btn-outline-light my-2 my-sm-0">退出登录</button>
          <button v-show="!loginUser.id" v-on:click="openLoginModal()" class="btn btn-outline-light my-2 my-sm-0">登录/注册</button>
        </div>
      </div>
    </nav>

    <the-login ref="loginComponent"></the-login>
  </header>
</template>

<script>

import TheLogin from "./login"
export default {
  name: 'theHeader',
  components: {TheLogin},
  data: function () {
    return {
      user:{},
      loginUser: {},
      keywords: '',

    }
  },
  mounted() {
    let _this = this;
    _this.loginUser = Tool.getLoginUser();
  },
  methods: {
    /**
     * 打开登录注册窗口
     */
    openLoginModal() {
      let _this = this;
      _this.$refs.loginComponent.openLoginModal();
    },

    setLoginUser(loginUser) {
      let _this = this;
      _this.loginUser = loginUser;
    },

    logout () {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/signOut').then((response)=>{
        let resp = response.data;
        if (resp.code == 200) {
          Tool.setLoginUser(null);
          _this.loginUser = {};
          Toast.success("退出登录成功");
          _this.$router.push("/");
        } else {
          Toast.warning(resp.msg);
        }
      });
    },

    // goSearch () {
    //   let _this = this;
    //
    //   _this.$router.push({path: '/list', query: {keywords: this.keywords}})
    //   //_this.$router.push('/list/' + this.keywords)
    //
    // },

    flushCom () {
      this.$router.go(0);
    }

  }
}
</script>
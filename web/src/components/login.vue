<template>
  <div id="login-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-login" role="document">
      <div class="modal-content">
        <div class="modal-body">

          <div class="login-div" v-show="MODAL_STATUS === STATUS_LOGIN">
            <h3>登&nbsp;&nbsp;录</h3>

            <div class="form-group">
              <input v-model="user.phoneNumber" class="form-control" placeholder="手机号">
            </div>

            <div class="form-group">
              <input class="form-control" type="password" placeholder="密码" v-model="user.password">
            </div>


            <div class="form-group">
              <button v-on:click="login()" class="btn btn-primary btn-block submit-button">
                登&nbsp;&nbsp;录
              </button>
            </div>
            <div class="form-group">
              <div class="checkbox">
                <label>
                  <input type="checkbox" class=" rememberMe" v-model="rememberMe"> 记住密码
                </label>
                <div class="pull-right">
                  <a href="javascript:;" v-on:click="toRegisterDiv()">我要注册</a>
                </div>
              </div>
            </div>
            <div class="form-group to-register-div">
            </div>
          </div>

          <div class="register-div" v-show="MODAL_STATUS === STATUS_REGISTER">
            <h3>注&nbsp;&nbsp;册</h3>
            <div class="form-group">
              <input id="register-mobile" v-model="userRegister.phoneNumber"
                     class="form-control" placeholder="手机号">
            </div>

            <div class="form-group">
              <input id="register-name" v-model="userRegister.nickname"
                     class="form-control" placeholder="昵称">
            </div>
            <div class="form-group">
              <input id="register-password" v-model="userRegister.password"
                     class="form-control" placeholder="密码" type="password">
            </div>
            <div class="form-group">
              <input id="register-confirm-password" v-model="userRegister.passwordConfirm"
                     class="form-control" placeholder="确认密码"
                     name="userRegisterConfirm" type="password">
            </div>

            <div class="form-group">
              <button class="btn btn-primary btn-block submit-button" v-on:click="register()">
                注&nbsp;&nbsp;册
              </button>
            </div>
            <div class="form-group to-login-div">
              <a href="javascript:;" v-on:click="toLoginDiv()">我要登录</a>
            </div>
          </div>

        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
</template>

<script>

export default {
  name: 'the-login',
  data: function () {
    return {
      // 模态框内容切换：登录、注册、忘记密码
      STATUS_LOGIN: "STATUS_LOGIN",
      STATUS_REGISTER: "STATUS_REGISTER",
      MODAL_STATUS: "",

      user: {},
      userRegister: {},

      rememberMe: true, // 记住密码
    }
  },
  mounted() {
    let _this = this;
    _this.toLoginDiv();
  },
  methods: {

    /**
     * 打开登录注册窗口
     */
    openLoginModal() {
      let _this = this;
      $("#login-modal").modal("show");
    },

    //---------------登录框、注册框-----------------
    toLoginDiv() {
      let _this = this;

      // 从缓存中获取记住的用户名密码，如果获取不到，说明上一次没有勾选“记住我”
      let rememberUser = LocalStorage.get(LOCAL_KEY_REMEMBER_USER);
      if (rememberUser) {
        _this.user = rememberUser;
      }

      _this.MODAL_STATUS = _this.STATUS_LOGIN
    },

    toRegisterDiv() {
      let _this = this;
      _this.MODAL_STATUS = _this.STATUS_REGISTER
    },

    register() {
      let _this = this;

      if(_this.userRegister.passwordConfirm !== _this.userRegister.password) {
        Toast.warning("密码不一致");
      }


      // 调服务端注册接口
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/register', _this.userRegister).then((response) => {
        let resp = response.data;
        if (resp.code == 200) {
          Toast.success("注册成功");
          $("#login-modal").modal("hide");
          _this.userRegister = {};
        } else {
          Toast.warning(resp.msg);
        }
      })
    },

    //---------------登录框-----------------
    login () {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/login', _this.user).then((response)=>{
        let resp = response.data;
        if (resp.code == 200) {
          console.log("登录成功：", resp);
          let loginUser = resp.data;
          Tool.setLoginUser(resp.data);

          // 判断“记住我”
          if (_this.rememberMe) {
            // 如果勾选记住我，则将用户名密码保存到本地缓存

            console.log(_this.user.phoneNumber);
            console.log(_this.user.password);

            LocalStorage.set(LOCAL_KEY_REMEMBER_USER, {
              phoneNumber: _this.user.phoneNumber,
              password: _this.user.password,
            });
          } else {
            // 没有勾选“记住我”时，要把本地缓存清空，否则下次显示登录框时会自动显示用户名密码
            LocalStorage.set(LOCAL_KEY_REMEMBER_USER, null);
          }

          _this.$parent.setLoginUser(loginUser);
          $("#login-modal").modal("hide");
        } else {
          Toast.warning(resp.msg);
          _this.user.password = "";
        }
      });
    },


  }
}
</script>

<style scoped>
/* 登录框 */
.login-div .input-group-addon {
  padding: 0;
  border: 0;
}

#login-modal h3 {
  text-align: center;
  margin-bottom: 20px;
}

#login-modal .modal-login {
  max-width: 400px;
}

#login-modal input:not(.rememberMe) {
  height: 45px;
  font-size: 16px;
}

#login-modal .submit-button {
  height: 50px;
  font-size: 20px;
}

#login-modal .to-login-div {
  text-align: center;
}
</style>

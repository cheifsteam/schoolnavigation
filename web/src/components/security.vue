<template>
  <div class="info">
    <p class="title">修改密码</p>
    <hr/>
    <div class="personal">
      <el-form :model="user" class="demo-ruleForm" label-width="80px">
        <el-form-item prop="username" label="旧密码">
          <el-input v-model="user.password" placeholder="旧密码"></el-input>
        </el-form-item>
        <el-form-item  prop="newPassword" label="新密码">
          <el-input   placeholder="新密码" v-model="user.newPassword" ></el-input>
        </el-form-item>
        <el-form-item  prop="passwordConfirm" label="再输一次">
          <el-input   placeholder="再输一次" v-model="user.passwordConfirm" ></el-input>
        </el-form-item>
      </el-form>
      <div class="btn">
        <div  @click="update">保存</div>
        <div  @click="goback"> 取消</div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "security",
  props: {
    user: {},

  },
  mounted() {
    let _this = this;
    this.user = Tool.getLoginUser();
  },
  methods:{
    update() {
      let _this = this;
      console.log(_this.user);
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/updatePassword', _this.user).then((response)=>{
        let resp = response.data;
        if (resp.code==200) {
          Toast.success("更新成功！");
        } else {
          Toast.warning(resp.msg)
        }
      })
    },
    goback () {
      this.$router.go(-1)
    },
  }

}

</script>

<style lang="scss" scoped>
@import '../assets/css/info.scss';
</style>
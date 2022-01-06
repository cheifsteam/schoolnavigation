<template>
  <div class="info">
    <p class="title">编辑个人资料</p>
    <hr/>
    <div class="personal">
      <el-form :model="user" class="demo-ruleForm" label-width="80px">
        <el-form-item prop="username" label="用户名">
          <el-input v-model="user.nickname" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item  prop="phoneNum" label="手机号">
          <el-input  oninput ="value=value.replace(/[^\d]/g,'')" placeholder="手机号" v-model="user.phoneNumber" ></el-input>
        </el-form-item>

        <el-form-item prop="img" label="头像">
        <el-upload
            :http-request="uploadSectionFile"
            :before-upload="beforeAvatarUpload">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>修改头像</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过10M</div>
        </el-upload>
        </el-form-item>
      </el-form>
      <div class="btn">
        <div  @click="update">保存</div>
        <div @click="goback"> 取消</div>
      </div>
        </div>

      </div>





</template>

<script>
import {swiperList} from "@/assets/data/swiper";


export default {
  name: "user",

  props: {
    user: {},

  },
  mounted() {
    let _this = this;
    this.user = Tool.getLoginUser();
  },
  data () {
    return {
      imageUrl: ''
    }
  },
  // computed: {
  //   ...mapGetters([
  //     'userId'
  //   ])
  // },
  methods: {
    update() {
      let _this = this;
      if (_this.user.nickname==null)
      {
        Toast.warning("用户呢称不能为空")
        return;
      }
      if(_this.user.nickname.length>20)
      {
        Toast.warning("呢称字数过多")
        return;
      }
      if (_this.user.phoneNumber.length!=11)
      {
        Toast.warning("手机号格式错误，应为11位")
        return;
      }
      console.log(_this.user);
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/updateInfo', _this.user).then((response)=>{

        let resp = response.data;
        if (resp) {

          Toast.success("更新成功！");
        } else {
          Toast.warning(resp)
        }
      })
    },
    goback () {
      this.$router.go(-1)
    },
    uploadSectionFile (param) {
      let form = new FormData()
      var that = this
      var dir='/img/user/'
      form.append('file', param.file)
      form.append('dir', dir)
      that.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/updateImg',form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        onUploadProgress: progressEvent => {
          that.uploadPercent = (progressEvent.loaded / progressEvent.total * 100) | 0
        }
      }).then((res) => {
        Toast.success("上传成功！");
        console.log('上传结束')
        console.log(res)
      }).catch((err) => {
        Toast.error("上传错误'");
        console.log('上传错误')
        console.log(err)
      })
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 10
      if (!isJPG) {
        Toast.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        Toast.error('上传头像图片大小不能超过 10MB!')
      }
      return isJPG && isLt2M
    }
  },


}
</script>


<style lang="scss" scoped>
@import '../assets/css/info.scss';
</style>
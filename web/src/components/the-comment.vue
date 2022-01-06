<template>
  <div class="comment">


        <div>
          <img :src='attachImageUrl(user.img)' class="popular-img" alt="">
        </div>
        <ul class="popular-msg">
          <li class="name">{{user.nickname}}</li>
          <li class="content">{{comment.content}}</li>
        </ul>
        <hr>


  </div>

</template>

<script>


import comment from "../../../admin/src/views/admin/comment";

export default {
  name: "the-comment",

  props: {
    comment: {},

  },
  data()
  {
    return{
      user:{}
    }
  },
  mounted() {
    let _this = this;
    this.user = _this.getUser(_this.comment.userId)
  },
  methods:{
    attachImageUrl (srcUrl) {
      return srcUrl ? process.env.VUE_APP_SERVER + srcUrl || '/img/user/user.png' : process.env.VUE_APP_SERVER+ '/img/user/user.png'
    },
    getUser(id) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/get/' + id).then((response) => {
          let resp = response.data;
          _this.user = resp.data;
        })
      },
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/comment';
</style>
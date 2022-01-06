<template>
  <main role="main">
    <div>
      <div class="dqwz">
        <a class="a" href="/">
          &nbsp;首页>
        </a>

        <a>
          {{info.title}}
        </a>

      </div>
    <div class="m1">
        <div class="m1_z">

          <h1>{{info.title}}</h1>
          <h5>发布时间：{{info.time}}</h5>

          <h5>{{info.info}}</h5>
          </div>



      </div>

    </div>

  </main>

</template>

<script>
import TheInfo from "@/components/the-info";

export default {
  name: "infodetail",
  data: function () {
    return {
      id: "",
      info: {},
      infos:[],
    }
  },
  components:{
    TheInfo
  },
  mounted() {
    let _this = this;
    _this.id = _this.$route.query.id;
    _this.findInfo();
    _this.listNewInfo(1)
  },
  methods: {
    findInfo() {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/schoolInformation/get/' + _this.id).then((response) => {
        let resp = response.data;
        _this.info = resp.data;
        _this.info.time = new Date(_this.info.time)
        this.info.time = _this.info.time.getFullYear() + '-' + _this.checkTime(_this.info.time.getMonth() + 1) + '-' + _this.checkTime(_this.info.time.getDate())

      })
    },

    listNewInfo() {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/schoolInformation/home').then((response)=>{
        let resp = response.data;
        if (resp.code == 200) {
          console.log(resp.data);
          _this.infos = resp.data.data;
        }
      }).catch((response)=>{
        console.log("error：", response);
      })
    },
    checkTime(i) {
      if (i < 10) {
        i = '0' + i
      }
      return i
    },
  }
}
</script>

<style scoped>
h5{
  word-wrap:break-word;

}
.card{
  display: inline-block;

  line-height: 30px;
  text-align: center;
  border: 2px solid #95d2f6;
  margin: 0 20px;

}
.m1 {
  width: 1100px;
  margin-right: auto;
  margin-left: auto;
  margin-top: 15px;
}
 .m1_z {
  width: 770px;
}
 .m1_y{

  margin-left: 200px;
   width: 300px;
 }
 .my-con{
   border-bottom-width: 0;
   border-bottom-style: none;
   border-bottom-color: #eee;
   padding-top: 10px;
   padding-bottom: 10px;
 }
 .my_tit{
   font-size: 16px;
   border-bottom-width: 1px;
   border-bottom-style: solid;
   border-bottom-color: #eee;
   margin-bottom: 15px;
   margin-right: auto;
   margin-left: auto;
 }

</style>
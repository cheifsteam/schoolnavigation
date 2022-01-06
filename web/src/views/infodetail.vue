<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="card-body">
        <div class="container">
          <div class="card mb-4 shadow-sm school">
          <h1>{{info.title}}</h1>
          <h5>发布时间：{{info.time}}</h5>

          <h5>{{info.info}}</h5>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div v-for="o in infos" class="col-md-12">
        <the-info v-bind:info="o"></the-info>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: "infodetail",
  data: function () {
    return {
      id: "",
      info: {},
      infos:[],
    }
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
</style>
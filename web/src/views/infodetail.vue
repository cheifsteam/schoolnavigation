<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="card-body">
        <div class="container">
          <h1>{{info.title}}</h1>
          <h5>发布时间：{{info.time}}</h5>
          <div class="card mb-4 shadow-sm school">
          <h5>{{info.info}}</h5>
          </div>
        </div>
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
    }
  },
  mounted() {
    let _this = this;
    _this.id = _this.$route.query.id;
    _this.findInfo();
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

</style>
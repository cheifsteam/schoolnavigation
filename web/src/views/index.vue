<template>
  <main role="main">

    <section class="jumbotron text-center">
      <div class="container">
        <h1>学校导航系统</h1>
        <p class="lead text-muted m-3">
          欢迎使用学校导航系统
        </p>
      </div>
    </section>

    <div class="album py-5 bg-light">
      <div class="container">
        <div class="title1">热门学校</div>
        <div class="row">
          <div v-for="o in news" class="col-md-4">
            <the-school v-bind:school="o"></the-school>
          </div>
        </div>

        <hr>

        <div class="title2">热门资讯</div>
        <div class="row">
          <div v-for="i in infos" class="col-md-4">
            <the-info v-bind:info="i"></the-info>
          </div>
        </div>
      </div>
    </div>


  </main>
</template>

<script>

import TheSchool from "../components/the-school";
import TheInfo from "../components/the-info";
export default {
  name: 'index',
  components: {TheSchool, TheInfo},
  data: function () {
    return {
      news: [],
      infos: [],
    }
  },
  mounted() {
    let _this = this;
    _this.listNew();
    _this.listNewInfo();
  },
  methods: {

    listNew() {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/school/getAll').then((response)=>{
        let resp = response.data;
        if (resp.code == 200) {
          console.log(resp.data);
          _this.news = resp.data.data;
        }
      }).catch((response)=>{
        console.log("error：", response);
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

  }
}
</script>


<style>
.title1{
  margin-bottom: 2rem;
  color: #fafafa;
  letter-spacing: 0;
  text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
  font-size: 2rem;
}
.title2{
  margin-bottom: 2rem;
  color: transparent;
  -webkit-text-stroke: 1px black;
  letter-spacing: 0.04em;
  font-size: 2rem;
}
</style>

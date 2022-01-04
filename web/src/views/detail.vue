<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="container">
        <h1>学校信息</h1>
        <div class="card mb-4 shadow-sm school">

          <div class="card-body">
            <h1>学校名字：{{school.name}}</h1>
            <h1>学校性质：{{school.nature}}</h1>
            <h1>学校类型：{{SCHOOL_TYPE | optionKV(school.level)}}</h1>
            <h1>学校地址：{{school.address}}</h1>
            <h1>学校电话：{{school.telephone}}</h1>
          </div>
        </div>

            <hr>
        <h1>学校简介</h1>
            <div class="card mb-4 shadow-sm school">
              <div class="card-body">
                 <h1>{{school.info}}</h1>
              </div>
            </div>

        <hr>
        <h1>相关资讯</h1>


        <div class="row">
          <div v-for="o in infos" class="col-md-12">
            <the-info v-bind:info="o"></the-info>
          </div>
        </div>

        <div class="col-md-12">
          <pagination ref="pagination" v-bind:list="listNewInfo"></pagination>
        </div>


        <hr>
        <h1>评论</h1>

        <div class="col-lg-14">
          <div class="input-group input-group-lg">
            <input v-model="comment" type="text" class="form-control" placeholder="请输入评论">
        <button v-on:click="share()" class="btn btn-outline-dark" type="button">发送</button>
          </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->


        <hr>


          <the-comment v-bind:comments=comments></the-comment>



      </div>






    </div>
  </main>
</template>


<script>

import TheInfo from "../components/the-info";
import TheComment from "../components/the-comment"
import Pagination from "../components/pagination";
export default {
  name: 'detail',
  components: {Pagination,TheInfo, TheComment},
  data: function () {
    return {
      id: "",
      school: {},
      SCHOOL_TYPE: SCHOOL_TYPE,
      infos:[],
      comment:'',
      comments:[],
    }
  },
  mounted() {
    let _this = this;
    _this.id = _this.$route.query.id;
    _this.$refs.pagination.size = 5;
    _this.findSchool();
    _this.listNewInfo(1);
    _this.listcomment(1);
  },

  methods: {
    findSchool() {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/school/get/' + _this.id).then((response) => {
        let resp = response.data;
        _this.school = resp.data;
      })
    },

    listNewInfo(page) {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/schoolInformation/getAllBySchoolId/' + _this.id,{
        page: page,
        pageSize: _this.$refs.pagination.size,
      }).then((response)=>{
        let resp = response.data;
        if (resp.code == 200) {
          console.log(resp.data);
          _this.infos = resp.data.data;
          _this.$refs.pagination.render(page, resp.data.total);
        }
      }).catch((response)=>{
        console.log("error：", response);
      })
    },

    listcomment(page) {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/getCommentBySchoolId/' + _this.id,{
        page: page,
        pageSize: _this.$refs.pagination.size,
      }).then((response)=>{
        let resp = response.data;
        if (resp.code == 200) {
          console.log(resp.data);
          _this.comments = resp.data.data;
          _this.$refs.pagination.render(page, resp.data.total);
        }
      }).catch((response)=>{
        console.log("error：", response);
      })

    },


    share() {
      let _this = this;
      console.log(_this.comment)
    },

  }
}
</script>

<style>
.school-head {
}

.school-head h1 {
  font-size: 2rem;
  margin-bottom: 1.5rem;
}

.school-head-item span {
  margin-right: 1rem;
}

.school-head-desc {
  font-size: 1rem;
  color: #555
}

.school-head a {
}

.school-head-price {
  font-size: 2rem;
}

@media (max-width: 700px) {
  .school-head h1 {
    font-size: 1.5rem;
  }
}
</style>

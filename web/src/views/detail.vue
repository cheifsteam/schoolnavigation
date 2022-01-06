<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="dqwz">
        <a class="a" href="/">
         &nbsp;学校导航系统>
        </a>
        <a class="a" href="/list">
         全部学校>
          </a>
        <a>
          {{school.name}}
        </a>

      </div>
      <div class="container">

        <div class="stk">
          <h1>{{school.name}}</h1>
        <div class="xxbz">
          <div class="tp">
            <img :src=attachImageUrl(school.img) class="pc">
          </div>
        </div>
          <div class="xxsz">
            <div class="z"><strong>学校名字：</strong>{{school.name}}</div>
            <div class="z"><strong>学校性质：</strong>{{SCHOOL_NATURE | optionKV(school.nature)}}</div>
            <div class="z"><strong>学校类型：</strong>{{SCHOOL_TYPE | optionKV(school.level)}}</div>
            <div class="z"><strong>学校地址：</strong>{{school.address}}</div>
            <div class="z"><strong>学校电话：</strong>{{school.telephone}}</div>

          </div>
        </div>
        <div class="stq" style="height: auto !important;">

          <div class="zsbt">
            <strong>学校简介</strong>
          </div>
            <div class="jj">

                 <p>{{school.info}}</p>
            </div>

        </div>
        <div class="stq" style="height: auto !important;">
          <div class="zsbt">
            <strong>相关资讯</strong>
          </div>
          <div class="row">
            <div v-for="o in infos" class="col-md-12">
              <the-info v-bind:info="o"></the-info>
            </div>
          </div>
        </div>






        <pagination ref="pagination" v-bind:list="listNewInfo"></pagination>



        <hr>
        <h1>评论</h1>

        <div class="col-lg-14">
          <div class="input-group input-group-lg">
            <input v-model="comment" type="text" class="form-control" placeholder="请输入评论">
        <button v-on:click="share()" class="btn btn-outline-dark" type="button">发送</button>
          </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->


        <hr>



          <ul v-for="o in comments" class="popular">
            <li><the-comment v-bind:comment=o></the-comment></li>
          </ul>



          <pagination ref="pagination" v-bind:list="listcomment"></pagination>



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
      SCHOOL_NATURE:SCHOOL_NATURE,
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
    attachImageUrl (srcUrl) {
      return srcUrl ? process.env.VUE_APP_SERVER + srcUrl  : process.env.VUE_APP_SERVER+ '/img/school/school6.jfif'
    },
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
      let loginUser = Tool.getLoginUser();

      if(Tool.isEmpty(loginUser)) {
        Toast.warning("请先登录");
        return;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/user/addComment',{
        content: _this.comment,
        schoolId: _this.school.id
      }).then((response)=> {
        let resp = response.data
        Toast.success(resp.msg)
      }).catch((response)=>{
        console.log("error：", response);
      })

      _this.listcomment(1)
    },

  }
}
</script>

<style>
.school-head {
}
.a{
  text-decoration: none;
  color: #1a1a1a;
}
.stk{

  height: 311px;
  border: 1px solid #BDE3FF;
  padding-bottom: 8px;
  overflow: hidden;
}
.xxbz{
  float: left;
  width: 280px;
  border-right-width: 1px;
  border-right-style: dotted;
  border-right-color: #BDE3FF;
  padding-top: 10px;
  padding-bottom: 6px;
}
.pc{
  height: 240px;
  width: 260px;
}
.tp{
  text-align: center;
  background-color: #FFC;
  margin-right: auto;
  margin-left: auto;
  width: 260px;
  overflow: hidden;
  padding: 1px;
  border: 1px solid #090;
  height: 240px;
}
.xxsz {
  overflow: hidden;
  line-height: 20px;
  line-height: 1.7;

  padding-left: 10px;

}
.z{
  height: 2em;
  line-height: 2em;
  overflow: hidden;
  margin-right: 1%;
  font-size: 20px;
}
.stq{
  border: 1px solid #BDE3FF;
  padding-bottom: 8px;
  overflow: hidden;
  margin-top: 10px;
}
.zsbt{
  font-size: 16px;
  font-weight: bold;
  color: #F60;
  line-height: 33px;
  height: 33px;
  clear: both;
  background-image: url(../assets/img/zsbtbg.gif);
  background-repeat: repeat-x;
  background-position: center;
  width: 98%;
  background-position: center ;

}
.jj{
  line-height: 1.7;
  padding-top: 12px;
  padding-bottom: 10px;
  width: 95%;
  overflow: hidden;
  margin-right: auto;
  margin-left: auto;
  clear: both;
}
.dqwz{
  margin-left: 270px;
  line-height: 1.5;
  padding: 10px;
  background-color: #FDFDFD;
}

h4{
  font-size: 20px;
  font-weight: lighter;
}
h1{
  font-size: 20px;
  font-weight: bold;
  color: #F60;
  padding-left: 12px;
  clear: both;
  margin: 0;
  padding-top: 12px;
  padding-right: 0;
  padding-bottom: 0;
  display: block;
}
h2{
  font-size: 20px;
  color: #67757f;
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

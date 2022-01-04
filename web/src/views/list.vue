<template>
  <main role="main">
    <div class="header-nav">
      <div class="clearfix">
        <div class="container">
          <div class="row">
            <div class="col-12">
              <a v-on:click="onClickLevel1('00000000')" id="category-00000000" href="javascript:;" class="cur">全部</a>
              <a v-for="o in level1" v-on:click="onClickLevel1(o.id)" v-bind:id="'category-' + o.id" href="javascript:;">{{o.name}}</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="skill clearfix">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <a v-for="o in level2" v-on:click="onClickLevel2(o.id)" v-bind:id="'category-' + o.id" href="javascript:;">{{o.name}}</a>

            <div style="clear:both"></div>
          </div>
        </div>
      </div>
    </div>

    <hr v-show="level2Id != null">

    <div class="skill clearfix">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <a v-for="o in level3" v-on:click="onClickLevel3(o.id)" v-bind:id="'category-' + o.id" href="javascript:;">{{o.name}}</a>
            <div style="clear:both"></div>
          </div>
        </div>
      </div>
    </div>

    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <pagination ref="pagination" v-bind:list="listSchool"></pagination>
          </div>
        </div>
        <br>
        <div class="row">
          <div v-for="o in schools" class="col-md-4">
            <the-school v-bind:school="o"></the-school>
          </div>
<!--          <h3 v-show="schools.length === 0">课程还未上架</h3>-->
        </div>
      </div>
    </div>

  </main>
</template>

<script>
import TheSchool from "../components/the-school";
import Pagination from "../components/pagination";

export default {
  components: {Pagination, TheSchool},
  name: 'list',
  data: function () {
    return {
      keywords: "",
      schools: [],
      level1: [],
      level2: [],
      level3: [],
      categorys: [],
      level1Id: "",
      level2Id: "",
      level3Id: "",
      categoryId: "",
    }
  },
  mounted() {
    let _this = this;
    _this.keywords = _this.$route.query.keywords;
    _this.$refs.pagination.size = 50;
    _this.listSchool(1);
    _this.all();
  },
  methods: {
    /**
     * 查询课程列表
     */
    listSchool(page) {
      let _this = this;
      console.log("Makiori:" + _this.keywords)
      if(_this.keywords === "" || _this.keywords === undefined) {
        _this.categoryId = _this.level3Id || _this.level2Id || _this.level1Id || "", // 优先取level2Id

            _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/school/getAllSchool/' + _this.categoryId, {
              page: page,
              pageSize: _this.$refs.pagination.size,
            }).then((response) => {
              let resp = response.data;
              if (resp.code == 200) {
                _this.schools = resp.data.data;
                _this.$refs.pagination.render(page, resp.data.total);
              }
            }).catch((response) => {
              console.log("error：", response);
            })
      } else {
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/web/school/getLike/' + _this.keywords, {
          page: page,
          pageSize: _this.$refs.pagination.size,
        }).then((response)=>{
          let resp = response.data
          if (resp.code == 200) {
            _this.schools = resp.data.data;
            _this.$refs.pagination.render(page, resp.data.total);
          }
        }).catch((response) => {
          console.log("error：", response);
        })
      }
    },


    /**
     * 初始化
     */

    all() {
      let _this = this;
      _this.level2 = [];
      _this.level3 = [];

      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/getTop').then((response)=>{

        let resp = response.data
        _this.level1 = resp.data
        _this.categorys = resp.data;
      })
    },



    /**
     * 点击一级分类时
     * @param level1Id
     */
    onClickLevel1(level1Id) {
      let _this = this;
      _this.keywords = "";
      _this.level2 = [];
      _this.level3 = [];
      _this.level1Id = level1Id;
      _this.level2Id = "";
      _this.level3Id = "";

      //点击一级分类时，显示激活状态
      $("#category-" + level1Id).siblings("a").removeClass("cur");
      $("#category-" + level1Id).addClass("cur");

      if (level1Id === '00000000') {
        _this.level1Id = null;
        this.listSchool(1);

      } else {
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/getSub/' + level1Id).then((response) => {
          let resp = response.data
          _this.level2 = resp.data
          this.listSchool(1);
        })
      }
    },


    /**
     * 点击二级分类时
     * @param level2Id
     */
    onClickLevel2(level2Id) {
      let _this = this;
      _this.level2Id = level2Id;
      _this.level3Id = null;
      _this.keywords = "";

      $("#category-" + level2Id).siblings("a").removeClass("on");
      $("#category-" + level2Id).addClass("on");


      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/getSub/' + level2Id).then((response)=> {
        let resp = response.data
        _this.level3 = resp.data
        this.listSchool(1);
      })
    },

    /**
     * 点击三级分类时
     * @param level3Id
     */
    onClickLevel3(level3Id) {
      let _this = this;
      _this.keywords = "";
      _this.level3Id = level3Id;

      $("#category-" + level3Id).siblings("a").removeClass("on");
      $("#category-" + level3Id).addClass("on");
      this.listSchool(1);
    },
  }
}
</script>
<style>
/* 头部 一级分类 */
.header-nav {
  height: auto;
  background: #fff;
  box-shadow: 0 8px 16px 0 rgba(28,31,33,.1);
  padding: 16px 0;
  box-sizing: border-box;
  position: relative;
  z-index: 1;
  /*background-color: #d6e9c6;*/
}
.header-nav>div {
  width: 100%;
  padding-left: 12px;
  box-sizing: border-box;
  margin-left: auto;
  margin-right: auto;
  /*background-color: #B4D5AC;*/
}
.header-nav a {
  float: left;
  font-size: 16px;
  color: #07111b;
  line-height: 50px;
  height: 45px;
  position: relative;
  margin-right: 46px;
  font-weight: 700;
}
.header-nav a:hover {
  color: #c80;
}
.header-nav a.cur {
  color: #c80;
}
.header-nav a.cur:before {
  display: block;
}
.header-nav a:before {
  display: none;
  content: ' ';
  position: absolute;
  bottom: 0;
  background: #c80;
  width: 16px;
  height: 3px;
  left: 50%;
  margin-left: -8px;
}
/* 二级分类 */
.skill {
  width: 100%;
  padding: 24px 0 0;
  position: relative;
  margin: 0 auto;
}
.skill a.on {
  color: #c80;
  background: rgba(204,136,0,.1);
}
.skill a {
  float: left;
  margin-right: 20px;
  padding: 0 12px;
  font-size: 14px;
  color: #4d555d;
  line-height: 32px;
  border-radius: 6px;
  margin-bottom: 12px;
}
.skill a:hover {
  background: #d9dde1;
}

</style>
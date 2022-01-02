<template>
  <div>

    <div class="row">
      <div class="col-md-4">
        <p>
          <button v-on:click="add1()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-edit"></i>
            新增一级
          </button>
          &nbsp;
          <button v-on:click="all()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-refresh"></i>
            刷新
          </button>
        </p>

        <table id="level1-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>id</th>
            <th>名称</th>
            <th>顺序</th>
            <th>操作</th>
          </tr>
          </thead>

          <tbody>
          <tr v-for="category in level1" v-on:click="onClickLevel1(category)" v-bind:class="{'active' : category.id === active1.id}">
            <td>{{category.id}}</td>
            <td>{{category.name}}</td>
            <td>{{category.sort}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit1(category)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>

      <div class="col-md-4">
        <p>
          <button v-on:click="add2()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-edit"></i>
            新增二级
          </button>
        </p>

        <table id="level2-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>id</th>
            <th>名称</th>
            <th>顺序</th>
            <th>操作</th>
          </tr>
          </thead>

          <tbody>
          <tr v-for="category in level2" v-on:click="onClickLevel2(category)" v-bind:class="{'active' : category.id === active2.id}">
            <td>{{category.id}}</td>
            <td>{{category.name}}</td>
            <td>{{category.sort}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit2(category)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>

      <div class="col-md-4">
        <p>
          <button v-on:click="add3()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-edit"></i>
            新增三级
          </button>
        </p>

        <table id="level-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>id</th>
            <th>名称</th>
            <th>顺序</th>
            <th>操作</th>
          </tr>
          </thead>

          <tbody>
          <tr v-for="category in level3">
            <td>{{category.id}}</td>
            <td>{{category.name}}</td>
            <td>{{category.sort}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit3(category)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>

    </div>


    <div id="form-modal1" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">新增一级分类</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">父分类</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{active1.name || "无"}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="category.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="category.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save1()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="form-modal2" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">新增二级分类</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">父分类</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{active1.name || "无"}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="category.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="category.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save2()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="form-modal3" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">新增三级分类</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">父分类</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{active2.name || "无"}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="category.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="category.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save3()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="form-modal4" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改一级分类</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">父分类</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{active1.name || "无"}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="category.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="category.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="update1()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="form-modal5" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改二级分类</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">父分类</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{active1.name || "无"}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="category.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="category.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="update2()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="form-modal6" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改三级分类</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">父分类</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{active2.name || "无"}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="category.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="category.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="update3()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


  </div>
</template>

<script>
export default {
  name: "business-category",
  data: function() {
    return {
      category: {},
      categorys: [],
      level1: [],
      level2: [],
      level3: [],
      active1: {},
      active2: {},
    }
  },
  mounted: function() {
    let _this = this;
    _this.all();
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("business-category-sidebar");

  },
  methods: {
    /**
     * 点击【新增一级】
     */
    add1() {
      let _this = this;
      _this.active1 = {};
      _this.active2 = {};
      _this.category = {
        parent: "00000000"
      };
      $("#form-modal1").modal("show");
    },

    /**
     * 点击【新增二级】
     */
    add2() {
      let _this = this;
      if (Tool.isEmpty(_this.active1)) {
        Toast.warning("请先点击一级分类");
        return;
      }
      _this.category = {
        parent: _this.active1.id
      };
      $("#form-modal2").modal("show");
    },

    /**
     * 点击【新增三级】
     */
    add3() {
      let _this = this;
      if (Tool.isEmpty(_this.active2)) {
        Toast.warning("请先点击二级分类");
        return;
      }
      _this.category = {
        parent: _this.active2.id
      };
      $("#form-modal3").modal("show");
    },

    /**
     * 点击【一级编辑】
     */
    edit1(category) {
      let _this = this;
      _this.category = $.extend({}, category);
      $("#form-modal4").modal("show");
    },

    /**
     * 点击【二级编辑】
     */
    edit2(category) {
      let _this = this;
      _this.category = $.extend({}, category);
      $("#form-modal5").modal("show");
    },

    /**
     * 点击【三级编辑】
     */

    edit3(category) {
      let _this = this;
      _this.category = $.extend({}, category);
      $("#form-modal6").modal("show");
    },

    /**
     * 列表查询
     */
    all() {
      let _this = this;
      _this.level2 = [];
      _this.level3 = [];
      _this.active1 = {};
      _this.active2 = {};
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/getTop').then((response)=>{
        Loading.hide();
        let resp = response.data
        _this.level1 = resp.data
        _this.categorys = resp.data;
      })
    },

    /**
     * 点击【新增一级】
     */
    save1() {
      let _this = this;

      // 保存校验
      if (1 != 1
          || !Validator.require(_this.category.parent, "父id")
          || !Validator.require(_this.category.name, "名称")
          || !Validator.length(_this.category.name, "名称", 1, 50)
      ) {
        return;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/addTop', _this.category).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.code == 200) {
          $("#form-modal1").modal("hide");
          _this.all();
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message)
        }
      })
    },

    /**
     * 点击【修改一级】
     */
    update1() {
      let _this = this;

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/updataTop', _this.category).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.code == 200) {
          $("#form-modal4").modal("hide");
          _this.all();
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message)
        }
      })
    },

    /**
     * 点击【保存二级】
     */
    save2() {
      let _this = this;

      // 保存校验
      if (1 != 1
          || !Validator.require(_this.category.parent, "父id")
          || !Validator.require(_this.category.name, "名称")
          || !Validator.length(_this.category.name, "名称", 1, 50)
      ) {
        return;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/addSecondary', _this.category).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.code == 200) {
          $("#form-modal2").modal("hide");
          _this.all();
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message)
        }
      })
    },

    /**
     * 点击【修改二级】
     */
    update2() {
      let _this = this;

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/updataTop', _this.category).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.code == 200) {
          $("#form-modal5").modal("hide");
          _this.all();
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message)
        }
      })
    },

    /**
     * 点击【保存三级】
     */
    save3() {
      let _this = this;

      // 保存校验
      if (1 != 1
          || !Validator.require(_this.category.parent, "父id")
          || !Validator.require(_this.category.name, "名称")
          || !Validator.length(_this.category.name, "名称", 1, 50)
      ) {
        return;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/addSecondary', _this.category).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.code == 200) {
          $("#form-modal3").modal("hide");
          _this.all();
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message)
        }
      })
    },

    /**
     * 点击【修改三级】
     */
    update3() {
      let _this = this;

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/updataTop', _this.category).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.code == 200) {
          $("#form-modal6").modal("hide");
          _this.all();
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message)
        }
      })
    },



    /**
     * 点击【删除】
     */
    del(id) {
      let _this = this;
      Confirm.show("删除分类后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/admin/category/delete/' + id).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.code == 200) {
            _this.all();
            Toast.success("删除成功！");
          }
        })
      });
    },

    onClickLevel1(category) {
      let _this = this;

       _this.level2 = [];
       _this.level3 = [];
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/getSub/' + category.id).then((response)=> {
        Loading.hide();
        let resp = response.data
        _this.level2 = resp.data
        _this.active1 = category


      })
    },

    onClickLevel2(category) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/getSub/' + category.id).then((response)=> {
        Loading.hide();
        let resp = response.data
        _this.level3 = resp.data
        _this.active2 = category

      })
    }
  }
}
</script>

<style scoped>
.active td {
  background-color: #d6e9c6 !important;
}
</style>
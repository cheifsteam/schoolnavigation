<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>id</th>
        <th>学校名称</th>
        <th>地区</th>
        <th>学校性质</th>
        <th>学校级别</th>
        <th>电话</th>
        <th>地址</th>
        <th>简介</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="school in schools">
        <td>{{school.id}}</td>
        <td>{{school.name}}</td>
        <td>{{school.area}}</td>
        <td>{{school.nature}}</td>
        <td>{{school.level}}</td>
        <td>{{school.telephone}}</td>
        <td>{{school.address}}</td>
        <td>{{school.info}}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(school)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(school.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">新增学校信息</h4>
          </div>
          <div class="modal-body">


            <form class="form-horizontal">


              <div class="form-group">
                <label class="col-sm-2 control-label">学校id</label>
                <div class="col-sm-10">
                  <input v-model="school.id" class="form-control">
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">学校名称</label>
                <div class="col-sm-10">
                  <input v-model="school.name" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">所在地区</label>
                <div class="col-sm-10">
                  <input v-model="school.area" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校性质</label>
                <div class="col-sm-10">
                  <input v-model="school.nature" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校级别</label>
                <div class="col-sm-10">
                  <input v-model="school.level" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校电话</label>
                <div class="col-sm-10">
                  <input v-model="school.telephone" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校地址</label>
                <div class="col-sm-10">
                  <input v-model="school.address" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校简介</label>
                <div class="col-sm-10">
                  <input v-model="school.info" class="form-control">
                </div>
              </div>

            </form>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


  </div>
</template>

<script>
import Pagination from "../../components/pagination";
export default {
  components: {Pagination},
  name: "business-school",
  data: function() {
    return {
      school: {},
      schools: [],
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 5;
    _this.list(1);
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("system-school-sidebar");

  },
  methods: {
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.school = {};
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(school) {
      let _this = this;
      _this.school = $.extend({}, school);
      $("#form-modal").modal("show");
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      _this.$ajax.post('http://localhost:8090/admin/school/getAll', {
        page: page,
        pageSize: _this.$refs.pagination.size,
      }).then((response)=>{
        let resp = response.data;
        _this.schools = resp.data.data;
        _this.$refs.pagination.render(page, resp.data.total);

      })
    },

    /**
     * 点击【保存】
     */
    save() {
      let _this = this;

      //_this.school.categorys = categorys;

      Loading.show();
      console.log(_this.teacher);
      _this.$ajax.post('http://10.24.4.42:8080/admin/school/add', _this.school).then((response)=>{

        Loading.hide();
        let resp = response.data;
        if (resp) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp)
        }
      })
    },

    /**
     * 点击【删除】
     */
    del(id) {
      let _this = this;
      Confirm.show("删除学校信息后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete('http://10.24.4.42:8080/admin/school/delete/' + id).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp) {
            _this.list(1);
            Toast.success("删除成功！");
          }
        })
      });
    },
  }
}
</script>
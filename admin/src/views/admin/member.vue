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
        <th>管理员id</th>
        <th>管理员登录名</th>
        <th>管理员昵称</th>
        <th>管理员密码</th>
        <th>管理员头像</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="member in members">
        <td>{{member.id}}</td>
        <td>{{member.adminName}}</td>
        <td>{{member.nickname}}</td>
        <td>{{member.password}}</td>
        <td>{{member.img}}</td>


        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="editPassword(member)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-key bigger-120"></i>
            </button>
            <button v-on:click="edit(member)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(member.id)" class="btn btn-xs btn-danger">
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
            <h4 class="modal-title">新增管理员</h4>
          </div>
          <div class="modal-body">


            <form class="form-horizontal">


              <div class="form-group">
                <label class="col-sm-2 control-label">管理员id</label>
                <div class="col-sm-10">
                  <input v-model="member.id" class="form-control">
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">管理员账号</label>
                <div class="col-sm-10">
                  <input v-model="member.adminName" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">管理员昵称</label>
                <div class="col-sm-10">
                  <input v-model="member.nickname" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">管理员密码</label>
                <div class="col-sm-10">
                  <input v-model="member.password" class="form-control">
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">管理员头像</label>
                <div class="col-sm-10">
                  <input v-model="member.img" class="form-control">
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

    <div id="form-modal2" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改管理员信息</h4>
          </div>
          <div class="modal-body">


            <form class="form-horizontal">


              <div class="form-group">
                <label class="col-sm-2 control-label">管理员id</label>
                <div class="col-sm-10">
                  <input v-model="member.id" class="form-control">
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">管理员账号</label>
                <div class="col-sm-10">
                  <input v-model="member.adminName" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">管理员昵称</label>
                <div class="col-sm-10">
                  <input v-model="member.nickname" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">管理员密码</label>
                <div class="col-sm-10">
                  <input v-model="member.password" class="form-control">
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">管理员头像</label>
                <div class="col-sm-10">
                  <input v-model="member.img" class="form-control">
                </div>
              </div>



            </form>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="update()" type="button" class="btn btn-primary">更新</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


    <div id="edit-password-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改密码</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-2">密码</label>
                <div class="col-sm-10">
                  <input class="form-control" type="password" v-model="member.password" name="password">
                </div>
              </div>

              <div class="form-group">
                <label class="control-label col-sm-2">新密码</label>
                <div class="col-sm-10">
                  <input class="form-control" type="password" v-model="member.newPassword" name="password">
                </div>
              </div>

              <div class="form-group">
                <label class="control-label col-sm-2">请重复一遍</label>
                <div class="col-sm-10">
                  <input class="form-control" type="password" v-model="member.passwordConfirm" name="password">
                </div>
              </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
              <i class="ace-icon fa fa-times"></i>
              取消
            </button>
            <button type="button" class="btn btn-white btn-info btn-round" v-on:click="savePassword()">
              <i class="ace-icon fa fa-plus blue"></i>
              保存密码
            </button>
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
  name: "business-member",
  data: function() {
    return {
      member: {},
      members: [],
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 5;
    _this.list(1);
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("system-member-sidebar");

  },
  methods: {
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.member = {};
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(member) {
      let _this = this;
      _this.member = $.extend({}, member);
      $("#form-modal2").modal("show");
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER+'/admin/admin/getAll', {
        page: page,
        pageSize: _this.$refs.pagination.size,
      }).then((response)=>{
        let resp = response.data;
        console.log(resp)
        _this.members = resp.data.data;
        _this.$refs.pagination.render(page, resp.data.total);
        console.log(resp.data.data);

      })
    },

    /**
     * 点击【新增】
     */
    save() {
      let _this = this;

      //_this.member.categorys = categorys;

      Loading.show();
      console.log(_this.member);
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/admin/add', _this.member).then((response)=>{

        Loading.hide();
        let resp = response.data;
        if (resp.code == 200) {
          $("#form-modal").modal("hide");
          //_this.list(1);
          Toast.success("新增成功！");
        } else {
          Toast.warning(resp.msg)
        }
      })
    },

    /**
     * 点击【更新】
     */
    update() {
      let _this = this;
      Loading.show();
      console.log(_this.member);
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/admin/update', _this.member).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp) {
          $("#form-modal2").modal("hide");
          _this.list(1);
          Toast.success("更新成功！");
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
        _this.$ajax.delete(process.env.VUE_APP_SERVER+'/admin/admin/delete/' + id).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp) {
            _this.list(1);
            Toast.success("删除成功！");
          }
        })
      });
    },

    /**
     * 点击【重置密码】
     */
    editPassword(member) {
      let _this = this;
      _this.member = $.extend({}, member);
      _this.member.password = null;
      _this.member.newPassword = null;
      _this.member.passwordConfirm = null;

      $("#edit-password-modal").modal("show");
    },

    /**
     * 点击【保存密码】
     */
    savePassword() {
      let _this = this;

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/admin/updatePassword', _this.member).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp) {
          $("#edit-password-modal").modal("hide");
          //_this.list(1);
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message)
        }
      })
    },
  }
}
</script>
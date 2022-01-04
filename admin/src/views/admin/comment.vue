<template>
  <div>
    <p>

      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>评论id</th>
        <th>用户id</th>
        <th>评论内容</th>
        <th>学校id</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="comment in comments">
        <td>{{comment.id}}</td>
        <td>{{comment.userId}}</td>
        <td>{{comment.content}}</td>
        <td>{{comment.schoolId}}</td>



        <td>
          <div class="hidden-sm hidden-xs btn-group">
<!--            <button v-on:click="edit(comment)" class="btn btn-xs btn-info">-->
<!--              <i class="ace-icon fa fa-pencil bigger-120"></i>-->
<!--            </button>-->
            <button v-on:click="del(comment.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

<!--    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">-->
<!--      <div class="modal-dialog" role="document">-->
<!--        <div class="modal-content">-->
<!--          <div class="modal-header">-->
<!--            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
<!--            <h4 class="modal-title">新增资讯</h4>-->
<!--          </div>-->
<!--          <div class="modal-body">-->


<!--            <form class="form-horizontal">-->


<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">资讯id</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input v-model="comment.id" class="form-control">-->
<!--                </div>-->
<!--              </div>-->


<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">资讯题目</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input v-model="comment.userId" class="form-control">-->
<!--                </div>-->
<!--              </div>-->

<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">发布时间</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input type="datetime-local" v-model="comment.time" class="form-control">-->
<!--                </div>-->
<!--              </div>-->

<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">资讯内容</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input v-model="comment.info" class="form-control">-->
<!--                </div>-->
<!--              </div>-->


<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">学校id</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input v-model="comment.schoolId" class="form-control">-->
<!--                </div>-->
<!--              </div>-->



<!--            </form>-->

<!--          </div>-->
<!--          <div class="modal-footer">-->
<!--            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
<!--            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>-->
<!--          </div>-->
<!--        </div>&lt;!&ndash; /.modal-content &ndash;&gt;-->
<!--      </div>&lt;!&ndash; /.modal-dialog &ndash;&gt;-->
<!--    </div>&lt;!&ndash; /.modal &ndash;&gt;-->

<!--    <div id="form-modal2" class="modal fade" tabindex="-1" role="dialog">-->
<!--      <div class="modal-dialog" role="document">-->
<!--        <div class="modal-content">-->
<!--          <div class="modal-header">-->
<!--            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
<!--            <h4 class="modal-title">修改资讯信息</h4>-->
<!--          </div>-->
<!--          <div class="modal-body">-->


<!--            <form class="form-horizontal">-->


<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">资讯id</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input v-model="comment.id" class="form-control">-->
<!--                </div>-->
<!--              </div>-->


<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">资讯题目</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input v-model="comment.userId" class="form-control">-->
<!--                </div>-->
<!--              </div>-->

<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">发布时间</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input type="datetime-local" v-model="comment.time" class="form-control">-->
<!--                </div>-->
<!--              </div>-->

<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">资讯内容</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input v-model="comment.info" class="form-control">-->
<!--                </div>-->
<!--              </div>-->


<!--              <div class="form-group">-->
<!--                <label class="col-sm-2 control-label">学校Id</label>-->
<!--                <div class="col-sm-10">-->
<!--                  <input v-model="comment.schoolId" class="form-control">-->
<!--                </div>-->
<!--              </div>-->



<!--            </form>-->

<!--          </div>-->
<!--          <div class="modal-footer">-->
<!--            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
<!--            <button v-on:click="update()" type="button" class="btn btn-primary">更新</button>-->
<!--          </div>-->
<!--        </div>&lt;!&ndash; /.modal-content &ndash;&gt;-->
<!--      </div>&lt;!&ndash; /.modal-dialog &ndash;&gt;-->
<!--    </div>&lt;!&ndash; /.modal &ndash;&gt;-->



  </div>
</template>

<script>
import Pagination from "../../components/pagination";
export default {
  components: {Pagination},
  name: "business-comment",
  data: function() {
    return {
      comment: {},
      comments: [],
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 5;
    _this.list(1);
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("system-comment-sidebar");

  },
  methods: {
    /**
     * 点击【新增】
     */
    // add() {
    //   let _this = this;
    //   _this.comment = {};
    //   $("#form-modal").modal("show");
    // },

    /**
     * 点击【编辑】
     */
    // edit(comment) {
    //   let _this = this;
    //   _this.comment = $.extend({}, comment);
    //   $("#form-modal2").modal("show");
    // },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER+'/admin/user/getAllComment', {
        page: page,
        pageSize: _this.$refs.pagination.size,
      }).then((response)=>{
        let resp = response.data;
        _this.comments = resp.data.data;
        _this.$refs.pagination.render(page, resp.data.total);
        console.log(resp.data.data);

      })
    },

    /**
     * 点击【保存】
     */
    // save() {
    //   let _this = this;
    //
    //   //_this.comment.categorys = categorys;
    //
    //   Loading.show();
    //   console.log(_this.comment);
    //   _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/schoolInformation/add', _this.comment).then((response)=>{
    //
    //     Loading.hide();
    //     let resp = response.data;
    //     if (resp.code==200) {
    //       $("#form-modal").modal("hide");
    //       _this.list(1);
    //       Toast.success("保存成功！");
    //     } else {
    //       Toast.warning(resp)
    //     }
    //   })
    // },
    //
    // /**
    //  * 点击【更新】
    //  */
    // update() {
    //   let _this = this;
    //   Loading.show();
    //   console.log(_this.comment);
    //   _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/schoolInformation/update', _this.comment).then((response)=>{
    //     Loading.hide();
    //     let resp = response.data;
    //     if (resp) {
    //       $("#form-modal2").modal("hide");
    //       _this.list(1);
    //       Toast.success("更新成功！");
    //     } else {
    //       Toast.warning(resp)
    //     }
    //   })
    // },

    /**
     * 点击【删除】
     */
    del(id) {
      let _this = this;
      Confirm.show("删除学校信息后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER+'/admin/user/deleteComment/' + id).then((response)=>{
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
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
        <th>学校id</th>
        <th>学校名称</th>
        <th>学校性质</th>
        <th>学校级别</th>
        <th>学校类型</th>
        <th>电话</th>
        <th>地址</th>
        <th>简介</th>
        <th>学校图片</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="school in schools">
        <td>{{school.id}}</td>
        <td>{{school.name}}</td>
        <td>{{school.nature}}</td>
        <td>{{school.level}}</td>
        <td>{{school.type}}</td>
        <td>{{school.telephone}}</td>
        <td>{{school.address}}</td>
        <td>{{school.info}}</td>
        <td>{{school.img}}</td>

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
                <label class="col-sm-2 control-label">
                  分类
                </label>
                <div class="col-sm-10">
                  <ul id="tree1" class="ztree"></ul>
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">学校id</label>
                <div class="col-sm-10">
                  <input type="number" v-model="school.id" class="form-control">
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">学校名称</label>
                <div class="col-sm-10">
                  <input v-model="school.name" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校性质</label>
                <div class="col-sm-10">
                  <select v-model="school.nature" class="form-control">
                    <option v-for="o in SCHOOL_NATURE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校类型</label>
                <div class="col-sm-10">
                  <select v-model="school.type" class="form-control">
                    <option v-for="o in SCHOOL_TYPE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校级别</label>
                <div class="col-sm-10">
                  <input v-model="school.level" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                  <input v-model="school.telephone" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">地址</label>
                <div class="col-sm-10">
                  <input v-model="school.address" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">简介</label>
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

    <div id="form-modal2" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改学校信息</h4>
          </div>
          <div class="modal-body">


            <form class="form-horizontal">

              <div class="form-group">
                <label class="col-sm-2 control-label">
                  分类
                </label>
                <div class="col-sm-10">
                  <ul id="tree2" class="ztree"></ul>
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">学校id</label>
                <div class="col-sm-10">
                  <input type="number" v-model="school.id" class="form-control" disabled>
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">学校名称</label>
                <div class="col-sm-10">
                  <input v-model="school.name" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校性质</label>
                <div class="col-sm-10">
                  <select v-model="school.nature" class="form-control">
                    <option v-for="o in SCHOOL_NATURE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校类型</label>
                <div class="col-sm-10">
                  <select v-model="school.type" class="form-control">
                    <option v-for="o in SCHOOL_TYPE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">学校级别</label>
                <div class="col-sm-10">
                  <input v-model="school.level" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                  <input v-model="school.telephone" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">地址</label>
                <div class="col-sm-10">
                  <input v-model="school.address" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">简介</label>
                <div class="col-sm-10">
                  <input v-model="school.info" class="form-control">
                </div>
              </div>


            </form>
            <el-upload
                :http-request="uploadSectionFile">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>修改头像</em></div>
              <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过10M</div>
            </el-upload>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="update()" type="button" class="btn btn-primary">更新</button>
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
      loginUser: {},
      categorys: [],
      tree1: {},
      tree2: {},
      SCHOOL_TYPE: SCHOOL_TYPE,
      SCHOOL_NATURE:SCHOOL_NATURE,
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 5;
    _this.list(1);
    _this.allCategory();
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("system-school-sidebar");
    _this.loginUser = Tool.getLoginUser()
  },
  methods: {

    uploadSectionFile (param) {
      let form = new FormData()
      var that = this
      form.append('id', that.school.id)
      form.append('file', param.file)
      form.append('dir', '/img/school/')
      that.$ajax.post(process.env.VUE_APP_SERVER + '/admin/school/updateImg',form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        onUploadProgress: progressEvent => {
          that.uploadPercent = (progressEvent.loaded / progressEvent.total * 100) | 0
        }
      }).then((res) => {
        Toast.success("上传成功！");
        console.log('上传结束')
        console.log(res)
      }).catch((err) => {
        Toast.error("上传错误'");
        console.log('上传错误')
        console.log(err)
      })
    },
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.school = {};
      _this.tree1.checkAllNodes(false);
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(school) {
      let _this = this;
      _this.school = $.extend({}, school);
      _this.listCategory(school.id);
      $("#form-modal2").modal("show");
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      console.log(_this.loginUser.data)
      _this.$ajax.post(process.env.VUE_APP_SERVER+'/admin/school/getAll', {
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

      if (1 != 1
          || !Validator.require(_this.school.id, "学校id")
          || !Validator.require(_this.school.name, "学校名称")
          || !Validator.require(_this.school.nature, "学校性质")
          || !Validator.require(_this.school.level, "学校级别")
          || !Validator.require(_this.school.type, "学校类型")
      ) {
        return;
      }

      let categorys = _this.tree1.getCheckedNodes();

      if (Tool.isEmpty(categorys)) {
        Toast.warning("请选择分类！");
        return;
      }
      _this.school.categorys = categorys;


      Loading.show();

      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/school/add', _this.school).then((response)=>{

        Loading.hide();
        let resp = response.data;
        console.log(resp.code);
        if (resp.code == 200) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("新增成功！");
        } else {
          Toast.warning(resp)
        }
      })
    },

    /**
     * 点击【更新】
     */
    update() {
      let _this = this;

      let categorys = _this.tree2.getCheckedNodes();
      _this.school.categorys = categorys;

      Loading.show();
      console.log(_this.school);
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/school/update', _this.school).then((response)=>{
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
        _this.$ajax.delete(process.env.VUE_APP_SERVER+'/admin/school/delete/' + id).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp) {
            _this.list(1);
            Toast.success("删除成功！");
          }
        })
      });
    },

    allCategory() {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/category/getall').then((respond)=> {

        Loading.hide();
        let resp = respond.data;
        console.log(resp);
        _this.categorys = resp.data;
        _this.initTree();
      })
    },


    initTree() {
      let _this = this;
      let setting = {
        check: {
          enable: true
        },
        data: {
          simpleData: {
            idKey: "id",
            pIdKey: "parent",
            rootPId: "00000000",
            enable: true
          }
        }
      };

      let zNodes = _this.categorys;

      _this.tree1 = $.fn.zTree.init($("#tree1"), setting, zNodes);
      _this.tree2 = $.fn.zTree.init($("#tree2"), setting, zNodes);


      // 展开所有的节点
      // _this.tree.expandAll(true);
    },

    /**
     * 查找课程下所有分类
     * @param schoolId
     */
    listCategory(schoolId) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/school/list-category/' + schoolId).then((res)=>{
        Loading.hide();
        let response = res.data;
        let categorys = response.data;


        // 勾选查询到的分类
        _this.tree2.checkAllNodes(false);
        for (let i = 0; i < categorys.length; i++) {
          let node = _this.tree2.getNodeByParam("id", categorys[i].categoryId);
          _this.tree2.checkNode(node, true);
        }
      })
    },
    uploadUrl () {

      return `${process.env.VUE_APP_SERVER}/admin/school/updateImg?id=${_this.school.id}`
    },
    handleAvatarSuccess (res, file) {

      if (res.code === 200) {
        this.imageUrl = URL.createObjectURL(file.raw)
       Toast.success("修改成功")
      } else {

        Toast.warning("修改失败")

      }
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 10
      if (!isJPG) {
        Toast.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        Toast.error('上传头像图片大小不能超过 10MB!')
      }
      return isJPG && isLt2M
    }
  },
}
</script>
<style lang="scss" scoped>
@import 'src/assets/css/upload.scss';
</style>

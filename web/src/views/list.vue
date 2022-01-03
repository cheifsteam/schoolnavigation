<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row">
          <div v-for="o in schools" class="col-md-4">
            <the-school v-bind:school="o"></the-school>
          </div>
          <h3 v-show="schools.length === 0">查无学校</h3>
        </div>
      </div>
    </div>

  </main>
</template>

<script>
import TheCourse from "../components/the-school";

export default {
  components: {TheCourse},
  name: 'list',
  data: function () {
    return {
      schools: [],
    }
  },
  mounted() {
    let _this = this;
    _this.listSchool();
  },
  methods: {
    /**
     * 查询课程列表
     */
    listSchool() {
      let _this = this;
    },

  }
}
</script>
<style>

</style>
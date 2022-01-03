<template>
  <main role="main">
    <div className="album py-5 bg-light">
      <div className="container">
        <div className="row course-head">

            <h1>{{ school.name }}</h1>

        </div>
      </div>
    </div>


  </main>
</template>

<script>

export default {
  name: 'detail',
  data: function () {
    return {
      id: "",
      school: {},
    }
  },
  mounted() {
    let _this = this;
    _this.id = _this.$route.query.id;
    _this.findSchool();
  },

  methods: {
    findSchool() {
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/admin/school/get/' + _this.id).then((response) => {
        let resp = response.data;
        _this.course = resp.content;

      })
    },
  }
}
</script>

<style>
.course-head {
}

.course-head h1 {
  font-size: 2rem;
  margin-bottom: 1.5rem;
}

.course-head-item span {
  margin-right: 1rem;
}

.course-head-desc {
  font-size: 1rem;
  color: #555
}

.course-head a {
}

.course-head-price {
  font-size: 2rem;
}

@media (max-width: 700px) {
  .course-head h1 {
    font-size: 1.5rem;
  }
}
</style>

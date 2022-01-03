<template>
  <div class="card mb-4 shadow-sm school">
    <div class="card-body">
      <h5 class="">{{school.name}}</h5>
      <h5>学校类型：{{SCHOOL_TYPE | optionKV(school.level)}}</h5>
      <h5 class="">所在地区：{{school.address}}</h5>


      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <router-link v-bind:to="'/detail?id=' + school.id" class="btn btn-outline-secondary">学校详情</router-link>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: 'the-school',
  props: {
    school: {},

  },
  data: function () {
    return {
      SCHOOL_TYPE: SCHOOL_TYPE,
    }
  },
}
</script>

<style>
.course h4 {
  font-size: 1.25rem;
  margin: 15px 0;
}

.course .text-muted .badge {
  font-size: 1rem;
}
</style>

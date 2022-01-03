<template>
  <div class="card mb-4 shadow-sm info">
    <div class="card-body">
      <router-link v-bind:to="'/infodetail?id=' + info.id" >{{info.title}}</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'the-info',
  props: {
    info: {},

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

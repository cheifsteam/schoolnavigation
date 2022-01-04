<template>
  <div class="comment">

    <div class="row">
      <div  v-for="o in comments" class="col-md-12">
      <div>
        <h5>用户id: {{o.userId}}</h5>
        <h5>评论： {{o.content}}</h5>
      </div>
        <hr>
      </div>
    </div>

  </div>
</template>

<script>


export default {
  name: "the-comment",

  props: {
    comment: {},
    comments: [],
  },
}
</script>

<style scoped>

</style>
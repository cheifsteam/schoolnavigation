<template>
  <div class="comment">

    <div class="col-lg-14">
      <div>
        <h5>用户id: {{comment.userId}}</h5>
        <h5>评论： {{comment.content}}</h5>
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
  },
}
</script>

<style scoped>

</style>
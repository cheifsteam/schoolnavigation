<template>

  <div >
    <div class="item-con">
      <img class="item-img" :src="attachImageUrl(school.img)" alt="">
    </div>
    <div class="item-center">
    <div class="line-limit-length">
      <h5 class="">{{school.name}}</h5>
      <h6>电话：{{school.telephone}}</h6>
      <h6 class="">地址：{{school.address}}</h6>
    </div>
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
  methods:{
    attachImageUrl (srcUrl) {
      return srcUrl ? process.env.VUE_APP_SERVER + srcUrl || '../assets/img/user.jpg' : ''
    },
  }
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
.item-img{
  width: 200px;
  height: 200px;
}
.line-limit-length {

  width: 350px;
  overflow: hidden;

  text-overflow: ellipsis;

  white-space: nowrap;


}
.item-center{
  margin-bottom: 20px;
 float: left;
}
.item-con{
  box-sizing: border-box;
  overflow: hidden;
  float: right;
}



</style>

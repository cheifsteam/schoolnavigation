<template>
  <div class="setting">
    <div class="leftCol">
      <div class="settingsMainHeader">设置</div>
      <ul class="setting-aside">
        <li v-for="(item, index) in settingList" :key="index" :class="{activeColor: activeName === item.name}" @click="handleClick(item)">
          {{item.name}}
        </li>
      </ul>
    </div>
    <div class="contentCol">
      <component :is="componentSrc"></component>
    </div>
  </div>
</template>

<script>

import User from '../components/user'
import Security from '../components/security'
export default {
  name: 'setting',
  components: {
    User,
    Security,

  },
  data () {
    return {
      activeName: '个人信息',
      componentSrc: 'User',
      settingList: [{
        icon: '',
        name: '个人信息',
        path: 'User'
      }, {
        icon: '',
        name: '修改密码',
        path: 'Security'
      }]
    }
  },
  methods: {
    handleClick (item) {
      this.activeName = item.name
      this.componentSrc = item.path
    }
  }
}
</script>

<style  lang="scss" scoped>
@import '../assets/css/setting.scss';
</style>

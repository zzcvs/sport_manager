<template>
  <div class="list_menu diy_menu">
    <router-link
            to="/user_center/index"
            class="menu_item"
            :class="{ selected: this.$route.path=== '/user_center/index'}"
     >
      <span class="left_span">个人首页</span>
      <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
    </router-link>
          <router-link
          to="/member_user/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/member_user/table'}"
          v-if="$check_action('/member_user/table','get')">
        <span class="left_span">会员用户</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/course_information/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/course_information/table'}"
          v-if="$check_action('/course_information/table','get')">
        <span class="left_span">课程信息</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/course_participation/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/course_participation/table'}"
          v-if="$check_action('/course_participation/table','get')">
        <span class="left_span">课程参与</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/fitness_advice/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/fitness_advice/table'}"
          v-if="$check_action('/fitness_advice/table','get')">
        <span class="left_span">健身建议</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/membership_card/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/membership_card/table'}"
          v-if="$check_action('/membership_card/table','get')">
        <span class="left_span">会员卡</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/device_information/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/device_information/table'}"
          v-if="$check_action('/device_information/table','get')">
        <span class="left_span">设备信息</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
              <router-link
          to="/financial_statements/table"
          class="menu_item"
          :class="{ selected: this.$route.path=== '/financial_statements/table'}"
          v-if="$check_action('/financial_statements/table','get')">
        <span class="left_span">财务报表</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
      </router-link>
      <router-link
              to="/user/collect"
              class="menu_item"
              :class="{ selected: this.$route.path=== '/user/collect'}"
              v-if="$check_action('/collect/list','get')">
        <span class="left_span">收藏</span>
        <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
  </router-link>
	<router-link
	            to="/comment/table"
	            class="menu_item"
	            :class="{ selected: this.$route.path=== '/comment/table'}"
	            v-if="$check_action('/comment/table','get')">
	      <span class="left_span">评论管理</span>
	      <span class="right_span"><b-icon icon="house-fill"></b-icon></span>
	</router-link>
  </div>
</template>

<script>
import mixin from "@/mixins/page.js";
export default {
  mixins: [mixin],
  data() {
    return {
    };
  },
  mounted() {
  },
  methods: {
  },

  components: {
  },
};
</script>

<style scoped>
.list_menu {
  flex: 0 0 25%;
  border-bottom: 1px solid #eee;
}
.menu_item {
  display: flex;
  justify-content: space-between;
  padding: 15px 20px;
  border: 1px solid #eee;
  border-bottom: none;
  background-color: #fff;
}
.menu_item.selected {
  background-color: #000;
}
.menu_item.selected span {
  color: #fff ;
}
.content {
  flex: 0 0 74%;
}
</style>

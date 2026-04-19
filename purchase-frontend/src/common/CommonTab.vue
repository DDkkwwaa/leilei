<template>
  <div class="tabs-bar">
    <el-tag
      v-for="(item, index) in tags"
      :key="item.path"
      :effect="$route.name === item.name ? 'dark' : 'plain'"
      :closable="item.name !== 'Index'"
      @click="changeMenu(item)"
      @close="handleClose(item, index)"
    >
      {{ item.label }}
    </el-tag>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  name: "CommonTab",
  computed: {
    ...mapState({
      tags: (state) => state.tab.tabsList,
    }),
  },
  methods: {
    ...mapMutations(["closeTag"]),
    changeMenu(item) {
      this.$router.push(item.path);
    },
    handleClose(item) {
      this.closeTag(item);
      if (this.$route.name !== item.name) {
        return;
      }
      this.$router.push(this.tags[0]);
    },
  },
};
</script>

<style lang="less" scoped>
.tabs-bar {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 10px;
  margin: 0 2px;
  padding: 0 0 8px;

  .el-tag {
    cursor: pointer;
    height: 34px;
    border: 1px solid #dbe3ea;
    border-radius: 999px;
    background: rgba(255, 255, 255, 0.92);
    color: #708090;
    line-height: 32px;
    box-shadow: 0 8px 16px rgba(18, 34, 47, 0.04);
  }

  /deep/ .el-tag--dark {
    border-color: transparent;
    background: linear-gradient(135deg, #11202c 0%, #1c2f3d 100%);
    color: #ffffff;
  }
}
</style>

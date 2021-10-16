<template>
  <a-layout id="components-layout-demo-side" style="min-height: 100vh">
    <a-layout-sider v-model="collapsed" width="260" collapsible="">
      <div class="logo"/>
      <a-menu router theme="dark" :default-selected-keys="['1']" mode="inline">
        <a-menu-item key="1">
          <router-link :to="{path: '/'}">
            <a-icon type="desktop"/>
            首页
          </router-link>
        </a-menu-item>
<!--        <a-sub-menu key="2">-->
<!--          <span slot="title"><a-icon type="pie-chart" /><span>可视化图表</span></span>-->
        <a-menu-item key="2">
          <router-link :to="{path: '/BicycleData'}"/>
          骑行量数据统计
        </a-menu-item>
        <a-menu-item key="3">
          <router-link :to="{path: '/BicycleTime'}"/>
          骑行时长数据统计
        </a-menu-item>
        <a-menu-item key="4">
          <router-link :to="{path: '/BicycleDistance'}"/>
          骑行距离数据统计
        </a-menu-item>
        <a-menu-item key="5">
          <router-link :to="{path: '/BicycleTrace01'}"/>
          骑行动态轨迹
        </a-menu-item>
        <a-menu-item key="6">
          <router-link :to="{path: '/BicycleTrace02'}"/>
          骑行静态轨迹
        </a-menu-item>
        <a-menu-item key="7">
          <router-link :to="{path: '/to2dHot'}"/>
          2d热力图
        </a-menu-item>
<!--        <a-menu-item key="8">-->
<!--          <router-link :to="{path: '/testOL'}"/>-->
<!--          测试跳转geoserver发布图层-->
<!--        </a-menu-item>-->
        <a-menu-item key="8">
          <router-link :to="{path: '/to3dHot'}"/>
          3d热力图
        </a-menu-item>
        <a-menu-item key="9">
          <router-link :to="{path: '/flow'}"/>
          流向图
        </a-menu-item>
        <a-menu-item key="10">
          <router-link :to="{path: '/isolated'}"/>
          孤立社区分析
        </a-menu-item>
        <a-menu-item key="11">
          <router-link :to="{path: '/spread'}"/>
          社区扩散指数分析
        </a-menu-item>
<!--        </a-sub-menu>-->
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0" />
      <a-layout-content :style="{ margin: '24px 16px 0', overflow: 'initial' }">
        <router-view></router-view>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>
<script>
export default {
  data() {
    return {
      collapsed: false,
    };
  },
};
</script>

<style>
#components-layout-demo-side .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
</style>

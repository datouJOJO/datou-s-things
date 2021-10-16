<template>
  <div>
    <div id="container"></div>
  </div>
</template>

<script>
import { Column } from '@antv/g2plot';
export default {
  data() {
    return {
      // data: require('D:/workplace/bicycleTime01/bicycleJson.json')
      data: require('../assets/distance.json')
    };
  },
  methods:{
    init(){
      const columnPlot = new Column('container',{
        data: this.data,
        xField:'type',
        yField:'distance',
      });
      columnPlot.render()
    }
  },
  mounted() {
    this.init();
  }
}
</script>

<style scoped>

</style>

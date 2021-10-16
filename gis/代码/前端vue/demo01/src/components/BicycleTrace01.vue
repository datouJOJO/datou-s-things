<template>
  <div>
    <div id="map"></div>
  </div>
</template>
<script>
import { Scene, PointLayer,LineLayer } from '@antv/l7';
import { GaodeMap } from '@antv/l7-maps';
import { Mapbox } from '@antv/l7-maps';

export default {
  data() {
    return{
      // data:require('../assets/BicyclePath.json')
      data:require('D:/workplace/bicycleTime01/BicyclePath.json')
    };
  },
  methods:{
    init(){
      const scene = new Scene({
        id: 'map',
        position: 'relative',
        map: new GaodeMap({
          // resizeEnable: true, // 是否监控地图容器尺寸变化
          center: [ 114.25796536341, 30.574366718337],
          zoom: 12,
          style: 'dark',

        }),
      });
      const lineLayer = new LineLayer().source(this.data, {
        parser: {
          type: 'json',
          coordinates: 'path',
        },
      })
      .size(1.5)
      .shape('line')
      .color('color', v => {
        return `rgb(${v})`;
      })
      .animate({
        interval: 0.6,
        trailLength: 1.5,
        duration: 6
      });
      scene.addLayer(lineLayer);
    }
  },
  mounted() {
    this.init();
  }
};
</script>

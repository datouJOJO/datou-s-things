<template>
  <div id="container"></div>
</template>

<script>
import { Line } from '@antv/g2plot'

export default {
  data() {
    return {
      // data: require('D:/workplace/bicycleTime01/bicycleJson.json')
      data: require('../assets/bicycleJson.json')
    };
  },
  methods: {
    init() {
      const linePlot = new Line(document.getElementById("container"), {
        padding: [20, 100, 30, 80],
        forceFit: true,
        data: this.data,
        xField: 'time',
        yField: 'frequency',
        yAxis:{
          tickCount:10,
          // tickInterval: 1000
        },
        color:'green',
        xAxis: {
          label: {
            autoRotate: false,
          },
        },
        slider: {
          start: 0.0,
          end: 1.0,
          height:10
        },
        label: {
          style: {
            fill: 'blue',
            opacity: 0.6,
            fontSize: 10
          },
          rotate: true
        }
      });
      linePlot.render();
    },
  },
  mounted() {
    this.init();
  }
};
</script>


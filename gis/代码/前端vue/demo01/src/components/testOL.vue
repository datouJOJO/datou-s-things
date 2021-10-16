<template>
  <div id="map" ref="rootMap" class="map"></div>
</template>

<script>
import "ol/ol.css";
import Map from "ol/Map";
import View from "ol/View";
import TileLayer from "ol/layer/Tile";
import TileWMS from "ol/source/TileWMS";
export default {
  data() {
    return {
      map: null
    };
  },
  methods: {
    init() {
      var layers = [
        new TileLayer({
          extent: [// 边界
            -1.63519322872162,
            -0.806867003440857,
            0.699570834636688,
            0.583691000938416
          ],
          source: new TileWMS({
            url: "http://localhost:8080/geoserver/datougis/wms",
            // Layers需要指定要显示的图层名
            params: { LAYERS: "datougis:testdata", TILED: true },
            // serverType明显为geoserver
            serverType: "geoserver",
            // Countries have transparency, so do not fade tiles:
            transition: 0
          })
        })
      ];
      this.map = new Map({
        layers: layers,
        target: "map",
        view: new View({
          projection: "EPSG:4326",
          center: [0.1, -0.00824],
          zoom: 9
        })
      });
    }
  },
  mounted() {
    this.init();
  }
}
</script>

<style scoped>
#map{
  height:800px;
  width: 1400px;
}
/*隐藏ol的一些自带元素*/
.ol-attribution,.ol-zoom { display: none;}
</style>

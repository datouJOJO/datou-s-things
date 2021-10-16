import Vue from 'vue'
import Router from 'vue-router'
import BicycleTrace01 from '../components/BicycleTrace01'
import BicycleTrace02 from "../components/BicycleTrace02";
import BicycleDistance from "../components/BicycleDistance";
import BicycleTime from '../components/BicycleTime'
import BicycleData from "../components/BicycleData";
import to2dHot from "../components/to2dHot"
import testOL from "../components/testOL";
import to3dHot from "../components/to3dHot";
import hello from "../components/hello";
import flow from "../components/flow";
import spread from "../components/spread";
import isolated from "../components/isolated";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'hello',
      component: hello
    },
    {
      path: '/BicycleTrace01',
      name: 'BicycleTrace01',
      component: BicycleTrace01
    },
    {
      path: '/BicycleTrace02',
      name: 'BicycleTrace02',
      component: BicycleTrace02
    },
    {
      path: '/BicycleDistance',
      name: 'BicycleDistance',
      component: BicycleDistance
    },
    {
      path: '/BicycleTime',
      name: 'BicycleTime',
      component: BicycleTime
    },
    {
      path: '/BicycleData',
      name: 'BicycleData',
      component: BicycleData
    },
    {
      path: '/to2dHot',
      name: 'to2dHot',
      component: to2dHot
    },
    {
      path: '/to3dHot',
      name: 'to3dHot',
      component: to3dHot
    },
    {
      path: '/testOL',
      name: 'testOL',
      component: testOL
    },
    {
      path: '/flow',
      name: 'flow',
      component: flow
    },
    {
      path: '/spread',
      name: 'spread',
      component: spread
    },
    {
      path: '/isolated',
      name: 'isolated',
      component: isolated
    },

  ]
})

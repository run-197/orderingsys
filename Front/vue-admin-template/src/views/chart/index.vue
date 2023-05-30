<template>
  <div class="dashboard-editor-container">

    <el-row :gutter="50">
      <el-select v-model="salesmonth" placeholder="请选择月份" @change="selectMonth(salesmonth)">
        <el-option
          v-for="item in options"
          :key="item| formatMonth"
          :label="item| formatMonth"
          :value="item| formatMonth"
        />
      </el-select>
    </el-row>
    <el-row :gutter="50" style="margin-top: 20px; height: 600px;">
      <el-col :xs="24" :sm="24" :lg="8" style="height: 100%;">
        <div>
          <pie-chart ref="chart" :name="name1" :chart-data="chartData1" :legend-data="legendData1" :width="'400px'" :height="'400px'" :radius=" [15, 75]" @chart-click="handleChartClick" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8" style="height: 100%;">
        <div>
          <pie-chart ref="chart" :name="name2" :chart-data="chartData2" :legend-data="legendData2" :radius=" [30, 205]" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Vue from 'vue'
import PieChart from './components/PieChart'
import { getmonth, getmonthsales, getDishmonthsales } from '@/api/dish'
export default {
  name: 'Chart',
  components: {
    PieChart
  },
  data() {
    return {
      selectedmonth: '',
      salesmonth: '',
      options: [],
      hasSalesInfo: false,
      name1: '',
      chartData1: [{ name: 'null', value: 1 }],
      legendData1: ['null'],
      name2: '',
      chartData2: [{ name: 'null', value: 1 }],
      legendData2: ['null']
    }
  },
  mounted() {
    getmonth().then(response => {
      this.options = response
      console.log(this.options)
    })
  },
  methods: {
    selectMonth(val) {
      this.hasSalesInfo = true
      console.log(val)
      const monthprama = this.options.filter(option => {
        const formattedValue = Vue.filter('formatMonth')(option)
        return formattedValue === val
      })
      console.log(monthprama[0])
      this.selectedmonth = Vue.filter('formatDate')(monthprama[0])
      getmonthsales(Vue.filter('formatDate')(monthprama[0])).then(response => {
        console.log(response)
        const typename = response.map(obj => obj.name)
        this.name1 = val + '菜品类目销售数据'
        this.chartData1 = response
        this.legendData1 = typename
      })
      this.name2 = ''
      this.chartData2 = [{ name: 'null', value: 1 }]
      this.legendData2 = ['null']
    },
    handleChartClick(params) {
      console.log(params.data.name) // 输出点击的参数
      if (params.data.name !== 'null') {
        getDishmonthsales(this.selectedmonth, params.data.name).then(response => {
          console.log(response)
          const dishname = response.map(obj => obj.name)
          console.log(dishname)
          this.name2 = '菜品销售数据'
          this.chartData2 = response
          this.legendData2 = dishname
        })
      }
      // 在这里处理点击事件
    }
  }
}
</script>

  <style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .github-corner {
      position: absolute;
      top: 0px;
      border: 0;
      right: 0;
    }

    .chart-wrapper {
      background: #ede7e7;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }

  @media (max-width:1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
  </style>

<!-- <template>
  <div ref="chart" style="width: 100%; height: 400px;" />
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'PieChart',
  mounted() {
    this.drawChart()
  },
  methods: {
    drawChart() {
      // 生成随机数据
      const data = [
        { name: 'A', value: 200 },
        { name: 'B', value: 152 },
        { name: 'C', value: 76 },
        { name: 'D', value: 42 },
        { name: 'E', value: 577 }
      ]

      // 初始化ECharts实例
      const chart = echarts.init(this.$refs.chart)

      // 配置图表选项
      const option = {
        title: {
          text: 'Pie Chart'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 10,
          data: data.map(item => item.name)
        },
        series: [
          {
            name: 'Pie Chart',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '30',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: data
          }
        ]
      }

      // 设置图表选项
      chart.setOption(option)

      // 绑定点击事件
      chart.on('click', params => {
        console.log(params.name, params.value)
      })
    }
  }
}
</script> -->

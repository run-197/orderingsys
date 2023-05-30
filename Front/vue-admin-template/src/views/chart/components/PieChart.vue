<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './resize'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '600px'
    },
    height: {
      type: String,
      default: '600px'
    },
    name: {
      type: String,
      default: ''
    },
    legendData: {
      type: Array,
      default: null
    },
    chartData: {
      type: Array,
      default: null
    },
    radius: {
      type: Array,
      default: null
    }
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    chartData: {
      handler() {
        this.updateChart()
      },
      deep: true
    },
    legendData: {
      handler() {
        this.updateChart()
      },
      deep: true
    },
    name: {
      handler() {
        this.updateChart()
      },
      deep: true
    },
    radius: {
      handler() {
        this.updateChart()
      },
      deep: true
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
      this.chart.on('click', (data) => {
        this.handleClick(data)
      })
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    handleClick(data) {
      // 触发自定义事件
      this.$emit('chart-click', data)
    },
    updateChart() {
      if (!this.chart) {
        return
      }
      this.chart.setOption({
        legend: {
          data: this.legendData
        },
        series: [
          {
            radius: this.radius,
            name: this.name,
            data: this.chartData
          }
        ]
      })
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: this.legendData
        },
        series: [
          {
            name: this.name,
            type: 'pie',
            roseType: 'radius',
            radius: this.radius,
            center: ['50%', '46%'],
            data: this.chartData,
            animationEasing: 'cubicInOut',
            animationDuration: 2600,
            itemStyle: {
              normal: {
                label: {
                  show: true,
                  formatter: '{b} : {c} ({d}%)'
                },
                labelLine: { show: true }
              }
            }
          }
        ]
      })
    }
  }
}
</script>


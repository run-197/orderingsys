<template>
  <div class="app-container">
    <el-descriptions v-if="orderInfo!=null" v-loading="infoLoading" title="订单详情" direction="vertical" :column="4" border>
      <template slot="extra">
        <el-button v-if="orderInfo.order_status=='Submitted'" type="success" icon="el-icon-finished" size="small" @click="serving(orderInfo.order_ID)">上菜</el-button>
      </template>
      <el-descriptions-item align="center" width="150" label="订单号">
        {{ orderInfo.order_ID }}
      </el-descriptions-item>
      <el-descriptions-item align="center" width="150" label="桌号">
        {{ orderInfo.table_ID }}
      </el-descriptions-item>
      <el-descriptions-item align="center" width="150" label="订单总额">{{ orderInfo.total_amount }}</el-descriptions-item>
      <el-descriptions-item align="center" label="订单时间">
        <i class="el-icon-time" />
        <span>  {{ orderInfo.order_time | formatDate }}</span>
      </el-descriptions-item>
      <el-descriptions-item align="center" label="订单状态">
        <el-tag :type="orderInfo.order_status | statusFilter">{{ orderInfo.order_status| statuslanguageFilter }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item v-if="orderInfo.order_status!=='Submitted'" align="center" label="用户评论反馈">{{ orderInfo.comment }}</el-descriptions-item>
    </el-descriptions>
    <el-tag :type="orderInfo.order_status | statusFilter" style="width: 100%; font-size: large;" align="center">订单菜品详情</el-tag>
    <el-table v-loading="listLoading" :data="dishlist" element-loading-text="Loading" height="300" border fit highlight-current-row>
      <el-table-column align="center" width="100" label="编号">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" width="200" label="菜品ID">
        <template slot-scope="scope">
          {{ scope.row.dish_ID }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="菜品名称">
        <template slot-scope="scope">
          {{ scope.row.dish_name }}
        </template>
      </el-table-column>
      <el-table-column width="200" align="center" label="菜品单价">
        <template slot-scope="scope">
          <span>{{ scope.row.dish_nuitprice }}</span>
        </template>
      </el-table-column>
      <el-table-column width="200" align="center" label="菜品数量">
        <template slot-scope="scope">
          {{ scope.row.dish_num }}
        </template>
      </el-table-column>
      <el-table-column width="200" align="center" label="合计">
        <template slot-scope="scope">
          {{ scope.row.dish_num * scope.row.dish_nuitprice }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getOneorder, getDishesofOrder, dishServing } from '@/api/order'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        commented: 'success',
        settled: 'gray',
        Submitted: 'danger'
      }
      return statusMap[status]
    },
    statuslanguageFilter(status) {
      const languageMap = {
        commented: '已评论',
        settled: '未评论',
        Submitted: '待上餐'
      }
      return languageMap[status]
    }
  },
  data() {
    return {
      ID: -1,
      orderInfo: null,
      dishlist: null,
      infoLoading: true,
      listLoading: true
    }
  },
  created() {
    this.ID = this.$route.query.ID
    this.getOrderInfo()
  },
  methods: {
    getOrderInfo() {
      this.infoLoading = true
      getOneorder(this.ID).then(response => {
        this.orderInfo = response
        console.log(response)
        this.infoLoading = false
      })
      this.listLoading = false
      getDishesofOrder(this.ID).then(response => {
        console.log(response)
        this.dishlist = response
      })
    },
    serving(id) {
      this.$confirm('请确定餐品已经完成？', '上菜', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        dishServing(id).then(response => {
          if (response) {
            this.$message({
              type: 'success',
              message: '完成上菜!'
            })
            this.fetchData()
          } else {
            this.$message({
              type: 'error',
              message: '网络错误，联系管理人员处理'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        })
      })
    }
  }
}
</script>

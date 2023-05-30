<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" height="650" border fit highlight-current-row>
      <el-table-column align="center" label="编号" width="100">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="订单ID" width="100" prop="order_ID" sortable :sort-method="(a, b) => a.order_ID - b.order_ID" :sort-by="['order_ID']">
        <template slot-scope="scope">
          {{ scope.row.order_ID }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="餐桌号" width="100">
        <template slot-scope="scope">
          {{ scope.row.table_ID }}
        </template>
      </el-table-column>
      <el-table-column label="总价" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.total_amount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="菜品种类数" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.item_quantity }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="订单状态" width="150" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.order_status | statusFilter">{{ scope.row.order_status| statuslanguageFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-document-checked" size="small" @click="checkDetail(scope.row)">查看</el-button>
          <el-button v-if="scope.row.order_status=='Submitted'" type="success" icon="el-icon-finished" size="small" @click="serving(scope.row.order_ID)">上菜</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="订单时间" :sortable="true">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.order_time | formatDate }}</span>
        </template>
      </el-table-column>
      <router-view />
    </el-table>
  </div>
</template>

<script>
import { getorderList, dishServing } from '@/api/order'

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
      list: null,
      listLoading: true,
      tobesettle: []
    }
  },
  created() {
    this.fetchData()
    setInterval(() => {
      this.fetchData()
    }, 60000) // 60 seconds
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getorderList().then(response => {
        console.log(response)
        this.list = response.reverse()
        this.listLoading = false

        // Check for new orders with status 'Submitted'
        const newOrders = response.filter(order => order.order_status === 'Submitted')
        if (newOrders.length > 0) {
          newOrders.forEach(order => {
            if (!this.tobesettle.includes(order.order_ID)) {
              this.tobesettle.push(order.order_ID)
              this.$alert('有新的订单，请尽快备餐', '新订单', {
                confirmButtonText: '确定'
              })
            }
          })
        }
      })
    },
    checkDetail(row) {
      var ID = row.order_ID
      this.$router.push({ name: '订单详情', query: { ID }})
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


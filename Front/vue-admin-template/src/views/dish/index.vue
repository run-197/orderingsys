<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label="编号" width="80">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="菜品ID" width="100" prop="dish_ID" sortable :sort-method="(a, b) => a.dish_ID - b.dish_ID" :sort-by="['dish_ID']">
        <template slot-scope="scope">
          {{ scope.row.dish_ID }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="菜品名称" width="120">
        <template slot-scope="scope">
          {{ scope.row.dish_name }}
        </template>
      </el-table-column>
      <el-table-column label="单价" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.dish_nuitprice }}</span>
        </template>
      </el-table-column>
      <el-table-column label="月销量" width="100" align="center" prop="monthly_sales" sortable :type="'number'" :sort-by="['monthly_sales']">
        <template slot-scope="scope">
          {{ scope.row.monthly_sales }}
        </template>
      </el-table-column>
      <el-table-column label="评分" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.dish_rating }}
        </template>
      </el-table-column>
      <el-table-column label="菜品描述" align="center">
        <template slot-scope="scope">
          {{ scope.row.dish_description }}
        </template>
      </el-table-column>
      <el-table-column label="菜品状态" width="100" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.dish_quantity | dishststus">{{ scope.row.dish_quantity | dishststuslang }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-document-checked" size="small" @click="updateDetail(scope.row.dish_ID)">修改详情</el-button>
          <el-button v-if="scope.row.dish_quantity=='sellout'" type="success" icon="el-icon-finished" size="small" @click="shelf(scope.row.dish_ID)">上架</el-button>
          <el-button v-else type="danger" icon="el-icon-circle-close" size="small" @click="sellout(scope.row.dish_ID)">售罄</el-button>
          <el-button type="danger" icon="el-icon-delete" size="small" @click="deletedish(scope.row.dish_ID)">删除</el-button>
        </template>
      </el-table-column>
      <router-view />
    </el-table>
  </div>
</template>

<script>
import { getdishList, updateDishstatus, delDishbyID } from '@/api/dish'

export default {

  filters: {
    dishststuslang(status) {
      const statusMap = {
        sellout: '售罄',
        shelf: '在售'
      }
      return statusMap[status]
    },
    dishststus(status) {
      const statusMap = {
        sellout: 'danger',
        shelf: 'success'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getdishList().then(response => {
        this.list = response.slice(1)
        this.listLoading = false
      })
    },
    updateDetail(ID) {
      this.$router.push({ name: '更新菜品', query: { ID }})
    },
    shelf(ID) {
      this.$confirm('请确定菜品已准备充分？', '上架菜品', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        updateDishstatus(ID, 'shelf').then(response => {
          if (response) {
            this.$message({
              type: 'success',
              message: '上架成功!'
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
    },
    sellout(ID) {
      this.$confirm('确定要下架菜品吗？', '菜品售罄', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        updateDishstatus(ID, 'sellout').then(response => {
          if (response) {
            this.$message({
              type: 'success',
              message: '已宣布售罄!'
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
    },
    deletedish(ID) {
      this.$confirm('删除操作不可逆，确认删除？', '删除菜品', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delDishbyID(ID).then(response => {
          if (response) {
            this.$message({
              type: 'success',
              message: '删除成功!'
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


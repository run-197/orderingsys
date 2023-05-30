<template>
  <div class="app-container">
    <el-form ref="form" :rules="rules" :model="form" label-width="120px">
      <el-form-item label="菜品名称" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="菜品种类" prop="type">
        <el-select v-model="form.type" filterable allow-create default-first-option placeholder="请选择菜品种类">
          <el-option
            v-for="item in options"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="菜品单价" prop="nuitprice">
        <el-input v-model.number="form.nuitprice" type="number" step="0.1" min="0" />
      </el-form-item>
      <el-form-item label="菜品描述" prop="description">
        <el-input
          v-model="form.description"
          type="textarea"
          placeholder="请输入内容"
          maxlength="50"
          show-word-limit
        />
      </el-form-item>
      <el-form-item label="菜品图片">
        <el-upload
          ref="upload"
          class="upload-demo"
          drag
          action="http://localhost:8080/upload"
          :auto-upload="false"
          :on-success="receiveaddr"
          multiple
        >
          <i class="el-icon-upload" />
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div slot="tip" class="el-upload__tip">支持上传jpg/jpeg/png/gif文件，大小不超过2Mb</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">添加</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getalltype, addnewdish, adddishpic } from '@/api/dish'

export default {
  data() {
    return {
      options: [],
      value: [],
      form: {
        name: '',
        type: '',
        nuitprice: 0,
        description: ''
      },
      picList: [],
      rules: {
        name: [
          { required: true, message: '请输入菜品名称', trigger: 'blur' },
          { min: 1, max: 10, message: '太长了哦', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择菜品种类', trigger: 'change' }
        ],
        nuitprice: [
          { required: true, message: '请输入菜品单价', trigger: 'blur' },
          { type: 'number', message: '必须为数字' },
          { pattern: /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/, message: '价格非法' }
        ]
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getalltype().then(response => {
        this.options = response
        console.log(this.options)
      })
    },
    submitUpload() {
      this.$refs.upload.submit() // 这个地方的upload要和上面定义的ref一样
    },
    submitForm(formName) {
      var picadd = ''
      if (this.picList.length !== 0) { picadd = this.picList[0] }
      console.log(picadd)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.form)
          addnewdish(this.form.name, this.form.nuitprice, 'sellout', this.form.description, picadd, this.form.type).then(response => {
            this.picList.forEach(e => {
              adddishpic(response, e)
            })
            this.$message({
              type: 'success',
              message: '提交成功!' })
            this.$router.push('/dish')
          })
        } else {
          this.$message('请填写信息!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$message({
        message: '表单已重置!',
        type: 'warning'
      })
      this.$refs[formName].resetFields()
    },
    receiveaddr(res) {
      this.picList.push('http://150.158.23.80:8233/img/' + res)
    }
  }
}
</script>

  <style scoped>
  .line{
    text-align: center;
  }
  </style>


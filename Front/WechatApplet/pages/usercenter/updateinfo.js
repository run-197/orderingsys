const app = getApp()



Page({
  data: {
    openid:'',
    nickName:"",
    avatarUrl: '',
    theme: wx.getSystemInfoSync().theme,
  },
  onLoad() {
    // console.log(app.avatarUrl)
    wx.onThemeChange((result) => {
      this.setData({
        theme: result.theme
      })
    })
    this.setData({
      openid:app.openid,
      avatarUrl:app.avatarUrl
    })
  },  
  onClickLeft() {
    wx.navigateBack({
      delta: 1,  // 返回上一级页面。
  success: function() {
    }}
    )
  },
  onChooseAvatar(e) {
    const { avatarUrl } = e.detail 
    this.setData({
      avatarUrl:avatarUrl
    })
    app.avatarUrl=avatarUrl
    wx.setStorage({
      key: "avatarUrl",
      data: avatarUrl,
    })
    // console.log(e.detail)
  },
  formSubmit(e) {
    var nickName=e.detail.value.nickname
    var phone_number=e.detail.value.phone_number
    const regex = /^1[3456789]\d{9}$/;  
    var isvalid= regex.test(phone_number);  
    var avatarurl=this.data.avatarUrl
    var that=this
    if(nickName==''){
      wx.showToast({
        title: '请填写昵称',
        icon: 'error',
        duration: 1000
      })
      return
    }
    if(phone_number!=''&&!isvalid){
      wx.showToast({
        title: '手机号不正确',
        icon: 'error',
        duration: 1000
      })
      return
    }
    this.setData({
      nickName:nickName
    })
    app.nickName=nickName
    wx.setStorage({
      key: "nickName",
      data: nickName,
    })
    app.hasUserInfo=true
    
    wx.uploadFile({
      url: app.globalData.baseUrl+'/upload',
      filePath: avatarurl,
      name: 'file', //需要传给后台的图片字段名称
      formData: {},
      header: {
        "Content-Type": "multipart/form-data", //form-data格式
        'Accept': 'application/json',
      },
      success: function (res) {
        // console.log(res.data)
// 以下仅为测试后端接受上传图片的接口，本地测试环境使用虽然可以保存图片，但是小程序不能访问项目外的资源地址
        let avatarurl="http://150.158.23.80:8233/img/"+res.data
        wx.setStorage({
          key: "avatarUrl",
          data: avatarurl,
        })
        wx.request({
          url: app.globalData.baseUrl+'/customer/updateCustomer',
          data:{
            customer_ID:app.customerid,
            customer_nickname:that.data.nickName,
            phone_number:phone_number,
            avatar_address:avatarurl,
          },
          success:function(){
            wx.showToast({
              title: '修改成功',
              mask:true,
            })
          }
        })
      }
    })
  wx.navigateBack();
  },
})

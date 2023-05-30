// app.js
App({
  code: -1,
  hasLogin: false,
  openid: "",
  customerid: -1,
  tableid: -1,
  hasUserInfo: false,
  nickName: "",
  avatarUrl: "",
  globalData: {
    baseUrl:"http://localhost:8080",
    dishesList: [{
      dish_ID: 1,
      dish_description: "",
      dish_name: "",
      dish_nuitprice: 0.0,
      dish_quantity: "",
      dish_rating: "",
      monthly_sales: "",
      picture_address: ""
    }, ],
    orderrecord: [{
      index: -1,
      amount: 0
    }, ],
    iscartEmpty: true,
    totalamount: 0
  },
  onLaunch: function () {
    var that = this;
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.request({
            method: "GET",
            // 以code换取 用户唯一标识openid 和 会话密钥session_key
            url: that.globalData.baseUrl + '/customer/getOpenID',
            data: {
              code: res.code
            },
            success: res => {
              // console.log(res.data);
              wx.setStorage({
                key: 'openid',
                data: res.data,
              });
              that.openid = res.data
              wx.request({
                url: that.globalData.baseUrl + '/customer/getCustomerinfo',
                data: {
                  openid: res.data
                },
                success(res) {
                  // console.log(res.data)
                  if (res.data != '') {
                    that.customerid = res.data.customer_ID,
                      that.nickName = res.data.customer_nickname,
                      that.avatarUrl = res.data.avatar_address,
                      that.hasUserInfo = true
                  }
                }
              })
            }
          })
        } else {
          wx.showModal({
            title: '提示信息',
            content: '登录失败！'
          })
        }
      }
    })
    try {
      var value = wx.getStorageSync('nickName')
      if (value) {
        // console.log(value)
        that.hasUserInfo = true
        that.nickName = value
        console.log("成功获取昵称缓存")
      } else {
        console.log("昵称缓存为空")
      }
    } catch (e) {
      console.log("something error")
    }
    try {
      var value = wx.getStorageSync('avatarUrl')
      if (value) {
        // console.log(value)
        that.avatarUrl = value
        console.log("成功获取头像缓存")
      } else {
        console.log("头像缓存为空")
      }
    } catch (e) {
      console.log("something error")
    }
  }

})
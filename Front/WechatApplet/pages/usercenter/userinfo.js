const app = getApp()

const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'


Page({
  data: {
    openid: '',
    nickName: "",
    avatarUrl: defaultAvatarUrl,
    theme: wx.getSystemInfoSync().theme,
  },
  onLoad() {
    wx.onThemeChange((result) => {
      this.setData({
        theme: result.theme
      })
    })
    this.setData({
      openid: app.openid
    })
  },
  onClickLeft() {
    wx.navigateBack({
      delta: 1, // 返回上一级页面。
      success: function () {}
    })
  },
  onChooseAvatar(e) {
    const {
      avatarUrl
    } = e.detail
    this.setData({
      avatarUrl: avatarUrl
    })
    wx.setStorage({
      key: "avatarUrl",
      data: avatarUrl,
    })
    // console.log(e.detail)
  },
  formSubmit(e) {
    var nickName = e.detail.value.nickname
    var avatarurl = this.data.avatarUrl
    var that = this
    if (nickName == '') {
      wx.showToast({
        title: '请填写昵称',
        icon: 'error',
        duration: 1000
      })
      return
    }
    this.setData({
      nickName: nickName
    })
    if (avatarurl == defaultAvatarUrl) {
      wx.showToast({
        title: '请选择头像',
        icon: 'error',
        duration: 1000
      })
      return
    }
    wx.setStorage({
      key: "nickName",
      data: nickName,
    })
    app.hasUserInfo = true
    wx.uploadFile({
      url: app.globalData.baseUrl + '/upload',
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
        let avatarurl = "http://150.158.23.80:8233/img/" + res.data
        wx.setStorage({
          key: "nickName",
          data: nickName,
        })
        wx.setStorage({
          key: "avatarUrl",
          data: avatarurl,
        })
        wx.request({
          url: app.globalData.baseUrl + '/customer/addNewCustomer',
          data: {
            customer_nickname: nickName,
            phone_number: '',
            avatar_address: avatarurl,
            openID: that.data.openid
          },
          success(res) {
            app.nickName = nickName
            app.avatarUrl = avatarurl
            wx.request({
              url: that.globalData.baseUrl + '/customer/getCustomerinfo',
              data: {
                openid: that.data.openid
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
      }
    })
    wx.navigateBack();
  },
})
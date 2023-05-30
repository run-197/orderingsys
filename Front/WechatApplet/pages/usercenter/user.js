// pages/usercenter/user.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0,
    veriTip: "未认证",
    openid: '',
    customerid: -1,
    avatarUrl: '',
    nickName: '',
    hasUserInfo: false,
    canIUseGetUserProfile: true,
    canIUseOpenData: false, // 如需尝试获取用户信息可改为false
    userinfo: '',
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onload() {},
  onShow() {
    const taBar = this.getTabBar()
    taBar.setData({
      active: 2
    })
    // console.log(app.avatarUrl)
    var that = this
    this.setData({
      openid: app.openid
    })
    if (app.hasUserInfo) {
      this.setData({
        hasUserInfo: app.hasUserInfo,
        nickName: app.nickName,
        avatarUrl: app.avatarUrl
      })
    } else {
      wx.request({
        url: app.globalData.baseUrl + '/customer/getCustomerinfo',
        data: {
          openid: that.data.openid
        },
        success(res) {
          // console.log(res.data)
          if (res.data != '') {
            that.setData({
              customerid: res.data.customer_ID,
              nickName: res.data.customer_nickname,
              avatarUrl: res.data.avatar_address,
              hasUserInfo: true
            })
            app.customerid = res.data.customer_ID,
              app.nickName = res.data.customer_nickname,
              app.avatarUrl = res.data.avatar_address,
              app.hasUserInfo = true
          }
        }
      })
    }
  },

  getUserProfile() {
    wx.navigateTo({
      url: './userinfo',
    })
  },
  skip2myorder: function () {
    if (!app.hasUserInfo) {
      wx.showModal({
        title: '提示',
        content: '请先登录',
        success(res) {
            return   
        }
      })
    } else {
      wx.navigateTo({
        url: './myorder',
      })
    }

  },
  skip2updateinfo: function () {
    wx.navigateTo({
      url: './updateinfo',
    })
  },
  about: function () {
    wx.navigateTo({
      url: './about',
    })
  },
  bindViewTap: function () {
    // console.log(this.data.avatarUrl)
  },
  bitphone: function () {
    wx.makePhoneCall({
      phoneNumber: '12323456789',
      success: function () {}
    })
  },
})
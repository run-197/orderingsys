const app = getApp()
const dayjsMin = require("../../scripts/dayjs.min");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasShowed: false,
    isorderEmpty: true,
    orderList: [{}],
    orderList4show: [{}]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (!app.hasUserInfo)
      return
    var that = this
    wx.request({
      url: app.globalData.baseUrl + '/order/getallorderbyID',
      data: {
        customer_ID: app.customerid
      },
      success: function (res) {
        var orderList4show = res.data.filter(p => p.order_status == 'Submitted');
        orderList4show.forEach(element => {
          let time = dayjsMin(element.order_time).format()
          let date = new Date(time)
          var ans = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + '\xa0\xa0\xa0\xa0' + date.getHours() + ':' + date.getMinutes()
          element.order_time = ans
        });
        that.setData({
          orderList: res.data.reverse(),
          orderList4show: orderList4show.reverse()
        })
        // console.log(that.data.orderList4show)
        if (that.data.orderList4show.length != 0)
          that.setData({
            isorderEmpty: false
          })
        // console.log(orderList4show)
      }
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    const taBar = this.getTabBar()
    taBar.setData({
      active: 1
    })
    if (!app.hasUserInfo)
      return
    var that = this
    wx.request({
      url: app.globalData.baseUrl + '/order/getallorderbyID',
      data: {
        customer_ID: app.customerid
      },
      success: function (res) {
        var orderList4show = res.data.filter(p => p.order_status == 'Submitted');
        orderList4show.forEach(element => {
          let time = dayjsMin(element.order_time).format()
          let date = new Date(time)
          var ans = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + '\xa0\xa0\xa0\xa0' + date.getHours() + ':' + date.getMinutes()
          element.order_time = ans
        });
        that.setData({
          orderList: res.data.reverse(),
          orderList4show: orderList4show.reverse()
        })
        if (that.data.orderList4show.length != 0) {
          that.setData({
            isorderEmpty: false
          })
        } else {
          that.setData({
            isorderEmpty: true
          })
        }

        var settled = res.data.filter(p => p.order_status == 'settled');
        if (settled.length > 0) {
          let latest = settled[settled.length - 1]
          let time = dayjsMin(latest.order_time).format()
          let date = new Date(time)
          let now = new Date()
          if (!that.data.hasShowed && date.getFullYear() == now.getFullYear() && date.getMonth() == now.getMonth() && date.getDate() == now.getDate()) {
            that.setData({
              hasShowed: true
            })
            wx.showModal({
              title: '提示',
              content: '您有订单尚未评价，是否前往评价？',
              success(res) {
                if (res.confirm) {
                  wx.navigateTo({
                    url: '../usercenter/myorder',
                  })
                } else if (res.cancel) {
                  console.log('用户点击取消')
                }
              }
            })
          }
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },
  skip2home: function () {
    wx.switchTab({
      url: '../home/index'
    })
  },
  skip2orderdetail: function (e) {
    var order_ID = e.detail.order_ID
    wx.navigateTo({
      url: './orderdetail?order_ID=' + order_ID,
    })
  }
})
const app=getApp()
const dayjsMin = require("../../scripts/dayjs.min");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderList:[{}],
    orderList4show:[{
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this
    if(!app.hasUserInfo)
    return
    wx.request({
      url: app.globalData.baseUrl+'/order/getallorderbyID',
      data:{
        customer_ID:app.customerid
      },
      success:function(res){
        var orderList4show=res.data.filter(p=>p.order_ID>0);
        orderList4show.forEach(element => {
          let time=dayjsMin(element.order_time).format()
          let date=new Date(time)
          var ans = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()+ '\xa0\xa0\xa0\xa0' + date.getHours()+ ':' + date.getMinutes()
          element.order_time=ans
        });
        that.setData({
          orderList:res.data.reverse(),
          orderList4show:orderList4show.reverse()
        })
        if(that.data.orderList4show.length!=0)
          that.setData({
            isorderEmpty:false
          })
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
  onShow(){
    var that=this
    if(!app.hasUserInfo)
    return
    wx.request({
      url: app.globalData.baseUrl+'/order/getallorderbyID',
      data:{
        customer_ID:app.customerid
      },
      success:function(res){
        var orderList4show=res.data.filter(p=>p.order_ID>0);
        orderList4show.forEach(element => {
          let time=dayjsMin(element.order_time).format()
          let date=new Date(time)
          var ans = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()+ '\xa0\xa0\xa0\xa0' + date.getHours()+ ':' + date.getMinutes()
          element.order_time=ans
        });
        that.setData({
          orderList:res.data.reverse(),
          orderList4show:orderList4show.reverse()
        })
        if(that.data.orderList4show.length!=0)
          that.setData({
            isorderEmpty:false
          })
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

  skip2orderdetail:function(e){
    var order_ID=e.detail.order_ID
    wx.navigateTo({
      url: '../order/orderdetail?order_ID=' + order_ID,
    })
  },
  onClickLeft() {
    wx.navigateBack({
      delta: 1,  // 返回上一级页面。
    })
  },
})
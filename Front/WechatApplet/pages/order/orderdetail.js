// pages/order/orderdetail.js
const app=getApp();
const dayjsMin = require("../../scripts/dayjs.min");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    scrollheight:0,
    dishtypenum:0,
    dishnumList:[{}],
    dishinfolist:[{
      dishid:-1,
      imgurl:'',
      name:'',
      price:0,
      amount:0,
    }],
    orderInfo:{},
    ordertime:'00:00'
  },

  onClickLeft() {
    wx.navigateBack({
      delta: 1,  // 返回上一级页面。
  success: function() {
    }}
    )
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this
    var order_ID = parseInt(options.order_ID)
    wx.request({
      url: app.globalData.baseUrl+'/order/getorderbyorderID',
      data: {
        order_ID: order_ID
      },
      success: function (res) {
        that.setData({
          orderInfo : res.data
        })
        // console.log(res.data)
        let time=dayjsMin(res.data.order_time).format()
        let date=new Date(time)
        var ans = date.getHours()+ ':' + date.getMinutes()
        that.setData({
          ordertime:ans
        })
      }
    })
    wx.request({
      url: app.globalData.baseUrl+'/order/getdishinfobyorderID',
      data:{
        order_ID:order_ID
      },
      success:function(res){
        //获取滑动窗口高度
        var radio = 0
        let screenWidth = wx.getSystemInfoSync().screenWidth;
        var radio = 750 / screenWidth
        let screenHeight = wx.getSystemInfoSync().screenHeight;
        let windowHeight = wx.getSystemInfoSync().windowHeight;
        let statusHeight = wx.getSystemInfoSync().statusBarHeight;
        let bottomY = wx.getSystemInfoSync().safeArea.bottom;
        const safeAreabottom = screenHeight - bottomY;
        that.setData({
          dishnumList:res.data,
          dishtypenum:res.data.length,
          scrollheight: (windowHeight - statusHeight - safeAreabottom - 50) * radio-300-100,
        })

        
        //获取订单中的菜品和数量信息并汇总到dishinfolist中
        var idList=[]
        var numList=[]
        res.data.forEach(element => {
          idList.push(element.dish_ID)
          numList.push(element.dish_number)
        });
        wx.request({
          url: app.globalData.baseUrl+'/dish/getdishbyIDList',
          method: 'POST',
          header: {
            'content-type': 'application/json'
            },
          data:{
            idList:idList
          },
          success : function(res){
            var dishinfolist=[]
            for(var i=0;i<res.data.length;i++){
              var obj={
                dishid:res.data[i].dish_ID,
                imgurl:res.data[i].picture_address,
                name:res.data[i].dish_name,
                price:res.data[i].dish_nuitprice,
                amount:numList[i],
              }
              dishinfolist.push(obj)
            }
            that.setData({
              dishinfolist:dishinfolist
            })
          }
        })
      }
    })
  },
  skip2comment:function(){
    var order_ID=this.data.orderInfo.order_ID
    var order_status=this.data.orderInfo.order_status
    wx.navigateTo({
      url: './comment?order_ID=' + order_ID+'&order_status='+order_status,
    })
  },
  skip2home:function(){
    wx.switchTab({
      url: '../home/index',
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})
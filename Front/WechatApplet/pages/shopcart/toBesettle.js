// pages/shopcart.js
const app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    iscartEmpty:true,
    tobeSettledishesList: [{
      dish_ID:1,
      dish_description:"",
      dish_name:"",
      dish_nuitprice:0.0,
      dish_quantity:"",
      dish_rating:"",
      monthly_sales:"",
      picture_address:""
    },],
    dishamount:[-1,],
    idList:[],
    numList:[],
    totalamount:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this
    var dish_IDList=[]
    var dish_numlist=[]
    // console.log(app.globalData.dishesList)
    // console.log(app.globalData.orderrecord)
    app.globalData.orderrecord.forEach(element => {
      // console.log(element)
      if(element.index!=-1){
      dish_IDList.push(app.globalData.dishesList[element.index+1].dish_ID)
      dish_numlist.push(element.amount)
    }
    });
    // console.log(dish_IDList)
    wx.request({
      url: app.globalData.baseUrl+'/dish/getdishbyIDList',
      method: 'POST',
      header: {
        'content-type': 'application/json'
        // 'content-type': 'application/x-www-form-urlencoded'
        },
      data:{
        // dishIDList:JSON.stringify(dish_IDList)
        // dishIDList:dish_IDList
        idList:dish_IDList
      },
      success : function(res){
        that.setData({
          tobeSettledishesList:res.data
        })
        // console.log(that.data.tobeSettledishesList)
      }
    })
    var dishamount=this.data.dishamount
    app.globalData.orderrecord.forEach(element => {
      if(element.index!=-1)
        dishamount[app.globalData.dishesList[element.index+1].dish_ID]=element.amount
    });
    this.setData({
      idList:dish_IDList,
      numList:dish_numlist,
      dishamount:dishamount,
      totalamount:app.globalData.totalamount
    })
  },
  onSubmit:function(e){
    if(app.tableid==-1){
      wx.showModal({
        showCancel:false,
        title: '提  示',
        content: '请先扫描获取桌码',
        success (res) {
          if (res.confirm) {
            wx.scanCode({
              onlyFromCamera:true,
              success:function(res){
                if(!isNaN(parseInt(res.result))&&parseInt(res.result)>0){
                  app.tableid=parseInt(res.result)
                  // console.log(app.tableid)
                  wx.showToast({
                    title: '成功获取桌码',
                  })
              }
              else{
                wx.showToast({
                  title: '未获取到桌码',
                  icon:'error',
                })
              }
              }
            })
          }
        }
      })
    return
  }
    wx.request({
      url: app.globalData.baseUrl+'/order/submitorder',
      method: 'POST',
      header: {
        'content-type': 'application/json'
        // 'content-type': 'application/x-www-form-urlencoded'
        },
      data:{
        table_ID:app.tableid,
        customer_ID:app.customerid,
        dishAmout:this.data.totalamount,
        idList:this.data.idList,
        numList:this.data.numList,
      },
      success : function(res){
        app.globalData.orderrecord=[{
          index:-1,
          amount:0
        },],
        app.globalData.iscartEmpty=true,
        app.globalData.totalamount=0
        // console.log(res)
        wx.switchTab({
          url: '../home/index',
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

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  onClickLeft() {
    wx.navigateBack({
      delta: 1,  // 返回上一级页面。
  success: function() {
    }}
    )
  },
})
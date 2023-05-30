// pages/order/comment.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    order_ID: 0,
    order_status: '',
    comment: '',
    scrollheight: 0,
    dishtypenum: 0,
    idlist: [],
    ratinglist: [],
    dishinfolist: [{
      dishid: -1,
      imgurl: '',
      name: '',
      rating: 0,
    }],
    orderInfo: {},
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this
    var order_ID = parseInt(options.order_ID)
    var order_status = options.order_status
    // console.log(order_ID + ' ' + order_status)
    if (order_status == 'commented') {
      wx.request({
        url: app.globalData.baseUrl + '/comment/getcommentbyID',
        data: {
          order_ID: order_ID
        },
        success: function (res) {
          that.setData({
            comment: res.data,
            order_status: order_status
          })
          // console.log(res.data)
        }
      })

      return
    }
    this.setData({
      order_ID: order_ID,
      order_status: order_status,
    })
    wx.request({
      url: app.globalData.baseUrl + '/order/getdishinfobyorderID',
      data: {
        order_ID: order_ID
      },
      success: function (res) {
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
          dishtypenum: res.data.length,
          scrollheight: (windowHeight - statusHeight - safeAreabottom - 50) * radio - 550,
        })


        //获取订单中的菜品和数量信息并汇总到dishinfolist中
        var idList = []
        var ratingList=[]
        res.data.forEach(element => {
          idList.push(element.dish_ID)
          ratingList.push(5.0)
        });
        that.setData({
          idlist:idList,
          ratinglist:ratingList
        })
        console.log(that.data.idList+' '+that.data.ratingList)
        wx.request({
          url: app.globalData.baseUrl + '/dish/getdishbyIDList',
          method: 'POST',
          header: {
            'content-type': 'application/json'
          },
          data: {
            idList: idList
          },
          success: function (res) {
            var dishinfolist = []
            for (var i = 0; i < res.data.length; i++) {
              var obj = {
                dishid: res.data[i].dish_ID,
                imgurl: res.data[i].picture_address,
                name: res.data[i].dish_name,
                rating: res.data[i].dish_rating,
              }
              dishinfolist.push(obj)
            }
            that.setData({
              dishinfolist: dishinfolist
            })
            // console.log(that.data.dishinfolist)
          }
        })
      }
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
  rating(e) {
    //  console.log(e)
    // console.log(this.data.idlist+' ' +this.data.ratinglist)

    var dish_ID = e.currentTarget.dataset.id
    var idlist = this.data.idlist
    var ratinglist = this.data.ratinglist
    let index = idlist.indexOf(dish_ID)
    if (index != -1) {
      ratinglist[index] = e.detail
    } else {
      idlist.push(dish_ID)
      ratinglist.push(e.detail)
    }
    this.setData({
      idlist: idlist,
      ratinglist: ratinglist,
    })
    // console.log(this.data.idlist+' ' +this.data.ratinglist)
  },
  uploadimg(e) {
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
      }
    })
  },
  formSubmit(e) {
    var that = this
    // console.log('form发生了submit事件，携带数据为：', e.detail.value.input)
    // console.log(that.data.order_ID)
    wx.request({
      url: app.globalData.baseUrl + '/comment/addcomment',
      data: {
        order_ID: that.data.order_ID,
        comment: e.detail.value.input
      }
    })
    console.log(app.customerid)
    console.log(that.data.order_ID)
    console.log(that.data.idlist)
    console.log(that.data.ratinglist)
    wx.request({
      url: app.globalData.baseUrl + '/comment/addRatings',
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      data: {
        customer_ID: app.customerid,
        order_ID: that.data.order_ID,
        dish_ID: that.data.idlist,
        rating: that.data.ratinglist
      },
      success: function (res) {
        // console.log(res.data)
        wx.showToast({
          title: '感谢您的评价',
          duration: 2000,
          success: function () {
            setTimeout(() => {
              wx.switchTab({
                url: '/pages/home/index',
              })
            }, 1200)
          }
        })
      }
    })
  },
  onClickLeft: function () {
    wx.navigateBack();
  }
})
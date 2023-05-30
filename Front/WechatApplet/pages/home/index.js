// index.js
const app = getApp()
Page({
  data: {
    selectId: 'a',
    scrollheight: 0,
    heightArr: [],
    cardheight: 0,
    typeheight: 24,
    iscartEmpty: true,
    cartbuttonShow: true,
    actionsheetShow: false,
    totalamount: 0,
    amountvisable: [false, ],
    dishamount: [0, ],
    allDishOfTypeList: [{
      the_type: '',
      dishList: [{
        dish_ID: 1,
        dish_description: "",
        dish_name: "",
        dish_nuitprice: 0.0,
        dish_quantity: "",
        dish_rating: "",
        monthly_sales: "",
        picture_address: ""
      }]
    }],
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
    activeKey: 0,
    autoplay: true,
    /*自动切换图片开关*/
    interval: 2000,
    /*切换时间属性1000=1秒*/
  },
  onShow() {
    const taBar = this.getTabBar()
    taBar.setData({
      active: 0
    })
    // console.log(app.globalData.orderrecord)
    if (app.globalData.iscartEmpty) {
      this.setData({
        iscartEmpty: true,
        cartbuttonShow: true,
        actionsheetShow: false,
        totalamount: 0,
        amountvisable: [false, ],
        dishamount: [0, ],
      })
    }
    var that = this
    wx.request({
      url: app.globalData.baseUrl + '/dish/getdishoftype',
      success: function (res) {
        // console.log(res)
        let datalen = res.data.length
        that.setData({
          allDishOfTypeList: res.data
        })
        // console.log(that.data.allDishOfTypeList)
        var heightArr = []
        for (var i = 0; i < datalen; i++) {
          let dishlen = res.data[i].dishList.length
          heightArr[i] = that.data.typeheight + that.data.cardheight * dishlen
        }
        // console.log(heightArr)
        that.setData({
          heightArr: heightArr
        })
      }
    })

    wx.request({
      url: app.globalData.baseUrl + '/dish/getalldish',
      success: function (res) {
        // console.log(res)
        that.setData({
          dishesList: res.data.slice(1)
        })
        app.globalData.dishesList = res.data
        // console.log(that.data.dishesList)
      }
    })
  },
  onLoad: function (options) {
    var that = this
    wx.request({
      url: app.globalData.baseUrl + '/dish/getdishoftype',
      success: function (res) {
        // console.log(res)
        that.setData({
          allDishOfTypeList: res.data
        })
        // console.log(that.data.allDishOfTypeList)
      }
    })

    wx.request({
      url: app.globalData.baseUrl + '/dish/getalldish',
      success: function (res) {
        // console.log(res)
        that.setData({
          dishesList: res.data.slice(1)
        })
        app.globalData.dishesList = res.data
        // console.log(that.data.dishesList)
      }
    })
    var radio = 0
    let screenWidth = wx.getSystemInfoSync().screenWidth;
    var radio = 750 / screenWidth
    let screenHeight = wx.getSystemInfoSync().screenHeight;
    let windowHeight = wx.getSystemInfoSync().windowHeight;
    let statusHeight = wx.getSystemInfoSync().statusBarHeight;
    let bottomY = wx.getSystemInfoSync().safeArea.bottom;
    const safeAreabottom = screenHeight - bottomY;
    // console.log(windowHeight+' '+screenHeight+' '+statusHeight+' '+safeAreabottom+' '+bottomY)
    // console.log((windowHeight - statusHeight - safeAreabottom) * radio-350)
    // console.log((windowHeight - statusHeight - safeAreabottom - 50-46) * radio-350)

    this.setData({
      scrollheight: (windowHeight - statusHeight - safeAreabottom - 50 - 46) * radio - 380,
    })
    wx.createSelectorQuery().select('.van-card').boundingClientRect().exec(e => {
      // console.log(e[0].height)
      this.setData({
        cardheight: e[0].height
      })
    })
  },
  //左侧侧边栏选中滑动右侧窗口
  proItemTap(e) {
    this.setData({
      selectId: 'index' + e.detail
    })
  },
  handlescroll(e) {
    if (this.data.heightArr.length == 0) {
      return
    }
    let scrollTop = e.detail.scrollTop
    let heightArr = this.data.heightArr
    // console.log(scrollTop)
    for (var i = 0; i < heightArr.length; i++) {
      if (scrollTop < heightArr[i]) {
        this.setData({
          activeKey: i
        })
        return
      } else {
        scrollTop -= heightArr[i]
      }
    }


  },
  // 计算滚动的区间
  calculateHeight(height) {
    if (!this.data.heightArr.length) {
      this.data.heightArr.push(height)
    } else {
      this.data.heightArr.forEach(ele => {
        height += ele
      })
      this.data.heightArr.push(height);
    }
  },


  //在菜单添加菜品
  addDishnum: function (e) {
    //添加菜品，购物车不空
    app.globalData.iscartEmpty = false
    var id = e.currentTarget.dataset.id
    //这里其实有些问题，index需要的是菜品列表中的索引
    //但其实id是dish_ID这个属性，恰巧是索引加一
    // var index=e.currentTarget.dataset.id-1
    //上述方法弃用，改用findIndex找到具体的索引
    var index = this.data.dishesList.findIndex(p => p.dish_ID == id)
    //索引并没有用到，使用ID值当作索引可以直接操作数组

    var visable = this.data.amountvisable
    var amount = this.data.dishamount
    var totalamount = this.data.totalamount
    visable[id] = true
    if (amount[id] === undefined)
      amount[id] = 1
    else
      amount[id]++
    totalamount += this.data.dishesList[index].dish_nuitprice
    if (amount[id] >= 100) {
      wx.showToast({
        title: "请适量点餐",
        icon: "error"
      })
      return
    }
    this.setData({
      amountvisable: visable,
      dishamount: amount,
      totalamount: totalamount,
      iscartEmpty: false
    })
    const result = app.globalData.orderrecord.find(p => p.index === index);
    if (result === undefined)
      app.globalData.orderrecord.push({
        index: index,
        amount: 1
      })
    else
      result.amount++
    // console.log(app.globalData.orderrecord)
  },

  //在菜单移除菜品
  subDishnum: function (e) {
    var id = e.currentTarget.dataset.id
    var index = this.data.dishesList.findIndex(p => p.dish_ID == id)
    var visable = this.data.amountvisable
    var amount = this.data.dishamount
    var totalamount = this.data.totalamount
    amount[id]--
    totalamount -= this.data.dishesList[index].dish_nuitprice
    if (amount[id] <= 0) {
      visable[id] = false
      amount[id] = 0
    }
    this.setData({
      amountvisable: visable,
      dishamount: amount,
      totalamount: totalamount
    })
    const result = app.globalData.orderrecord.find(p => p.index === index);
    result.amount--
    if (result.amount == 0) {
      app.globalData.orderrecord.splice(app.globalData.orderrecord.findIndex(p => p.index === index), 1);
      if (app.globalData.orderrecord.length == 1) {
        app.globalData.iscartEmpty = true
        this.setData({
          iscartEmpty: true
        })
      }
    }
    // console.log(app.globalData.orderrecord)
  },

  //在购物车中添加菜品
  addinCart: function (e) {
    app.globalData.iscartEmpty = false
    var id = e.detail.dishid
    var index = e.detail.dishindex
    var amount = this.data.dishamount
    var visable = this.data.amountvisable
    var totalamount = this.data.totalamount
    visable[id] = true
    if (amount[id] === undefined)
      amount[id] = 1
    else
      amount[id]++
    totalamount += this.data.dishesList[index].dish_nuitprice
    if (amount[id] >= 100) {
      wx.showToast({
        title: "请适量点餐",
        icon: "error"
      })
      return
    }
    this.setData({
      amountvisable: visable,
      dishamount: amount,
      totalamount: totalamount,
      iscartEmpty: false
    })
    const result = app.globalData.orderrecord.find(p => p.index === index);
    if (result === undefined)
      app.globalData.orderrecord.push({
        index: index,
        amount: 1
      })
    else
      result.amount++
    // console.log(app.globalData.orderrecord)
  },
  subinCart: function (e) {
    var id = e.detail.dishid
    var index = e.detail.dishindex
    var amount = this.data.dishamount
    var visable = this.data.amountvisable
    var totalamount = this.data.totalamount
    if (amount[id] == 0) {
      wx.showToast({
        title: "不能再少啦",
        icon: "error"
      })
      return
    }
    amount[id]--
    totalamount -= this.data.dishesList[index].dish_nuitprice
    if (amount[id] <= 0) {
      visable[id] = false
      amount[id] = 0
    }
    this.setData({
      amountvisable: visable,
      dishamount: amount,
      totalamount: totalamount
    })
    const result = app.globalData.orderrecord.find(p => p.index === index);
    result.amount--
    if (result.amount == 0) {
      app.globalData.orderrecord.splice(app.globalData.orderrecord.findIndex(p => p.index === index), 1);
      if (app.globalData.orderrecord.length == 1) {
        app.globalData.iscartEmpty = true
        this.setData({
          iscartEmpty: true
        })
      }
    }
    // console.log(app.globalData.orderrecord)
  },

  showtheactionsheet: function (e) {
    this.setData({
      orderrecord: app.globalData.orderrecord,
      actionsheetShow: true,
      cartbuttonShow: false
    })
  },
  actionsheetClose: function (e) {
    this.setData({
      actionsheetShow: false,
      cartbuttonShow: true
    })
  },
  skip2detail: function (e) {
    var index = parseInt(e.currentTarget.id) - 1
    var dishID = this.data.dishesList[index].dish_ID
    // console.log(dishID)
    wx.navigateTo({
      url: './dishdetail?dish_ID=' + dishID
    })
  },
  skip2settle: function (e) {
    app.globalData.totalamount = this.data.totalamount
    if (!app.hasUserInfo) {
      wx.showModal({
        title: '提示',
        content: '请先登录',
        success(res) {
          if (res.confirm) {
            wx.switchTab({
              url: '../usercenter/user',
            })
          } else if (res.cancel) {
            return
          }
        }
      })
    } else {
      wx.navigateTo({
        url: '../shopcart/toBesettle'
      })
    }

  }
})
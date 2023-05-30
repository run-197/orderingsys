// static/component/orderinfo.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    order_ID:{
      type:Number,
      value:-1
    },
    orderstatus:{
      type:String,
      value:'settled'
    },
    ordertime:{
      type:String,
      value:'2000-01-01'
    },
    table_ID:{
      type:Number,
      value:0
    },
    item_quantity:{
      type:Number,
      value:0
    },
    totalamount:{
      type:Number,
      value:0
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    skip2detail: function(){
      var EventDetail = {
        order_ID:this.data.order_ID,
      } // detail对象，提供给事件监听函数
      var EventOption = {} // 触发事件的选项
      this.triggerEvent('skip2detail', EventDetail, EventOption)
    }
  }
})

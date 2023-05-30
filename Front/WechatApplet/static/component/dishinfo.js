// static/component/dishinfo.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    image_url:{
      type:String,
      value:''
    },
    dishid:{
      type:Number,
      value:0
    },
    dishname:{
      type:String,
      value:''
    },
    dishnuitprice:{
      type:Number,
      value:0.0
    },
    dishindex:{
      type:Number,
      value:0
    },
    dishnum:{
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
    subinCart: function(){
      var myEventDetail = {
        dishid:this.data.dishid,
        dishindex:this.data.dishindex,
        dishnum:this.data.dishnum
      } // detail对象，提供给事件监听函数
      var myEventOption = {} // 触发事件的选项
      this.triggerEvent('subinCart', myEventDetail, myEventOption)
    },
    addinCart: function(){
      var myEventDetail = {
        dishid:this.data.dishid,
        dishindex:this.data.dishindex,
        dishnum:this.data.dishnum
      } // detail对象，提供给事件监听函数
      var myEventOption = {} // 触发事件的选项
      this.triggerEvent('addinCart', myEventDetail, myEventOption)
    }
  }
})

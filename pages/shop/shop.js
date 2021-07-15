// pages/shop/shop.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    huachecked:false,
    longchecked:false,
    huastock:0,
    shalongstock:0,
    huacount:0,
    shalongcount:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    var app= getApp();

    //这里可以把stock改为数组传过去，待改进
    wx.request({
      url:'http://localhost:8083/ssm/stock',
      //把带有sessionId的模拟Cookie放入请求头，
      header:app.globalData.header,
      dataType:'json',
      data:{
        name:'hua'
      },
      success:function(res){
        console.log("ok")
        if (res!=null) {
          that.setData({
            huastock:res.data.result
          })
        } else {
          console.log("没找到")
        }
      }
    })

    wx.request({
      url:'http://localhost:8083/ssm/stock',
      //把带有sessionId的模拟Cookie放入请求头，
      header:app.globalData.header,
      dataType:'json',
      data:{
        name:'shalong'
      },
      success:function(res){
        console.log("ok")
        if (res!=null) {
          that.setData({
            shalongstock:res.data.result
          })
        } else {
          console.log("没找到")
        }
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
  onShow: function () {

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

  

  huachange:function () { 
    this.setData({
      'huachecked':!this.data.huachecked
    })
  },

  longchange:function () { 
    this.setData({
      'longchecked':!this.data.longchecked
    })
  },
  
  huabuy:function (e) {
    //手机端input.type设为number后，点input会弹出数字键盘，不用parseint
    var value=parseInt(e.detail.value)
    if (value<0) {
      
    }else
    if (value>this.data.huastock) {
      this.setData({
        huacount:this.data.huastock
      })
    }else{
      this.setData({
        huacount:value
      })
    }
  },

  shalongbuy:function (e) {
    var value=parseInt(e.detail.value)
    if (value<0) {
      
    }else
    if (value>this.data.shalongstock) {
      this.setData({
        shalongcount:this.data.shalongstock
      })
    }else{
      this.setData({
        shalongcount:value
      })
    }
  },

  //向后台传数据【勾选的每件商品的名字和数量】，这里还是用数组吧
  buy:function(){
    var hua=1
    var shalong=1
    if (this.data.huachecked==false) {
      hua=0
    }
    if (this.data.shalongchecked==false) {
      shalong=0
    }
    var he=this.data.huacount*hua+this.data.shalongcount*shalong
    if (he==0) {
      
    } else {
      var app=getApp()
      var goodsAll=[
        {'name':'hua','stock':this.data.huacount},
        {'name':'shalong','stock':this.data.shalongcount},
      ];
      console.log(goodsAll)
      console.log(JSON.stringify(goodsAll))
      wx.request({
        url: "http://localhost:8083/ssm/shop",
        header:app.globalData.header,
        method:'POST',
        data: {
          goodsAll:JSON.stringify(goodsAll)
          
        },
        dataType:'json',
        success:function(res){
          if (res.data.result!=0) {
            //付款界面,这里需要注册些东西，后续改进
            // wx.requestPayment({
            //   nonceStr: 'nonceStr',
            //   package: 'package',
            //   paySign: 'paySign',
            //   timeStamp: 'timeStamp',
            // })
            //判断所有勾选的商品数量总和是否大于0
            wx.showToast({
              title: '购买成功',
              duration:2000,
            })
          } else {
            wx.showToast({
              title: '更改失败',
              icon: 'none',
              duration: 3000
            })
          }
        }
      })
    }
    
  },
})
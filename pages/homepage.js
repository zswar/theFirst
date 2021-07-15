// pages/homepage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("可以尝试把user转成json装进storage")
    this.setData({
      userName:options.userName,
    })
    wx.setStorageSync({
      data: options.userName,
      key: 'userName',
      index:[],
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

  scan:function(){
    wx.navigateTo({
      url: '/pages/scan/scan',
    })
  },

  set:function(){
    wx.navigateTo({
      url: '/pages/set/set',
    })
  },
  map:function(){
    wx.navigateTo({
      url: '/pages/map/map',
    })
  },
  shop:function(){
    wx.navigateTo({
      url: '/pages/shop/choose',
    })
  },

  delMachine:function () {
    wx.navigateTo({
      url: '/pages/scan/delMachine',
    })
  }
})
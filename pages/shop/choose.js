// pages/shop/choose.js
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

  },

  customer:function(){
    wx.navigateTo({
      url: '/pages/shop/customer',
    })
  },

  newshop:function(){
    wx.navigateTo({
      url: '/pages/shop/newshop',
    })
  },
})
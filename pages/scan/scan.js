// pages/scan/scan.js
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
    wx.scanCode({
      complete: (res) => {
          console.log(res);
          if (res.result==='http://qr61.cn/oDzWR5/qRkhZkz') {
            wx.navigateTo({
              url: '/pages/scan/newmachine?index='+'0',
            })
          }
          if (res.result==='http://qr61.cn/oDzWR5/qCMApWL') {
            wx.navigateTo({
              url: '/pages/scan/newmachine?index='+'1',
            })
          }
          
          
          
      },
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

  }
})
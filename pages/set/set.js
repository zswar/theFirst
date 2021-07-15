// pages/set/set.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ruler:0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  protect:function () {
    wx.navigateTo({
      url: '/pages/set/user/protect',
    })
  },

  changepwd:function () {
    wx.navigateTo({
      url: '/pages/set/user/change',
    })
  },

  machine:function () {
    wx.navigateTo({
      url: '/pages/set/user/machine',
    })
  },

  //退出账号
  logout:function () {
    wx.showModal({
      title:'提示',
      content: '确认退出？',
      success (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          var app=getApp()
          //清除Tomcat上的session，这里把全局变量的cookie清理了好像效果一样
          wx.request({
            url: "http://localhost:8083/ssm/logout",
            header:app.globalData.header,
            method:'POST',
            dataType:'json',
            success:function(res){
              if (res.data.result===1) {
                //这里可不可以设个synchro
                wx.showToast({
                  title: '退出成功',
                  icon: 'none',
                  duration: 3000
                })
                wx.navigateTo({
                  url: '/pages/login/login',
                })
                
              } else {
                wx.showToast({
                  title: '退出失败',
                  icon: 'none',
                  duration: 3000
                })
              }
            }
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  drop:function () {
    wx.showModal({
      title:'警告！',
      content: '你的账号将永久消失，真的要执行操作吗？',
      success (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          var app=getApp()
          wx.request({
            url: "http://localhost:8083/ssm/drop",
            header:app.globalData.header,
            method:'POST',
            dataType:'json',
            success:function(res){
              if (res.data.result==1) {
                //提示信息1秒不到就执行跳转了，这里可不可以设个synchro
                wx.showToast({
                  title: '账号已注销',
                  icon: 'none',
                  duration: 3000
                })
                wx.navigateTo({
                  url: '/pages/login/login',
                })
              } else {
                wx.showToast({
                  title: '注销账号失败',
                  icon: 'none',
                  duration: 3000
                })
              }
            }
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  
  //服务条款的显示与隐藏
  ruler:function () {
    if (this.data.ruler==0) {
      this.setData({
        ruler:1
      })
    } else {
      this.setData({
        ruler:0
      })
    }
  }
})
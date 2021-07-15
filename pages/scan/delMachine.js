// pages/scan/delMachine.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    firstopa:0,
    secondopa:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var app=getApp()
    var that=this
    wx.request({
      url: "http://localhost:8083/ssm/selectMachine",
      //主要就是传个cookie
      header:app.globalData.header,
      method:'POST',
      data: {},
      dataType:'json',
      success:function(res){
        var indexs=res.data
        if (res.data!=null&&res.data!=undefined) {
          indexs.forEach(element => {
            if (element==1) {
              that.setData({
                firstopa:1
              })
            }
            if (element==2) {
              that.setData({
                secondopa:1
              })
            }
          })
          
        } else {
          wx.showToast({
            title: '您还没有绑定设备',
            icon: 'none',
            duration: 3000
          })
        }
      }
    })
  
  },

  del1:function () {
    var app=getApp()
    var that=this
    wx.request({
      url: "http://localhost:8083/ssm/deleteMachine",
      //主要就是传个cookie
      header:app.globalData.header,
      method:'POST',
      data: { index:1 },
      dataType:'json',
      success:function(res){
        if (res.data==1) {
          wx.showToast({
            title: '删除成功',
            icon: 'none',
            duration: 3000
          })
          that.setData({
            firstopa:0
          })
        } else {
          wx.showToast({
            title: '操作失败',
            icon: 'none',
            duration: 3000
          })
        }
      }
    })
  },
  del2:function () {
    var app=getApp()
    var that=this
    wx.request({
      url: "http://localhost:8083/ssm/deleteMachine",
      //主要就是传个cookie
      header:app.globalData.header,
      method:'POST',
      data: { index:2 },
      dataType:'json',
      success:function(res){
        if (res.data==1) {
          wx.showToast({
            title: '删除成功',
            icon: 'none',
            duration: 3000
          })
          that.setData({
            secondopa:0
          })
        } else {
          wx.showToast({
            title: '操作失败',
            icon: 'none',
            duration: 3000
          })
        }
      }
    })
  }

})
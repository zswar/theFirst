// pages/scan/newmachine.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    url:["/pages/img/1号.jpg","/pages/img/2号.jpg"]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    this.setData({
      index:options.index
    });
  },

  sub:function () {
    var app=getApp()
    var that=this
    var index=parseInt(this.data.index)+1
    wx.request({
      url: "http://localhost:8083/ssm/insertMachine",
      //主要就是传个cookie
      header:app.globalData.header,
      method:'POST',
      data: {index:index},
      dataType:'json',
      success:function(res){
        console.log(res.data)
        if (res.data==1) {
          wx.showToast({
            title: '添加成功',
            icon: 'none',
            duration: 3000
          })
        } else {
          wx.showToast({
            title: '该设备已添加',
            icon: 'none',
            duration: 3000
          })
        }
      }
    })
  }


})
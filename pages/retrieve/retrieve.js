// pages/retrieve/retrieve.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userName:''
  },

  

  inputChange:function (e) {
    this.setData({
      userName:e.detail.value
    })
  },

  sub:function () {
    var that=this
    var app= getApp();
    console.log(that.data.userName)
    wx.request({
      url:'http://localhost:8083/ssm/selectUser',
      header:app.globalData.header,
      dataType:'json',
      method:'POST',
      data:{
        userName:that.data.userName
      },
      
      success:function(res){
        console.log(res.data)
        if (res!="fail") {
          
          app.globalData.header.Cookie = 'JSESSIONID='+res.data.sessionId;
          wx.navigateTo({
            url: '/pages/set/user/protect',
          })
        } else {
          console.log("没找到")
        }
      }
    })
  }

})
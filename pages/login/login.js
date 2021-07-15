// pages/login/login.js
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
    //清除本地缓存（后面改用session存信息，这是历史遗留问题）
    wx.clearStorage({
      complete: (res) => {},
    })
  },
  
  userLogin: function(){
    wx.request({
      url: "http://localhost:8083/ssm/wxlogin",
      data: {userName : this.data.userName,
             password : this.data.password,
      },
      dataType:'json',
      success:function(res){
        if (res.data.message==="登录成功") {
          console.log(res.data)
          var app = getApp();
          //登录成功，把服务端返回的sessionId记录在Cookie里，后续与后台交互都需要带上cookie
          app.globalData.header.Cookie = 'JSESSIONID='+res.data.sessionId;
          wx.navigateTo({
            url: '/pages/homepage?userName='+res.data.userName,
          })
        } else {
          wx.showToast({
            title: '登录失败,请确认账号与密码是否错误',
            icon:'none',
            duration:3000,
          })
        }

      }
    })
    
    
  },

  userRegister:function(){
    wx.navigateTo({
      url: '/pages/register/register',
    })
  },

  retrieve:function(){
    wx.navigateTo({
      url: '/pages/retrieve/retrieve',
    })
  },

  inputChange: function(e){
    var prop = e.currentTarget.dataset.type;
    this.data[prop] = e.detail.value;
  },
})
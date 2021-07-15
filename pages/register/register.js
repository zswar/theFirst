// pages/register/register.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array:["你爸爸的名字是？","你妈妈的名字是？"],
    index:0
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

  bindPickerChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    })
  },

  formSubmit:function (e) {
    console.log(e)
    this.setData({
      unerror:"",
      pwderror:"",
      conerror:"",
      deperror:"",
      emerror:"",
      telerror:"",
    })
    if (!(/^[A-Za-z0-9\u4E00-\u9FA5_&*@#]{1,12}$/.test(e.detail.value.userName))) {
      this.setData({
        unerror:"用户名不合法，应由英文数字中文下划线&*@#等符号组成，长度1-12位"
      })
    } 
    else if(!(/^.{1,12}$/.test(e.detail.value.password))){
      this.setData({
        pwderror:"密码格式不正确，可以由任意字符组成，长度1-12位"
      })
    } 
    else if(!(e.detail.value.password===e.detail.value.confirmpwd)){
      this.setData({
        conerror:"两次输入的密码不一致"
      })
    } 
    else if(!(/^.{1,40}$/.test(e.detail.value.department))){
      this.setData({
        deperror:"单位格式不正确，可以由任意字符组成，长度1-40位"
      })
    } 
    else if(!(/^[A-Za-z0-9\u4E00-\u9FA5_&*@#]{6,24}$/.test(e.detail.value.email))){
      this.setData({
        emerror:"邮箱格式不合法，可以由英文数字中文下划线&*@#等符号组成，长度6-24位"
      })
    }
    else if(!(/^1[0-9]{10}$/.test(e.detail.value.tel))){
      this.setData({
        telerror:"手机号不正确"
      })
    }
    else{
      //可能会有参数泄露或数据量超过的问题，后面都用post
      wx.request({
        url:'http://localhost:8083/ssm/register',
        method:"GET",
        dataType:'json',
        data:{
          userName:e.detail.value.userName,
          password:e.detail.value.password,
          department:e.detail.value.department,
          email:e.detail.value.email,
          tel:e.detail.value.tel,
          question:e.detail.value.question,
          answer:e.detail.value.answer
        },
        success:function (res) {
          console.log(res)
          console.log(1)
          if (res.data.message==="提交成功") {
            var app = getApp();
            //注册成功，把服务端返回的sessionId记录在Cookie里，后续与后台交互都需要带上cookie
            app.globalData.header.Cookie = 'JSESSIONID='+res.data.sessionId;

            wx.showToast({
              title: '提交成功',
              icon:"success",
              duration:4000
            })
            wx.navigateTo({
              url: '/pages/login/login'
            })
          } 
          if (res.data.message==="提交失败") {
            wx.showToast({
              title: '提交失败',
              icon:"loading",
              duration:4000
            })
            
          } 
        }
      })
    } 
  }
})
// pages/set/user/change.js
/**
 * 该界面与protect界面类似，注释去看那边的吧
 */
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array:["你爸是？","你妈是？"],
    index:0,
    opa:1,
    newopa:0,
    ans:'',
    user:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    var app= getApp();
    wx.request({
      url:'http://localhost:8083/ssm/nowUser',
      //把带有sessionId的模拟Cookie放入请求头，
      header:app.globalData.header,
      dataType:'json',
      method:'POST',
      success:function(res){
        console.log("ok")
        if (res!=null) {
          that.setData({
            ['user.id']:null,
            ['user.userName']:res.data.userName,
            ['user.password']:res.data.password,
            ['user.department']:res.data.department,
            ['user.email']:res.data.email,
            ['user.tel']:res.data.tel,
            ['user.question']:res.data.question,
            ['user.answer']:res.data.answer,
          })
          console.log(res.data)
          //改值时不能用this.data.index而用this.setdata
          that.setData({
            index:res.data.question
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

  ans:function (e) {
    this.setData({
      ans:e.detail.value
    })
    console.log(e.detail.value)
  },

  ansSub:function () {
    if (this.data.ans===this.data.user.answer) {
      this.setData({
        opa:0,
        newopa:1
      })
    }else{
      console.log(this.data.ans)
      console.log(this.data.user.answer)
      wx.showToast({
        title: '密保答案不正确',
        icon: 'none',
        duration: 2000
      })
    }
  },

  submit:function (e) {
    if(!(/^.{1,12}$/.test(e.detail.value.password))){
      this.setData({
        pwderror:"密码格式不正确，可以由任意字符组成，长度1-12位"
      })
    } 
    else if(!(e.detail.value.password===e.detail.value.confirmpwd)){
      this.setData({
        conerror:"两次输入的密码不一致"
      })
    } else{
      this.data.user.password = e.detail.value.password
      var app=getApp()
      wx.request({
        url: "http://localhost:8083/ssm/update",
        header:app.globalData.header,
        method:'POST',
        data: {
          user:JSON.stringify(this.data.user)
          
        },
        dataType:'json',
        success:function(res){
          if (res.data.result===1) {
            wx.showToast({
              title: '更改成功',
              icon: 'none',
              duration: 3000
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
  }
})
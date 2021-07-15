// pages/set/user/protect.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //密保问题数组可以改，数据库存的是问题的数组下标，答案存的是string字符串
    array:["你爸是？","你妈是？"],
    index:0,
    opa:1,
    newopa:0,
    ans:'',
    answer:'',
    user:{}
  },

  /**
   * 加载时，查找当前用户
   */
  onLoad: function (options) {
    var that=this;
    var app= getApp();
    console.log(app.globalData.header)
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

  

  bindPickerChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    console.log(this.data.index)
    this.setData({
      index: e.detail.value
    })
  },

  ans:function (e) {
    this.setData({
      ans:e.detail.value
    })
    console.log(e.detail.value)
  },

  //点击提交后，根据answer判断是否更改透明度
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

  answer:function (e) {
    this.data.user.answer = e.detail.value
    
  },

  //设置密保答案
  answerSub:function () {
    if (this.data.user.answer==='') {
      wx.showToast({
        title: '密保答案不能为空',
        icon: 'false',
        duration: 3000
      })
    } else {
      //设置密保问题（问题数组的索引值）
      this.data.user.question = this.data.index
      var app = getApp()
      wx.request({
        url: "http://localhost:8083/ssm/update",
        header:app.globalData.header,
        method:'POST',
        data: {
          //json传对象感觉规范些
          user:JSON.stringify(this.data.user)
          //用id~answer可以传值，后台用user对象接
          // id:null,
          // userName:this.data.user.userName,
          // password:this.data.user.password,
          // department:this.data.user.department,
          // email:this.data.user.email,
          // tel:this.data.user.tel,
          // question:this.data.user.question,
          // answer:this.data.user.answer
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
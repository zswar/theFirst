// pages/shop/delete.js
function detail(id, name ,price , stock,img) {
  this.id=null;
  this.name=name;
  this.price=price;
  this.stock=stock;
  this.img=img;
}

function info() {
  this.details=[];
}



Page({

  /**
   * 页面的初始数据
   */
  data: {
    //商品信息
    info:{},
    //radio的checked信息
    checked:[]
  },

  /**
   * 加载页面时，从后台取出商品信息并展示，同时添加一个radio
   */
  onLoad: function (options) {
    let app=getApp();
    let info=this.data.info;
    let checked=this.data.checked
    let that=this;
    wx.request({
      url: "http://localhost:8083/ssm/mall/selectAll",
      header:app.globalData.header,
      method:'POST',
      data: {},
      dataType:'json',
      success:function(res){
        if (res!=null&&res!=undefined) {
          console.log(res.data)
          info.details=res.data
          //给每一个商品后面添加一个false的radio
          info.details.forEach(element => {
            checked.push(false)
          });
          that.setData({
            info:info,
            checked:checked
          })
          console.log(info.details)
        } else {
          wx.showToast({
            title: '加载数据失败',
            icon: 'none',
            duration: 3000
          })
        }
      }
    })
  },

  
  /**
   * 提交操作，把checked==false的商品名加到names数组传给后台
   */
  sub:function (e) {
    let checked=this.data.checked
    let info=this.data.info
    let names=[]
    let key=0;
    checked.forEach((e1,index) => {
      if (e1==true) {
        key++
        names.push(info.details[index]['name'])
        console.log(info.details[index]['name'])
      }
    });
    //至少选一个商品
    if (key==0) {
      wx.showToast({
        title:"请至少选择一件商品再执行此操作",
        icon:'none',
        duration:4000
      })
      return
    }
    let app=getApp();
    names=JSON.stringify(names);
    wx.request({
      url: "http://localhost:8083/ssm/mall/delete",
      header:app.globalData.header,
      method:'POST',
      data: {
        names:names
      },
      dataType:'其他',
      success:function(res){
        if (res.data.result!=0) {
          wx.showToast({
            title: '下架成功',
            duration:2000,
          })
          //下架成功后跳转选择界面
          wx.navigateTo({
            url: '/pages/shop/choose',
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

  change:function (e) { 
    let checked=this.data.checked
    var index=e.currentTarget.dataset.index;
    checked[index]=!checked[index]
    this.setData({
      checked:checked
    })
  },

})
// pages/shop/customer.js

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

function trolley() {
  this.details=[];
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //初始商品信息
    info:{},
    //要购买的商品信息（购物车，与info的区别就是数量）
    trolley:{},
    //每种商品的购买数量
    counts:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //执行提交后会刷新界面，这里把counts重置下，输入框归零
    let counts=this.data.counts
    counts=[]
    this.setData({
      counts:counts
    })

    let app=getApp();
    let info=this.data.info;
    let that=this;
    //初始new个购物车
    that.setData({
      trolley:new trolley(),
    })
    wx.request({
      url: "http://localhost:8083/ssm/mall/selectAll",
      //主要就是传个cookie
      header:app.globalData.header,
      method:'POST',
      data: {},
      dataType:'json',
      success:function(res){
        if (res!=null&&res!=undefined) {
          console.log(res.data)
          info.details=res.data
          // 给输入框初始化为0
          info.details.forEach(element => {
            counts.push(0)
          });
          that.setData({
            info:info,
            counts:counts
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

  inputchange:function (e) {
    //输入框输入英文汉字会被去掉
    var value=parseInt(e.detail.value)
    var index=e.currentTarget.dataset.index;
    var info=this.data.info;
    var that=this;
    // 控制输入框的值在0~总数之间，并把输入的值存到counts
    if (value<0) {
      
    }else
    if (value>info.details[index]['stock']) {
      var counts=that.data.counts;
      counts[index]=info.details[index]['stock'];
      this.setData({
        counts:counts,
      })
    }else{
      var counts=that.data.counts;
      counts[index]=value;
      this.setData({
        counts:counts,
      })
    }
  },

  sub:function () {
    let counts=this.data.counts
    let info=this.data.info
    //把info的stock替换为counts里的数据，就成了购物车
    info.details.forEach((e,index)=> {
      e.stock=counts[index]
    });
    //购物车
    this.setData({
      trolley:info
    })
    let key=0
    counts.forEach(e1 => {
      if (e1>0) {
        key++
      }
    });
    if (key==0) {
      wx.showToast({
        title:"请至少购买一件商品再执行此操作",
        icon:'none',
        duration:4000
      })
      //刷新当前界面
      let pages=getCurrentPages();
      let perpage=pages[pages.length-1];
      perpage.onLoad()
      return
    }
    //如果信息完整，才可执行提交操作，与后端交互
    let app=getApp();

    let items=JSON.stringify(this.data.trolley.details);
    console.log(items)
    let that=this;
    console.log(items)
    wx.request({
      url: "http://localhost:8083/ssm/mall/update",
      header:app.globalData.header,
      method:'POST',
      data: {
        items:items
      },
      dataType:'其他',
      success:function(res){
        if (res.data.result!=0) {
          wx.showToast({
            title: '购买成功',
            duration:2000,
          })
          //刷新当前界面
          let pages=getCurrentPages();
          let perpage=pages[pages.length-1];
          perpage.onLoad()
        } else {
          wx.showToast({
            title: '添加失败',
            icon: 'none',
            duration: 3000
          })
        }
      }
    })

  }
})
// pages/shop/newshop.js
/**
 * 这次把page里的无用函数删了，
 * 加了俩添商品时用到的构造器
 * */
function detail(id ,name ,price , stock,img) {
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
    //商品信息，info.details是对象数组
    info:{},
    //装商品图片和加号图片
    imgs:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    //初始化info
    this.setData({
      info: new info(),
    });
  },
  
  //点击添加商品后，会new一个detail进info.details，同时往imgs装一个加号
  add:function () {
    let info=this.data.info;
    info.details.push(new detail());
    let imgs=this.data.imgs;
    imgs.push("/pages/img/加号.jpg")
    this.setData({
      info:info,
      imgs:imgs
    })
  },

  //更改输入框信息
  set:function (e) {
    console.log(e.currentTarget.dataset.index)
    var index=e.currentTarget.dataset.index;
    var prop = e.currentTarget.dataset.type;
    var info=this.data.info;
    info.details[index][prop] = e.detail.value;
    console.log(info)
    this.setData({
      info:info
    })
    console.log(info)
  },

  upload:function (e) {
    
  },

  //添加图片
  setimg:function(e){
    var _this=this;
    wx.chooseImage({
      sizeType:['original','compressed'],
      sourceType: ['album', 'camera'],
      success (res) {
        // tempFilePath是数组，取出里面的字符串可以作为img标签的src属性显示图片
        //这里把数据库里img字段最大长度设255
        var tempFilePaths = res.tempFilePaths[0]
        
        var index=e.currentTarget.dataset.index;
        var prop = e.currentTarget.dataset.type;
        var info=_this.data.info;
        info.details[index][prop] = tempFilePaths;
        console.log(tempFilePaths)
        var imgs=_this.data.imgs;
        //先把加号删了，再添加图片地址
        imgs.pop()
        imgs.push(tempFilePaths)
        _this.setData({
          info:info,
          imgs:imgs
        })
        console.log(info)
        console.log(e)
      }
    });
  },

  sub:function () {
    let info=this.data.info
    console.log(info)
    if (info.details.length==0) {
      wx.showToast({
        title:"请至少添加一种商品再执行此操作",
        icon:'none',
        duration:4000
      })
      return;
    }
    info.details.forEach(e1 => {
      if (e1.name===''||e1.name===undefined||e1.price===''||e1.price===undefined||e1.stock===''||e1.stock===undefined||e1.img===''||e1.img===undefined) {
        wx.showToast({
          title:"请完整填好所有商品信息再执行此操作",
          icon:'none',
          duration:4000
        })
        return
      }
    });
    //如果信息完整，才可执行提交操作，与后端交互
    let app=getApp();
    let items=JSON.stringify(this.data.info.details);
    console.log(items)
    wx.request({
      url: "http://localhost:8083/ssm/mall/insert",
      header:app.globalData.header,
      method:'POST',
      data: {
        items:items
      },
      dataType:'其他',
      success:function(res){
        if (res.data.result==1) {
          wx.showToast({
            title: '添加成功',
            icon: 'none',
            duration: 3000
          })

        } else {
          
        }
      }
    })

  },

  delete:function(){
    wx.navigateTo({
      url: '/pages/shop/delete',
    })
  },



})
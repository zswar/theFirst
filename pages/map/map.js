// pages/map/map.js


Page({

  /**
   * 页面的初始数据
   */
  data: {
    cityName:[],
    markers: [{
      iconPath: "/pages/img/1号.jpg",
      id: 1,
      latitude: 39.956013,
      longitude: 116.841303,
      width: 30,
      height: 30,
      //alpha透明度在部分机型好像没用，隐藏不了。这里设想如果没添加该设备，就不读取该地点环境并隐藏图标
      alpha:0,
      callout:{
        content:'我是1号气泡',
        fontSize:14,
        color:'#ffffff',
        bgColor:'#000000',
        padding:8,
        borderRadius:4,
        boxShadow:'4px 8px 16px 0 rgba(0)'
      }
    },{
      iconPath: "/pages/img/2号.jpg",
      id: 2,
      latitude: 29.960458,
      longitude: 116.850053,
      width: 30,
      height: 30,
      alpha:0,
      callout:{
        content:'我是2号气泡',
        fontSize:14,
        color:'#ffffff',
        bgColor:'#000000',
        padding:8,
        borderRadius:4,
        boxShadow:'4px 8px 16px 0 rgba(0)'
      }
    }],
  },

  markertap:function (e) {
    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //用的高德地图api，这是api提供的js
    var amapFile = require('amap-wx.js');//如：..­/..­/libs/amap-wx.js
    var that = this;
    //申请的应用key
    var myAmapFun = new amapFile.AMapWX({key:'15a03addc1a6af43cd6767ab8f9c95cf'});
    var markers=that.data.markers;
    var app=getApp();

    wx.request({
      url: "http://localhost:8083/ssm/selectMachine",
      header:app.globalData.header,
      method:'POST',
      data: {},
      dataType:"json",
      success:function(res){
        if (res!=null&&res!=undefined) {
          console.log(res.data)
          var indexs=res.data
          indexs.forEach(element => {
            if (element==1) {
              //没来得及做动态遍历的
              myAmapFun.getWeather({
                type:'live',
                city:'北京',
                success: function (data) {
                  //不能直接获取data里的markers里的0索引的callout的content，转来转去
                  var json1= JSON.parse(JSON.stringify(markers[0]))
                  var json2= JSON.parse(JSON.stringify(json1['callout']))
                  console.log(json2['content'])
                  json2['content']="城市："
                    + data.city.data+"\r\n"
                    + "天气：" + data.liveData.weather + "\r\n"
                    + "温度：" + data.temperature.data + "\r\n"
                    + "湿度：" + data.humidity.data + "\r\n"
                    + "风向：" + data.winddirection.data + "\r\n"
                    + "风力：" + data.windpower.data ;
                  //把content转换完后，装回去
                  json1['callout']=json2;
                  markers[0]=json1;
                  that.setData({
                    markers:markers
                  })            
                },
                fail: function (info) {
                  //失败回调
                  console.log("fail --> "+info)
                }
              })
            }

            if (element==2) {
              myAmapFun.getWeather({
                type:'live',
                city:'彭泽',
                success: function (data) {
                  //成功回调
                  var json1= JSON.parse(JSON.stringify(markers[1]))
                  var json2= JSON.parse(JSON.stringify(json1['callout']))
                  console.log(json2['content'])
                  json2['content']="城市："
                    + data.city.data+"\r\n"
                    + "天气：" + data.liveData.weather + "\r\n"
                    + "温度：" + data.temperature.data + "\r\n"
                    + "湿度：" + data.humidity.data + "\r\n"
                    + "风向：" + data.winddirection.data + "\r\n"
                    + "风力：" + data.windpower.data ;
                  json1['callout']=json2;
                  markers[1]=json1;
                  that.setData({
                    markers:markers
                  }) 
                        
                },
                fail: function (info) {
                  //失败回调
                  console.log("fail --> "+info)
                }
              })
            }
          });
        } else {
          wx.showToast({
            title: '暂时没有添加设备',
            icon: 'none',
            duration: 3000
          })
        }
      }
    })


    // var amapFile = require('amap-wx.js');//如：..­/..­/libs/amap-wx.js
    // var that = this;
    // var myAmapFun = new amapFile.AMapWX({key:'15a03addc1a6af43cd6767ab8f9c95cf'});
    // var local=wx.getLocation({
    //   type:'gcj02'
    // });
    // console.log(local)
    // // wx.openLocation({
    // //   116.841303,39.956013
    // //   116.850053,39.960458
    // //   latitude :39.954095,
    // //   longitude :116.837146,
    // //   scale:100,
    // //   name:'燕郊经济开发区',
    // //   address:'福泽御园南区',
    // //   complete:function(){
    // //     console.log('complete',arguments);
    // //   }
    // // });
    // // myAmapFun.getRegeo({
    // //   success: function(res){
    // //     //成功回调
    // //     console.log(res[0].regeocodeData.formatted_address)
    // //   },
    // //   fail: function(info){
    // //     //失败回调
    // //     console.log(info)
    // //   }
    // // })
    // // wx.getSystemInfo({
    // //     success: function(data){
    // //       var height = data.windowHeight;
    // //       var width = data.windowWidth;
    // //       var size = width + "*" + height;
    // //       myAmapFun.getStaticmap({
    // //         zoom: 8,
    // //         size: size,
    // //         scale: 2,
    // //         markers: "mid,0xFF0000,A:116.37359,39.92437;116.47359,39.92437",
    // //         success: function(data){
    // //           that.setData({
    // //             src: data.url
    // //           })
    // //         },
    // //         fail: function(info){
    // //           wx.showModal({title:info.errMsg})
    // //         }
    // //       })

    // //     }
    // //   })

    
    
    
    
     
      
  },

  
})
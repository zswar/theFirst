package com.dmu.demo.controller;

/**
 * 初版shop。可以删了
 * */


import org.springframework.stereotype.Controller;



@Controller
public class ShopController {
	
	
	
//	@RequestMapping("/shop")
//	@ResponseBody
//	public JSONObject update(String name,Integer stock){
//		Goods goods = new Goods(name, stock);
//		Integer result = goodsService.updateGoods(goods);
//		Map<String, Integer> map = new HashMap<>();
//		map.put("result", result);
//		JSONObject jsonObject=JSONObject.fromObject(map);
//		System.out.println(jsonObject);
//		return jsonObject;
//	}
	
	//废弃
//	@RequestMapping("/shop")
//	@ResponseBody
//	public JSONObject update(String goodsAll){
//		System.out.println(goodsAll);
//		JSONArray gArray=JSONArray.fromObject(goodsAll);
//		int result = 0;
//		for (int i = 0; i < gArray.size(); i++) {
//			JSONObject json=gArray.getJSONObject(i);
//			Goods goods = (Goods)JSONObject.toBean(json, Goods.class);
//			result=result+goodsService.updateGoods(goods);
//		}
////		Goods goods = new Goods(name, stock);
////		Integer result = goodsService.updateGoods(goods);
//		Map<String, Integer> map = new HashMap<>();
//		map.put("result", result);
//		JSONObject jsonObject=JSONObject.fromObject(map);
//		System.out.println(jsonObject);
//		return jsonObject;
//	}
//	
//	
//	
//	@RequestMapping("/stock")
//	@ResponseBody
//	public JSONObject select(String name){
//		int result = goodsService.selectStock(name);
//		Map<String, Integer> map = new HashMap<>();
//		map.put("result", result);
//		JSONObject jsonObject=JSONObject.fromObject(map);
//		System.out.println(jsonObject);
//		return jsonObject;
//	}
	
	
}

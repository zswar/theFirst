package com.dmu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmu.demo.dao.GoodsDao;
import com.dmu.demo.entity.Goods;

@Service
public class GoodsService {
	
	@Autowired
	GoodsDao goodsDao;
	
	public int updateGoods(Goods goods){
		int updateByGoods = goodsDao.updateByGoods(goods);
		return updateByGoods;
	}
	
	public int selectStock(String name){
		int result=goodsDao.selectByGoodsname(name);
		return result;
	}
}

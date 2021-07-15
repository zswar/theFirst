package com.dmu.demo.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dmu.demo.entity.Goods;

public interface GoodsDao {
	
	@Update("update goods set stock=stock-#{stock}"
			+ "         where name=#{name} and stock>#{stock}")
	int updateByGoods(Goods goods);
	
	@Select("select stock from goods where name=#{name}")
	int selectByGoodsname(String name);
	
}

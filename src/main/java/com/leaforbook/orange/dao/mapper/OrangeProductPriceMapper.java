package com.leaforbook.orange.dao.mapper;

import com.leaforbook.orange.dao.model.OrangeProductPrice;
import com.leaforbook.orange.dao.model.OrangeProductPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrangeProductPriceMapper {
    long countByExample(OrangeProductPriceExample example);

    int deleteByExample(OrangeProductPriceExample example);

    int deleteByPrimaryKey(String priceId);

    int insert(OrangeProductPrice record);

    int insertSelective(OrangeProductPrice record);

    List<OrangeProductPrice> selectByExample(OrangeProductPriceExample example);

    OrangeProductPrice selectByPrimaryKey(String priceId);

    int updateByExampleSelective(@Param("record") OrangeProductPrice record, @Param("example") OrangeProductPriceExample example);

    int updateByExample(@Param("record") OrangeProductPrice record, @Param("example") OrangeProductPriceExample example);

    int updateByPrimaryKeySelective(OrangeProductPrice record);

    int updateByPrimaryKey(OrangeProductPrice record);
}
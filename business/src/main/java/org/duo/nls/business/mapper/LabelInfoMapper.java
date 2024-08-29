package org.duo.nls.business.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.duo.nls.business.domain.LabelInfo;
import org.duo.nls.business.domain.LabelInfoExample;

public interface LabelInfoMapper {
    long countByExample(LabelInfoExample example);

    int deleteByExample(LabelInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LabelInfo record);

    int insertSelective(LabelInfo record);

    List<LabelInfo> selectByExample(LabelInfoExample example);

    LabelInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LabelInfo record, @Param("example") LabelInfoExample example);

    int updateByExample(@Param("record") LabelInfo record, @Param("example") LabelInfoExample example);

    int updateByPrimaryKeySelective(LabelInfo record);

    int updateByPrimaryKey(LabelInfo record);
}
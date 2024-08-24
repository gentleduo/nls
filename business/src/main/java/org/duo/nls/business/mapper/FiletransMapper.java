package org.duo.nls.business.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.duo.nls.business.domain.Filetrans;
import org.duo.nls.business.domain.FiletransExample;

public interface FiletransMapper {
    long countByExample(FiletransExample example);

    int deleteByExample(FiletransExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Filetrans record);

    int insertSelective(Filetrans record);

    List<Filetrans> selectByExample(FiletransExample example);

    Filetrans selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Filetrans record, @Param("example") FiletransExample example);

    int updateByExample(@Param("record") Filetrans record, @Param("example") FiletransExample example);

    int updateByPrimaryKeySelective(Filetrans record);

    int updateByPrimaryKey(Filetrans record);
}
package org.duo.nls.business.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.duo.nls.business.domain.FiletransSubtitle;
import org.duo.nls.business.domain.FiletransSubtitleExample;

public interface FiletransSubtitleMapper {
    long countByExample(FiletransSubtitleExample example);

    int deleteByExample(FiletransSubtitleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FiletransSubtitle record);

    int insertSelective(FiletransSubtitle record);

    List<FiletransSubtitle> selectByExample(FiletransSubtitleExample example);

    FiletransSubtitle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FiletransSubtitle record, @Param("example") FiletransSubtitleExample example);

    int updateByExample(@Param("record") FiletransSubtitle record, @Param("example") FiletransSubtitleExample example);

    int updateByPrimaryKeySelective(FiletransSubtitle record);

    int updateByPrimaryKey(FiletransSubtitle record);
}
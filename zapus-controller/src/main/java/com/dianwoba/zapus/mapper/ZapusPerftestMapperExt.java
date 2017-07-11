package com.dianwoba.zapus.mapper;

import com.dianwoba.zapus.model.ZapusPerftest;
import com.dianwoba.zapus.model.ZapusPerftestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhuyuwei
 * @date 2017/7/11
 * @description:
 */
public interface ZapusPerftestMapperExt extends ZapusPerftestMapper {

    /**
     * 分页查询
     * @param example
     * @param offset
     * @param limit
     * @return
     */
    List<ZapusPerftest> selectByExampleWithPaging(@Param("example") ZapusPerftestExample example,
        @Param("offset") int offset, @Param("limit") int limit);
}

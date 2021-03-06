package com.dianwoba.zapus.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.dianwoba.zapus.model.ZapusPerfTestTagExample;
import com.dianwoba.zapus.model.ZapusPerfTestTag;

public interface ZapusPerfTestTagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_perf_test_tag
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    long countByExample(ZapusPerfTestTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_perf_test_tag
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int deleteByExample(ZapusPerfTestTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_perf_test_tag
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int deleteByPrimaryKey(ZapusPerfTestTag key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_perf_test_tag
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int insert(ZapusPerfTestTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_perf_test_tag
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int insertSelective(ZapusPerfTestTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_perf_test_tag
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    List<ZapusPerfTestTag> selectByExample(ZapusPerfTestTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_perf_test_tag
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int updateByExampleSelective(@Param("record") ZapusPerfTestTag record,
        @Param("example") ZapusPerfTestTagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_perf_test_tag
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int updateByExample(@Param("record") ZapusPerfTestTag record,
        @Param("example") ZapusPerfTestTagExample example);
}
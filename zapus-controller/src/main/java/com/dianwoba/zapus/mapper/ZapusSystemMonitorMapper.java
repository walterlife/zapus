package com.dianwoba.zapus.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.dianwoba.zapus.model.ZapusSystemMonitor;
import com.dianwoba.zapus.model.ZapusSystemMonitorExample;

public interface ZapusSystemMonitorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    long countByExample(ZapusSystemMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int deleteByExample(ZapusSystemMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int insert(ZapusSystemMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int insertSelective(ZapusSystemMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    List<ZapusSystemMonitor> selectByExample(ZapusSystemMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    ZapusSystemMonitor selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int updateByExampleSelective(@Param("record") ZapusSystemMonitor record,
        @Param("example") ZapusSystemMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int updateByExample(@Param("record") ZapusSystemMonitor record,
        @Param("example") ZapusSystemMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int updateByPrimaryKeySelective(ZapusSystemMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zapus_system_monitor
     *
     * @mbg.generated Mon Jul 10 17:22:31 CST 2017
     */
    int updateByPrimaryKey(ZapusSystemMonitor record);
}
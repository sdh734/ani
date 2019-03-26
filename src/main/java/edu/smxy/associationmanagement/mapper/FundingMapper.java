package edu.smxy.associationmanagement.mapper;

import edu.smxy.associationmanagement.domain.Funding;
import edu.smxy.associationmanagement.domain.chartjs.ChartRequest;

import java.util.List;

public interface FundingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Funding record);

    int insertSelective(Funding record);

    Funding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Funding record);

    int updateByPrimaryKey(Funding record);

    List<Funding> getAllFundingByAssid(int assid);

    List<Funding> getCountByAssidInTime(ChartRequest record);

    List<Funding> getCostCountByAssidInTime(ChartRequest record);
}

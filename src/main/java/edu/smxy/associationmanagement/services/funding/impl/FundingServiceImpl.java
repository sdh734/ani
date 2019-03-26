package edu.smxy.associationmanagement.services.funding.impl;

import edu.smxy.associationmanagement.domain.Funding;
import edu.smxy.associationmanagement.domain.chartjs.ChartRequest;
import edu.smxy.associationmanagement.mapper.FundingMapper;
import edu.smxy.associationmanagement.services.funding.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: 会费管理Service实现类
 * @author: SDH
 * @create: 2019-03-23 15:13
 **/
@Service("FundingService")
public class FundingServiceImpl implements FundingService {
    @Autowired
    FundingMapper fundingMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return fundingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Funding record) {
        return fundingMapper.insert(record);
    }

    @Override
    public int insertSelective(Funding record) {
        return fundingMapper.insertSelective(record);
    }

    @Override
    public Funding selectByPrimaryKey(Integer id) {
        return fundingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Funding record) {
        return fundingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Funding record) {
        return fundingMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Funding> getAllFundingByAssid(int assid) {
        return fundingMapper.getAllFundingByAssid(assid);
    }

    @Override
    public List<Funding> getCountByAssidInTime(ChartRequest record) {
        return fundingMapper.getCountByAssidInTime(record);
    }

    @Override
    public List<Funding> getCostCountByAssidInTime(ChartRequest record) {
        return fundingMapper.getCostCountByAssidInTime(record);
    }
}

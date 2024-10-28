package com.stray.cat.service.additional.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stray.cat.dao.ReportMapper;
import com.stray.cat.pojo.Report;
import com.stray.cat.service.additional.ReportService;
import com.stray.cat.vo.IndexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ReportServiceImpl implements ReportService {

    private static final String REPORT_CACHE_NAME = "report";

    @Autowired
    ReportMapper reportMapper;


    @Override
    public int addReport(Report report) {
        return reportMapper.addReport(report);
    }

    @Override
    public int deleteReport(int reportId) {
        return reportMapper.deleteReport(reportId);
    }

    @Override
    @Cacheable(value = REPORT_CACHE_NAME, key = "'findPageReport'+#page+#limit")
    public PageInfo<Report> findPageReport(int page, int limit) {
        PageHelper.startPage(page,limit);
        List<Report> reports=reportMapper.queryAllReport();
        return new PageInfo<>(reports);

    }
}

package com.stray.cat.service.additional;

import com.github.pagehelper.PageInfo;
import com.stray.cat.pojo.Report;
import com.stray.cat.vo.IndexVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportService {

    int addReport(Report report);

    int deleteReport(int reportId);

    PageInfo<Report> findPageReport(int page, int limit);
}

package market.common.service;

import market.common.utils.SalesReportDto;

public interface ReportService {

    SalesReportDto getSalesReportDetails(String date);

}

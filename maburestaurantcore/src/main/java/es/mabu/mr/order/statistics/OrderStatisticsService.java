package es.mabu.mr.order.statistics;

import java.util.List;

public interface OrderStatisticsService {

	List<OrderChartDto> OrderChartgetLast6MonthsData();

}

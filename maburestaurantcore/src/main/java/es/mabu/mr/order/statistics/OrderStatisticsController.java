package es.mabu.mr.order.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics/order")
public class OrderStatisticsController {

	@Autowired
	OrderStatisticsService orderStatisticsService;

	@RequestMapping(method = RequestMethod.GET, value = "/month6")
	List<OrderChartDto> OrderChartgetLast6MonthsData() {

		return orderStatisticsService.OrderChartgetLast6MonthsData();
	}
}

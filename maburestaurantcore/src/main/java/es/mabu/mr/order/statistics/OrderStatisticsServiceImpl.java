package es.mabu.mr.order.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mabu.mr.order.OrderRepository;

@Service
public class OrderStatisticsServiceImpl implements OrderStatisticsService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<OrderChartDto> OrderChartgetLast6MonthsData() {
		List<Object[]> query = orderRepository.getLast6MonthsData();
		List<OrderChartDto> resultDto = query.stream()
				.map(e -> new OrderChartDto((BigInteger) e[0], (BigDecimal) e[1], (Double) e[2]))
				.collect(Collectors.toList());

		if (resultDto.size() == 6) {
			return resultDto;
		}

		List<OrderChartDto> result = new ArrayList<>(6);
		int nextMonth = ((Calendar.getInstance().get(Calendar.MONTH) + 7) % 12) + 1;
		for (int i = 0; i < resultDto.size(); i++) {
			while (resultDto.get(i).getMonth() != nextMonth) {
				result.add(new OrderChartDto(0, 0, nextMonth == 0 ? 12 : nextMonth));
				nextMonth = (nextMonth + 1) % 12;
			}
			result.add(resultDto.get(i));
			nextMonth = (nextMonth + 1) % 12;
		}
		if (result.size() < 6) {
			int lastMonth = result.get(result.size() - 1).getMonth() + 1;
			int size = result.size();
			for (int i = size; i < 6; i++) {
				result.add(new OrderChartDto(0, 0, lastMonth == 0 ? 12 : lastMonth));
				lastMonth++;
			}
		}
		return result;

	}

}

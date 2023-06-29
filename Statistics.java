/**
 * Calculates potential statistics such as order total, average cost,
 * popular order, asn prints this as a string (not yet implemented)
 */

import java.time.LocalDateTime;
import java.util.List;

public class Statistics {
    private List<CoffeeOrder> orders;
    public Statistics(List<CoffeeOrder> orders) {
        this.orders = orders;

    }
    /**
     * Calculates the total order amount within the specified time range.
     *
     * @param start the start time of the range
     * @param end   the end time of the range
     * @return the total order amount within the time range
     */
    public double getTotal(LocalDateTime start, LocalDateTime end) {
        double total = 0;
        for (CoffeeOrder order : orders) {
            LocalDateTime orderDate = order.getOrderDate();
            if (orderDate.isEqual(start) || orderDate.isEqual(end) || (orderDate.isAfter(start) && orderDate.isBefore(end))) {
                total += order.getTotal();
            }
        }
        return total;
    }
    /**
     * Calculates the average cost of all coffee orders.
     *
     * @return the average cost of all coffee orders
     */
    public double getAvgCost() {
        double totalCost = 0;
        for (CoffeeOrder order : orders) {
            totalCost += order.getTotal();
        }
        return totalCost / orders.size();

    }

    public String[] getPopularOrders(int maxRank) {
        return null;
    }
    /**
     * Prints the calculated statistics as a formatted string.
     *
     * @return a string of the calculated statistics
     */
    public String printStatistics() {
        LocalDateTime start = LocalDateTime.MIN;
        LocalDateTime end = LocalDateTime.MAX;

        double total = getTotal(start, end);
        double avgCost = getAvgCost();

        StringBuilder statistics = new StringBuilder();
        statistics.append("Statistics:\n");
        statistics.append("Total Sales: $").append(total).append("\n");
        statistics.append("Average Cost: $").append(avgCost).append("\n");

        return statistics.toString();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReportsInPricing;

/**
 *
 * @author serena
 */


import com.github.javafaker.Faker;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Faker faker = new Faker();
        Random random = new Random();

        // 1. 建立基础目录
        SupplierDirectory supplierDirectory = new SupplierDirectory();
        CustomerDirectory customerDirectory = new CustomerDirectory();
        MarketDirectory marketDirectory = new MarketDirectory();
        MasterOrderList masterOrderList = new MasterOrderList();

        // 2. 创建 Suppliers & Products
        createSuppliersAndProducts(supplierDirectory, faker, random);

        // 3. 创建 Channels
        List<Channel> channels = createChannels();

        // 4. 创建 Markets 并配置 Channels
        createMarketsAndAssignChannels(marketDirectory, channels);

        // 5. 创建 Customers，并根据规则分配 Market & Channel
        createCustomersAndAssignMarketChannel(customerDirectory, marketDirectory, faker, random);

        // 6. 为每个 Customer 创建 Orders
        createOrdersForCustomers(customerDirectory, supplierDirectory, masterOrderList, random);

        // 7. 打印报表
        System.out.println("\n===== REPORT 1: BY MARKET =====");
        printReportByMarket(marketDirectory, masterOrderList);

        System.out.println("\n===== REPORT 2: BY CHANNEL =====");
        printReportByChannel(channels, masterOrderList);
    }

    // ------------------ Data Creation ------------------

    private static void createSuppliersAndProducts(SupplierDirectory supplierDirectory,
                                                   Faker faker,
                                                   Random random) {
        Supplier s1 = supplierDirectory.addSupplier("S1", "Amazon Supplier");
        Supplier s2 = supplierDirectory.addSupplier("S2", "Apple Supplier");
        Supplier s3 = supplierDirectory.addSupplier("S3", "Costco Supplier");

        List<Supplier> suppliers = supplierDirectory.getSuppliers();

        int productId = 1;
        for (Supplier s : suppliers) {
            for (int i = 0; i < 10; i++) {
                String pid = "P" + (productId++);
                String name = faker.commerce().productName();
                double price = 20 + random.nextInt(200); // 20 ~ 220
                s.getProductCatalog().addProduct(pid, name, price, s);
            }
        }
    }

    private static List<Channel> createChannels() {
        List<Channel> channels = new ArrayList<>();
        Channel online = new Channel("C1", "Online Channel", "Online");
        Channel mobileApp = new Channel("C2", "Mobile App Channel", "MobileApp");
        Channel retail = new Channel("C3", "Retail Store Channel", "RetailStore");
        Channel premium = new Channel("C4", "Premium Service Channel", "Premium");

        channels.add(online);
        channels.add(mobileApp);
        channels.add(retail);
        channels.add(premium);

        return channels;
    }

    private static void createMarketsAndAssignChannels(MarketDirectory marketDirectory,
                                                       List<Channel> channels) {
        Channel online = channels.get(0);
        Channel mobileApp = channels.get(1);
        Channel retail = channels.get(2);
        Channel premium = channels.get(3);

        // Market A: Value Market
        Market valueMarket = marketDirectory.addMarket(
                "M1", "Value Market",
                "Young customers with lower income, price sensitive."
        );
        valueMarket.addChannel(online);
        valueMarket.addChannel(mobileApp);

        // Market B: Professional Market
        Market professionalMarket = marketDirectory.addMarket(
                "M2", "Professional Market",
                "Working professionals with mid-level income."
        );
        professionalMarket.addChannel(online); // shared
        professionalMarket.addChannel(retail);

        // Market C: Premium Market
        Market premiumMarket = marketDirectory.addMarket(
                "M3", "Premium Market",
                "High-income customers who value premium services."
        );
        premiumMarket.addChannel(retail);
        premiumMarket.addChannel(premium);
    }

    private static void createCustomersAndAssignMarketChannel(CustomerDirectory customerDirectory,
                                                              MarketDirectory marketDirectory,
                                                              Faker faker,
                                                              Random random) {
        String[] regions = {"San Jose", "San Francisco", "Los Angeles", "New York", "Seattle"};

        List<Market> markets = marketDirectory.getMarkets();
        Market valueMarket = markets.get(0);
        Market professionalMarket = markets.get(1);
        Market premiumMarket = markets.get(2);

        for (int i = 1; i <= 50; i++) {
            String id = "C" + i;
            String name = faker.name().fullName();
            int age = 18 + random.nextInt(48); // 18 ~ 65
            double income = 30000 + random.nextInt(170000); // 30k ~ 200k
            String region = regions[random.nextInt(regions.length)];

            Customer c = customerDirectory.addCustomer(id, name, age, income, region);

            // --- 根据收入 + 年龄分配 Market ---
            Market assignedMarket;
            if (income < 60000 && age < 30) {
                assignedMarket = valueMarket; // Market A
            } else if (income <= 120000 && age <= 50) {
                assignedMarket = professionalMarket; // Market B
            } else {
                assignedMarket = premiumMarket; // Market C
            }
            c.setMarket(assignedMarket);
            assignedMarket.addCustomer(c);

            // --- 根据 Market 分配 Channel ---
            assignChannelForCustomer(c, random);
        }
    }

    private static void assignChannelForCustomer(Customer customer, Random random) {
        Market m = customer.getMarket();
        List<Channel> channels = m.getChannels();
        Channel channel1 = channels.get(0);
        Channel channel2 = channels.get(1);

        int age = customer.getAge();
        double income = customer.getIncome();

        // 按我们设计的规则来
        switch (m.getName()) {
            case "Value Market":
                // 市场 A：年轻用 App，其余 Online
                if (age < 25) {
                    customer.setChannel(channel2); // MobileApp
                } else {
                    customer.setChannel(channel1); // Online
                }
                break;

            case "Professional Market":
                // 市场 B：50% Online，50% Retail
                if (random.nextBoolean()) {
                    customer.setChannel(channel1); // Online
                } else {
                    customer.setChannel(channel2); // Retail
                }
                break;

            case "Premium Market":
                // 市场 C：收入特别高的用 Premium，其余 Retail
                if (income > 150000) {
                    // 在这个 Market 中，channel2 是 PremiumServiceChannel
                    customer.setChannel(channel2);
                } else {
                    customer.setChannel(channel1); // Retail
                }
                break;

            default:
                // 兜底：就用第一个 channel
                customer.setChannel(channel1);
        }
    }

    private static void createOrdersForCustomers(CustomerDirectory customerDirectory,
                                                 SupplierDirectory supplierDirectory,
                                                 MasterOrderList masterOrderList,
                                                 Random random) {
        List<Supplier> suppliers = supplierDirectory.getSuppliers();

        int orderIdCounter = 1;

        for (Customer c : customerDirectory.getCustomers()) {
            // 每个 customer 1~5 单
            int numOrders = 1 + random.nextInt(5);

            for (int i = 0; i < numOrders; i++) {
                String orderId = "O" + (orderIdCounter++);
                Channel channel = c.getChannel();

                Order order = new Order(orderId, c, channel);

                // 每单 1~4 个 orderItems
                int numItems = 1 + random.nextInt(4);
                for (int j = 0; j < numItems; j++) {
                    Supplier s = suppliers.get(random.nextInt(suppliers.size()));
                    List<Product> products = s.getProductCatalog().getProducts();
                    Product p = products.get(random.nextInt(products.size()));

                    int quantity = 1 + random.nextInt(5);
                    // 售价在 basePrice 的 0.8 ~ 1.2 之间浮动
                    double salesPrice = p.getBasePrice() * (0.8 + random.nextDouble() * 0.4);

                    OrderItem item = new OrderItem(p, quantity, salesPrice);
                    order.addOrderItem(item);
                }

                c.addOrder(order);
                masterOrderList.addOrder(order);
            }
        }
    }

    // ------------------ Reports ------------------

    private static void printReportByMarket(MarketDirectory marketDirectory,
                                            MasterOrderList masterOrderList) {
        List<Market> markets = new ArrayList<>(marketDirectory.getMarkets());
        List<Order> orders = masterOrderList.getOrders();

        // 先计算每个 Market 的总收入
        Map<Market, Double> marketRevenueMap = new HashMap<>();
        for (Market m : markets) {
            double total = 0.0;
            for (Order o : orders) {
                if (o.getCustomer().getMarket() == m) {
                    total += o.getTotalRevenue();
                }
            }
            marketRevenueMap.put(m, total);
        }

        // 按总收入降序排序 Market
        markets.sort((m1, m2) -> Double.compare(
                marketRevenueMap.get(m2),
                marketRevenueMap.get(m1)
        ));

        // 打印
        for (Market m : markets) {
            double totalRevenue = marketRevenueMap.get(m);
            System.out.printf("\nMarket: %s | Total Revenue: $%.2f\n", m.getName(), totalRevenue);
            System.out.println("  Channels: ");

            // 对 Market 内的两个 channel 再按该市场里的收入排序
            List<Channel> channels = new ArrayList<>(m.getChannels());
            Map<Channel, ChannelStats> channelStatsMap = new HashMap<>();

            for (Channel c : channels) {
                int orderCount = 0;
                double channelRevenue = 0.0;
                for (Order o : orders) {
                    if (o.getCustomer().getMarket() == m && o.getChannel() == c) {
                        orderCount++;
                        channelRevenue += o.getTotalRevenue();
                    }
                }
                channelStatsMap.put(c, new ChannelStats(orderCount, channelRevenue));
            }

            channels.sort((c1, c2) -> Double.compare(
                    channelStatsMap.get(c2).revenue,
                    channelStatsMap.get(c1).revenue
            ));

            for (Channel c : channels) {
                ChannelStats stat = channelStatsMap.get(c);
                System.out.printf("    Channel: %-25s | Orders: %3d | Revenue: $%.2f\n",
                        c.getName(), stat.orderCount, stat.revenue);
            }
        }
    }

    private static void printReportByChannel(List<Channel> channels,
                                             MasterOrderList masterOrderList) {
        List<Order> orders = masterOrderList.getOrders();

        Map<Channel, ChannelStats> statsMap = new HashMap<>();

        for (Channel c : channels) {
            int orderCount = 0;
            double revenue = 0.0;
            for (Order o : orders) {
                if (o.getChannel() == c) {
                    orderCount++;
                    revenue += o.getTotalRevenue();
                }
            }
            statsMap.put(c, new ChannelStats(orderCount, revenue));
        }

        // 按收入降序排序 channels
        channels.sort((c1, c2) -> Double.compare(
                statsMap.get(c2).revenue,
                statsMap.get(c1).revenue
        ));

        for (Channel c : channels) {
            ChannelStats stat = statsMap.get(c);
            System.out.printf("Channel: %-25s | Orders: %3d | Revenue: $%.2f\n",
                    c.getName(), stat.orderCount, stat.revenue);
        }
    }

    // 简单的内部辅助类，用来存储统计结果
    private static class ChannelStats {
        int orderCount;
        double revenue;

        public ChannelStats(int orderCount, double revenue) {
            this.orderCount = orderCount;
            this.revenue = revenue;
        }
    }
}


Pricing Model
Author: Qinxuan Song

1. Project Overview

This project implements a complete pricing model system in Java.
It simulates a business environment that includes:

- Suppliers and their product catalogs
- Customers with demographic attributes
- Orders and order items
- Market segmentation based on meaningful rules
- Channel assignment based on customer behavior
- Revenue calculation by markets and by channels

The program automatically generates data and produces two analytical
reports:
1. Report by Market (sorted by revenue)
2. Report by Channel (sorted by revenue)


2. Market Segmentation Rule

Customers are assigned to one of three markets based on two
meaningful and realistic attributes: income level and age.

Market A: Value Market
- Income < $60,000
- Age < 30

Market B: Professional Market
- Income between $60,000 and $120,000
- Age 25–50

Market C: Premium Market
- Income > $120,000
- Age > 35



3. Customer Attributes Used

The model uses the following attributes for each customer:
- Name
- Age
- Income level
- Region
- Assigned Market
- Assigned Channel

Among these, age and income are the attributes used for the
market segmentation logic.


4. Channels Created

The project defines four channels:

1. Online Channel
2. Mobile App Channel
3. Retail Store Channel
4. Premium Service Channel

Each market is assigned exactly two channels:

Value Market:
- Online Channel
- Mobile App Channel

Professional Market:
- Online Channel
- Retail Store Channel

Premium Market:
- Retail Store Channel
- Premium Service Channel


5. Shared Channel

To satisfy the assignment requirement that at least one channel must
be used by two markets, the Online Channel is shared between:

- Value Market
- Professional Market

This reflects realistic cross-market online shopping behavior.


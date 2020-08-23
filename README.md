Program to output pricing of bicycle by accepting components list in JSON text.
Complete price and price of different components is printed individually.

Taken 2020-01-01 as start date and will not accept date before that.
Per quarter 10% compound interest is applied on the base price of 2020-01-01.

Wrong 

Component Structure provided in doc: https://docs.google.com/spreadsheets/d/1tP8UfTiRJJpVzzPlvKI1vQ07lfZCU9KM0yDA0DPuXUI/edit?usp=sharing

Sample Json text:
{
  "dateForPricing": "2021-01-01",
  "bicycle": {
    "wheel": {
      "type": "alloy",
      "sdf": "sdfs",
      "rim": "20"
    },
    "sdfsdf": {
      "type": "alloy",
      "sdf": "sdfs",
      "rim": "20"
    }
  }
}

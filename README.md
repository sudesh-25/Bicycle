Program to output pricing of bicycle by accepting components list in JSON text.
Complete price and price of different components is printed individually.

Taken 2020-01-01 as start date and will not accept date before that.
Per quarter 10% compound interest is applied on the base price of 2020-01-01.

Wrong key value pair will be printed out and default price will be taken for that component in that case.

BicycleMain.java: Main file that runs the program and takes json text file path input and prints the pricing on console.
components: this module has the code for all the components to validate whether it is a valid component and its value is correct.
pricing: this modules deals with calculation of pricing of different components.

Component Structure provided in doc: https://docs.google.com/spreadsheets/d/1tP8UfTiRJJpVzzPlvKI1vQ07lfZCU9KM0yDA0DPuXUI/edit?usp=sharing

Provide file path like: /Users/sudeshkumar/Downloads/test.txt

Sample Json text:
{
  "dateForPricing": "2021-02-01",
  "bicycle": {
    "wheel": {
      "spoke": "steelSpoke",
      "tube": "tubeless",
      "rim": "20"
    },
    "seat": {
      "seatMaterial": "fibreSeat"
    }
  }
}

Program to output pricing of bicycle by accepting components list in JSON text.
Complete price and price of different components is printed individually.

BicycleMain.java: Main file that runs the program and takes json text file path input and prints the pricing on console.
components: this module has the code for all the components to validate whether it is a valid component and its value is correct.
pricing: this modules deals with calculation of pricing of different components.
input: this module to provide some sample cases in app.

Component Structure provided in doc: https://docs.google.com/spreadsheets/d/1tP8UfTiRJJpVzzPlvKI1vQ07lfZCU9KM0yDA0DPuXUI/edit?usp=sharing

Taken 2020-01-01 as start date and will not accept date before that.
Per quarter 10% compound interest is applied on the base price of 2020-01-01.


Unit Test Cases:
All these cases are given in src/bicycle/input/ folder as test file.

Parmeters:
dateForPricing: date for which Price is required
bicycle: bicycle component configuration
Component Structure provided in doc: https://docs.google.com/spreadsheets/d/1tP8UfTiRJJpVzzPlvKI1vQ07lfZCU9KM0yDA0DPuXUI/edit?usp=sharing

test1.txt:
  Input:
    Only Date provided or empty bicycle component provided: 
    {
      "dateForPricing": "2021-02-01",
    }
  Output:
    Default component price is considered.
  
test2.txt:
  Input:
    A proper conf is provided according to the above mentioned Doc:
    {
      "dateForPricing": "2021-02-01",
      "bicycle": {
        "wheel": {
          "spoke": "steelSpoke",
          "tube": "tubeless",
          "rim": "20"
        },
        "chainAssembly": {
          "gear": "5"
        }
      }
    }
  Output:
    Calculated price is printed.
    
test3.txt:
  Input:
    Date is lesser than minimum date i.e. 2020-01-01
    {
      "dateForPricing": "2019-02-01",
      "bicycle": {
        "wheel": {
          "spoke": "steelSpoke"
        },
        "chainAssembly": {
          "gear": "5"
        }
      }
    }
  Output:
    No price provided for it.
    
test4.txt:
  Input:
    No date is provided
    {"bicycle": {
        "wheel": {
          "spoke": "steelSpoke",
          "tube": "witTube",
          "rim": "20"
        },
        "chainAssembly": {
          "gear": "5"
        }
      }
    }
  Output:
    Will take default start date to provide pricing.
    
test5.txt:
  Input:
     Wrong configuration is provided(key, value pair is out from doc)
     {
      "dateForPricing": "2021-02-01",
      "bicycle": {
        "wheel": {
          "spoke": "steelpoke",
          "tube": "abc",
          "rim": "20"
        },
        "chainAssembly": {
          "gear": "5"
        }
      }
    }
  Output:
    Will print the wrong pair and take default pricing for that component.
    
After default inputs from app finishes it asks for a file path to be provided as input to test custom cases.
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

# ""(parameterization) provides reusability i.e. with the help of ""...
#..we can use same step definaton(ex:addPlace) in another scenario also

Feature: validate place API's

@AddPlace
Scenario Outline: validate if place is successfully added using AddPlaceAPI
    Given add place payload "<name>" "<address>" "<language>"
    When user calls AddPlaceAPI using post http method
    Then user should get response with statuscode "200"
    And "status" of response should be "ok"  #status and ok are taken from expected response
    And "scope" of response should be "APP"
    And verify place_id created using AddPlaceAPI maps to "<name>" using getPlaceApi

#passing name,language and address to testdatabuild class(data driven testing)
#if we have 2 sets of data in examples for name,language and address then..
#..our scenario will also run 2 times..so we can run our test scenario 'n' number
#of times using test data in 'Examples:'

Examples:
     | name              | address                          | language          |
     | Frontline house   |  29, side layout, cohen 09       |     French-IN     |
     |     rr            |                  rtr             |  hindi            |

@DeletePlace  #tag given to scenario, if we want to run only few selected 
#scenarios from feature file then we can use 'tags'
#the scenarios whose tags are mentioned in testrunner class will only run.
#if no tags are mentioned in testrunner class then it will run all scenarios
#of mentioned feature file
Scenario: Validate if place is succesfully deleted using DeletePlaceApi
 Given  deletePlace payload
 When user calls DeletePlace Api using delete http method
 Then user should get response with statuscode 200
 And "status" of response should be "ok"
 
 #28th and 29th lines are same as 9th and 10th line of above scenario
 #so cucumber will use step defination methods of above scenario automatically and 
 #we need not to write any code for these line in stepdefination file
# WendysDoubleQuarterNoCheese

##### This project is a demonstration of test automation with Cucumber BDD, Java, Selenium, Python, OpenCV Template Matching, Page-Object Model, and Apache POI Word.

##### The automation is running against a C# MVC 5 website on my GitHub, "SafeDuncanFlight".

##### Python is doing the image recognition with OpenCV.

##### Cucumber is driving the execution of the tests.

##### An automation framework has been built using a Page-Object Model design pattern. This automation framework is imported to use in the Step Definitions.

##### Utility classes have been made to handle image recognition and to report the output of Cucumber (JSON) as a Word document.

##### Java is a good choice to build an automation framework because of the static typing, which results in strong intellisense.

# Installing OpenCV (for image recognition)
## Python Instructions
##### pip install opencv-python

## Java Instructions
###### OpenCV must be downloaded and added to the Eclipse workspace.
### Windows Installation Instructions
###### https://opencv-java-tutorials.readthedocs.io/en/latest/01-installing-opencv-for-java.html#install-opencv-3-x-under-windows  

### Download Page
###### https://opencv.org/releases/  

### Eclipse Setup Instructions
###### If the user library does not already exist, follow the instructions in this link.
###### https://opencv-java-tutorials.readthedocs.io/en/latest/01-installing-opencv-for-java.html#set-up-opencv-for-java-in-eclipse  
###### If the user library already exists, right click the project name, then "Build Path" => "Add Libraries" => "User Library" => "opencv".
  
# Installing Winium Driver (like Selenium for WPF apps)
###### https://www.youtube.com/watch?v=juRKu9cBwQ0&t=937s 

###### GitHub - Winium For Desktop
###### https://github.com/2gis/Winium.Desktop
###### GitHub - Winium Driver
###### https://github.com/2gis/Winium.Desktop/releases

# Cucumber Hooks
###### Global hooks go in the Cucumber Runner using the standard JUnit annotations, which are @BeforeClass and @AfterClass. @BeforeClass executes before all scenarios. @AfterClass executes after all scenarios.
###### General scenario hooks that are shared for all scenarios go in the 'Hooks.java' file. Control execution order with 'order' like '@Before(order=99)' and '@After(order=1)'. @Before order starts execution at 1 and counts up. @After starts execution at 999 and counts down to 1.
###### Specific scenario hooks go in the corresponding StepDef for the feature file. The corresponding feature file must have an annotation at the top if you want the hook to run for every scenario in that feature file. If the annotation at the top of the feature file is '@ExampleFeature', then the annotation to run the hooks for every scenario in that feature file would be '@Before(value="ExampleFeature")' and '@After(value="ExampleFeature"). Order can also be used at the same time like '@Before(value="ExampleFeature", order=1).

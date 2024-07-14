# WebOps framework test automation
Repository is used for the testing of the Webops applications. 
The main flows for automated testing are UI testing and API testing. 
The API tests are based on rest assured, the UI tests are based on playwright.

## Installation
For setting up repository and using it for testing on your local machine, please first follow the following guidelines.
Following should be installed.
* Maven 3.8.2
* Node 14.17.6
* JDK 11
### UI
We use playwright for UI testing, to install and are the following in your command line/terminal.
```bash
npm install playwright
```
### API
No additional installs are necessary.
### How to run
#### Parameters
##### Common
Following common parameters are required to run the tests
* environment (self-explanatory)
    * dev - int - acc -prd
* tenant (test8, test4 etc...)
##### UI
For running the UI tests following parameters are also required
* app.browser
  * firefox - webkit - chrome
* app.headless
  * true -false
* app.screenshotFolder _(optional)_
  * Set a folder to place the screenshots. 
  * _If not set, the folder 'screenshots' will be added to the UI project._


##### API
No additional parameters required to run API tests

describe('create new ograniczenie - ', function() {
  var ptor = browser.driver;
  ptor.manage().window().maximize();
  
  function waitForElementToAppear(locator) {
	ptor.wait(function() { return ptor.isElementPresent(locator); }, 5000);
  }
  
  function waitForElementToShow(locator) {
	ptor.wait(function() { 
		return ptor.findElement(locator).isDisplayed().then(function(isVisible){ return isVisible;});
	}, 10000);
  }
  
  function waitForElementToHide(locator) {
	ptor.wait(function() { 
		return ptor.findElement(locator).isDisplayed().then(function(isVisible){ return !isVisible;});
	}, 10000);
  }
  function waitForSplashToDisappear() {
	waitForElementToAppear(by.id('loadingModal'));
	waitForElementToHide(by.id('loadingModal'));
  };
  
  var logIn = function() {
    ptor.get('http://localhost:8080/uaiContacts');

    ptor.findElement(by.css('#j_username')).sendKeys('admin@gmail.com');
	ptor.findElement(by.css('#j_password')).sendKeys('admin');
	ptor.findElement(by.name('submit')).click();
  }
  
  var logOut = function() {
	ptor.get('http://localhost:8080/uaiContacts/logout');
  }

  it('nawigacja po stranie', function() {
	var selectedPage;
	logIn();
	
    selectedPage = ptor.findElement(by.css('.navbar .active p'));
    expect(selectedPage.getText()).toEqual('Informacje');
	
	ptor.get('http://localhost:8080/uaiContacts/protected/rozwiazania');
	waitForSplashToDisappear();
	selectedPage = ptor.findElement(by.css('.navbar .active p'));
    expect(selectedPage.getText()).toEqual('Rozwiązania');
	
	ptor.get('http://localhost:8080/uaiContacts/protected/zadania');
	waitForSplashToDisappear();
	selectedPage = ptor.findElement(by.css('.navbar .active p'));
    expect(selectedPage.getText()).toEqual('Zadania');
	
	logOut();
  });
  
  it('tworzenie nowego ograniczenia', function() {
	logIn();
	ptor.get('http://localhost:8080/uaiContacts/protected/zadania');
	waitForSplashToDisappear();
	
	var results = element.all(by.css('table tbody tr'));
	expect(results.count()).not.toBe(0);
	waitForSplashToDisappear();
	
	results.get(0).element(by.css('a')).click();
	
	waitForElementToShow(by.css('[ng-click="openNoweOgraniczenie();"]'));
	ptor.findElement(by.css('[ng-click="openNoweOgraniczenie();"]')).click();
	
	waitForElementToShow(by.css('[ng-click="saveOgraniczenie();"]'));
	var saveButton = ptor.findElement(by.css('[ng-click="saveOgraniczenie();"]'));
	var alert = ptor.findElement(by.css('div.alert'));
	
	saveButton.click();
	waitForSplashToDisappear();
	waitForElementToShow(by.id('addOgraniczenie'));
	expect(alert.isDisplayed()).toEqual(true);
	
	waitForElementToShow(by.css('[ng-model="ograniczenia.nowe.nazwa"]'));
	ptor.findElement(by.css('[ng-model="ograniczenia.nowe.nazwa"]')).sendKeys('nazwa');
	saveButton.click();
	waitForSplashToDisappear();
	waitForElementToShow(by.id('addOgraniczenie'));
	expect(alert.isDisplayed()).toEqual(true);
	
	waitForElementToShow(by.css('[ng-model="ograniczenia.nowe.jezyk"]'));
	ptor.findElement(by.css('[ng-model="ograniczenia.nowe.jezyk"]')).sendKeys('Java');
	saveButton.click();
	waitForSplashToDisappear();
	waitForElementToShow(by.id('addOgraniczenie'));
	expect(alert.isDisplayed()).toEqual(true);
	
	waitForElementToShow(by.css('[ng-model="ograniczenia.nowe.noweSlowoKluczowe"]'));
	ptor.findElement(by.css('[ng-model="ograniczenia.nowe.noweSlowoKluczowe"]')).sendKeys('slowoKluczowe');
	ptor.findElement(by.css('[ng-click="dodajSlowoKluczowe();"]')).click();
	saveButton.click();
	waitForSplashToDisappear();
	expect(alert.isDisplayed()).toEqual(false);
	
	logOut();
  });
});
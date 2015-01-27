function zadaniaController($scope, $http) {
    $scope.pageToGet = 0;

    $scope.state = 'busy';

    $scope.url = "/uaiContacts/protected/zadania/";

    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displaySearchMessage = false;
    $scope.displaySearchButton = false;
    $scope.displayCreateContactButton = false;

    $scope.zadanie = {}
    
    $scope.ograniczenia = {}
    $scope.ograniczenia.pageToGet = 0;

    $scope.getZadanieList = function () {
        var url = $scope.url;

        $scope.startDialogAjaxRequest();

        var config = {params: {page: $scope.pageToGet}};

        $http.get(url, config)
            .success(function (data) {
console.log(data);
                $scope.finishAjaxCallOnSuccess(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateContactButton = false;
            });
    }

    $scope.populateTable = function (data) {
        if (data.pagesCount > 0) {
            $scope.state = 'list';

            $scope.page = {source: data.zadania, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totalContacts};

            if($scope.page.pagesCount <= $scope.page.currentPage){
                $scope.pageToGet = $scope.page.pagesCount - 1;
                $scope.page.currentPage = $scope.page.pagesCount - 1;
            }

            $scope.displayCreateContactButton = true;
            $scope.displaySearchButton = true;
        } else {
            $scope.state = 'noresult';
            $scope.displayCreateContactButton = true;

        }
    }

    $scope.changePage = function (page) {
        $scope.pageToGet = page;
        $scope.getZadanieList();
    };

    $scope.exit = function (modalId) {
        $(modalId).modal('hide');

        $scope.zadanie = {};
        $scope.errorOnSubmit = false;
        $scope.errorIllegalAccess = false;
        $scope.displayValidationError = false;
    }

    $scope.finishAjaxCallOnSuccess = function (data, modalId, isPagination) {
        $scope.populateTable(data);
        $("#loadingModal").modal('hide');

        if(!isPagination){
            if(modalId){
                $scope.exit(modalId);
            }
        }
    }

    $scope.startDialogAjaxRequest = function () {
        $scope.displayValidationError = false;
        $("#loadingModal").modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    }

    $scope.handleErrorInDialogs = function (status) {
        $("#loadingModal").modal('hide');
        $scope.state = $scope.previousState;

        // illegal access
        if(status == 403){
            $scope.errorIllegalAccess = true;
            return;
        }

        $scope.errorOnSubmit = true;
    }
    
   /* $scope.loadOgraniczenia = function () {
	    $scope.lastAction = 'listOgraniczenia';
console.log($scope.url +  $scope.zadanie.id);	
	    var url = $scope.url +  $scope.zadanie.id;
	
	    $scope.startDialogAjaxRequest();
	
	    var config = {};
	
	    $http.get(url, config)
	        .success(function (data) {
console.log(data);
	            $scope.finishAjaxCallOgraniczeniaOnSuccess(data, "#editZadaniaModal");
	            $scope.displaySearchMessage = true;
	        })
	        .error(function(data, status, headers, config) {
	        	$scope.state = 'error';
	            $scope.handleErrorInDialogs(status);
	        });
    }*/
    
   /* $scope.startDialogAjaxRequest = function () {
        $("#loadingModal").modal('show');
    }*/
    
    /*$scope.finishAjaxCallOgraniczeniaOnSuccess = function (data, modalId) {
    	$scope.zadanie.ograniczenia = data.ograniczenia;
        $scope.page.ograniczenia = {source: data.zadania, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totalContacts};
    	$("#loadingModal").modal('hide');
    	$(modalId).modal('show');
console.log($scope.zadanie);
    }*/


    $scope.selectedZadanie = function (zadanie) {
        var selectedZadanie = angular.copy(zadanie);
        $scope.zadanie = selectedZadanie;
        
        $scope.zadanie.orgOgraniczenia = $scope.zadanie.ograniczenia;
        $scope.ograniczenia.pageToGet = 0;
        $scope.ograniczenia.populateTable();
    }
    
    $scope.ograniczenia.changePage = function (page) {
        $scope.ograniczenia.pageToGet = page;
        $scope.ograniczenia.populateTable();
    };
    
    $scope.ograniczenia.populateTable = function () {
        if ($scope.zadanie.ograniczenia.length > 0) {
            $scope.ograniczenia.state = 'list';
            
            $scope.ograniczenia.page = {source: $scope.zadanie.ograniczenia.slice($scope.ograniczenia.pageToGet*5, ($scope.ograniczenia.pageToGet + 1)*5), currentPage: $scope.ograniczenia.pageToGet, pagesCount: Math.ceil($scope.zadanie.ograniczenia.length/5), totalContacts : $scope.zadanie.ograniczenia.length};
            if($scope.ograniczenia.page.pagesCount <= $scope.ograniczenia.page.currentPage){
                $scope.ograniczenia.pageToGet = $scope.ograniczenia.page.pagesCount - 1;
                $scope.ograniczenia.page.currentPage = $scope.ograniczenia.page.pagesCount - 1;
            }

        } else {
            $scope.ograniczenia.state = 'noresult';
        }
    };
    
    $scope.openNoweOgraniczenie = function () {
    	$scope.ograniczenia.nowe = {};
    	$scope.exit('#editZadaniaModal');
    }
    
    $scope.saveOgraniczenie = function () {
console.log($scope.ograniczenia.nowe);
    	$scope.exit('#addOgraniczenie');
    }
    
    $scope.dodajSlowoKluczowe = function () {
    	if (!$scope.ograniczenia.nowe.slowaKluczowe) {
    		$scope.ograniczenia.nowe.slowaKluczowe = [];
    	}
    	if ((!($scope.ograniczenia.nowe.noweSlowoKluczowe == "")) && ($.inArray($scope.ograniczenia.nowe.noweSlowoKluczowe, $scope.ograniczenia.nowe.slowaKluczowe) == -1)) {
        	$scope.ograniczenia.nowe.slowaKluczowe.push($scope.ograniczenia.nowe.noweSlowoKluczowe);
        	$scope.ograniczenia.nowe.noweSlowoKluczowe = "";
    	}
    }
    
    $scope.usunSlowoKluczowe = function (slowoKluczowe) {
    	if ($scope.ograniczenia.nowe.slowaKluczowe) {
    		var index = $scope.ograniczenia.nowe.slowaKluczowe.indexOf(slowoKluczowe);
    		if (index > -1) {
    			$scope.ograniczenia.nowe.slowaKluczowe.splice(index, 1);
    		}
    	}
    }

    $scope.todo = function () {
    	alert('TODO');
    };
    



    $scope.getZadanieList();
}

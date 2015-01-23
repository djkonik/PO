function zadaniaController($scope, $http) {
    $scope.pageToGet = 0;

    $scope.state = 'busy';

    $scope.lastAction = '';

    $scope.url = "/uaiContacts/protected/zadania/";

    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displaySearchMessage = false;
    $scope.displaySearchButton = false;
    $scope.displayCreateContactButton = false;

    $scope.zadanie = {}

    $scope.searchFor = ""

    $scope.getZadanieList = function () {
        var url = $scope.url;
        $scope.lastAction = 'list';

        $scope.startDialogAjaxRequest();

        var config = {params: {page: $scope.pageToGet}};

        $http.get(url, config)
            .success(function (data) {
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

            if(!$scope.searchFor){
                $scope.displaySearchButton = false;
            }
        }

        if (data.actionMessage || data.searchMessage) {
            $scope.displayMessageToUser = $scope.lastAction != 'search';

            $scope.page.actionMessage = data.actionMessage;
            $scope.page.searchMessage = data.searchMessage;
        } else {
            $scope.displayMessageToUser = false;
        }
    }

    $scope.changePage = function (page) {
        $scope.pageToGet = page;

        if($scope.searchFor){
            $scope.searchContact($scope.searchFor, true);
        } else{
            $scope.getZadanieList();
        }
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

        $scope.lastAction = '';
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
        $scope.lastAction = '';
    }
    
    $scope.loadOgraniczenia = function () {
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
    }
    
    $scope.startDialogAjaxRequest = function () {
        $("#loadingModal").modal('show');
    }
    
    $scope.finishAjaxCallOgraniczeniaOnSuccess = function (data, modalId) {
    	$scope.zadanie.ograniczenia = data.ograniczenia;
        $scope.page.ograniczenia = {source: data.zadania, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totalContacts};
    	$("#loadingModal").modal('hide');
    	$(modalId).modal('show');
console.log($scope.zadanie);
    }


    $scope.selectedZadanie = function (zadanie) {
        var selectedZadanie = angular.copy(zadanie);
        $scope.zadanie = selectedZadanie;
        $scope.loadOgraniczenia();
    }  

    $scope.todo = function () {
    	alert('TODO');
    };
    



    $scope.getZadanieList();
}

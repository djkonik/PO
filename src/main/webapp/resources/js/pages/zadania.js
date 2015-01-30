function zadaniaController($scope, $http) {
    $scope.pageToGet = 0;

    $scope.state = 'busy';

    $scope.url = "/uaiContacts/protected/zadania/";

    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayValidationError = false;
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

            $scope.page = {source: data.zadania, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalCount : data.totalCount};

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

    $scope.selectedZadanie = function (zadanie) {
        var selectedZadanie = angular.copy(zadanie);
        $scope.zadanie = selectedZadanie;
        
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
            
            $scope.ograniczenia.page = {source: $scope.zadanie.ograniczenia.slice($scope.ograniczenia.pageToGet*5, ($scope.ograniczenia.pageToGet + 1)*5), currentPage: $scope.ograniczenia.pageToGet, pagesCount: Math.ceil($scope.zadanie.ograniczenia.length/5), totalCount : $scope.zadanie.ograniczenia.length};
            if($scope.ograniczenia.page.pagesCount <= $scope.ograniczenia.page.currentPage){
                $scope.ograniczenia.pageToGet = $scope.ograniczenia.page.pagesCount - 1;
                $scope.ograniczenia.page.currentPage = $scope.ograniczenia.page.pagesCount - 1;
            }

        } else {
            $scope.ograniczenia.state = 'noresult';
        }
    };
    
    $scope.openNoweOgraniczenie = function () {
    	$scope.ograniczenia.nowe = { slowaKluczowe: [] };
    	$('#editZadaniaModal').modal('hide');
    }
    
    $scope.saveOgraniczenie = function () {
        $scope.displayValidationError = false;
        $('#addOgraniczenie').modal('hide');
        $("#loadingModal").modal('show');

		var url = $scope.url;
		var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}, params:{slowa: "", idZadania:$scope.zadanie.id}};
		var noweOgraniczenie = {nazwa: $scope.ograniczenia.nowe.nazwa, jezyk: $scope.ograniczenia.nowe.jezyk};
		
		for (var i=0; i<$scope.ograniczenia.nowe.slowaKluczowe.length; i++) {
			config.params.slowa += ($scope.ograniczenia.nowe.slowaKluczowe[i] + " ");
		}
		
		var ograniczenieInvalid = function() {
			console.log("invalid");
			$("#loadingModal").modal('hide');
        	$scope.displayValidationError = true;
        	$('#addOgraniczenie').modal('show');
		}
		
		var ograniczenieSaved = function (ograniczenieId) {
			console.log("saved");
			$("#loadingModal").modal('hide');
			noweOgraniczenie.id = ograniczenieId;
        	$scope.zadanie.ograniczenia.push(noweOgraniczenie);
        	$scope.ograniczenia.populateTable();
        	$('#editZadaniaModal').modal('show');
		}
	
		$http.post(url, $.param(noweOgraniczenie), config)
	        .success(function (data) {
	        	console.log(data);
	        	if (data != 0) {
	        		ograniczenieSaved(data);
	        	} else {
	        		ograniczenieInvalid();
	        	}

	        })
	        .error(function(data, status, headers, config) {
	        	ograniczenieInvalid();
	        });
    	
    }
    
    $scope.dodajSlowoKluczowe = function () {
    	if ($scope.ograniczenia.nowe.noweSlowoKluczowe && (!($scope.ograniczenia.nowe.noweSlowoKluczowe == "")) && ($.inArray($scope.ograniczenia.nowe.noweSlowoKluczowe, $scope.ograniczenia.nowe.slowaKluczowe) == -1)) {
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
    
    $scope.hideValidationError = function () {
    	$scope.displayValidationError = false;
    }

    $scope.todo = function () {
    	alert('TODO');
    }
    
    $scope.getZadanieList();
}

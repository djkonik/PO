function LocationController($scope, $location) {
    if($location.$$absUrl.lastIndexOf('/rozwiazania') > 0){
    	$scope.activeURL = 'rozwiazania';
    } else if($location.$$absUrl.lastIndexOf('/zadania') > 0){
    	$scope.activeURL = 'zadania';
    } else {
        $scope.activeURL = 'home';
    }
}
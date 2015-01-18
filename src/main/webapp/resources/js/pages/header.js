function LocationController($scope, $location) {
    if($location.$$absUrl.lastIndexOf('/contacts') > 0){
        $scope.activeURL = 'contacts';
    } else if($location.$$absUrl.lastIndexOf('/rozwiazania') > 0){
    	$scope.activeURL = 'rozwiazania';
    } else {
        $scope.activeURL = 'home';
    }
}
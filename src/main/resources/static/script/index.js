var app = angular.module("INDEX", []);

app.controller("INDEX_CONTROLLER",function($scope, $http, $filter) {

    $scope.groupsObject = [{
        groupName: "",
        quantityStudents: ""
    }]

    $scope.studentsObject = [{
        date: "",
        fullName: ""
    }]



    $scope.groupName = localStorage.getItem("tasks")

    let loadTableGroup = function () {
        $http({
            url: "/groups",
            method: "GET"
        }).then(function (response) {
            $scope.groupsObject = response.data;
        })
    }
    let loadTableStudents = function () {
        $http({
            url: "/students/by/" + $scope.groupName,
            method: "GET"
        }).then(function (response) {
            for (let i = 0; i < response.data.length; i++) {
                let date = $filter('date')(response.data[i].date,'dd.MM.yyyy');
                let fullName = response.data[i].firstName + " " + response.data[i].middleName + " " + response.data[i].lastName;
                $scope.studentsObject[i] = ({
                        date: date,
                        fullName: fullName
                })
            }
            console.log($scope.studentsObject)

        })
    }

    loadTableStudents()
    loadTableGroup()

    $scope.edit = function (index) {
        localStorage.setItem("tasks", $scope.groupsObject[index].groupName);
    }


});
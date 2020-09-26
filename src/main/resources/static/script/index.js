var app = angular.module("INDEX", []);

app.controller("INDEX_CONTROLLER",function($scope, $http, $filter, $window) {

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
            // console.log(response.data)
            if(response.data.length !== 0) {
                for (let i = 0; i < response.data.length; i++) {
                    let date = $filter('date')(response.data[i].date, 'dd.MM.yyyy');
                    let fullName = response.data[i].lastName + " " + response.data[i].firstName + " " + response.data[i].middleName;
                    $scope.studentsObject[i] = ({
                        date: date,
                        fullName: fullName
                    })
                }
            } else $scope.studentsObject = response.data;
            console.log($scope.studentsObject)
        })
    }

    let update = function (flag) {
        $http({
            url: "/groups/by/" + $scope.groupName,
            method: "GET"
        }).then(function (response) {
            if(response.data.length === 0) {
                updateQuantityStudents(0, flag)
            } else updateQuantityStudents(response.data, flag)
        })
    }

    let updateQuantityStudents = function (quantityStudents, flag) {
        if (flag) quantityStudents++
        else quantityStudents--;
        $http({
            url: '/groups/update',
            method: 'POST',
            data: {
                "groupName": $scope.groupName,
                "quantityStudents": quantityStudents
            }
        }).then(function (response) {
            // loadTableStudents()
        })
    }

    let addGroupInTableDB = function (inputNameGroup) {
        console.log("group in db")
        console.log($scope.groupName)
        $http({
            url: '/groups',
            method: 'POST',
            data: {
                "groupName": inputNameGroup,
                "quantityStudents": 0
            }
        }).then(function (response) {
            loadTableStudents()
        })
    }

    loadTableStudents()
    loadTableGroup()

    $scope.edit = function (index) {
        localStorage.setItem("tasks", $scope.groupsObject[index].groupName);
    }

    $scope.addStudent = function () {
        let actualDate = new Date();
        let date = $filter('date')(actualDate, 'yyyy-MM-dd');
        let massName = $(".inputFIO").val();
        let lastName = massName.split(" ")[0];
        let firstName = massName.split(" ")[1];
        let middleName = massName.split(" ")[2];
        if(lastName == null || firstName == null || middleName == null) {
            alert("Неверный формать ФИО")
        } else {
            console.log("Add students in db" +  date + " " + massName);
            $http({
                url: '/students',
                method: 'POST',
                data: {
                    "date": date,
                    "firstName": firstName,
                    "middleName": middleName,
                    "lastName": lastName,
                    "groupName": $scope.groupName
                }
            }).then(function (response) {
                loadTableStudents()
                update(true);
            })
        }
    }

    $scope.delete = function (index) {
        let fullName = $scope.studentsObject[index].fullName;
        let firstName = fullName.split(" ")[1];
        let lastName = fullName.split(" ")[0];
        $http({
            url: "/students/" + firstName + "/" + lastName + "/" + $scope.groupName,
            method: "DELETE"
        }).then(function (response) {
            $scope.studentsObject.splice(index,1)
            loadTableStudents()
            update(false);
        })
    }

    $scope.addGroup = function () {
        if($(".inputNameGroup").val() === ""){
            alert("Введите имя группы")
        } else {
            localStorage.setItem("tasks", $(".inputNameGroup").val());
            addGroupInTableDB($(".inputNameGroup").val())
            $window.location.href = '/group';
        }
        console.log($scope.groupName)
    }

});

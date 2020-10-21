apiclient = (function () {
    let url = "http://localhost:8080/v1/";
    return {
        getAllCases: function (callback) {
            $.getJSON(url, (data) => {
                callback(data);
            }, null)
        },
        getCasesByCountry: function (country, callback) {
            $.getJSON(url + "stats/?country=" + country, (data) => {
                callback(data);
            }, null)
        },

    }

})();

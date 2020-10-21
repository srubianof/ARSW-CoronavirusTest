let app = (() => {
    let client = apiclient;

    var mapObjetos = (funciones) => {
        listaFunciones = funciones.map(({name, num_deaths, num_infected, num_cured}) => ({
                name: name,
                num_deaths: num_deaths,
                num_infected: num_infected,
                num_cured: num_cured
            })
        )
        $("#tabla > tbody").empty();
        listaFunciones.forEach(({name, num_deaths, num_infected, num_cured}) => {
            $("#tabla > tbody").append(
                `<tr>
            <td><a onclick="app.consultarPais('${name}')">${name}</a> </td>   
            <td> ${num_deaths} </td>
            <td> ${num_infected} </td>
            <td> ${num_cured} </td>
        </tr>`
            );
        });
    }
    var mapPaises = (funciones) => {
        listaPaises = funciones.provinces;
        plotMarkers(funciones.location);
        $("#tablaPais > tbody").empty();
        // const p = listaFunciones.map(a => a.provinces.valueOf())
        // console.log(p)
        listaPaises.forEach((province) => {
            $("#tablaPais > tbody").append(
                `<tr>
            <td> ${province.name} </td>   
            <td> ${province.num_deaths} </td>
            <td> ${province.num_infected} </td>
            <td> ${province.num_cured} </td>
        </tr>`
            );
        });
    }
    var initMap = () => {
        map = new google.maps.Map(document.getElementById("map"), {
            zoom: 2,
            center: {lat: 35.717, lng: 139.731},
        });
    }

    function plotMarkers(m) {
        console.log(m)
        markers = [];
        bounds = new google.maps.LatLngBounds();
        console.log(m.latitude, m.longitude);
        var position = new google.maps.LatLng(m.latitude, m.longitude);
        console.log(position);
        markers.push(
            new google.maps.Marker({
                position: position,
                map: map,
                animation: google.maps.Animation.DROP
            })
        );
        bounds.extend(position);
        map.fitBounds(bounds);
        map.setZoom(4);
    }

    function init() {
        initMap();
        client.getAllCases(mapObjetos);
    }

    return {
        init: init,
        consultarPais(nombre) {
            console.log(nombre)
            client.getCasesByCountry(nombre, mapPaises);
        }
    }
})();

//
// <tr>
//     <td> ${name} </td>
//     <td> ${num_deaths} </td>
//     <td> ${num_infected} </td>
//     <td> ${num_cured} </td>
//     <td> <button type="button" class="btn btn-success btn-lg btn-block" onclick="">Ver Sillas</button> </td>
// </tr>

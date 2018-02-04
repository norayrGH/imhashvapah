
var  cityes = $("#selectCityes");
var  regionselect = $("#regionSelect");
var  stritFleet = $("#stritFleet");


var address={stritFleet:"",region:"",city:""};
var textaddress="";
/*


$.each( addresses, function( key, value ){
    cityes.append($("<option>").attr({class:"city"}).text(key));
});


*/








/*
$("#employee").bind('ajax:complete', function() {
    alert("ok");
    $("#circleTax").attr({style:"display:inline;"});


});
*/

function goBack() {
    window.history.back();
}


$("#stritFleet").focusout(function () {

    address["stritFleet"]=$("#stritFleet").val();
    $.each( address, function( key , value ){
        textaddress+=value;
        if(key != "city" )
            textaddress+=",";
    });
    $("#juridicalAddress").val(textaddress);

    textaddress="";

});
$("#selectCityes").change(function () {
    regionselect.empty();

    address["city"]="";
    var city = $("#selectCityes").val();
    address["city"] = city;
    var regions = addresses[city];
    regionselect.append($("<option>"));
    $.each( regions, function( key,value ){
        regionselect.append($("<option>").attr({class:"region"}).text(value));
    });


    $.each( address, function( key, value ){
        textaddress+=value;
        if(key != "city" )
            textaddress+=",";

    });

    $("#juridicalAddress").val(textaddress);
    textaddress="";

});
$("#regionSelect").change(function () {

    address["region"]="";
    console.log($("#regionSelect").val());
    address["region"] = $("#regionSelect").val();

    console.log(address);

    $.each( address, function( key, value ){
        textaddress+=value;
        if(key != "city" )
            textaddress+=",";
    });
    $("#juridicalAddress").val(textaddress);

    textaddress="";


});
$('#start').change(function() {
    var date = $("#mob-gig-date-gteq").val();
    console.log(date, 'change')
});
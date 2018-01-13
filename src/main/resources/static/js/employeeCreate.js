/*var  cityes = $("<select>").attr({ required:" required",id:"selectCityes"}).append($("<option>").attr({selected:"selected",disabled :"disabled "}).text(" Ընտրեք քաղաքը "));
var  regionselect = $("<select>").attr({required:" required",id:"regionSelect"}).append($("<option>").attr({selected:"selected",disabled :"disabled "}).text(" Ընտրեք Մարզերը "));
var  stritFleet = $("<input>").attr({id:"stritFleet",placeholder:"փողոցի անուն , շենք կամ տուն "});*/
var  cityes = $("#selectCityes");
var  regionselect = $("#regionSelect");
var  stritFleet = $("#stritFleet");


var address={stritFleet:"",region:"",city:""};
var textaddress="";


$.each( addresses, function( key, value ){
    cityes.append($("<option>").attr({class:"city"}).text(key));
});










$("#employee").bind('ajax:complete', function() {
    alert("ok");
    $("#circleTax").attr({style:"display:inline;"});


});


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

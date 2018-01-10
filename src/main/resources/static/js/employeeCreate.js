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

/*

.append($("<option>").text("text")).appendTo($("#address"))
*/

  /*  var selectValue = $( this ).val();
    console.log(selectValue);
    $("#address").append($("<select>").attr("id","city"));*/



   /* var circleTax={
        circleTaxType:values["circleTaxType"],
        circleTaxClassificationOfEconomicActivity:values["circleTaxClassificationOfEconomicActivity"],
        circleTaxActionAddress:values["circleTaxActionAddress"],
        circleTaxTypeDesc:values["circleTaxTypeDesc"]

    };


    var employee={
        employeeName:values["employeeName"],
        hch:values["hch"],
        registrationNumber:values["registrationNumber"],
        registrationDate:values["registrationDate"],
        certificateNumber:values["certificateNumber"],
        taxpayerIdentificationNumber:values["taxpayerIdentificationNumber"],
        actingAddress:values["actingAddress"],
        dateOfOpeningBalances:values["dateOfOpeningBalances"],
        juridicalAddress:values["juridicalAddress"],
        circleTax:circleTax

    };
    var token = $("#csrfToken").val();

    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', token);
        }
    });

    $.ajax({

        method : "POST",
        contentType : "application/json",
        url : "/account/employee/create",
        dataType: 'json',
        data :JSON.stringify(employee),
        success: function(data){
            console.log(data);
        }
    });
*/

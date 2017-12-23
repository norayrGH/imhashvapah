var  cityes = $("<select>").attr({id:"selectCityes"}).append($("<option>").attr({selected:"selected",disabled :"disabled "}).text(" Ընտրեք քաղաքը "));
var address={city:null,region:null,stritFleet:null};

$.each( addresses, function( key, value ){
    cityes.append($("<option>").attr({class:"city"}).text(key));
});

$("#cityes").append(cityes);




$("#selectCityes").change(function () {
    $("#regions").empty();
      address["city"]=null;
      var city = $("#selectCityes").val();
      address["city"] = city;
      var regions = addresses[city];
      var regionselect = $("<select>").attr("id","regionSelect").append($("<option>").attr({selected:"selected",disabled :"disabled "}).text(" Ընտրեք Մարզերը "));
    $.each( regions, function( key,value ){
        regionselect.append($("<option>").attr({class:"region"}).text(value));
    });
    $("#regions").append(regionselect);

    console.log(address);
    $("#juridicalAddress").text(JSON.stringify(address));


});



$("#regionSelect").change(function () {
    alert("ok");
   address["region"]=null;
    console.log($("#regionSelect").val());
    address["region"] = $("#regionSelect").val();

    console.log(address);
    $("#juridicalAddress").text(JSON.stringify(address));


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

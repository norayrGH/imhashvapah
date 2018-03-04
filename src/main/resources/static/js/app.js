var cityes = $("#selectCityes");
var regionselect = $("#regionSelect");
var stritFleet = $("#stritFleet");


var address = {stritFleet: "", region: "", city: ""};
var textaddress = "";


if (cityes.val() != undefined) {
    $.each(addresses, function (key, value) {
        cityes.append($("<option>").attr({class: "city"}).text(key));
    });
}


$("#employee").bind('ajax:complete', function () {

    $("#circleTax").attr({style: "display:inline;"});


});

function goBack() {
    window.history.back();
}

$("#stritFleet").focusout(function () {

    address["stritFleet"] = $("#stritFleet").val();
    $.each(address, function (key, value) {
        textaddress += value;
        if (key != "city")
            textaddress += ",";
    });
    $("#juridicalAddress").val(textaddress);

    textaddress = "";

});
$("#selectCityes").change(function () {
    regionselect.empty();

    address["city"] = "";
    var city = $("#selectCityes").val();
    address["city"] = city;
    var regions = addresses[city];
    regionselect.append($("<option>"));
    $.each(regions, function (key, value) {
        regionselect.append($("<option>").attr({class: "region"}).text(value));
    });


    $.each(address, function (key, value) {
        textaddress += value;
        if (key != "city")
            textaddress += ",";

    });

    $("#juridicalAddress").val(textaddress);
    textaddress = "";

});
$("#regionSelect").change(function () {

    address["region"] = "";
    console.log($("#regionSelect").val());
    address["region"] = $("#regionSelect").val();

    console.log(address);

    $.each(address, function (key, value) {
        textaddress += value;
        if (key != "city")
            textaddress += ",";
    });
    $("#juridicalAddress").val(textaddress);

    textaddress = "";


});

function  showFixedAssets(fixedAssets) {


    $('#showFixedAssets').empty();

    var fixedAssetsTableTh= ["Գույքահամար","Անվանում","Ամսաթիվ","Ձեռքբերման գումար"];
    var fixedAssetsTable = $("<table />")
        .attr({id:"showFixedAssetsTable",class:"table table-hover "});
   var tHead =$("<thead />");
   var tHeadTr = $("<tr />");
    $.each(fixedAssetsTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
    tHeadTr.append($("<th />").text("Գործողություն"));
    tHead.append(tHeadTr);
    fixedAssetsTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(fixedAssets, function(i, item) {





        var date = new Date (item.acquiringDate).toISOString();
        tBody.append($("<tr />").attr({scope:"row"}).append($("<td />").text(item.inventoryNumber)
        ,$("<td />").text(item.name)
        ,$("<td />").text(date.substring(0,date.indexOf('T')))
        ,$("<td />").text(item.acquiringАmount)
        ,$("<td />").append($("<a />").attr({href:"/account/organization/fixedasset/edit/"+item.id , class:"glyphicon glyphicon-pencil"})
                            ,"&nbsp;&nbsp;&nbsp;"
                            ,$("<a />").attr({href:"/account/organization/fixedasset/delete/"+item.id , class:"glyphicon glyphicon-trash"}))

        ));
    });

    fixedAssetsTable.append(tBody);

    $('#showFixedAssets').append(fixedAssetsTable);

}
$(function () {
    $(".range-picker .datepicker").each(function () {
        $(this).datepicker();
        $(this).on('change', function () {
            var from = $(".range-picker .datepicker.from").val();
            var to = $(".range-picker .datepicker.to").val();
            $(this).closest(".range-picker").attr('data-range', from + "-" + to);
            // $(this).closest(".range-picker").trigger('change', from + "-" + to);
        });
    });
});
$(".selectWaletInType").change(function () {
   alert("ok");
});
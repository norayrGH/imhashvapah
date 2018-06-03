
var cityes = $("#selectCityes");
var regionselect = $("#regionSelect");
var stritFleet = $("#stritFleet");


var address = {stritFleet: "", region: "", city: ""};
var textaddress = "";
var chashInTypes={CashInFromBankAccount:"Մուտք բանկային հաշվից",CashInFromCredit:"Վարկի ստացում",CashInFromLoan:"Փոխառության ստացում",CashInFromPointOfSale:"Մուտք առևտրի կետից",CashInFromSaleOfGoods:"Մուտք ապրանքների իրացումից",CashInFromServiceProvision:"Մուտք ծառայության մատուցումից"};
var debtDetaileType={CashOutForTax:"Հարկ",CashOutForGoodsProvider:"Վճարում մատակարարին ապրանքների համար",CashOutForSerivceProvider:" Վճարում մատակարարին ծառայության համար "
                    ,CashOutForRent:"Վճարում վարձակալության համար",CashOutForBankAccount:"Վճարում բանկային հաշվին",
    CashOutForRedemptionPercent:"Վարկի տոկոսի մարում",CashOutForLoanPayment:"Փոխառության մարում",
    CashOutForBankSpending:"Բանկային ծախս",CashOutForOtherExpenses:"Այլ ծախսեր",
    CashOutForCreditPayment:"Վարկի մարում",
    PurchaseFixedAsset:"Ձեռքբերում",
    PurchaseGoods:"Ձեռքբերում",
    PurchaseService:"Ձեռքբերում",
};

if (cityes.val() != undefined) {
    $.each(addresses, function (key, value) {
        cityes.append($("<option>").attr({class: "city"}).text(key));
    });
}
function toDate(dateStr) {
    var parts = dateStr.split("/");
    console.log(new Date(parts[2] + "-" + parts[0] + "-" + parts[1]));
    return new Date(parts[2]+"-"+parts[0]+"-"+parts[1]);
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
function  partnerCustomersShow(partnerCustomers) {


    $('#partnerCustomersShow').empty();

    var partnerCustomersTableTh= ["Գնորդ","Հեռախոս","Հասցե","ՀՎՀՀ"];
    var partnerCustomersTable = $("<table />")
        .attr({id:"showFixedAssetsTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(partnerCustomersTableTh, function(i, item) {
        tHeadTr.append($("<th />").attr({style:"font-weight:bold"}).text(item));
    });
    tHeadTr.append($("<th />").attr({style:"font-weight:bold"}).text("Գործողություն"));
    tHead.append(tHeadTr);
    partnerCustomersTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(partnerCustomers, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.customerName)
            ,$("<td />").text(item.phoneNumber)
            ,$("<td />").text(item.address)
            ,$("<td />").text(item.hvhh)
            ,$("<td />").append(
                $("<a />").attr(
                    {href:"/account/partner/customer/edit/"+(item.type.includes("Individual")?'individualcustomer/':'companycustomer/')+'?'+'customerId='+item.id[0]+'&'+'customerInnerId='+item.id[1]  ,
                        class:"glyphicon glyphicon-pencil"}
                        )
                ,"&nbsp;&nbsp;&nbsp;"
                ,$("<a />").attr(
                    {href:"/account/organization/fixedasset/delete/"+item.id ,
                        class:"glyphicon glyphicon-trash"}
                        )
                ,"&nbsp;&nbsp;&nbsp;"
                ,(item.full==false)?$("<a />").attr(
                    {class:"glyphicon glyphicon-warning-sign"}
                ):""
            )
        ));
    });

    partnerCustomersTable.append(tBody);

    $('#partnerCustomersShow').append(partnerCustomersTable);

}
function  partnerOtherPartnerShow(partnerOtherPartner) {


    $('#partnerOtherPartnerShow').empty();

    var partnerOtherPartnerTableTh= ["Գնորդ","Հեռախոս","Հասցե","ՀՎՀՀ"];
    var partnerOtherPartnerTable = $("<table />")
        .attr({id:"showFixedAssetsTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(partnerOtherPartnerTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
    tHeadTr.append($("<th />").text("Գործողություն"));
    tHead.append(tHeadTr);
    partnerOtherPartnerTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(partnerOtherPartner, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.customerName)
            ,$("<td />").text(item.phoneNumber)
            ,$("<td />").text(item.address)
            ,$("<td />").text(item.hvhh)
            ,$("<td />").append(
                $("<a />").attr(
                    {href:"/account/partner/otherpartner/edit/"+(item.type.includes("Individual")?'individualotherpartner/':'companyotherpartner/')+'?'+'customerId='+item.id[0]+'&'+'customerInnerId='+item.id[1]  ,
                        class:"glyphicon glyphicon-pencil"}
                )
                ,"&nbsp;&nbsp;&nbsp;"
                ,$("<a />").attr(
                    {href:"/account/organization/fixedasset/delete/"+item.id ,
                        class:"glyphicon glyphicon-trash"}
                )
                ,"&nbsp;&nbsp;&nbsp;"
                ,(item.full==false)?$("<a />").attr(
                    {
                        class:"glyphicon glyphicon-warning-sign"
                    }
                ):""
            )
        ));
    });

    partnerOtherPartnerTable.append(tBody);

    $('#partnerOtherPartnerShow').append(partnerOtherPartnerTable);

}
function  partnerSuppliersShow(partnerSuppliers) {


    $('#partnerSuppliersShow').empty();

    var partnerSuppliersTableTh= ["Մատակարար","Հեռախոս","Հասցե","ՀՎՀՀ"];
    var partnerSuppliersTable = $("<table />")
        .attr({id:"partnerSuppliersTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(partnerSuppliersTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
    tHeadTr.append($("<th />").text("Գործողություն"));
    tHead.append(tHeadTr);
    partnerSuppliersTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(partnerSuppliers, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.suplllierName)
            ,$("<td />").text(item.phoneNumber)
            ,$("<td />").text(item.address)
            ,$("<td />").text(item.hvhh)
            ,$("<td />").append(
                $("<a />").attr(
                    {href:"/account/partner/supplier/edit/"+(item.type.includes("Individual")?'individualsupplier/':'companysupplier/')+'?'+'customerId='+item.id,
                        class:"glyphicon glyphicon-pencil"}
                )
                ,"&nbsp;&nbsp;&nbsp;"
                ,$("<a />").attr(
                    {href:"/account/organization/fixedasset/delete/"+item.id ,
                        class:"glyphicon glyphicon-trash"}
                )
                ,"&nbsp;&nbsp;&nbsp;"
                ,(item.full==false)?$("<a />").attr(
                    {
                        class:"glyphicon glyphicon-warning-sign"
                    }
                ):""
            )
        ));
    });

    partnerSuppliersTable.append(tBody);

    $('#partnerSuppliersShow').append(partnerSuppliersTable);

}
function  partnerSupplierDebtDetailsShow(supplierDebtDetails) {

    $('#supplierDebtDetails').empty();

    var partnerSuppliersDebtDetailsTableTh= ["Ամսաթիվ","Բովանդակություն","Կատարած Վճարում","Ձեռքբերում"];
    var partnerSuppliersDebtDetailsTable = $("<table />")
        .attr({id:"partnerSuppliersDebtDetailsTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
   $.each(partnerSuppliersDebtDetailsTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
    tHead.append(tHeadTr);
    partnerSuppliersDebtDetailsTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(supplierDebtDetails, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.debtDate)
            ,$("<td />").text(debtDetaileType[item.contents])
            ,$("<td />").append(item.contents.slice(0,8)!="Purchase"?$("<a>").attr("href","/cashout/cashdesk/edit/"+item.contents.toLowerCase()+"/"+item.id).text(item.payment):"")
            ,$("<td />").append(item.contents.slice(0,8)=="Purchase"?$("<a>").attr("href","partner/supplier/purchase/edit/"+item.id).text(item.purchase):"")
        ));
    });

    partnerSuppliersDebtDetailsTable.append(tBody);
     $('#supplierDebtDetails').append(partnerSuppliersDebtDetailsTable);
}
function  partnerSuppliersDebtShow(partnerSuppliersDebts) {
    $('#supplierShowDebt').empty();

    var partnerSuppliersDebtTableTh= ["Մատակարար","Կանխավճար","Պարտք"];
    var partnerSuppliersDebtTable = $("<table />")
        .attr({id:"partnerSuppliersDebtTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(partnerSuppliersDebtTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
    tHeadTr.append($("<th />").text("Տեսնել"));
   tHead.append(tHeadTr);
    partnerSuppliersDebtTable.append(tHead);
     var tBody = $("<tbody />");

    $.each(partnerSuppliersDebts, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.name)
            ,$("<td />").text(item.prepayment)
            ,$("<td />").text(item.debt)
            ,$("<td />").append(
                $("<a />").attr(
                    {
                        href:"/account/partner/supplier/debt/details/?supplierType="+item.type+'&'+'supplierId='+item.id ,
                        class:"glyphicon glyphicon-tasks"
                    }
                        )


            )
        ));
    });

    partnerSuppliersDebtTable.append(tBody);
    console.log(partnerSuppliersDebtTable);
    $("#supplierShowDebt").append(partnerSuppliersDebtTable);

}

function  showCashIn(cashIn) {


    $('#showCashIn').empty();

    var cashInTableTh= ["№","Ամսաթիվ","Գումար","Մուտքի տեսակ"];
    var cashInTable = $("<table />")
        .attr({id:"showCashInTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(cashInTableTh, function(i, item) {
        tHeadTr.append($("<th />").attr({"class": "h5", "style": "font-weight:bold"}).text(item));
    });
    tHeadTr.append($("<th />").text("Գործողություն"));
    tHead.append(tHeadTr);
    cashInTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(cashIn, function(i, item) {



        var date = new Date (item.inDate);
        date.setDate(date.getDate());
       var  test = new Date(date).toISOString();
        console.log(test);
        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.id)
            ,$("<td />").text(test.substring(0,test.indexOf('T')))
            ,$("<td />").text(item.inCash)
            ,$("<td />").text(chashInTypes[item.inType])
            ,$("<td />").append($("<a />").attr({href:"/account/cash/cashin/cashdesk/edit?cashintype="+item.inType+"&cashinid="+item.id, class:"glyphicon glyphicon-pencil"})
                ,"&nbsp;&nbsp;&nbsp;"
                ,$("<a />").attr({href:"/account/cash/cashin/cashdesk/delete?cashintype="+item.inType+"&cashinid="+item.id, class:"glyphicon glyphicon-trash"}))

        ));
    });

    cashInTable.append(tBody);

    $('#showCashIn').append(cashInTable);

}

function  showCashOut(cashOut) {

    $('#showCashOut').empty();

    var cashOutTableTh= ["№","Ամսաթիվ","Գումար","Ելքի տեսակ"];
    var cashOutTable = $("<table />")
        .attr({id:"showCashOutTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(cashOutTableTh, function(i, item) {
        tHeadTr.append($("<th />").attr({"class": "h5", "style": "font-weight:bold"}).text(item));
    });
    tHeadTr.append($("<th />").text("Գործողություն"));
    tHead.append(tHeadTr);
    cashOutTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(cashOut, function(i, item) {

        var date = new Date (item.outDate);
        date.setDate(date.getDate());
        var test = new Date(date).toISOString();
        console.log(test);
        tBody.append($("<tr />").attr({scope:"row"}).append(
            $("<td />").text(item.id)
            ,$("<td />").text(test.substring(0,test.indexOf('T')))
            ,$("<td />").text(item.outCash)
            ,$("<td />").text(debtDetaileType[item.outType])
            ,$("<td />").append($("<a />").attr({href:"/account/cash/cashout/cashdesk/edit?cashouttype="+item.outType+"&cashoutid="+item.id, class:"glyphicon glyphicon-pencil"})
                ,"&nbsp;&nbsp;&nbsp;"
                ,$("<a />").attr({href:"/account/cash/cashout/cashdesk/delete?cashouttype="+item.outType+"&cashoutid="+item.id, class:"glyphicon glyphicon-trash"}))

        ));
    });

    cashOutTable.append(tBody);

    $('#showCashOut').append(cashOutTable);

}
function showCash(cash){


    $('#showCash').empty();

    var cashTableTh= ["Անվանում","Սկզբնական մնացորդ","Մուտքեր","Ելքեր","Վերջնական մնացորդ"];
    var cashTable = $("<table />")
        .attr({id:"showCashInTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(cashTableTh, function(i, item) {
        tHeadTr.append($("<th />").attr({"class":"h5", "style":"font-weight: bold;"}).text(item));
    });
    tHeadTr.append($("<th />").text("Տեսնել"));
    tHead.append(tHeadTr);
    cashTable.append(tHead);
    var tBody = $("<tbody />");
    $.each(cash, function(i, item) {


        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.name ).attr({"class":"h5","style":"font-weight: bold;"}),
             $("<td />").text(item.openingBalance ),
             $("<td />").text(item.in),
             $("<td />").text(item.out),
             $("<td />").text(item.finalBalance)

        ));
    });

    cashTable.append(tBody);

    $('#showCash').append(cashTable);
}
function  showBankAccount(accounts) {


    $('#showBankAccount').empty();

    var bankAccountTableTh= ["Բանկ","Բանկային Հաշիվ","Սկզբնական մնացորդ"];
    var bankAccountTable = $("<table />")
        .attr({id:"showBankAccountTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(bankAccountTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
    tHeadTr.append($("<th />").text("Գործողություն"));
    tHead.append(tHeadTr);
    bankAccountTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(bankAccount, function(i, item) {


        tBody.append($("<tr />").attr({scope:"row"}).append(
           /*  $("<td />").text(item.inventoryNumber)
            ,$("<td />").text(item.name)
            ,$("<td />").text(item.acquiringАmount)
            ,$("<td />").append($("<a />").attr({href:"/account/organization/fixedasset/edit/"+item.id , class:"glyphicon glyphicon-pencil"})
                ,"&nbsp;&nbsp;&nbsp;"
                ,$("<a />").attr({href:"/account/organization/fixedasset/delete/"+item.id , class:"glyphicon glyphicon-trash"}))
*/
        ));
    });

    bankAccountTable.append(tBody);

    $('#showBankAccount').append(bankAccountTable);

}
//new DATE PIKER

function todayDate(element) {
    element.val(new Date)

}




$(function () {
    $(".range-picker .datepicker").each(function () {
        $(this).datepicker();
        $(this).on('change', function () {
            var from = $(".range-picker .datepicker.from").val();
            var to =   $(".range-picker .datepicker.to").val();
            $(this).closest(".range-picker").attr('data-range', from + "-" + to);
            // $(this).closest(".range-picker").trigger('change', from + "-" + to);
        });
    });
});

function chose(a) {
    if(a.href== window.location.href)
        console.log("true")
}

$(".selectWaletInType").change(function () {
    var fragmentNumber = parseInt(this.value);

    console.log(JSON.stringify(fragmentNumber));
    var csrf_token = $('#walletInCreate').find('input[name="_csrf"]').val();

    switch (fragmentNumber){

        case 1:
            alert("in ajax");
            $.ajax({

                method : "GET",
                contentType : "application/json",
                url: '/account/cash/cashin/cashdesk/create/selectformchange/cashinfromsaleofgoods/'+$("#organizationId").val(),

                beforeSend: function(xhr){
                    xhr.setRequestHeader("X-CSRF-TOKEN",  csrf_token);
                }
            });

            break;

    }


});

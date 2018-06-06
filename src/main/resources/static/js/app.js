
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
    Sale:"Վաճառք",
    CashInFromBankAccount:"Մուտք բանկային հաշվից",CashInFromCredit:"Վարկի ստացում",CashInFromLoan:"Փոխառության ստացում",CashInFromPointOfSale:"Մուտք առևտրի կետից",CashInFromSaleOfGoods:"Մուտք ապրանքների իրացումից",CashInFromServiceProvision:"Մուտք ծառայության մատուցումից"
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
    var tHeadTr = $("<tr />").attr({class:"h5"});
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
             $("<td />").text(item.suplllierName)
            ,$("<td />").text(item.phoneNumber)
            ,$("<td />").text(item.address)
            ,$("<td />").text(item.hvhh)
            ,$("<td />").append(
                $("<a />").attr(
                    {href:"/account/partner/customer/edit/?customertype="+(item.type.includes("Individual")?'IndividualCustomer':item.type.includes("Company")?'CompanyCustomer':'PrivateEntrepreneurCustomer')+'&'+'customerid='+item.id ,
                        class:"glyphicon glyphicon-pencil"}
                        )
                ,"&nbsp;&nbsp;&nbsp;"
                ,$("<a />").attr(
                    {href:"/account/partner/customer/delete/?customertype="+(item.type.includes("Individual")?'IndividualCustomer':item.type.includes("Company")?'CompanyCustomer':'PrivateEntrepreneurCustomer')+'&'+'customerid='+item.id ,
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
                    {href:"/account/partner/otherpartner/edit/?otherpartnertype="+(item.type.includes("Individual")?'IndividualOtherPartner':item.type.includes("Company")?'CompanyOtherPartner':'PrivateEntrepreneurOtherPartner')+'&'+'otherpartnerid='+item.id  ,
                        class:"glyphicon glyphicon-pencil"}
                )
                ,"&nbsp;&nbsp;&nbsp;"
               ,$("<a />").attr(
                    {href:"/account/partner/otherpartner/delete/?otherpartnertype="+(item.type.includes("Individual")?'IndividualOtherPartner':item.type.includes("Company")?'CompanyOtherPartner':'PrivateEntrepreneurOtherPartner')+'&'+'otherpartnerid='+item.id  ,
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
                    {href:"/account/partner/supplier/edit/"+(item.type.includes("Individual")?'individualsupplier/':item.type.includes("Company")?'companyotherpartner/':'privateentrepreneursupplier/')+'?'+'supplierId='+item.id,
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
            ,$("<td />").append(item.contents.slice(0,8)!="Purchase"?$("<a>").attr("href","/account/cash/cashout/cashdesk/edit?cashouttype="+item.contents+"&cashoutid="+item.id).text(item.payment):"")
            ,$("<td />").append(item.contents.slice(0,8)=="Purchase"?$("<a>").attr("href","/account/partner/supplier/purchase/edit/?purchasetype="+item.contents+"&purchaseid="+item.id).text(item.purchase):"")
        ));
    });

    partnerSuppliersDebtDetailsTable.append(tBody);
     $('#supplierDebtDetails').append(partnerSuppliersDebtDetailsTable);
}
function  partnerCustomerDebtDetailsShow(customerDebtDetails) {

    $('#customerDebtDetails').empty();

    var partnercustomerDebtDetailsTableTh= ["Ամսաթիվ","Բովանդակություն","Վաճառք","Ստացված Վճարում"];
    var partnercustomerDebtDetailsTable = $("<table />")
        .attr({id:"partnerSuppliersDebtDetailsTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
   $.each(partnercustomerDebtDetailsTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
    tHead.append(tHeadTr);
    partnercustomerDebtDetailsTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(customerDebtDetails, function(i, item) {


        tBody.append($("<tr />").attr({scope:"row"}).append(
            $("<td />").text( item.debtDate )
            ,$("<td />").text(debtDetaileType[item.contents])
            ,$("<td />").append(item.contents.slice(0,4)=="Sale"?$("<a>").attr("href","account/partner/customer/sale/edit/"+item.id).text(item.purchase):"")
            ,$("<td />").append(item.contents.slice(0,4)!="Sale"?$("<a>").attr("href","account/cashin/cashdesk/edit/"+item.contents.toLowerCase()+"/"+item.id).text(item.payment):"")

        ));
    });
    partnercustomerDebtDetailsTable.append(tBody);
     $('#customerDebtDetails').append(partnercustomerDebtDetailsTable);
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
function  partnerCustomersDebtShow(partnerCustomersDebts) {
    $('#customerShowDebt').empty();

    var partnerCustomersDebtTableTh= ["Գնորդ","Պարտք","Կանխավճար"];
    var partnerCustomersDebtTable = $("<table />")
        .attr({id:"partnerCustomersDebtTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(partnerCustomersDebtTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
    tHeadTr.append($("<th />").text("Տեսնել"));
   tHead.append(tHeadTr);
    partnerCustomersDebtTable.append(tHead);
     var tBody = $("<tbody />");

    $.each(partnerCustomersDebts, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.name)
            ,$("<td />").text(item.debt)
            ,$("<td />").text(item.prepayment)
            ,$("<td />").append(
                $("<a />").attr(
                    {
                        href:"/account/partner/customer/debt/details/?customerType="+item.type+'&'+'customerId='+item.id ,
                        class:"glyphicon glyphicon-tasks"
                    }
                        )
            )
        ));
    });

    partnerCustomersDebtTable.append(tBody);
    console.log(partnerCustomersDebtTable);
    $("#customerShowDebt").append(partnerCustomersDebtTable);

}
function cashDetailsShow(cashDetails) {
    $('#cashDetailsShow').empty();

    var cashDetailsTableTh= ["Ամսաթիվ","No","Մուտք","Ելք","Տեսակ"];
    var cashDetailsTable = $("<table />")
        .attr({id:"cashDetailsTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(cashDetailsTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
   tHead.append(tHeadTr);
    cashDetailsTable.append(tHead);
     var tBody = $("<tbody />");

    $.each(cashDetails, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
            $("<td />").text(item.cashDate)
            ,$("<td />").text(item.cashId)
            ,$("<td />").append(item.type.slice(0,7)!="CashOut"?$("<a>").attr("href","/account/cash/cashin/cashdesk/edit?cashintype="+item.type+"&cashinid="+item.cashId).text(item.cashIn):"")
            ,$("<td />").append(item.type.slice(0,7)=="CashOut"?$("<a>").attr("href","/account/cash/cashout/cashdesk/edit?cashouttype="+item.type+"&cashoutid="+item.cashId).text(item.cashOut):"")
            ,$("<td />").text(debtDetaileType[item.type])
        ));
    });

    cashDetailsTable.append(tBody);
    console.log(cashDetailsTable);
    $("#cashDetailsShow").append(cashDetailsTable);

}
function customerPaymentShow(customerPayments) {
    $('#customerPaymentShow').empty();

    var customerPaymentTableTh= ["No","Ամսաթիվ","Գումար","Գնորդ","Մուտքի տեսակ"];
    var customerPaymentTable = $("<table />")
        .attr({id:"customerPaymentTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(customerPaymentTableTh, function(i, item) {
        tHeadTr.append($("<th />").text(item));
    });
   tHead.append(tHeadTr);
    customerPaymentTable.append(tHead);
     var tBody = $("<tbody />");

    $.each(customerPayments, function(i, item) {
        tBody.append($("<tr />").attr({scope:"row"}).append(
            $("<td />").text(item.id)
            ,$("<td />").text(item.paymentDate)
            ,$("<td />").append($("<a>").attr("href","/account/cashin/cashdesk/edit?cashintype="+item.type+"&cashinid="+item.id).text(item.paymentSum))
            ,$("<td />").text(item.customerName)
            ,$("<td />").text(debtDetaileType[item.type])
        ));
    });

    customerPaymentTable.append(tBody);
    console.log(customerPaymentTable);
    $("#customerPaymentShow").append(customerPaymentTable);

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
function  showCustomerSales(customerSales) {


    $('#showCustomerSales').empty();

    var customerSalesTableTh= ["№","Ամսաթիվ","Վաճառքի գումար","Գնորդ"];
    var customerSalesTable = $("<table />")
        .attr({id:"showCustomerSalesTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(customerSalesTableTh, function(i, item) {
        tHeadTr.append($("<th />").attr({"class": "h5", "style": "font-weight:bold"}).text(item));
    });
    tHeadTr.append($("<th />").text("Գործողություն"));
    tHead.append(tHeadTr);
    customerSalesTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(customerSales, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.saleNumber)
            ,$("<td />").text(item.saleDate)
            ,$("<td />").text(item.sum)
            ,$("<td />").text(item.customerName)
            ,$("<td />").append($("<a />").attr({href:"/account/partner/customer/sale/edit?saleid="+item.id, class:"glyphicon glyphicon-pencil"})
                ,"&nbsp;&nbsp;&nbsp;"
                ,$("<a />").attr({href:"/account/partner/customer/sale/delete?saleid="+item.id, class:"glyphicon glyphicon-trash"}))

        ));
    });
    console.log("in showCustomerSales");
    customerSalesTable.append(tBody);

    $('#showCustomerSales').append(customerSalesTable);

}
function  showOtherPartnerPayments(otherPartnerPayments) {


    $('#showOtherPartnerPayments').empty();

    var otherPartnerPaymentsTableTh= ["№","Ամսաթիվ","Գումար","Այլ Գործընկեր","Մուտքի/Ելքի տեսակ"];
    var otherPartnerPaymentsTable = $("<table />")
        .attr({id:"showCashInTable",class:"table table-hover "});
    var tHead =$("<thead />");
    var tHeadTr = $("<tr />");
    $.each(otherPartnerPaymentsTableTh, function(i, item) {
        tHeadTr.append($("<th />").attr({"class":"h5", "style":"font-weight:bold"}).text(item));
    });
    tHead.append(tHeadTr);
    otherPartnerPaymentsTable.append(tHead);
    var tBody = $("<tbody />");

    $.each(otherPartnerPayments, function(i, item) {

        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.id)
            ,$("<td />").text(item.paymentDate)
            ,$("<td />").append(
                $("<a />").text(item.paymentSum).attr(
                    {
                        href:"/account/cash/"+(item.cashinOrCashOut.includes("CashIn")?'cashin':'cashout')+"/cashdesk/edit?"+(item.cashinOrCashOut.includes("CashIn")?"cashintype="+item.cashinOrCashOut+"&cashinid="+item.id:"cashoutype="+item.cashinOrCashOut+"&cashoutid="+item.id)
                    }))
            ,$("<td />").text(item.otherPartnerName)
            ,$("<td />").text(debtDetaileType[item.cashinOrCashOut])

        ));
    });

    otherPartnerPaymentsTable.append(tBody);

    $('#showOtherPartnerPayments').append(otherPartnerPaymentsTable);

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
    tHeadTr.append($("<th />").attr({"class":"h5", "style":"font-weight: bold;"}).text("Տեսնել"));
    tHead.append(tHeadTr);
    cashTable.append(tHead);
    var tBody = $("<tbody />");
    $.each(cash, function(i, item) {


        tBody.append($("<tr />").attr({scope:"row"}).append(
             $("<td />").text(item.name ).attr({"class":"h5","style":"font-weight: bold;"}),
             $("<td />").text(item.openingBalance ),
             $("<td />").text(item.in),
             $("<td />").text(item.out),
             $("<td />").text(item.finalBalance),
             $("<td />").append($("<a />").attr({href:"/account/cash/details", class:"glyphicon glyphicon-tasks"})
             )
            )
        )


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
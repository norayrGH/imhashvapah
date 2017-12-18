$("#employeeCreate").on('click',function () {

    var $inputs = $('#myForm :input');

    var values = {};
    $inputs.each(function() {
        values[this.name] = $(this).val();
    });
    var circleTax={
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

});
var subtaskPopulateApp = subtaskPopulateApp||{};
subtaskPopulateApp.$=AJS.$;//korzystaniezwlasnejwersjiJQuery

AJS.toInit(function() {
    AJS.$("#create-subtask").on("click", subtaskPopulateApp.addButton);
    //AJS.$(document).on("click", "#create-subtask", subtaskPopulateApp.addButton)
    AJS.$(document).on("click", "#populateBtn", subtaskPopulateApp.addPopUp);
});

subtaskPopulateApp.myInterval;
subtaskPopulateApp.addButton = function(){
    subtaskPopulateApp.myInterval = setInterval(function(){
        var newButton = "<button id='populateBtn' class='aui-button'>Select</button>";
        AJS.$(".aui-toolbar2-secondary").prepend(newButton); 
        if(AJS.$("button#populateBtn").length !== 0){
            clearInterval(subtaskPopulateApp.myInterval);
        }
    }, 100);   
}

subtaskPopulateApp.valuesArray = [];
subtaskPopulateApp.idValueArrayForSoy = [];
subtaskPopulateApp.uniqueIDAddition = "CopyCheckbox";

subtaskPopulateApp.addPopUp = function(){
    subtaskPopulateApp.valuesArray = [];
    subtaskPopulateApp.idValueArrayForSoy = [];
    AJS.$(".form-body .field-group label").map(function(){
        subtaskPopulateApp.valuesArray.push(AJS.$(this).text());
    });
    subtaskPopulateApp.parseLabels();
    
    for(var i = 0; i < subtaskPopulateApp.valuesArray.length; i++){
        subtaskPopulateApp.idValueArrayForSoy.push({id: subtaskPopulateApp.valuesArray[i],
            value: subtaskPopulateApp.parseString(subtaskPopulateApp.valuesArray[i])});
    }
    
    var dialog = new AJS.Dialog(500, 500);
    
    var template = CopyDialog.Template.createSubtaskExtenderDialog({fieldsToCopy: subtaskPopulateApp.idValueArrayForSoy, uniqueID: subtaskPopulateApp.uniqueIDAddition});
    dialog.addHeader("Subtask's field's filler", "aui-dialog2-header");
    dialog.addPanel("Panel 1", template, "panel-body");
    dialog.addButton("Copy", function (){
        AJS.$.each(subtaskPopulateApp.idValueArrayForSoy, function (index, value){
            if (AJS.$("#" + value.value + subtaskPopulateApp.uniqueIDAddition).prop("checked") ){
                console.log("#" + value.value + subtaskPopulateApp.uniqueIDAddition);
            }
        }); 
    dialog.remove();    
    }, "aui-button aui-button-primary");
    
    dialog.addCancel("Cancel", function () {
        dialog.remove();
    });
    dialog.gotoPage(0);
    dialog.gotoPanel(0);
    dialog.show();
};

subtaskPopulateApp.parseLabels = function(){
    subtaskPopulateApp.valuesArray.splice(0, 2); // usuwam pierwsze dwa elementy bo nie wiadomo sk¹d siê bior¹
    
    for(var i=0; i < subtaskPopulateApp.valuesArray.length; i++) {
        subtaskPopulateApp.valuesArray[i] = subtaskPopulateApp.valuesArray[i].replace("Required", "");
    }
    subtaskPopulateApp.valuesArray = _.reject(subtaskPopulateApp.valuesArray, function (value){
            return value.indexOf("Estimate") >= 0;
    });
};

subtaskPopulateApp.parseString = function(value){
    value = value.replace(" ", "");
    value = value.replace("/", "");
    value = value.toLowerCase();
    return value;
};
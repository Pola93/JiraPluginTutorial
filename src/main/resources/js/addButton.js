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

subtaskPopulateApp.myFields = {
    id: "1234",
    summary: "moje summary",
    description: "moje description"
};

subtaskPopulateApp.keyArray;
subtaskPopulateApp.uniqueIDAddition = "CopyCheckbox";

subtaskPopulateApp.addPopUp = function(){
    var dialog = new AJS.Dialog(500, 500);
    subtaskPopulateApp.keyArray = [];
    
    AJS.$.each( subtaskPopulateApp.myFields, function( key, value ) {
        subtaskPopulateApp.keyArray.push(key);
    });
    
    var template = CopyDialog.Template.createSubtaskExtenderDialog({fieldsToCopy: subtaskPopulateApp.keyArray, uniqueID: subtaskPopulateApp.uniqueIDAddition});
    dialog.addHeader("Subtask's field's filler", "aui-dialog2-header");
    dialog.addPanel("Panel 1", template, "panel-body");
    dialog.addButton("Copy", function (){
        AJS.$.each(subtaskPopulateApp.keyArray, function (index, value){
            if (AJS.$("#" + value + subtaskPopulateApp.uniqueIDAddition).prop("checked") ){
                console.log("#" + value + subtaskPopulateApp.uniqueIDAddition);
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
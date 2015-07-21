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
        var newButton = "<button id='populateBtn' class='aui-button'>Moj przycisk</button>";
        AJS.$(".aui-toolbar2-secondary").prepend(newButton); 
        if(AJS.$("button#populateBtn").length !== 0){
            clearInterval(subtaskPopulateApp.myInterval);
        }
    }, 100);   
}

subtaskPopulateApp.addPopUp = function(){
    console.log("klikam button popUp-a");
    var dialog = new AJS.Dialog(500, 500);
    
    var template = CopyDialog.Template.createSubtaskExtenderDialog();
    dialog.addPanel("Panel 1", template, "panel-body");
    dialog.show();
}

//openConfirmDialog();
//        function openConfirmDialog() {
//            var dialog = new AJS.Dialog(500, 500);
//
//            var template = UserCleaner.Templates.confirm({usersToActivate: toBeActivated.slice(0,-2).split(","), usersToDeactivate: toBeDeactivated.slice(0,-2).split(",")})
//            dialog.addPanel("Panel 1", template, "panel-body");
//            dialog.addCancel("Cancel", function () {
//                dialog.hide();
//            });
//            dialog.addButton("OK", function () {
//                oneRingToRuleThemAll(usersToUpdate, toActivate, toDeactivate);
//                dialog.hide();
//            })
//            dialog.gotoPage(0);
//            dialog.gotoPanel(0);
//            dialog.show();
////            console.log("no kurwa")
//        }
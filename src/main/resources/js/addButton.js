var subtaskPopulateApp = subtaskPopulateApp||{};
subtaskPopulateApp.$=AJS.$;//korzystaniezwlasnejwersjiJQuery

AJS.toInit(function() {
//    if(AJS.$('#create-subtask-dialog').is(':visible')){
//        subtaskPopulateApp.addButton();
//    }
    AJS.$("#create-subtask").on("click", subtaskPopulateApp.addButton);
});

subtaskPopulateApp.addButton = function(){
    
    setTimeout(function(){
        var newButton = "<button>Moj przycisk</button>";
        AJS.$(".aui-toolbar2-secondary").prepend(newButton);
        console.log("chce dodac buttona");  
    }, 500);
}

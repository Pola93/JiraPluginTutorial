var subtaskPopulateApp=subtaskPopulateApp||{};subtaskPopulateApp.$=AJS.$;AJS.toInit(function(){AJS.$("#create-subtask").on("click",subtaskPopulateApp.addButton)});subtaskPopulateApp.addButton=function(){setTimeout(function(){var a="<button>Moj przycisk</button>";AJS.$(".aui-toolbar2-secondary").prepend(a);console.log("chce dodac buttona")},500)};
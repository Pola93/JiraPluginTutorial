var myApp=myApp||{};
myApp.$=AJS.$;//korzystaniezwlasnejwersjiJQuery

AJS.toInit(function() {
    AJS.$("#roll").on("click", myApp.roll);
    AJS.$("#restSubmitButton").on("click", myApp.submit);
});

myApp.diceRoll=function(dice){
    return Math.floor((Math.random()*100)%dice)+1;
}

myApp.roll=function(event){
    var diceQuantity=AJS.$("input[name='diceCount']").val();
    var dice=AJS.$("select option:selected").val();

    var sum=0;
    for(var i=0; i<diceQuantity; i++){
        sum+=myApp.diceRoll(dice);
    }
    AJS.$("#resultBox").val(sum);
    event.preventDefault();
}

myApp.submit = function(event){
    
    var input = AJS.$("#restInput"),
        results = AJS.$("#restResult > p");

    AJS.$.ajax({
        url: '/rest/mojaklasa/1.0/' + input.val(),
        type: 'get',
        cache: false,
        async: false,
        dataType: 'json',
        success: function(response) {
            results.text("");
            results.append("Czas wyszukiwania: " + response.searchTime + " ms");
            //JAK CZYSCIC <p> BEZ WYWALANIE Z NIEGO JEGO ZNACZNIKOW
//            results.empty();
//            summary.text(AJS.format(AJS.params.searchSummary,
//                response.matches.length,
//                response.searchTime));
//            AJS.$.each(response.matches, function() {
//                results.append(renderMatch(this));
//            });
        },
        error: function(request) {
            alert('oh noes!');
        }
    });
    
    event.preventDefault();
}
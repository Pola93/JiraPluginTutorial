var myApp=myApp||{};myApp.$=AJS.$;AJS.toInit(function(){AJS.$("#roll").on("click",myApp.roll);AJS.$("#restSubmitButton").on("click",myApp.submit)});myApp.diceRoll=function(a){return Math.floor((Math.random()*100)%a)+1};myApp.roll=function(e){var a=AJS.$("input[name='diceCount']").val();var b=AJS.$("select option:selected").val();var d=0;for(var c=0;c<a;c++){d+=myApp.diceRoll(b)}AJS.$("#resultBox").val(d);e.preventDefault()};myApp.submit=function(c){var a=AJS.$("#restInput"),b=AJS.$("#restResult > p");AJS.$.ajax({url:"/rest/mojaklasa/1.0/"+a.val(),type:"get",cache:false,async:false,dataType:"json",success:function(d){b.text("");b.append("Czas wyszukiwania: "+d.searchTime+" ms");b.append(AJS.format(AJS.params.searchTimeOfSearchFormat,d.matches.length,d.searchTime))},error:function(d){alert("oh noes!")}});c.preventDefault()};
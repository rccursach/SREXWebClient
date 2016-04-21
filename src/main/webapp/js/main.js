$(document).ready(function(){
    $("#btn_go").click(function(){
        append_buscando();
        $.get("execute_query", {"query": $("#in_query").val() })
                .success(function(data){
                    append_result(data);
                })
                .error(function(){
                    alert("an error ocurred");
                });
    }); 
});

function append_result(data){
    $("#div_result").html("");
    try{
        var obj = eval("(" + data + ")");
        console.log('data:');
        console.log(obj);
        console.log('----');
        for(var i=0; i<obj.length; i++){
            $("#div_result").append("<div class='row result'> <h4>"+obj[i].ranking+" - <a href='"+obj[i].url+"'>"+obj[i].url+"</a></h4><br/><p>"+obj[i].snippet+"</p></div>");
        }
    }
    catch(e){
        $("#div_result").html("<h3>Error :(</h3>");
        $("#div_result").append("<hr><div class='row'><pre>"+data+"</pre></div>");
    }
    $("#div_result").append("<hr><div class='row'><pre>"+JSON.stringify(obj, null, 2)+"</pre></div>");
}

function append_buscando(){
    $("#div_result").html("");
    $("#div_result").html("<h3>Buscando...</h3>");
}

//(function () {

var eventTable = document.getElementById("eventTable");


function updateEventTable(row,cell,value){
    var updaterow = eventTable.querySelector('tr:nth-child('+row+')');
    var updatecell = updaterow.querySelector('td:nth-child('+cell+')');
    updatecell.innerHTML = value;
}

function updateEventTableValue(label,text){
    updateEventTable(1,1,label);
    updateEventTable(1,2,text);
}

function updateEventTableValue2(label,text){
    updateEventTable(2,1,label);
    updateEventTable(2,2,text);
}

function updateEventTableAll(label1,text1,label2,text2){
        updateEventTableValue(label1,text1);
        updateEventTableValue2(label2,text2);
    
}

//})();


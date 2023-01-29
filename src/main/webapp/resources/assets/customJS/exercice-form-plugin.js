/**
 * 
 *  require jQuery
 * 
 */

function toDescription(date) {
    let str = date.toString();
    str = str.split(" ");
    return str[1] + " " + str[3];
}

$(document).ready(function () {

    function setDescription (starts, ends) {
        $("#description").val(toDescription(starts) + " - " + toDescription (ends));
    }

    function toMonthStart(date){
        date.setDate(1);
        return date;
    }

    function toInputValue(date) {
        return date.toISOString().substring(0,10);
    }

    function changeAndAddStart(source) {
        let starts = toMonthStart(new Date(source.value));
        let ends = toMonthStart(new Date(source.value));
        
        ends.setYear(ends.getYear()+1901);
        ends.setDate(ends.getDate()-1);

        $("#starts").val(toInputValue(starts));
        $("#ends").val(toInputValue(ends));
        setDescription(starts, ends)
    }
    
    function changeAndAddEnd(source) {
        let ends = toMonthStart(new Date(source.value));
        let starts = toMonthStart(new Date(source.value));
        
        starts.setYear(starts.getYear()+1900-1);
        starts.setMonth(starts.getMonth()+1);
        starts = toMonthStart(starts);
        ends = new Date(starts.getTime());
        ends.setYear(ends.getYear()+1901);
        ends.setDate(ends.getDate()-1);

        $("#starts").val(toInputValue(starts));
        $("#ends").val(toInputValue(ends));
        setDescription(starts, ends)
    }

    $("#starts").change(function ()  {
        changeAndAddStart(this);
    });

    $("#ends").change(function ()  {
        changeAndAddEnd(this);
    });

});

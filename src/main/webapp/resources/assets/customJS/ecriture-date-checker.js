let period = $("#period-start").text();


function strPeriodToDate(str) {
    // console.log(str);
    let string = str.split(" ");
    // console.log(string[1])
    string = string[0]+ " 1 "+string[1];
    console.log(string);
    return new Date(string);
}

let date_period = strPeriodToDate(period);

let days = [31,28,31,30,31,30,31,31,30,31,30,31]

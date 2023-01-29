function makeTotal() {
    let debit_tot = 0,
        credit_tot = 0;
    let debits = $(".moves #debit");
    let credits = $(".moves #credit");
    let taux = $(".moves #taux");

    for (let i = 0; i < debits.length; i++) {
        let valdeb = debits[i].value;
        let valcred = credits[i].value;
        debit_tot += (parseInt(valdeb == "" ? "0" : valdeb) * taux[i].value ) ;
        credit_tot += (parseInt(valcred == "" ? "0" : valcred) * taux[i].value) ;
    }

    $("#total-debit").val(debit_tot);
    $("#total-credit").val(credit_tot);
}

$("#equilibrateBtn").click(function () {
    let moves = $(".moves");
    if (moves.length == 1) {
        toastr.error("On peut pas equilibres une seule ligne");
        return;
    }

    let empty_move = null;
    let empty_debit = null,
        empty_credit = null;
    let tot_debit = 0,
        tot_credit = 0;

    for (let i = 0; i < moves.length; i++) {
        let debit = $(".moves #debit")[i];
        let credit = $(".moves #credit")[i];
        let taux= $(".moves #taux")[i];

        if (
            debit.value == 0 &&
            debit.value == "" &&
            credit.value == debit.value
        ) {
            if (empty_move != null) {
                toastr.error("on peut pas equilibrer avec plusieurs lignes vide");
                return;
            }
            empty_move = moves[i];
            empty_debit = debit;
            empty_credit = credit;
        }

        tot_debit += parseInt(debit.value == "" ? "0" : debit.value) * taux.value;
        tot_credit += parseInt(credit.value == "" ? "0" : credit.value) * taux.value;
    }

    let diff = tot_debit - tot_credit;

    if (diff != 0 && empty_move == null) {
        toastr.error("Pour equilibrer il faut q'une ligne soit vide");
        return;
    }

    if (diff < 0) {
        empty_debit.value = -1 * diff;
    } else {
        empty_credit.value = diff;
    }

    makeTotal();
});


$(document).ready(function () {
    $("#moves-container").change(function () {
        makeTotal();
    });
});
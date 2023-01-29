$(function () {
    $(".select2").select2({});

    $(".select-exercice").select2({
        language: {
            noResults: function () {
                return "<a class='text-primary' onclick='show_exercice_modal()' id='add-exercice-link'> Ajouter nouveau </a>";
            },
        },
        escapeMarkup: function (markup) {
            return markup;
        },
        // matcher: matchStart
    });

    $(".select-devise").select2({
        language: {
            noResults: function () {
                return "<a class='text-primary' onclick='show_devise_modal()' id='add-devise-link'> Ajouter nouveau </a>";
            },
        },
        escapeMarkup: function (markup) {
            return markup;
        },
        // matcher: matchStart
    });

    $(".select2-container").click(function () {
        $(".select2-dropdown").show();
    });
});

$(document).on("select2:open", () => {
    document
        .querySelector(".select2-container--open .select2-search__field")
        .focus();
});

function show_exercice_modal() {
    $(".select2-dropdown").hide();
    let _new = $(".select-exercice").data("select2").dropdown.$search.val();
    $("#description").val(_new);
    $("#add-exercice-modal").modal("show");
}


function show_devise_modal() {
    $(".select2-dropdown").hide();
    let _new = $(".select-devise").data("select2").dropdown.$search.val();
    $("#description").val(_new);
    $("#add-devise-modal").modal("show");
}

function make_select_compte() {
    $(function () {
        $(".select-compte").select2({
            language: {
                noResults: function () {
                    return "<a class='text-primary' onclick='show_compte_modal()' id='add-exercice-link'> Ajouter nouveau </a>";
                },
            },
            escapeMarkup: function (markup) {
                return markup;
            },
            // matcher: matchStart
        });

        $(".select2-container").click(function () {
            $(".select2-dropdown").show();
        });
    });
}
make_select_compte();

function show_compte_modal() {
    $(".select2-dropdown").hide();
    $("#add-compte-modal").modal("show");
}

function make_select_tier() {
    $(function () {
        $(".select-tier").select2({
            language: {
                noResults: function () {
                    return "<a class='text-primary' onclick='show_tier_modal()' id='add-exercice-link'> Ajouter nouveau </a>";
                },
            },
            escapeMarkup: function (markup) {
                return markup;
            },
            // matcher: matchStart
        });

        $(".select2-container").click(function () {
            $(".select2-dropdown").show();
        });
    });
}

make_select_tier();

const targetNode = $("#moves-container")[0];


const config = { childList: true };

const refresh_select = function (mutationsList, observer) {
    for (const mutation of mutationsList) {
        if (mutation.type === "childList") {
            make_select_tier();
            make_select_compte();
        }
    }
};

const observer = new MutationObserver(refresh_select);
// observer.observe(targetNode, config);

function show_tier_modal() {
    $(".select2-dropdown").hide();
    let _new = $(".select-tier").data("select2").dropdown.$search.val();
    console.log(_new);
    $("#nomTier").val(_new);
    $("#add-tier-modal").modal("show");
}
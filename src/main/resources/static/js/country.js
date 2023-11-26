$(document).ready(function () {
    $("#detailContains").css("display", "none");
    // when click the create button, show the detailContains
    $("#selCreate").on('click', function () {
        // clear all input
        $(':input', '#frmDetail')
            .not(':button, :submit, :reset, :hidden')
            .val('');
        // show the detailContains
        $("#detailContains").css("display", "block");
        // hide the queryContainer
        $("#queryContainer").css("display", "none");
    });

    // when click the update button, show the queryContainer
    $("#selUpdate, #selDelete").on('click', function () {
        // show the queryContainer
        $("#queryContainer").css("display", "block");
        // hide the detailContains
        $("#detailContains").css("display", "none");
        // set the form action for update
        $("#frmDetail").attr("action", "/UpdateCountry");
    });

    // when click the return button, hide the detailContains
    $("#returnBtn").on('click', function () {
        // show the queryContainer
        // $("#queryContainer").css("display", "block");
        // hide the detailContains
        $("#detailContains").css("display", "none");
    });

    $("#deleteCountry").on("click", function () {
        id = $("#queryInput").val();
        $.ajax({
            type: "DELETE",
            url: "/country/del/" + id,        //  <- controller function name

            success: function () {
                // show the data in the detailContains
                alert("success");
            },
            error: function (e) {
                alert("error");
            }
        });

    });

    $("#updateCountry").on('click', function () {
        $.ajax({
            type: "POST",
            url: "/country/editCountry",        //  <- controller function name
            data: $("#frm").serialize(),
            success: function (data) {
                // show the data in the detailContains
                alert("success");
            },
            error: function (e) {
                alert("error");
            }
        });
    });

    $("#addCountry").on('click', function () {
        $.ajax({
            type: "POST",
            url: "/country/addCountry",        //  <- controller function name
            data: $("#frm").serialize(),
            success: function (data) {
                // show the data in the detailContains
                alert("success");
            },
            error: function (e) {
                alert("error");
            }
        });
    });

    $("#queryBtn").on('click', function () {
        // use ajax to post data to controller
        // recived the data from controller with json
        // show the data in the detailContains
        $.ajax({
            type: "POST",
            url: "/country/getCountry",        //  <- controller function name
            data: $("#frmSearch").serialize(),
            dataType: 'json',
            success: function (data) {
                $("#detailContains").css("display", "block");
                // show the data in the detailContains
                $("#countryCodeInput").val(data.mstcountrycd);
                $("#countryNameInput").val(data.mstcountrynanme);
            },
            error: function (e) {
                alert("error");
            }
        });
    });
});
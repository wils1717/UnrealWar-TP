$(document).ready(function() {
$(".btn-pref .btn").click(function () {
    $(".btn-pref .btn").removeClass("btn-primary").addClass("btn-default");
    // $(".tab").addClass("active"); // instead of this do the below 
    $(this).removeClass("btn-default").addClass("btn-primary");   
});
});

function generateQR(){ 
  var fname = $('input[name=fname]').val();
  var lname = $('input[name=lname]').val();  
  console.log(fname + ' ' + lname);
  var qrcode = new QRCode(document.getElementById("qrcode"), {
    text: fname + " " + lname,
    width: 128,
    height: 128,
    colorDark : "#000000",
    colorLight : "#ffffff",
    correctLevel : QRCode.CorrectLevel.H
  });
  
}
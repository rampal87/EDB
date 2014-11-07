/**
 * 
 */

$(document).ready(function() {
    $('a.edit').click(function () {
    	alert("jquery");
        var dad = $(this).parent().parent();
        alert(dad.innerHTML);
    });

    $('input[type=text]').focusout(function() {
        var dad = $(this).parent();
        $(this).hide();
        dad.find('label').show();
    });
});
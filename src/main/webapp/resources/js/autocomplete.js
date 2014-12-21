/**
 * Created by sadra on 12/13/14.
 */

$(document).ready(function() {

    $( "#name" ).autocomplete({
        source: function( request, response ) {
            $.ajax({
                url: 'currywithari/ajax/customer/' + $( this ).val() ,
                dataType: "json",
                data: {
                    customerName: request.term
                },
                success: function( data ) {
                    response( data );
                }
            });
        },
        minLength: 3,
//        select: function( event, ui ) {
//            log( ui.item ?
//                "Selected: " + ui.item.label :
//                "Nothing selected, input was " + this.value);
//        },
        open: function() {
            $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
        },
        close: function() {
            $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
        }
    });

});
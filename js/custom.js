function postToGoogle() {
    var name = $('#name').val();
    var company = $('#company').val();
    var email = $('#email').val();
    var enquiry = $('#enquiry').val();

    $.ajax({
        url: "https://docs.google.com/forms/d/16KIKy1R7Z9yFXTFYQdAuzRiyjbn5x1MEoZwj7GnCq4Y/formResponse",
        data: {
            "entry.669398217": name,
            "entry.1446189088": company,
            "entry.352565362": email,
            "entry.1293215105": enquiry
        },
        type: "POST",
        dataType: "xml",
        statusCode: {
            0: function() {
                    $('#success').slideDown();
                    console.log('success');
                    },
                    200: function() {
                       console.log('failure');
                       $('#fail').slideDown();
                    }
                }
            });
}

$(document).ready(function(){
    $('#form').submit(function() {
        postToGoogle();
        return false;
    });
});
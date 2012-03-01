$(function() {
    $('#user_candidature_inprogress_dialog').dialog({ autoOpen: false });

    $('#user_candidature_inprogress_dialog_link').click(function() {
        $('#user_candidature_inprogress_dialog').dialog('open');
        return false;
    });
});
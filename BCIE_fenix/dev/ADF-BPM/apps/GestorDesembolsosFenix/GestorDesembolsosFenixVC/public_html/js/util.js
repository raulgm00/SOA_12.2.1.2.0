function onFieldEnterKey(inputEvent) {
    if (inputEvent.getKeyCode() == AdfKeyStroke.ENTER_KEY) {
        var inputTextField = inputEvent.getSource();
        var button = inputTextField.findComponent('b4');
        var partialSubmit = true;
        AdfActionEvent.queue(button,partialSubmit);
        event.cancel();
    }
}

function b64toBlob(dataURI) 
{
    var byteString = atob(dataURI.split(',')[1]);
    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);

    for (var i = 0; i < byteString.length; i++) 
    {
        ia[i] = byteString.charCodeAt(i);
    }
    window.open(URL.createObjectURL(new Blob([ab], { type: 'application/pdf' })));
}
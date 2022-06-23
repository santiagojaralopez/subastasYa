'use strict';

const boton_foto = document.querySelector('#btn-foto');
const imagen = document.querySelector('#user-photo');
const input_foto = document.querySelector('#user-photo');
let imagenUrl;


let widget_cloudinary = cloudinary.createUploadWidget({
    cloudName: 'subastasya',
    uploadPreset: 'presert_pabs'
}, (err, result) => {
    if (!err && result && result.event === 'success') {
        console.log('Imagen subida con exito', result.info);
        imagenUrl = result.info.secure_url;
        console.log("almacenado imagen: " + imagenUrl);
        miFuncion(result);
    }
});


function miFuncion(result) {
    return result.info.secure_url;
}


function boton() {
    widget_cloudinary.open();
}
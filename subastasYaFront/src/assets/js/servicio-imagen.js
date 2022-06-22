'use strict';

const boton_foto = document.querySelector('#btn-foto');
const imagen = document.querySelector('#user-photo');
const input_foto = document.querySelector('#user-photo');


let widget_cloudinary = cloudinary.createUploadWidget({
    cloudName: 'subastasya',
    uploadPreset: 'presert_pabs'
}, (err, result) => {
    if (!err && result && result.event === 'success') {
        console.log('Imagen subida con exito', result.info);
        imagen.src = result.info.secure_url;
        console.log("hola");
    }
});


function fotoVariable() {
    return imagen;
}


function boton() {
    console.log("si entre en el boton")
    widget_cloudinary.open();
}
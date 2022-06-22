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
    }
});


boton_foto.addEventListener('click', () => {
    widget_cloudinary.open();
}, false);


function myMethod() {
    console.log("hola");
}

function myfunction(params1, params2) {
    console.log('param1', params1);
    console.log('param2', params2);
}
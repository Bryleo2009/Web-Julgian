function modal(opc, tipo, id) {
    /*onclic = "confirmar(this)" */
    event.preventDefault();
    if (tipo == 'eliminar') {
        Swal.fire({
            title: "Desea eliminar este registro?",
            text: "Una vez eliminado, no podrá recuperarse!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#262F38",
            cancelButtonColor: "#01D28E",
            confirmButtonText: "Si",
            cancelButtonText: "No",
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "Eliminado!",
                    text: "Registro eliminado con exito.",
                    icon: "success",
                    showConfirmButton: false,
                    timer: 1500
                }).then((result) => {
                    location.href = opc.href;
                });
            }
        });
    }
    if (tipo == 'modificar') {
        Swal.fire({
            title: "Desea guardar cambios?",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#01D28E",
            cancelButtonColor: "#262F38",
            confirmButtonText: "Si",
            cancelButtonText: "No",
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "Datos actualizados!",
                    text: "Registro modificado con exito.",
                    icon: "success",
                    showConfirmButton: false,
                    timer: 1500
                }).then((result) => {
                    let form = document.getElementById(id);
                    form.submit();
                });
            }
        });
    }
    if (tipo == 'guardar') {
        Swal.fire({
            title: "Datos guardados!",
            text: "Registro guardado con exito.",
            icon: "success",
            showConfirmButton: false,
            timer: 1500
        }).then((result) => {
            let form = document.getElementById(id);
            form.submit();
        });
    }
    if (tipo == 'sesion') {
        Swal.fire({
            title: "Desea cerrar sesion?",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#262F38",
            cancelButtonColor: "#01D28E",
            confirmButtonText: "Si",
            cancelButtonText: "No",
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = opc.href;
            }
        });
    }
}
function veri(element1, element2, id) {
    event.preventDefault();
    if (element1.value != element2.value) {
        Swal.fire('Contraseñas distintas');
    } else {
        Swal.fire({
            title: "Desea guardar cambios?",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#01D28E",
            cancelButtonColor: "#262F38",
            confirmButtonText: "Si",
            cancelButtonText: "No",
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "Datos actualizados!",
                    text: "Si la contraseña actual es correcta, se ha modificado el registro con exito",
                    icon: "success",
                    confirmButtonColor: "#01D28E",
                }).then((result) => {
                    if (result.isConfirmed) {
                        let form = document.getElementById(id);
                        form.submit();
                    }
                });
            }
        });
    }
}
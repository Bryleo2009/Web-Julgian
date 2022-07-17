$(document).ready(function () {
  $("#dtBasicExample").DataTable({
    lengthMenu: [
      [5, 10, 15, -1],
      [5, 10, 15, "All"],
    ],
    language: {
      processing: "Tratamiento en curso...",
      search: "Buscar&nbsp;:",
      lengthMenu: "Agrupar de _MENU_ items",
      info: "_START_ al _END_ de _TOTAL_ items",
      infoEmpty: "No existen datos.",
      infoFiltered: "(filtrado de _MAX_ elementos en total)",
      infoPostFix: "",
      loadingRecords: "Cargando...",
      zeroRecords: "No se encontraron datos con tu busqueda",
      emptyTable: "No hay datos disponibles en la tabla.",

      paginate: {
        first: "Primero",
        previous: "Anterior",
        next: "Siguiente",
        last: "Ultimo",
      },
      aria: {
        sortAscending: ": active para ordenar la columna en orden ascendente",
        sortDescending: ": active para ordenar la columna en orden descendente",
      },
    },
  });
  $(".dataTables_length").addClass("bs-select");
  //$('#dtBasicExample_length').hide();
});

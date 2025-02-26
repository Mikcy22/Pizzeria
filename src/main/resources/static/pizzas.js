
async function cargarPizzas() {
    try {
        const response = await fetch('http://localhost:8080/api/pizzas'); // Llamada a la API

        const pizzas = await response.json(); // Convertir respuesta a JSON
        
        const tablaBody = document.getElementById('pizza-table');
        tablaBody.innerHTML = ''; // Limpiar la tabla antes de insertar los datos

        pizzas.forEach(pizza => {
            const fila = `<tr>
                <td>${pizza.nombre}</td>
                <td>${pizza.ingredientes}</td>
                <td>${pizza.precio.toFixed(2)}€</td>
                <td><image src="${pizza.imageUrl}" width="200"></td>
                <td>
                    <button onClick="eliminarPizza('${pizza._id}')">Eliminar</button>
                    <button onClick="editarPizza('${pizza._id}')">Editar</button>
                </td>
            </tr>`;
            tablaBody.innerHTML += fila;
        });

    } catch (error) {
        console.error('Error cargando las pizzas:', error);
    }
}

function eliminarPizza(id) {
    
    const confirmacion = window.confirm("¿Estás seguro de que quieres eliminar esta pizza?");
    
    if (!confirmacion) {
        return;
    }

    fetch(`/api/pizzas/deletePizza?id=${id}`, { 
        method: "POST", 
        headers: {
            "Content-Type": "application/json"
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error eliminando la pizza");
        }
        return response.text();
    })
    .then(data => {
        alert("Pizza eliminada correctamente");
        window.location.reload(); 
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Hubo un error al eliminar la pizza.");
    });
}

function editarPizza(id) {
    window.location.href = `/pizzas/editarPizza/${id}`; // Ajusta la ruta según tu backend
}


document.addEventListener('DOMContentLoaded', cargarPizzas);
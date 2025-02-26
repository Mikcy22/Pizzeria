let ingredientes = []; // Lista donde guardamos los ingredientes

function agregarIngrediente() {
    let input = document.getElementById("nuevoIngrediente");
    let ingrediente = input.value.trim();

    if (ingrediente !== "") {
        ingredientes.push(ingrediente); // Agregamos a la lista
        actualizarLista(); // Mostramos la lista en la UI
        input.value = ""; // Limpiamos el campo
    }
}

function actualizarLista() {
    let lista = document.getElementById("lista-ingredientes");
    lista.innerHTML = "";
    ingredientes.forEach( ingrediente => {
        let li = document.createElement("li");
        li.classList.add("ingrediente-item")
        li.textContent = ingrediente
        lista.appendChild(li);
    }
    )
}

document.getElementById("addPizzaForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const pizza = {
        nombre : document.getElementById("nombre").value,
        descripcion : document.getElementById("descripcion").value,
        ingredientes : ingredientes,
        precio : document.getElementById("precio").value,
        imageUrl : document.getElementById("imageUrl").value,
        disponible : document.getElementsByClassName("disponible").checked
    }

    console.log(pizza);

    fetch("/api/pizzas/addNewPizza", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(pizza)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        window.location.href = "/pizzas";
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Hubo un error al registrar la pizza.");
    });

})


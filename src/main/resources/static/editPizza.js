let ingredientes = [];

        function cargarIngredientes() {
            const ingredientesInput = document.getElementById("ingredientesInput").value;

            try {
                ingredientes = JSON.parse(ingredientesInput) || [];
            } catch (error) {
                ingredientes = ingredientesInput ? ingredientesInput.replace("[", "").replace("]", "").split(",").map(i => i.trim()) : [];
            }

            actualizarLista();
        }

        function agregarIngrediente() {
            let input = document.getElementById("nuevoIngrediente");
            let ingrediente = input.value.trim();

            if (ingrediente) {
                ingredientes.push(ingrediente);
                input.value = "";
                actualizarLista();
            }
        }

        function eliminarIngrediente(index) {
            ingredientes.splice(index, 1);
            actualizarLista();
        }

        function actualizarLista() {
            let lista = document.getElementById("lista-ingredientes");
            lista.innerHTML = "";

            ingredientes.forEach((ingrediente, index) => {
                let li = document.createElement("li");
                li.textContent = ingrediente;

                let btnEliminar = document.createElement("button");
                btnEliminar.textContent = "Eliminar";
                btnEliminar.style.marginLeft = "10px";
                btnEliminar.onclick = () => eliminarIngrediente(index);

                li.appendChild(btnEliminar);
                lista.appendChild(li);
            });

            document.getElementById("ingredientesInput").value = JSON.stringify(ingredientes);
        }

        document.getElementById("editPizzaForm").addEventListener("submit", function(event) {
            event.preventDefault();

            const pizza = {
                _id: document.getElementById("id").value,
                nombre: document.getElementById("nombre").value,
                descripcion: document.getElementById("descripcion").value,
                ingredientes: ingredientes,
                precio: document.getElementById("precio").value,
                imageUrl: document.getElementById("imageUrl").value,
                disponible: document.getElementById("disponible").checked
            };

            console.log("Enviando:", pizza);

            fetch("/api/pizzas/editPizza", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(pizza)
            })
            .then(response => response.text())
            .then(data => {
                alert(data);
                window.location.href = "/pizzas";
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Hubo un error al registrar la pizza.");
            });
        });

        // Ejecutar al inicio para cargar ingredientes existentes
        window.onload = cargarIngredientes;
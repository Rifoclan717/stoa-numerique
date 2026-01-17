const newNoteInputButton = document.getElementById("newNoteButton");
const newNoteInput = document.getElementById("newNoteInput");
const titleInput = document.getElementById("titleInput");
const notesDivContainer = document.getElementById("notesDivContainer");


newNoteInputButton.addEventListener("click", async (ev) => {

    noteValue = newNoteInput.value;
    titleValue = titleInput.value;

    if(noteValue.trim() === "" || titleValue.trim() === "") {
        window.alert("Vous ne pouvez pas créer de note vide !");
    } else {
        try {
            const response = await fetch("http://localhost:8080/add", {
                method : "POST",
                headers : { "Content-Type": "application/json"
                },
                body : JSON.stringify({
                        titre: titleValue,
                        contenu : noteValue
                })
            })

            if(!response.ok){
                throw new Error("Erreur serveur");
            }
                const data = await response.json();
                console.log("Succès :", data);
        } catch (error) {
            console.error(error);
        }
        
    }
})


async function getNotesData() {
    
    try {
            const response = await fetch("http://localhost:8080/all", {
                method : "GET",
                headers : { "Content-Type": "application/json"
                }
            })
            if(!response.ok){
                throw new Error("Erreur serveur");
            }
                const data = await response.json();
                console.log(typeof(data));
                console.log("Succès :", data);
                console.log(Array.isArray(data))
                return data;
        } catch (error) {
            console.error(error);
            return;
        }

}


function renderNotes(data) {


    data.forEach(element => {
        let container = document.createElement("div");
        
        let titleSpan = document.createElement("span");
        titleSpan.textContent = element["titre"];

        let titleInput = document.createElement("input")
        titleInput.type = "text"
        titleInput.classList.add("title-input")
        titleInput.id = "titleInput"
        titleInput.maxLength = 100

        let br1 = document.createElement("br")

        let textArea = document.createElement("textarea")
        textArea.classList.add("large-input")
        textArea.id = "newNoteInput"
        textArea.textContent = element["contenu"];

        let br2 = document.createElement("br")
        let button = document.createElement("button")
        button.id = "newNoteButton"
        button.textContent = "Créer Nouvelle note"


        container.appendChild(titleSpan);
        container.appendChild(titleInput);
        container.appendChild(br1);
        container.appendChild(textArea);
        container.appendChild(br2);
        container.appendChild(button);

        notesDivContainer.appendChild(container);
    });
}


async function main() {
    let notesData = await getNotesData();
    renderNotes(notesData);
}

main()